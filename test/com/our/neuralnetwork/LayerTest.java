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
public class LayerTest {

    Layer testLayer;
    String name = "testLayer";
    int numberOfNeurons = 2;

    @Before
    public void setUp() {
        testLayer = this.buildLayer(name);
    }

    @Test
    public void testWithNeurons() {
        System.out.println("withNeurons");

        Layer result = Layer.withNeurons(name, numberOfNeurons);
        assertArrayEquals(testLayer.neurons, result.neurons);
        assertEquals(testLayer.name, name);
    }

    @Test
    public void testMakeNeurons() {
        System.out.println("makeNeurons");

        Neuron[] result = Layer.makeNeurons(name, numberOfNeurons);
        assertArrayEquals(testLayer.neurons, result);
    }

//    @Test
//    public void testOutputs() {
//        System.out.println("outputs");
//        Layer instance = null;
//        Double[] expResult = null;
//        Double[] result = instance.outputs();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    @Test
    public void testAddBias() { //TODO refactor this depends on testConnection & biasPosition
        System.out.println("addBias");
        Double weight = 0.5;
        Layer to = this.buildLayer("to");
        testLayer.connect(to, weight);

        double bias = -1.0;
        to.addBias(bias);
        
        for (int i = 0; i < to.biasPosition(); i++) {
            Neuron neuron = to.neurons[i];
            int biasPosition = neuron.inputs.size() - 1;
            assertEquals(neuron.inputs.get(biasPosition).value, bias, 0.0);
        }
    }

    @Test
    public void testBiasPosition() {
        System.out.println("biasPosition");
        Integer expResult = numberOfNeurons;
        Integer result = testLayer.biasPosition();
        assertEquals(expResult, result);
    }

    @Test
    public void testConnect() {
        System.out.println("connect");
        Double weight = 0.5;
        Layer to = this.buildLayer("to");

        testLayer.connect(to, weight);

        for (Neuron toNeuron : to.neurons) {
            for (int i = 0; i < toNeuron.inputs.size(); i++) {
                Connection c = toNeuron.inputs.get(i);
                assertEquals(testLayer.neurons[i], c.from);
                assertEquals(weight, c.weight, 0.0);
            }
        }
    }

    private Layer buildLayer(String layerName) {
        Neuron[] neurons = new Neuron[numberOfNeurons + 1];
        for (int i = 0; i < numberOfNeurons; i++) {
            neurons[i] = new Neuron(layerName + "n" + (i + 1));
        }
        neurons[numberOfNeurons] = new Neuron("bias");
        return new Layer(layerName, neurons);
    }
}
