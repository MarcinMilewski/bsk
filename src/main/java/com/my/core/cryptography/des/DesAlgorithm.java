package com.my.core.cryptography.des;

import com.my.core.cryptography.generator.stream.util.BinaryUtils;

import java.util.Properties;

import static com.my.core.cryptography.des.DesUtils.*;

public class DesAlgorithm {
    public static boolean[] initialPermutate(boolean[] block) {
        boolean[] result = new boolean[64];
        for (int i = 0; i < 64; ++i) {
            result[i] = block[initialPermutationLUT[i]];
        }
        return result;
    }

    public static boolean[] finalPermutate(boolean[] block) {
        boolean[] result = new boolean[64];
        for (int i = 0; i < 64; ++i) {
            result[i] = block[finalPermutationLUT[i]];
        }
        return result;
    }


    public static boolean[] get64BitKey(Properties properties) {
        final String keyContent = properties.getProperty(String.valueOf(DesProperty.KEY));
        if (keyContent.isEmpty() && keyContent.length() != 64) throw new IllegalArgumentException();
        return BinaryUtils.toBooleanArray(keyContent.replace("/s", ""));
    }

    public static boolean[] create16Subkeys(boolean[] key) {
        boolean[] leftKey26 = new boolean[26];
        for (int i = 0; i < 26; i++) {
            leftKey26[i] = key[DesUtils.pc1PermutationLeftLUT[i]];
        }
        return leftKey26;
    }

}
