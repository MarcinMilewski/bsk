package com.my.cryptography.railfence.encryptor;

import com.my.Encryptor;
import com.my.cryptography.railfence.properties.RailfenceProperty;

import java.util.Properties;

public class RailFenceEncryptor implements Encryptor {

    @Override
    public String encrypt(String data, Properties properties) {
        String depth = properties.getProperty(RailfenceProperty.DEPTH.name());
        if (depth == null || depth.isEmpty() || data.isEmpty()) throw new IllegalArgumentException();
        return railFenceEncrypt(Integer.valueOf(depth), data);
    }

    private String railFenceEncrypt(int depth, String data) {
        StringBuilder encrypted = new StringBuilder();
        int startIndex = 0;
        for (int stage = depth -1; stage >= 0; stage--, startIndex++) {
            encrypted.append(railFenceEncryptInternal(stage, depth, startIndex, data));
        }
        return encrypted.toString();
    }

    private String railFenceEncryptInternal(int stage, int depth, int startIndex, String data) {
        if (stage == 0) return railFenceEncryptInternal(depth-1, depth, depth-1, data);
        StringBuffer s = new StringBuffer();
        for (int i = startIndex; i < data.length(); i = i + 2*stage) {
            s.append(data.charAt(i));
        }
        return s.toString();
    }
}
