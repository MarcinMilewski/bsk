package com.my.cryptography;

import java.util.Properties;

public interface Encryptor {
    String encrypt(String data, Properties properties);
}
