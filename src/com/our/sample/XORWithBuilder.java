/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.sample;

import com.our.neuralnetwork.DataSet;
import com.our.neuralnetwork.NeuralNetwork;
import com.our.neuralnetwork.NeuralNetworkBuilder;
import com.our.neuralnetwork.Result;

/**
 *
 * @author Arthur Mauricio Delgadillo
 */
public class XORWithBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("builder.train");
        final Integer numberOfInputs = 2;
        final Integer numberOfOutputs = 1;
        NeuralNetworkBuilder builder = new NeuralNetworkBuilder(numberOfInputs, numberOfOutputs);
        NeuralNetwork net = builder.withHiddenLayers(2).build();
        
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

    }

}
