/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.neuralnetwork.activation;

/**
 *
 * @author Arthur Mauricio Delgadillo
 */
public class HiperbolicTangent implements ActivationFunction {

    @Override
    public double execute(double x) {
        return (Math.exp(2 * x) - 1) / (Math.exp(2 * x) + 1);
    }

    @Override
    public double derived(double x) {
        return 1 - Math.pow(this.execute(x), 2);
    }

    @Override
    public double normalize(double amount) {
        return amount >= 0 ? 1 : -1;
    }
}
