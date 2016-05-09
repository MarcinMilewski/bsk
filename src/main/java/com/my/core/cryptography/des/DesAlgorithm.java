package com.my.core.cryptography.des;

import com.my.core.cryptography.generator.stream.util.BinaryUtils;
import com.sun.deploy.util.ArrayUtil;

import java.util.Properties;
import java.util.stream.IntStream;

import static com.my.core.cryptography.des.DesUtils.*;

public class DesAlgorithm {
    public static boolean[] initialPermutate(boolean[] block) {
        boolean[] result = new boolean[64];
        for (int i = 0; i < 64; ++i) {
            result[i] = block[initialPermutationLUT[i] - 1];
        }
        return result;
    }

    public static boolean[] finalPermutate(boolean[] block) {
        boolean[] result = new boolean[64];
        for (int i = 0; i < 64; ++i) {
            result[i] = block[finalPermutationLUT[i] - 1];
        }
        return result;
    }


    public static boolean[] get64BitKey(Properties properties) {
        final String keyContent = properties.getProperty(String.valueOf(DesProperty.KEY));
        if (keyContent.isEmpty() && keyContent.length() != 64) throw new IllegalArgumentException();
        return BinaryUtils.toBooleanArray(keyContent.replace("/s", ""));
    }

    public static boolean[][] create16Subkeys(boolean[] key) {
        boolean[] leftKeySide = new boolean[28];
        boolean[] rightKeySide = new boolean[28];

        for (int i = 0; i < 28; i++) {
            leftKeySide[i] = key[i];
        }
        for (int i = 0, j = 28; i < 28; i++, j++) {
            rightKeySide[i] = key[j];
        }

        boolean[][] leftKeyShiftedSeries = createKeyShiftedSeries(leftKeySide);
        boolean[][] rightKeyShiftedSeries = createKeyShiftedSeries(rightKeySide);

        boolean[][] mergedKeyShiftedSeries = createMergedKeyShiftedSeries(leftKeyShiftedSeries, rightKeyShiftedSeries);
        boolean[][] keys = createKeys(mergedKeyShiftedSeries);

        return keys;
    }

    private static boolean[][] createMergedKeyShiftedSeries(boolean[][] leftKeyShiftedSeries, boolean[][] rightKeyShiftedSeries) {
        boolean[][] mergedShiftedSeries = new boolean[16][];
        for (int i = 1; i < 17; i++) {
            boolean[] series = new boolean[56];
            for (int j = 0; j < 28; j++) {
                series[j] = leftKeyShiftedSeries[i][j];
            }
            for (int j = 0, k = 28; j < 28; j++, k++) {
                series[k] = rightKeyShiftedSeries[i][j];
            }
            mergedShiftedSeries[i - 1] = series;
        }
        return mergedShiftedSeries;
    }

    private static boolean[][] createKeys(boolean[][] mergedShiftedSeries) {
        boolean[][] keys = new boolean[16][];
        for (int i = 0; i < 16; i++) {
            boolean[] key = new boolean[48];
            for (int j = 0; j < 48; j++) {
                key[j] = mergedShiftedSeries[i][pc2PermutationLUT[j] - 1];
            }
            keys[i] = key;
        }
        return keys;
    }

    private static boolean[][] createKeyShiftedSeries(boolean[] root) {
        boolean[][] shiftedSeries = new boolean[17][];
        shiftedSeries[0] = root;
        shiftedSeries[1] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[0], 1);
        shiftedSeries[2] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[1], 1);
        shiftedSeries[3] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[2], 2);
        shiftedSeries[4] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[3], 2);
        shiftedSeries[5] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[4], 2);
        shiftedSeries[6] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[5], 2);
        shiftedSeries[7] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[6], 2);
        shiftedSeries[8] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[7], 2);
        shiftedSeries[9] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[8], 1);
        shiftedSeries[10] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[9], 2);
        shiftedSeries[11] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[10], 2);
        shiftedSeries[12] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[11], 2);
        shiftedSeries[13] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[12], 2);
        shiftedSeries[14] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[13], 2);
        shiftedSeries[15] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[14], 2);
        shiftedSeries[16] = BinaryUtils.shiftLeftWithCarry(shiftedSeries[15], 1);
        return shiftedSeries;
    }

    public static boolean[] create64BitKey(boolean[] key) {
        boolean[] result = new boolean[56];
        for (int i = 0; i < 56; i++) {
            result[i] = key[DesUtils.pc1PermutationLUT[i] - 1];
        }
        return result;
    }

    public static boolean[] encryptBlock(boolean[] block, boolean[][] keys) {
        boolean[] leftSide = getLeftBlocksHalf(block);
        boolean[] rightSide = getRightBlocksHalf(block);
        boolean[] expandedRightSide = getExpanded(rightSide);
        boolean[] leftTmp = leftSide;
        boolean[] rightTmp = rightSide;

        // first iteration
        leftSide = rightSide;
        boolean[] sBoxValues = getSBoxValues(getEightSeries(rightSide));
        return null;
    }

    private static boolean[] getSBoxValues(boolean[][] eightSeries) {
        boolean[] result = new boolean[32];
        int j = 0;
        for (int i = 0; i < 8; i++) {
            int row = getFirstAndLastBitsDecValue(eightSeries[i]);
            int column = getMiddleBitsDecValue(eightSeries[i]);
            boolean[] sBoxValue = DesUtils.getSBoxBinValue(i, row, column);
            for (int k = 0; k < 4; k++,j++) {
                result[j] = sBoxValue[k];
            }
        }
        return result;
    }

    public static boolean[] getFunctionValue(boolean[] subKey, boolean[] expandedRightSide) {
        return BinaryUtils.xor(subKey, expandedRightSide);
    }

    public static boolean[] getExpanded(boolean[] rightSide) {
        boolean[] expanded = new boolean[48];
        for (int i = 0; i < 48; i++) {
            expanded[i] = rightSide[expansionFunctionLUT[i] - 1];
        }
        return expanded;
    }

    private static boolean[] getLeftBlocksHalf(boolean[] block) {
        boolean[] leftHalf = new boolean[32];
        for (int i = 0; i < 32; i++) {
            leftHalf[i] = block[i];
        }
        return leftHalf;
    }

    private static boolean[] getRightBlocksHalf(boolean[] block) {
        boolean[] rightHalf = new boolean[32];
        for (int i = 0, j = 32; i < 32; i++, j++) {
            rightHalf[i] = block[j];
        }
        return rightHalf;
    }

    private static boolean[][] getEightSeries(boolean[] block) {
        boolean[][] result = new boolean[8][6];
        int j = 0;
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 6; k++, j++) {
                result[i][k] = block[k];
            }
        }
        return result;
    }

    private static int getFirstAndLastBitsDecValue(boolean[] bits) {
        return BinaryUtils.toInt(new boolean[] {bits[0], bits[5]});
    }

    private static int getMiddleBitsDecValue(boolean[] bits) {
        return BinaryUtils.toInt(new boolean[]{bits[1], bits[2], bits[3], bits[4]});
    }
}
