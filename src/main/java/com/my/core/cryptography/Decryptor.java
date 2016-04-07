package com.my.core.cryptography;

import java.io.File;
import java.util.Properties;

public interface Decryptor {
    String decrypt(String data, Properties properties);
    File decrypt(File data, Properties properties);
}
