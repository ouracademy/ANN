package com.our.sample;

import com.our.neuralnetwork.DataSet;
import com.our.neuralnetwork.NeuralNetwork;
import com.our.neuralnetwork.Result;
import com.our.datasources.GetData;
import com.our.neuralnetwork.NeuralNetworkBuilder;
import java.util.Arrays;

/**
 *
 * @author Arthur Mauricio Delgadillo
 * @author Diana Quintanilla Perez
 */
public class Pregunta1 {

    public static void main(String[] args) {
        NeuralNetwork net = new NeuralNetworkBuilder(6, 1).build();
        net.maxIterations = 10000;

        DataSet[] dataSets = GetData.fromFile("src/com/our/datasources/DN80.txt");
        net.train(dataSets);

        Result result = net.test(
                new DataSet(
                        0.0728253540121376,
                        0.00601202404809619,
                        0.0928428428428428,
                        1.0, 0.307692307692308,
                        0.727272727272727
                ).out(0.167832167832168)
        );

        if (result.ok(0.2)) {
            System.out.println("OK!");
        } else {
            System.out.println("Expected:" + Arrays.toString(result.expected)
                    + "but get" + Arrays.toString(result.actual));
        }
    }
}
