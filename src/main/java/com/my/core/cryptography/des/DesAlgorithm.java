package com.my.core.cryptography.des;

import static com.my.core.cryptography.des.DesUtils.*;

public class DesAlgorithm {
    public  boolean[] initialPermutate(boolean[] block) {
        boolean[] result = new boolean[64];
        for (int i = 0; i < 64; ++i) {
            result[i] = block[initialPermutationLUT[i]];
        }
        return result;
    }

    public  boolean[] finalPermutate(boolean[] block) {
        boolean[] result = new boolean[64];
        for (int i = 0; i < 64; ++i) {
            result[i] = block[finalPermutationLUT[i]];
        }
        return result;
    }
}
