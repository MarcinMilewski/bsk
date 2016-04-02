package com.my.cryptography.factory;

import com.my.Encryptor;
import com.my.cryptography.enums.Algorithm;
import com.my.cryptography.railfence.encryptor.RailFenceEncryptor;

public class EncryptorFactory {
    private EncryptorFactory() {}

    public static Encryptor getEncryptor(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceEncryptor();
            default: throw new IllegalArgumentException();
        }
    }
}
