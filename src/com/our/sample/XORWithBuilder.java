/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.sample;

import com.our.datasources.GetData;
import com.our.neuralnetwork.DataSet;
import com.our.neuralnetwork.Layer;
import com.our.neuralnetwork.NeuralNetwork;
import com.our.neuralnetwork.Result;
import java.util.Arrays;

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
//        final Integer numberOfInputs = 2;
//        final Integer numberOfOutputs = 1;
//        NeuralNetworkBuilder builder = new NeuralNetworkBuilder(numberOfInputs, numberOfOutputs);
//        NeuralNetwork net = builder.withHiddenLayers(2).build();
//        
//        net.train(
//                new DataSet(1.0, 1.0).out(-1.0),
//                new DataSet(1.0, -1.0).out(1.0),
//                new DataSet(-1.0, 1.0).out(1.0),
//                new DataSet(-1.0, -1.0).out(-1.0)
//        );
//
//        System.out.println("iterations:" + net.iterations);
//        System.out.println("test");
//        Result result1 = net.test(new DataSet(1.0, 1.0).out(-1.0));
//        Result result2 = net.test(new DataSet(1.0, -1.0).out(1.0));
//        Result result3 = net.test(new DataSet(-1.0, 1.0).out(1.0));
//        Result result4 = net.test(new DataSet(-1.0, -1.0).out(-1.0));
//
//        System.out.println(result1);
//        System.out.println(result2);
//        System.out.println(result3);
//        System.out.println(result4);

        Layer input = Layer.withNeurons("input", 6);
        Layer hidden = Layer.withNeurons("hidden", 6);
        Layer output = Layer.withNeurons("output", 1);

        input.connect(hidden, 1.0);
        hidden.connect(output, 1.0);

        NeuralNetwork net = new NeuralNetwork(input, hidden, output);

        DataSet[] dataSets = GetData.fromFile("src/com/our/datasources/DN80.txt");
        net.maxIterations = 8000;
        net.train(dataSets);
        System.out.println("iterations:" + net.getIterations());

        DataSet[] testDataSets = GetData.fromFile("src/com/our/datasources/DN20.txt");
        for (DataSet testData : testDataSets) {
            Result result = net.test(testData);
            if (result.ok(0.001)) {
                System.out.println("OK!");
            } else {
                System.out.println("Fails expected:" + Arrays.toString(result.expected)
                        + "but get" + Arrays.toString(result.actual));
            }
        }

    }

}
