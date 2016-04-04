package com.my.ui.reader.factory;

import com.my.core.cryptography.enums.Algorithm;
import com.my.ui.reader.AlgorithmFieldsReader;
import com.my.ui.reader.matrix.SimpleMatrixTranspositionFieldsReader;
import com.my.ui.reader.railfence.RailFenceFieldsReader;

public class AlgorithmFieldsReaderFactory {
    private AlgorithmFieldsReaderFactory() {
    }

    public static AlgorithmFieldsReader getAlgorithmFieldsReader(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceFieldsReader();
            case SIMPLE_MATRIX_SHIFTING: return new SimpleMatrixTranspositionFieldsReader();
            default: throw new IllegalArgumentException();
        }
    }
}
