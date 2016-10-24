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

    @Test
    public void testTrain() {
        System.out.println("builder.train");
        NeuralNetwork net = builder.build();

        net.train(
                new DataSet(1.0, 1.0).out(-1.0),
                new DataSet(1.0, -1.0).out(1.0),
                new DataSet(-1.0, 1.0).out(1.0),
                new DataSet(-1.0, -1.0).out(-1.0)
        );

        System.out.println("iterations:" + net.iterations);
        System.out.println("test");
        Result result1 = net.test(new DataSet(1.0, 1.0).out(-1.0));
        Result result2 = net.test(new DataSet(1.0, -1.0).out(1.0));
        Result result3 = net.test(new DataSet(-1.0, 1.0).out(1.0));
        Result result4 = net.test(new DataSet(-1.0, -1.0).out(-1.0));
        
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);

        assertTrue(result1.ok(0.26));
        assertTrue(result2.ok(0.26));
        assertTrue(result3.ok(0.26));
        assertTrue(result4.ok(0.26));

    }

}
