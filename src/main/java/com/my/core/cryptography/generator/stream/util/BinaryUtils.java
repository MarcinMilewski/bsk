package com.my.core.cryptography.generator.stream.util;

import java.util.BitSet;

public class BinaryUtils {

    public static BitSet getMask(String maskString) {
        BitSet mask = new BitSet(maskString.length());
        for (int i = 0; i < maskString.length(); i++) {
            mask.set(i, Integer.parseInt(String.valueOf(maskString.charAt(i))) != 0);
        }
        return mask;
    }

    public static BitSet shiftRightNoCarry(BitSet bits) {
        BitSet shifted = new BitSet(bits.length());
        shifted.clear(0);

        int j = 1;
        for (int i = 0; i < bits.length(); i++, j++) {
            shifted.set(j, bits.get(i));
        }
        return shifted;
    }

    public static String toString(BitSet bits, int length) {
        String sbits = "";
        for (int i=length-1; i>=0; i--)
            sbits += (bits.get(i)?"1":"0");

        return sbits;
    }
}
