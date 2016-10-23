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
    
    creo q mejor haces muy bien la API luego vas aca...
    si te sientes comoda con la API
    recien empiezas a implementarlo..bueno eso digo yap 
    
    public connect(Layer to, Double weight) {
        for(Neuron neuron: this.neurons) {
            for(int i=0; i<to.neurons.lenght; i++){
                 Connection.create(neuron, to.neurons[i], weight);
            }
        }
    }
    
}
