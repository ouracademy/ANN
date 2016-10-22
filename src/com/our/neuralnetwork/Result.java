/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.neuralnetwork;

import java.util.Arrays;

/**
 *
 * @author Arthur Mauricio Delgadillo
 */
public class Result {

    Double[] expected;
    Double[] actual;

    public Result(Double[] expected, Double[] actual) {
        this.expected = expected;
        this.actual = actual;
    }

    boolean ok() {
        return Arrays.equals(this.expected, this.actual);
    }

    @Override
    public String toString() {
        return "Result{" + "expected=" + Arrays.toString(this.expected)
                + ", actual=" + Arrays.toString(this.actual) + '}';
    }

}
