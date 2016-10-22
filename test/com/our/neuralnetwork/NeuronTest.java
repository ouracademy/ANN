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
public class NeuronTest {

    Neuron node1;
    Neuron node3;

    @Before
    public void setUp() {
        Neuron inputX1 = new Neuron("x1");
        Neuron inputX2 = new Neuron("x2");
        Neuron inputBias = new Neuron("bias");
        node3 = new Neuron("node3");

        node1 = new Neuron("node1");

        Connection.create(inputX1, node1, 0.7).value = 1;
        Connection.create(inputX2, node1, 0.9).value = 1;
        Connection.create(inputBias, node1, 0.4).value = -1;
        Connection.create(node1, node3, 0.1);
    }

    @Test
    public void testGetActivation() {
        System.out.println("getActivation");
        double result = this.node1.calculateActivation();
        double expResult = 0.83365;

        assertEquals(expResult, result, 0.00001);
        assertEquals(expResult, node3.inputs.get(0).value, 0.00001);
    }

//    @Test
//    public void testUpdateError() {
//        System.out.println("");
//        double result = this.node1.getActivation();
//        double expResult = 0.83365;
//
//        assertEquals(expResult, result, 0.00001);
//        assertEquals(expResult, node3.inputs.get(0).value, 0.00001);
//    }
}
