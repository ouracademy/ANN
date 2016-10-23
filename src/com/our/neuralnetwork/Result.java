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

    public Double[] expected;
    public Double[] actual;

    public Result(Double[] expected, Double[] actual) {
        if (expected.length != actual.length) {
            throw new IllegalArgumentException("Different expected and actual length");
        }
        this.expected = expected;
        this.actual = actual;
    }

    public boolean ok(Double delta) {
        for (int i = 0; i < this.expected.length; i++) {
            System.out.println("ex: " + this.expected[i] + " ac:" + this.actual[i]);
            if(!(Math.abs(this.expected[i] - this.actual[i]) <= delta))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Result{" + "expected=" + Arrays.toString(this.expected)
                + ", actual=" + Arrays.toString(this.actual) + '}';
    }

}
