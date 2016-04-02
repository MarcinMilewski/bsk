package com.my.cryptography;

import java.util.Properties;

public interface Decryptor {
    String decrypt(String data, Properties properties);
}
