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

    public static boolean[] toBooleanArray(String zerosOnesString) {
        boolean[] result = new boolean[zerosOnesString.length()];
        for (int i = 0; i < result.length; i++) {
            char c = zerosOnesString.charAt(i);
            if (c != '1' && c != '0') throw new IllegalArgumentException("Invalid character: " + c);

            if (c == '1') {
                result[i] = true;
            } else if (c == '0') {
                result[i] = false;
            }
            else throw new IllegalArgumentException("Illegal character");
        }
        return result;
    }

    public static byte[] toByteArray(boolean[] input) {
        if (input.length % 8 != 0) throw new IllegalArgumentException("input should divide by 8 ");
        byte[] toReturn = new byte[input.length / 8];
        int j = 0;
        for(int i = 0; i < input.length / 8; i++, j+=8) {
            int k = j;
            toReturn[i] = (byte)((input[k+7]?1<<7:0) + (input[k+6]?1<<6:0) + (input[k+5]?1<<5:0) +
                    (input[k+4]?1<<4:0) + (input[k+3]?1<<3:0) + (input[k+2]?1<<2:0) +
                    (input[k+1]?1<<1:0) + (input[k+0]?1:0));
        }
        return toReturn;
    }

    public static boolean[][] toBoolean2DArray(byte[] input) {
        boolean[][] result = new boolean[input.length][8];
        for (int i = 0; i < input.length; i++) {
            result[i][0] = ((input[i] & 0x01) != 0);
            result[i][1] = ((input[i] & 0x02) != 0);
            result[i][2] = ((input[i] & 0x04) != 0);
            result[i][3] = ((input[i] & 0x08) != 0);
            result[i][4] = ((input[i] & 0x10) != 0);
            result[i][5] = ((input[i] & 0x20) != 0);
            result[i][6] = ((input[i] & 0x40) != 0);
            result[i][7] = ((input[i] & 0x80) != 0);
        }
        return result;
    }


}
