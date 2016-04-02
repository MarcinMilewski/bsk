package com.my;

import java.util.Properties;

public interface Encryptor {
    String encrypt(String data, Properties properties);
}
