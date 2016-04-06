package com.my.ui.creator.factory;

import com.my.core.cryptography.enums.Algorithm;
import com.my.ui.creator.AlgorithmFieldsCreator;
import com.my.ui.creator.caesar.CaesarFieldsCreator;
import com.my.ui.creator.matrix.number.NumberKeyMatrixTranspositionFieldsCreator;
import com.my.ui.creator.matrix.word.WordKeyColumnVariantMatrixTranspositionFieldsCreator;
import com.my.ui.creator.matrix.word.WordKeyMatrixTranspositionFieldsCreator;
import com.my.ui.creator.railfence.RailFenceFieldsCreator;

public class AlgorithmFieldsCreatorFactory {
    private AlgorithmFieldsCreatorFactory() {
    }

    public static AlgorithmFieldsCreator getCreator(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceFieldsCreator();
            case NUMBER_KEY_MATRIX_TRANSPOSITION: return new NumberKeyMatrixTranspositionFieldsCreator();
            case WORD_KEY_MATRIX_TRANSPOSITION: return new WordKeyMatrixTranspositionFieldsCreator();
            case WORD_KEY_COLUMN_VARIANT_MATRIX_TRANSPOSITION: return new WordKeyColumnVariantMatrixTranspositionFieldsCreator();
            case CAESAR_CIPHER: return new CaesarFieldsCreator();
            default: throw new IllegalArgumentException();
        }
    }
}
