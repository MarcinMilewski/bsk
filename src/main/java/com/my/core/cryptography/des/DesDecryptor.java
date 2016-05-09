package com.my.core.cryptography.des;

import com.my.core.cryptography.Decryptor;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.toByteArray;

/**
 * Created by marcin on 5/5/16.
 */
public class DesDecryptor implements Decryptor {
    private static Logger logger = Logger.getLogger(DesDecryptor.class);

    @Override
    public String decrypt(String data, Properties properties) {
        return null;
    }

    @Override
    public File decrypt(File data, Properties properties) throws IOException {
        String outputFilePath = properties.getProperty(DesProperty.OUTPUT_FILE_PATH.name());
        boolean[] key = DesAlgorithm.get64BitKey(properties);
        boolean[][] subKeys = DesAlgorithm.create16Subkeys(key);
        byte[] dataBytes = Files.readAllBytes(data.toPath());
        int complementaryBytes = 8 - dataBytes.length % 8;
        boolean[][] blocks = DesUtils.createBlocks(dataBytes);

        boolean[][] decryptedBlocks = new boolean[blocks.length][];
        for (int i = 0; i < blocks.length; i++) {
            decryptedBlocks[i] = DesAlgorithm.decryptBlock(blocks[i], subKeys);
        }

        return createFile(outputFilePath, toByteArray(decryptedBlocks));
    }

    private File createFile(String outputFilePath, byte[] output) throws IOException {
        logger.debug("Creating output file, output size = " + output.length);
        File outputFile = new File(outputFilePath);
        FileOutputStream stream = new FileOutputStream(outputFile);
        stream.write(output);
        stream.close();
        return outputFile;
    }

}
