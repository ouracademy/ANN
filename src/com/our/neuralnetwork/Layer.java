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
public class Layer {
    
    Neuron[] neurons;
    
    public Layer(Neuron... args) {
        this.neurons = args;
    }
    
    public Double[] outputs() {
        return Arrays
                .stream(this.neurons)
                .map(neuron -> neuron.outputs.get(0).value)
                .toArray(Double[]::new);
    }
    
    public void addBias(double bias){
        for(Neuron neuron: this.neurons){
            int biasPosition = neuron.inputs.size() - 1;
            neuron.inputs.get(biasPosition).value = -1;
        }
    }
    
}