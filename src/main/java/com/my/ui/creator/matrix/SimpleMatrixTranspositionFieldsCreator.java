package com.my.ui.creator.matrix;

import com.my.ui.creator.AlgorithmFieldsCreator;
import com.my.ui.items.SimpleMatrixTranspositionPropertyItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleMatrixTranspositionFieldsCreator implements AlgorithmFieldsCreator{
    @Override
    public List<TextField> getTextFields() {
        List<TextField> textFields = new ArrayList<>();
        TextField depth = new TextField();
        depth.setName(SimpleMatrixTranspositionPropertyItem.KEY.name());
        depth.setText("3,4,1,5,2");
        depth.setEditable(false);
        textFields.add(depth);
        return textFields;
    }
}
