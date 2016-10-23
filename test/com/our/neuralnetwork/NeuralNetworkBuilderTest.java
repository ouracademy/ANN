/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.neuralnetwork;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arthur Mauricio Delgadillo
 */
public class NeuralNetworkBuilderTest extends NeuralNetwork {

    public NeuralNetworkBuilder builder;
    private final Integer numberOfInputs = 2;
    private final Integer numberOfOutputs = 1;

    @Before
    public void setUp() {
        builder = new NeuralNetworkBuilder(numberOfInputs, numberOfOutputs);
    }

    @Test
    public void testBuild() {
        System.out.println("build");
        NeuralNetwork result = builder.build();

        assertLayerHas(result.layers[0], numberOfInputs);
        Integer lastLayerPosition = NeuralNetworkBuilder.DEFAULT_HIDDEN_LAYERS + 2 - 1;
        assertLayerHas(result.layers[lastLayerPosition], numberOfOutputs);
    }

    @Test
    public void testBuildHiddenLayers() {
        System.out.println("buildHiddenLayers");
        Layer[] result = builder
                .withHiddenLayers(5)
                .withNeuronsPerHiddenLayer(2)
                .buildHiddenLayers();

        System.out.println("withHiddenLayers");
        assertEquals(5, result.length);

        System.out.println("withNeuronsPerHiddenLayer");
        for (Layer hiddenLayer : result) {
            assertLayerHas(hiddenLayer, 2);
        }
    }

    private void assertLayerHas(Layer layer, int numberOfNeurons) {
        assertEquals(numberOfNeurons, layer.neurons.length - 1);
    }

}
