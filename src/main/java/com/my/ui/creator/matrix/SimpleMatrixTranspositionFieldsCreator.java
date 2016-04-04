package com.my.ui.creator.matrix;

import com.google.common.collect.Maps;
import com.my.core.cryptography.matrix.properties.SimpleMatrixTranspositionProperty;
import com.my.ui.creator.AlgorithmFieldsCreator;

import java.awt.*;
import java.util.Map;

public class SimpleMatrixTranspositionFieldsCreator implements AlgorithmFieldsCreator{
    @Override
    public Map<Label, TextField> getFields() {
        Map<Label, TextField> labelTextFieldMap = Maps.newHashMap();
        TextField depth = new TextField();
        depth.setName(SimpleMatrixTranspositionProperty.KEY.name());
        depth.setText("3,4,1,5,2");
        depth.setEditable(false);
        labelTextFieldMap.put(new Label("Key"), depth);
        return labelTextFieldMap;
    }
}
