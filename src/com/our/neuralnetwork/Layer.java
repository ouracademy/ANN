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

    String name;
    Neuron[] neurons;

    public static Layer withNeurons(String name, int numberOfNeurons) {
        return new Layer(name, makeNeurons(name, numberOfNeurons));
    }

    private static Neuron[] makeNeurons(String name, int numberOfNeurons) {
        Neuron[] neurons = new Neuron[numberOfNeurons + 1];
        for (int i = 0; i < numberOfNeurons; i++) {
            neurons[i] = new Neuron(name + "n" + i);
        }
        neurons[numberOfNeurons] = new Neuron("bias");
        return neurons;
    }

    public Layer(String name, Neuron... args) {
        this.name = name;
        this.neurons = args;
    }

    @Deprecated
    public Layer(Neuron... args) {
        this.neurons = args;
    }

    public Double[] outputs() {
        return Arrays
                .stream(this.neurons)
                .map(neuron -> neuron.outputs.get(0).value)
                .toArray(Double[]::new);
    }

    public void addBias(double bias) {
        for (Neuron neuron : this.neurons) {
            int biasPosition = neuron.inputs.size() - 1;
            neuron.inputs.get(biasPosition).value = bias;
        }
    }

    public void connect(Layer to, Double weight) {
        for (Neuron from : this.neurons) {
            for (Neuron toNeuron : to.neurons) {
                Connection.create(from, toNeuron, weight);
            }
        }
    }

    @Override
    public String toString() {
        return "Layer{" + "name=" + name + ", neurons=" + Arrays.toString(neurons) + '}';
    }

}
