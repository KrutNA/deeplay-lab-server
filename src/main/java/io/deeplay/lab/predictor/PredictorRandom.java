package io.deeplay.lab.predictor;

import io.deeplay.lab.data.SolverInput;

import java.util.Random;

public class PredictorRandom implements LocationProfitPredictor<SolverInput.SolverLocation, Double> {
    final Random random = new Random();

    @Override
    public Double predictOn(SolverInput.SolverLocation location, SolverInput.SolverLocation _location) {
        return random.nextGaussian(0, 2);
    }
}

