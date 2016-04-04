package com.my.core.cryptography.factory;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.enums.Algorithm;
import com.my.core.cryptography.matrix.decryptor.SimpleMatrixTranspositionDecryptor;
import com.my.core.cryptography.railfence.decryptor.RailFenceDecryptor;

public class DecryptorFactory {
    private DecryptorFactory() {
    }

    public static Decryptor getDecryptor(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceDecryptor();
            case SIMPLE_MATRIX_SHIFTING: return new SimpleMatrixTranspositionDecryptor();
            default: throw new IllegalArgumentException();
        }
    }
}
