package com.my.core.cryptography.stream.autokey.decryptor;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.generator.stream.lfsr.StatefulLfsrGenerator;
import com.my.core.cryptography.stream.ssc.property.SynchronousStreamProperty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.Properties;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.getMask;
import static com.my.core.cryptography.generator.stream.util.BinaryUtils.toBooleanArray;
import static com.my.core.cryptography.generator.stream.util.BinaryUtils.xor;

public class CiphertextAutoKeyDecryptor implements Decryptor {
    private StatefulLfsrGenerator statefulLfsrGenerator;


    @Override
    public String decrypt(String data, Properties properties) {
        return null;
    }

    @Override
    public File decrypt(File data, Properties properties) throws IOException {
        String polynomialString = properties.getProperty(SynchronousStreamProperty.POLYNOMIAL.name());
        String generatorStateString = properties.getProperty(SynchronousStreamProperty.SEED.name());
        String outputFilePath = properties.getProperty(SynchronousStreamProperty.OUTPUT_FILE_PATH.name());

        if (polynomialString == null || polynomialString.isEmpty()) throw new IllegalArgumentException();
        if (generatorStateString == null || generatorStateString.length() != polynomialString.length())
            throw new IllegalArgumentException();
        if (outputFilePath == null || outputFilePath.isEmpty())
            throw new IllegalArgumentException("Output file path is null");

        BitSet polynomial = getMask(polynomialString);
        BitSet seed = getMask(generatorStateString);

        statefulLfsrGenerator = new StatefulLfsrGenerator(toBooleanArray(polynomial, polynomialString.length()),
                toBooleanArray(seed, generatorStateString.length()));

        byte[] dataBytes = Files.readAllBytes(data.toPath());

        BitSet dataBitSet = BitSet.valueOf(dataBytes);
        BitSet outputBitSet = new BitSet(dataBytes.length * 8);

        for (int i = 0; i < dataBytes.length * 8; i++) {
            boolean generatedBit = statefulLfsrGenerator.generateNext();
            boolean xoredBit = xor(dataBitSet.get(i), generatedBit);
            statefulLfsrGenerator.shiftRightNoCarry();
            statefulLfsrGenerator.setFirstStateBit(dataBitSet.get(i));
            outputBitSet.set(i, xoredBit);
        }
        return createFile(outputFilePath, outputBitSet.toByteArray());
    }

    private File createFile(String outputFilePath, byte[] output) throws IOException {
        File outputFile = new File(outputFilePath);
        FileOutputStream stream = new FileOutputStream(outputFile);
        stream.write(output);
        stream.close();
        return outputFile;
    }
}
