package com.my.core.cryptography;

import java.util.BitSet;
import java.util.Properties;

public interface Generator {
    BitSet generate(Properties properties, int number);
}
