package test.uab.tqs.bingo.model;

import main.uab.tqs.bingo.model.RandomNumberGenerator;

public class MockRandomNumberGenerator extends RandomNumberGenerator {
    int[] v = {1,2,3,4,5,6,7,8,9,0};

    public MockRandomNumberGenerator(int min, int max, int totalNumbers) {
        super(min, max, totalNumbers);
    }

    @Override
    public int[] generateNumbers() {
        return v;
    }

}
