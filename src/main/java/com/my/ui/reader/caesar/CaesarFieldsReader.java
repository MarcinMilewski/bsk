package com.my.ui.reader.caesar;

import com.my.core.cryptography.caesar.property.CaesarProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class CaesarFieldsReader implements AlgorithmFieldsReader {
    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            CaesarProperty caesarProperty = CaesarProperty.valueOf(labelTextFieldEntry.getValue().getName());
            switch (caesarProperty) {
                case K0: properties.setProperty(CaesarProperty.K0.name(), labelTextFieldEntry.getValue().getText()); break;
                case K1: properties.setProperty(CaesarProperty.K1.name(), labelTextFieldEntry.getValue().getText()); break;
                case N: properties.setProperty(CaesarProperty.N.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;
    }
}
