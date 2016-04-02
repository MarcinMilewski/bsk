package com.my.core.cryptography;

import java.util.Properties;

public interface Encryptor {
    String encrypt(String data, Properties properties);
}
