package com.my.cryptography.factory;

import com.my.cryptography.Decryptor;
import com.my.cryptography.enums.Algorithm;
import com.my.cryptography.railfence.decryptor.RailFenceDecryptor;

public class DecryptorFactory {
    private DecryptorFactory() {
    }

    public static Decryptor getDecryptor(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceDecryptor();
            default: throw new IllegalArgumentException();
        }
    }
}
