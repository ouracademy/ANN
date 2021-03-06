package com.our.neuralnetwork;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// TODO need a lot of more test
public class NeuralNetworkTest extends NeuralNetwork {

    NeuralNetwork net;

    @Before
    public void setUp() {
        Neuron inputX1 = new Neuron("x1");
        Neuron inputX2 = new Neuron("x2");
        Neuron inputBias = new Neuron("bias");

        Layer inputLayer = new Layer(inputX1, inputX2, inputBias);

        Neuron node1 = new Neuron("n1");
        Neuron node2 = new Neuron("n2");
        Neuron layer1Bias = new Neuron("bias");
        Layer layer1 = new Layer(node1, node2, layer1Bias);

        Neuron node3 = new Neuron("n3");
        Neuron layer2Bias = new Neuron("bias");
        Layer layer2 = new Layer(node3, layer2Bias);

        Connection.create(inputX1, node1, 0.7);
        Connection.create(inputX2, node1, 0.9);
        Connection.create(inputBias, node1, 0.4);

        Connection.create(inputX1, node2, 0.6);
        Connection.create(inputX2, node2, 0.3);
        Connection.create(inputBias, node2, 0.8);

        Connection.create(node1, node3, 0.1);
        Connection.create(node2, node3, 0.5);
        Connection.create(layer1Bias, node3, 0.9);

        net = new NeuralNetwork(inputLayer, layer1, layer2);
    }

    @Test
    public void testFeedForward() {
        System.out.println("feedForward");
        Double[] result = net.feedForward(new DataSet(1.0, 1.0).out(-1.0));
        Double[] expResult = {-0.64507};
        assertEquals(expResult[0], result[0], 0.00001);
    }
    
    
    @Test
    public void testStopCondition() {
        System.out.println("stopCondition");
        net.maxIterations = 2;
        net.iterations = 0;
        
        assertFalse(net.stopCondition());
        assertTrue(net.stopCondition());
    }

    @Test
    public void testFails() {
        System.out.println("fails");
        net.currentDataSet = new DataSet(1.0, 1.0).out(-1.0);
        Double[] y = {-0.64507};
        assertFalse(net.fails(y));
    }

    @Test
    public void testTest() {
        System.out.println("train");
        net.train(
                new DataSet(1.0, 1.0).out(-1.0),
                new DataSet(1.0, -1.0).out(1.0),
                new DataSet(-1.0, 1.0).out(1.0),
                new DataSet(-1.0, -1.0).out(-1.0)
        );

        System.out.println("iterations:" + this.net.iterations);

        System.out.println("test");
        Result result1 = net.test(new DataSet(1.0, 1.0).out(-1.0));
        Result result2 = net.test(new DataSet(1.0, -1.0).out(1.0));
        Result result3 = net.test(new DataSet(-1.0, 1.0).out(1.0));
        Result result4 = net.test(new DataSet(-1.0, -1.0).out(-1.0));
        
        assertTrue(result1.ok(0.26));
        assertTrue(result2.ok(0.26));
        assertTrue(result3.ok(0.26));
        assertTrue(result4.ok(0.26));
    }

}
