package com.my.ui.creator.factory;

import com.my.core.cryptography.enums.Algorithm;
import com.my.ui.creator.AlgorithmFieldsCreator;
import com.my.ui.creator.matrix.SimpleMatrixTranspositionFieldsCreator;
import com.my.ui.creator.railfence.RailFenceFieldsCreator;

public class AlgorithmFieldsCreatorFactory {
    private AlgorithmFieldsCreatorFactory() {
    }

    public static AlgorithmFieldsCreator getCreator(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceFieldsCreator();
            case SIMPLE_MATRIX_SHIFTING: return new SimpleMatrixTranspositionFieldsCreator();
            default: throw new IllegalArgumentException();
        }
    }
}
