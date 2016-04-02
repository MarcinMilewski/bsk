package com.my.core.cryptography.railfence.encryptor;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.railfence.properties.RailfenceProperty;

import java.util.Properties;

public class RailFenceEncryptor implements Encryptor {

    @Override
    public String encrypt(String data, Properties properties) {
        String depth = properties.getProperty(RailfenceProperty.DEPTH.name());
        if (depth == null || depth.isEmpty() || data.isEmpty()) throw new IllegalArgumentException();
        return encryptInternal(Integer.valueOf(depth), data);
    }

    private String encryptInternal(int depth, String data) {
        if (depth <= 1) return data;
        StringBuilder encrypted = new StringBuilder();
        int startIndex = 0;
        for (int stage = depth - 1; stage >= 0; stage--, startIndex++) {
            encrypted.append(encryptStage(stage, depth, startIndex, data));
        }
        return encrypted.toString();
    }

    private String encryptStage(int stage, int depth, int startIndex, String data) {
        if (stage == 0) {
            return encryptStage(depth - 1, depth, depth - 1, data);
        } else {
            StringBuffer s = new StringBuffer();
            for (int i = startIndex; i < data.length(); i = i + 2 * stage) {
                s.append(data.charAt(i));
            }
            return s.toString();
        }
    }
}
