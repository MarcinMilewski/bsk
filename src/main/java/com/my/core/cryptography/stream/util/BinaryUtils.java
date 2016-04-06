package com.my.core.cryptography.stream.util;

import java.util.BitSet;

public class BinaryUtils {

    public static BitSet getMask(String maskString) {
        BitSet mask = new BitSet(maskString.length());
        for (int i = 0; i < maskString.length(); i++) {
            mask.set(i, Integer.parseInt(String.valueOf(maskString.charAt(i))) != 0);
        }
        return mask;
    }
}
