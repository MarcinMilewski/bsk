package com.my.core.cryptography.factory;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.caesar.encryptor.CaesarCipherEncryptor;
import com.my.core.cryptography.enums.Algorithm;
import com.my.core.cryptography.matrix.number.encryptor.NumberKeyMatrixTranspositionEncryptor;
import com.my.core.cryptography.matrix.word.encryptor.WordKeyColumnVariantMatrixTranspositionEncryptor;
import com.my.core.cryptography.matrix.word.encryptor.WordKeyMatrixTranspositionEncryptor;
import com.my.core.cryptography.railfence.encryptor.RailFenceEncryptor;

public class EncryptorFactory {
    private EncryptorFactory() {}

    public static Encryptor getEncryptor(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceEncryptor();
            case NUMBER_KEY_MATRIX_TRANSPOSITION: return new NumberKeyMatrixTranspositionEncryptor();
            case WORD_KEY_MATRIX_TRANSPOSITION: return new WordKeyMatrixTranspositionEncryptor();
            case WORD_KEY_COLUMN_VARIANT_MATRIX_TRANSPOSITION: return new WordKeyColumnVariantMatrixTranspositionEncryptor();
            case CAESAR_CIPHER: return new CaesarCipherEncryptor();
            default: throw new IllegalArgumentException();
        }
    }
}
