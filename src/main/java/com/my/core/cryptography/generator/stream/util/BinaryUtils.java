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

    public static boolean[] shiftRightNoCarry(boolean[] bits) {
        boolean[] shifted = new boolean[bits.length];

        int j = 1;
        for (int i = 0; i < bits.length - 1; i++, j++) {
            shifted[j] =  bits[i];
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

    public static BitSet leftShiftBitSet(BitSet bitSet) {
        final long maskOfCarry = 0x8000000000000000L;
        long[] aLong = bitSet.toLongArray();

        boolean carry = false;
        for (int i = 0; i < aLong.length; ++i) {
            if (carry) {
                carry = ((aLong[i] & maskOfCarry) != 0);
                aLong[i] <<= 1;
                ++aLong[i];
            } else {
                carry = ((aLong[i] & maskOfCarry) != 0);
                aLong[i] <<= 1;
            }
        }

        if (carry) {
            long[] tmp = new long[aLong.length + 1];
            System.arraycopy(aLong, 0, tmp, 0, aLong.length);
            ++tmp[aLong.length];
            aLong = tmp;
        }

        return BitSet.valueOf(aLong);
    }

    /**
     * Shifts a BitSet n digits to the right. For example, 0b0110101 with n=2 becomes 0b000110101.
     *
     * @param bits
     * @param n the shift distance.
     * @return
     */
    public static BitSet shiftRight(BitSet bits, int n) {
        if (n < 0)
            throw new IllegalArgumentException("'n' must be >= 0");
        if (n >= 64)
            throw new IllegalArgumentException("'n' must be < 64");

        long[] words = bits.toLongArray();

        // Expand array if there will be carry bits
        if (words[words.length - 1] >>> n > 0) {
            long[] tmp = new long[words.length + 1];
            System.arraycopy(words, 0, tmp, 0, words.length);
            words = tmp;
        }

        // Do the shift
        for (int i = words.length - 1; i > 0; i--) {
            words[i] <<= n; // Shift current word
            words[i] |= words[i - 1] >>> (64 - n); // Do the carry
        }
        words[0] <<= n; // shift [0] separately, since no carry

        return BitSet.valueOf(words);
    }

    public static boolean[] toBooleanArray(BitSet bitset, int length) {
        boolean[] result = new boolean[length];
        for (int i = 0; i < length; i++) {
            result[i] = bitset.get(i);
        }
        return result;
    }
}
