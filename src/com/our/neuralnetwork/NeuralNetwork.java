/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.neuralnetwork;

import com.our.neuralnetwork.activation.ActivationFunction;
import com.our.neuralnetwork.activation.HiperbolicTangent;
import com.our.neuralnetwork.activation.Sigmoid;
import java.util.Arrays;

/**
 *
 * @author Arthur Mauricio Delgadillo
 */
public class NeuralNetwork {

    private Layer[] layers;
    static int MAX_ITERATIONS = 4000;
    int iterations;
    public Double bias = -1.0;
    public Double learnFactor = 0.5;
    protected DataSet currentDataSet;
    private ActivationFunction f = new HiperbolicTangent();

    public NeuralNetwork(Layer... args) {
        this.layers = args;
        for (int i = 1; i < this.layers.length; i++) {
            this.layers[i].addBias(bias);
        }
    }

    public NeuralNetwork(Layer input, Layer[] hidden, Layer output) {
        this.layers = new Layer[hidden.length + 2];
        this.layers[0] = input;

        for (int i = 1; i < this.layers.length - 1; i++) {
            this.layers[i] = hidden[i - 1];
            this.layers[i].addBias(bias);
        }
        this.layers[this.layers.length - 1] = output;
    }

    public void train(DataSet... dataSets) {
        this.iterations = 0;
        int i = 0;
        while (!this.stopCondition() && i < dataSets.length) {
            Double y[] = feedForward(dataSets[i]);
            if (fails(y)) {
                backpropagate();
                i = -1;
            }
            i++;
        }
    }

    private boolean stopCondition() {
        iterations++;
        return iterations > MAX_ITERATIONS;
    }

    protected Double[] feedForward(DataSet arg) {
        this.currentDataSet = arg;
        this.feedInput();
        for (int i = 1; i < this.layers.length; i++) { // deep layers
            Layer layer = this.layers[i];
            for (int j = 0; j < layer.biasPosition(); j++) {
                Neuron neuron = layer.neurons[j];
                neuron.calculateActivation();
            }
        }

        return this.lastLayer().outputs();
    }

    protected void feedInput() {
        for (Neuron neuron : this.inputLayer().neurons) {
            // Put values to everyone except to the bias
            for (int i = 0; i < neuron.inputs.size() - 1; i++) {
                neuron.inputs.get(i).value = currentDataSet.inputs[i];
            }
        }
    }

    private Layer inputLayer() {
        return this.layers[1];
    }

    private Layer lastLayer() {
        return this.layers[this.layers.length - 1];
    }

    public boolean fails(Double y[]) {
        this.lastLayerErrors(y);
        for (Neuron neuron : this.lastLayer().neurons) {
            if (neuron.hasError()) {
                return true;
            }
        }
        return false;
    }

    private void lastLayerErrors(Double y[]) {
        for (int i = 0; i < this.lastLayer().neurons.length - 1; i++) {
            Neuron neuron = this.lastLayer().neurons[i];
            neuron.error = this.currentDataSet.out[i] - f.normalize(y[i]);
        }
    }

    private void backpropagate() {
        this.calculateErrors();
        this.updateWeights();
    }

    private void calculateErrors() {
        for (int i = this.layers.length - 2; i > 0; i--) {
            for (Neuron neuron : this.layers[i].neurons) {
                neuron.updateError();
            }
        }
    }

    private void updateWeights() {
        for (Layer layer : this.layers) {
            for (Neuron neuron : layer.neurons) {
                neuron.updateWeight(this.learnFactor);
            }
        }
    }

    public Result test(DataSet dataSet) {

        return new Result(
                dataSet.out,
                Arrays.stream(this.feedForward(dataSet))
                .map(x -> this.f.normalize(x))
                .toArray(Double[]::new)
        );
    }

    public void setActivationFunction(String funcion) {
        switch (funcion) {
            case "Sigmoidea":
                this.f = new Sigmoid();
                break;
            case "Tangent":
                this.f = new HiperbolicTangent();
                break;
        }
    }

}
