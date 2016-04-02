package com.my.core.cryptography;

import java.util.Properties;

public interface Decryptor {
    String decrypt(String data, Properties properties);
}
