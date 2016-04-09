package com.my.core.cryptography.generator.stream.util;

import java.util.BitSet;

public class BinaryUtils {

    public static boolean[] getBooleanArray(String maskString) {
        boolean[] mask = new boolean[maskString.length()];
        for (int i = 0; i < maskString.length(); i++) {
            mask[i] = maskString.charAt(i) == '1' ? true:  false;
        }
        return mask;
    }

    public static boolean[] shiftRightNoCarry(boolean[] bits) {
        boolean[] shifted = new boolean[bits.length];
        shifted[0] = false;
        int j = 1;
        for (int i = 0; i < bits.length - 1; i++, j++) {
            shifted[j] =  bits[i];
        }
        return shifted;
    }

    public static String toString(boolean[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i] == true ? '1' : '0');
        }
        return sb.toString();
    }

    public static boolean xor(boolean x, boolean y) {
        return ( ( x || y ) && ! ( x && y ) );
    }

    private byte[] xor(byte[] dataBytes, byte[] generatorBytes) {
        byte[] result = new byte[dataBytes.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (((int) dataBytes[i]) ^ ((int) generatorBytes[i]));
        }
        return result;
    }

    public static boolean[] toBooleanArray(BitSet bitset, int length) {
        boolean[] result = new boolean[length];
        for (int i = 0; i < length; i++) {
            result[i] = bitset.get(i);
        }
        return result;
    }

    public static byte[] toBytes(boolean[] input) {
        byte[] toReturn = new byte[input.length / 8];
        for (int entry = 0; entry < toReturn.length; entry++) {
            for (int bit = 0; bit < 8; bit++) {
                if (input[entry * 8 + bit]) {
                    toReturn[entry] |= (128 >> bit);
                }
            }
        }

        return toReturn;
    }
}
