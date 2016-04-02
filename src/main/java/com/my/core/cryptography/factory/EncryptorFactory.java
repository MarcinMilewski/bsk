package com.my.core.cryptography.factory;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.enums.Algorithm;
import com.my.core.cryptography.railfence.encryptor.RailFenceEncryptor;

public class EncryptorFactory {
    private EncryptorFactory() {}

    public static Encryptor getEncryptor(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceEncryptor();
            default: throw new IllegalArgumentException();
        }
    }
}
