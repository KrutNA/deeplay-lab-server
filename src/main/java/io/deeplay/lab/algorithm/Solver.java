package io.deeplay.lab.algorithm;

import io.deeplay.lab.data.SolverInput;
import io.deeplay.lab.data.SolverResult;

public interface Solver {
    SolverResult solve(SolverInput input);
}
