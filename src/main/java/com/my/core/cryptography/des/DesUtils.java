package com.my.core.cryptography.des;

import com.my.core.cryptography.generator.stream.util.BinaryUtils;

public class DesUtils {
    /**
     * @return array of 8 byte blocks (complemented with 0 if needed)
     */
    public static boolean[][] createBlocks(byte[] data) {
        boolean[][] dataBits = BinaryUtils.toBooleanArray(data);
        int fullBlocksNumber = (int) Math.floor(dataBits.length / 8);
        boolean isNeedComplementation = data.length - (fullBlocksNumber * 8) > 0 ? true : false;

        boolean[][] blocks =  isNeedComplementation ? new boolean[fullBlocksNumber+1][64] : new boolean[fullBlocksNumber][64];

        int blockIndex;
        int byteIndex = 0;
        for (blockIndex = 0; blockIndex < fullBlocksNumber; ++blockIndex) {
            int bitIndex = 0;
            for (int i = 0 ; i < 8; i++, byteIndex++) {
                for (int j = 0; j < 8; ++j, bitIndex++) {
                    blocks[blockIndex][bitIndex] = dataBits[byteIndex][j];
                }
            }
        }

        if (isNeedComplementation) {
            int bitIndex = 0;
            for (int i = 0; i < 8; i++, byteIndex++) {
                for (int j = 0; j < 8; j++) {
                    blocks[blockIndex][bitIndex] = byteIndex < dataBits.length ? dataBits[byteIndex][j] : false;
                    bitIndex++;
                }
            }
        }
        return blocks;
    }
}
