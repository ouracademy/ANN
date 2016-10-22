package com.our.neuralnetwork.activation;

public class Sigmoid implements ActivationFunction{

    double e;
    double p;

    public Sigmoid() {
        e = Math.E;
        p = 1;
    }

    @Override
    public double execute(double x) {
        return (1 / (1 + Math.pow(e, -x )));
    }
    
    @Override
    public double normalize(double amount){
        return amount >= 0.5 ? 1 : 0;
    }

    @Override
    public double derived(double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
