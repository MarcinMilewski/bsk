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

    public static byte[] toByteArray(BitSet bits) {
        byte[] bytes = new byte[bits.length()/8+1];
        for (int i=0; i<bits.length(); i++) {
            if (bits.get(i)) {
                bytes[bytes.length-i/8-1] |= 1<<(i%8);
            }
        }
        return bytes;
    }

    public static boolean[] byteToBoolArr(byte x) {
        boolean[] boolArr = new boolean[8];
        boolArr[0] = ((x & 0x01) != 0);
        boolArr[1] = ((x & 0x02) != 0);
        boolArr[2] = ((x & 0x04) != 0);
        boolArr[3] = ((x & 0x08) != 0);

        boolArr[4] = ((x & 0x10) != 0);
        boolArr[5] = ((x & 0x20) != 0);
        boolArr[6] = ((x & 0x40) != 0);
        boolArr[7] = ((x & 0x80) != 0);
        return boolArr;
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
}
