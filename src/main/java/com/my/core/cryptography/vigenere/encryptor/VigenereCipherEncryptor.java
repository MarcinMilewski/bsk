package com.my.core.cryptography.vigenere.encryptor;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.vigenere.property.VigenereCipherProperty;

import java.util.Properties;

import static com.my.core.util.AlphabetBiMap.alphabets;
import static com.my.core.util.AlphabetBiMap.characterValueMap;

public class VigenereCipherEncryptor implements Encryptor {

    @Override
    public String encrypt(String data, Properties properties) {
        String keyword = properties.getProperty(VigenereCipherProperty.KEY_WORD.name());
        if (keyword.isEmpty()) throw new IllegalArgumentException("k cannot be empty");
        String key = generateStraightKey(keyword, data.length());
        return encryptInternal(data, key);
    }

    private String generateStraightKey(String keyword, int length) {
        StringBuilder sb = new StringBuilder();
        int keywordLength = keyword.length();
        for (int i = 0; i < length; i++) {
            sb.append(keyword.charAt(i % keywordLength));
        }
        return sb.toString();
    }

    private String encryptInternal(String data, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            encrypted.append(encryptChar(data.charAt(i), key.charAt(i)));
        }
        return encrypted.toString();
    }

    private char encryptChar(char data, char key) {
        int keyValue = characterValueMap.get(key);
        return alphabets.get(keyValue).get(characterValueMap.get(data));
    }

}
