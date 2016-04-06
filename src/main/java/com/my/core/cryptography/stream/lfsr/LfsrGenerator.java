package com.my.core.cryptography.stream.lfsr;

import com.my.core.cryptography.stream.property.LfsrGeneratorProperty;

import java.util.BitSet;
import java.util.Properties;

import static com.my.core.cryptography.stream.util.BinaryUtils.getMask;

public class LfsrGenerator {
    BitSet generate(Properties properties, int number) {
        String maskString = properties.getProperty(LfsrGeneratorProperty.MASK.name());
        if (maskString == null || maskString.isEmpty()) throw new IllegalArgumentException();

        BitSet mask = getMask(maskString);
        BitSet output = new BitSet(number);
        for (int i = 0; i < number; i += maskString.length()) {

        }
        return output;
    }

}
