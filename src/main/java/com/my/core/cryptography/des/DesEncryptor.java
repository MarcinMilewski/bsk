package com.my.core.cryptography.des;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.generator.stream.util.BinaryUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

/**
 * Created by marcin on 5/5/16.
 */
public class DesEncryptor implements Encryptor {
    private static Logger logger = Logger.getLogger(DesEncryptor.class);
    private byte[] dataBytes;
    private boolean[][] blocks;

    @Override
    public String encrypt(String data, Properties properties) {
        return null;
    }

    @Override
    public File encrypt(File data, Properties properties) throws IOException {
        boolean[] key = DesAlgorithm.get64BitKey(properties);
        boolean[][] subkeys = DesAlgorithm.create16Subkeys(key);
        dataBytes = Files.readAllBytes(data.toPath());
        int complementaryBytes = 8 - dataBytes.length % 8;
        blocks = DesUtils.createBlocks(dataBytes);

        boolean[][] permutatedBlocks = new boolean[blocks.length][];
        for (int i = 0; i < blocks.length; i++) {
            permutatedBlocks[i] = DesAlgorithm.initialPermutate(blocks[i]);
        }


        return null;
    }

}
