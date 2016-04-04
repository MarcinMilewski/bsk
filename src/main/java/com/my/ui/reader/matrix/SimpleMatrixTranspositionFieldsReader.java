package com.my.ui.reader.matrix;

import com.my.core.cryptography.matrix.properties.SimpleMatrixTranspositionProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class SimpleMatrixTranspositionFieldsReader implements AlgorithmFieldsReader {

    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            SimpleMatrixTranspositionProperty simpleMatrixTranspositionProperty = SimpleMatrixTranspositionProperty.valueOf(labelTextFieldEntry.getValue().getName());
            switch (simpleMatrixTranspositionProperty) {
                case KEY: properties.setProperty(SimpleMatrixTranspositionProperty.KEY.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;    }
}
