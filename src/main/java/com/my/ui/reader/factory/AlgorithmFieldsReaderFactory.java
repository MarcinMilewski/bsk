package com.my.ui.reader.factory;

import com.my.cryptography.enums.Algorithm;
import com.my.ui.reader.AlgorithmFieldsReader;
import com.my.ui.reader.RailFenceFieldsReader;

public class AlgorithmFieldsReaderFactory {
    private AlgorithmFieldsReaderFactory() {
    }

    public static AlgorithmFieldsReader getAlgorithmFieldsReader(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceFieldsReader();
            default: throw new IllegalArgumentException();
        }
    }
}
