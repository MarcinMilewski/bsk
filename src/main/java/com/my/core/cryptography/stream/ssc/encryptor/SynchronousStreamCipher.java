package com.my.core.cryptography.stream.ssc.encryptor;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.generator.stream.lfsr.LfsrGenerator;
import com.my.core.cryptography.generator.stream.property.LfsrGeneratorProperty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.Properties;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.getMask;

public class SynchronousStreamCipher implements Encryptor{
    private LfsrGenerator lfsrGenerator;

    public SynchronousStreamCipher() {
        lfsrGenerator = new LfsrGenerator();
    }

    @Override
    public String encrypt(String data, Properties properties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public File encrypt(File data, Properties properties) throws IOException {
        String polynomialString = properties.getProperty(LfsrGeneratorProperty.POLYNOMIAL.name());
        String generatorStateString = properties.getProperty(LfsrGeneratorProperty.SEED.name());
        if (polynomialString == null || polynomialString.isEmpty()) throw new IllegalArgumentException();
        if (generatorStateString == null || generatorStateString.length() != polynomialString.length()) throw new IllegalArgumentException();
        BitSet polynomial = getMask(polynomialString);
        BitSet generatorState = getMask(generatorStateString);

        byte[] dataBytes = Files.readAllBytes(data.toPath());
        byte[] generatorBytes = lfsrGenerator.generate(properties, dataBytes.length).toByteArray();

        assert dataBytes.length == generatorBytes.length;

        byte[] output = xor(dataBytes, generatorBytes);
        return null;
    }

    private byte[] xor(byte[] dataBytes, byte[] generatorBytes) {
        byte[] result = new byte[dataBytes.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (((int) dataBytes[i]) ^ ((int) generatorBytes[i]));
        }
        return result;
    }

}
