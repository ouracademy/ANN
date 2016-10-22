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
public interface ActivationFunction {
    public double execute(double x);
    public double derived(double x);
    double normalize(double amount);
}
