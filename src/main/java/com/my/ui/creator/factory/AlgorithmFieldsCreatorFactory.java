package com.my.ui.creator.factory;

import com.my.cryptography.enums.Algorithm;
import com.my.ui.creator.AlgorithmFieldsCreator;
import com.my.ui.creator.RailFenceFieldsCreator;

public class AlgorithmFieldsCreatorFactory {
    private AlgorithmFieldsCreatorFactory() {
    }

    public static AlgorithmFieldsCreator getCreator(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceFieldsCreator();
            default: throw new IllegalArgumentException();
        }
    }
}
