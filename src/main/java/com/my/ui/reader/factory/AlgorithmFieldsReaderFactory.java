package com.my.ui.reader.factory;

import com.my.core.cryptography.enums.Algorithm;
import com.my.ui.reader.AlgorithmFieldsReader;
import com.my.ui.reader.caesar.CaesarFieldsReader;
import com.my.ui.reader.matrix.number.NumberKeyMatrixTranspositionFieldsReader;
import com.my.ui.reader.matrix.word.WordKeyColumnVariantMatrixTranspositionFieldsReader;
import com.my.ui.reader.matrix.word.WordKeyMatrixTranspositionFieldsReader;
import com.my.ui.reader.railfence.RailFenceFieldsReader;

public class AlgorithmFieldsReaderFactory {
    private AlgorithmFieldsReaderFactory() {
    }

    public static AlgorithmFieldsReader getAlgorithmFieldsReader(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceFieldsReader();
            case NUMBER_KEY_MATRIX_TRANSPOSITION: return new NumberKeyMatrixTranspositionFieldsReader();
            case WORD_KEY_MATRIX_TRANSPOSITION: return new WordKeyMatrixTranspositionFieldsReader();
            case WORD_KEY_COLUMN_VARIANT_MATRIX_TRANSPOSITION: return new WordKeyColumnVariantMatrixTranspositionFieldsReader();
            case CAESAR_CIPHER: return new CaesarFieldsReader();
            default: throw new IllegalArgumentException();
        }
    }
}
