package com.my.core.cryptography.factory;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.enums.Algorithm;
import com.my.core.cryptography.matrix.number.decryptor.SimpleMatrixTranspositionDecryptor;
import com.my.core.cryptography.matrix.word.decryptor.WordKeyMatrixTranspositionDecryptor;
import com.my.core.cryptography.railfence.decryptor.RailFenceDecryptor;

public class DecryptorFactory {
    private DecryptorFactory() {
    }

    public static Decryptor getDecryptor(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceDecryptor();
            case NUMBER_KEY_MATRIX_TRANSPOSITION: return new SimpleMatrixTranspositionDecryptor();
            case WORD_KEY_MATRIX_TRANSPOSITION: return new WordKeyMatrixTranspositionDecryptor();
            default: throw new IllegalArgumentException();
        }
    }
}
