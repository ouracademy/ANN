/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.neuralnetwork;

/**
 *
 * @author Arthur Mauricio Delgadillo
 */
public class Connection {

    Neuron from;
    Neuron to;
    double weight;
    double value;

    public static Connection create(Neuron from, Neuron to, double weight) {
        Connection result = new Connection(from, to, weight);
        from.outputs.add(result);
        to.inputs.add(result);
        return result;
    }

    public Connection(Neuron from, Neuron to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.value = 0;
    }
    
    public double synapse(){
        return this.weight * this.value;
    }

    @Override
    public String toString() {
        return "Synapse{" + "weight=" + weight + '}';
    }

}
