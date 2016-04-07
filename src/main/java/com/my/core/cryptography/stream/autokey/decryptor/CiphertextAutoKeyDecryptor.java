package com.my.core.cryptography.stream.autokey.decryptor;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.stream.autokey.encryptor.CiphertextAutoKeyEncryptor;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CiphertextAutoKeyDecryptor implements Decryptor{
    @Override
    public String decrypt(String data, Properties properties) {
        return null;
    }

    @Override
    public File decrypt(File data, Properties properties) throws IOException {
        CiphertextAutoKeyEncryptor ciphertextAutoKeyEncryptor = new CiphertextAutoKeyEncryptor();
        return ciphertextAutoKeyEncryptor.encrypt(data, properties);
    }
}
