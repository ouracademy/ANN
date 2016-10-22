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
public class Main { 
    public static void main(String[] args) {
        
        System.out.println("Hello world");
        Neuron inputX1 = new Neuron("x1");
        Neuron inputX2 = new Neuron("x2");
        Neuron inputBias = new Neuron("bias");

        Layer inputLayer = new Layer(inputX1, inputX2, inputBias);

        Neuron node1 = new Neuron("n1");
        Neuron node2 = new Neuron("n2");
        Layer layer1 = new Layer(node1, node2);

        Neuron node3 = new Neuron("n3");
        Layer layer2 = new Layer(node3);

        Connection.create(inputX1, node1, 0.7);
        Connection.create(inputX2, node1, 0.9);
        Connection.create(inputBias, node1, 0.4);

        Connection.create(inputX1, node2, 0.6);
        Connection.create(inputX2, node2, 0.3);
        Connection.create(inputBias, node2, 0.8);

        Connection.create(node1, node3, 0.1);
        Connection.create(node2, node3, 0.5);
        Connection.create(inputBias, node3, 0.9);

        Connection.create(node3, new Neuron("end"), 0);

        NeuralNetwork net = new NeuralNetwork(inputLayer, layer1, layer2);

        net.train(
                new DataSet(1.0, 1.0).out(-1.0),
                new DataSet(1.0, -1.0).out(1.0),
                new DataSet(-1.0, 1.0).out(1.0),
                new DataSet(-1.0, -1.0).out(-1.0)
        );

        Result result = net.test(new DataSet(1.0, 1.0).out(-1.0));
        if (result.ok()) {
            System.out.println("OK!");
        } else {
            System.out.println("Expected:" + Arrays.toString(result.expected)
                    + "but get" + Arrays.toString(result.actual));
        }
    }
}
