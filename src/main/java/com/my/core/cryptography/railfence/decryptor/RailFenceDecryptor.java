package com.my.core.cryptography.railfence.decryptor;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.railfence.properties.RailfenceProperty;

import java.io.File;
import java.util.Properties;

public class RailFenceDecryptor implements Decryptor {
    private int encryptedIndex;

    @Override
    public String decrypt(String data, Properties properties) {
        String depth = properties.getProperty(RailfenceProperty.DEPTH.name());
        if (depth == null || depth.isEmpty() || data.isEmpty()) throw new IllegalArgumentException();
        return decryptInternal(Integer.valueOf(depth), data);
    }

    @Override
    public File decrypt(File data, Properties properties) {
        return null;
    }

    private String decryptInternal(Integer depth, String data) {
        if (depth <= 1) return data;
        int startIndex = 0;
        encryptedIndex = 0;
        char[] result = new char[data.length()];
        for (int stage = depth - 1; stage >= 0; stage--, startIndex++) {
            decryptStage(stage, depth, startIndex, data, result);
        }
        return new String(result);
    }

    private void decryptStage(int stage, Integer depth, int startIndex, String data, char[] result) {
        if (stage == 0) {
            decryptStage(depth - 1, depth, depth - 1, data, result);
        } else {
            for (int i = startIndex; i < data.length(); i = i + 2 * stage, encryptedIndex++) {
                result[i] = data.charAt(encryptedIndex);
            }
        }
    }
}
