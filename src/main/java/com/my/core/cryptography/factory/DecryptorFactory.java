package com.my.core.cryptography.factory;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.enums.Algorithm;
import com.my.core.cryptography.matrix.number.decryptor.NumberKeyMatrixTranspositionDecryptor;
import com.my.core.cryptography.matrix.word.decryptor.WordKeyColumnVariantMatrixTranspositionDecryptor;
import com.my.core.cryptography.matrix.word.decryptor.WordKeyMatrixTranspositionDecryptor;
import com.my.core.cryptography.railfence.decryptor.RailFenceDecryptor;

public class DecryptorFactory {
    private DecryptorFactory() {
    }

    public static Decryptor getDecryptor(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceDecryptor();
            case NUMBER_KEY_MATRIX_TRANSPOSITION: return new NumberKeyMatrixTranspositionDecryptor();
            case WORD_KEY_MATRIX_TRANSPOSITION: return new WordKeyMatrixTranspositionDecryptor();
            case WORD_KEY_COLUMN_VARIANT_MATRIX_TRANSPOSITION: return new WordKeyColumnVariantMatrixTranspositionDecryptor();
            default: throw new IllegalArgumentException();
        }
    }
}
