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
public class NeuralNetworkBuilder {

    public static Double DEFAULT_WEIGHT = 1.0;
    public static Integer DEFAULT_HIDDEN_LAYERS = 10;

    private final Integer numberOfInputs;
    private final Integer numberOfOutputs;
    private Integer numberOfHiddenLayers = DEFAULT_HIDDEN_LAYERS;
    private Integer numberOfNeuronsInHiddenLayer;
    private Double weight = DEFAULT_WEIGHT;
    
    public NeuralNetworkBuilder(Integer numberOfInputs, Integer numberOfOutputs) {
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.numberOfNeuronsInHiddenLayer = numberOfInputs;
    }
    
    public NeuralNetworkBuilder withHiddenLayers(Integer hiddenLayers){
        this.numberOfHiddenLayers = hiddenLayers;
        return this;
    }
    
    public NeuralNetworkBuilder withNeuronsPerHiddenLayer(Integer neurons){
        this.numberOfNeuronsInHiddenLayer = neurons;
        return this;
    }

    public NeuralNetwork build() {
        Layer interfaceLayer = Layer.withNeurons("input", numberOfInputs);
        Layer[] hiddenLayers = buildHiddenLayers();
        Layer outputLayer = Layer.withNeurons("output", numberOfOutputs);

        interfaceLayer.connect(hiddenLayers[0], weight);
        for (int i = 0; i < hiddenLayers.length - 1; i++) {
            hiddenLayers[i].connect(hiddenLayers[i + 1], weight);
        }
        hiddenLayers[hiddenLayers.length - 1].connect(outputLayer, weight);

        return new NeuralNetwork(interfaceLayer, hiddenLayers, outputLayer);
    }

    protected Layer[] buildHiddenLayers() {
        Layer[] result = new Layer[numberOfHiddenLayers];
        for (int i = 0; i < numberOfHiddenLayers; i++) {
            result[i] = Layer.withNeurons("hidden(" + (i + 1) + ")", numberOfNeuronsInHiddenLayer);
        }
        return result;
    }
}
