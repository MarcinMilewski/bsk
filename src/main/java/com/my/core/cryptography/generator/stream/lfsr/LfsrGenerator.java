package com.my.core.cryptography.generator.stream.lfsr;

import com.google.common.collect.Lists;
import com.my.core.cryptography.Generator;
import com.my.core.cryptography.generator.stream.property.LfsrGeneratorProperty;
import com.my.core.cryptography.generator.stream.util.BinaryUtils;

import java.util.BitSet;
import java.util.List;
import java.util.Properties;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.getMask;
import static com.my.core.cryptography.generator.stream.util.BinaryUtils.shiftRightNoCarry;

public class LfsrGenerator implements Generator {
    private LfsrGeneratorBitComputer lfsrGeneratorBitComputer;

    public LfsrGenerator() {
        lfsrGeneratorBitComputer = new LfsrGeneratorBitComputer();
    }

    @Override
    public BitSet generate(Properties properties, int number) {
        String polynomialString = properties.getProperty(LfsrGeneratorProperty.POLYNOMIAL.name());
        String generatorStateString = properties.getProperty(LfsrGeneratorProperty.SEED.name());
        if (polynomialString == null || polynomialString.isEmpty()) throw new IllegalArgumentException();
        if (generatorStateString == null || generatorStateString.length() != polynomialString.length()) throw new IllegalArgumentException();
        BitSet polynomial = getMask(polynomialString);
        BitSet generatorState = getMask(generatorStateString);
        BitSet output = new BitSet(number);

        List<Integer> additionOrder = getAdditionOrder(polynomial);

        for (int i = 0; i < number; i++) {
            boolean computedBit = lfsrGeneratorBitComputer.compute(additionOrder, BinaryUtils.toBooleanArray(generatorState, generatorStateString.length()));
            generatorState = shiftRightNoCarry(generatorState);
            generatorState.set(0, computedBit);
            output.set(i, computedBit);
        }
        return output;
    }

    private List<Integer> getAdditionOrder(BitSet polynomial) {
        List<Integer> order = Lists.newArrayList();
        for (int i = polynomial.length() -1 ; i >=0 ; i--) {
            if (polynomial.get(i) == true) {
                order.add(i);
            }
        }
        return order;
    }

}
