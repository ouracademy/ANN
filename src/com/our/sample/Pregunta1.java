package com.our.sample;

import com.our.neuralnetwork.DataSet;
import com.our.neuralnetwork.Layer;
import com.our.neuralnetwork.NeuralNetwork;
import com.our.neuralnetwork.Result;
import com.our.datasources.GetData;
import java.util.Arrays;

/**
 *
 * @author Arthur Mauricio Delgadillo
 * @author Diana Quintanilla Perez
 */
public class Pregunta1 {

    static Double defaultWeight = 1.0;
    static Integer numberOfInputs = 6;
    static Integer numberOfOutputs = 1;
    static Integer numberOfHiddenLayers = 10;
    static Integer numberOfNeuronsInHiddenLayer = numberOfInputs;

    public static NeuralNetwork buildNeuralNetwork() {
        Layer interfaceLayer = Layer.withNeurons("input", numberOfInputs);
        Layer[] hiddenLayers = buildHiddenLayers();
        Layer outputLayer = Layer.withNeurons("output", numberOfOutputs);

        interfaceLayer.connect(hiddenLayers[0], defaultWeight);
        for (int i = 0; i < hiddenLayers.length - 1; i++) {
            hiddenLayers[i].connect(hiddenLayers[i + 1], defaultWeight);
        }
        hiddenLayers[hiddenLayers.length - 1].connect(outputLayer, defaultWeight);

        System.out.println(interfaceLayer);
        for (Layer hiddenLayer : hiddenLayers) {
            System.out.println(hiddenLayer);
        }
        System.out.println(outputLayer);

        return new NeuralNetwork(interfaceLayer, hiddenLayers, outputLayer);
    }

    private static Layer[] buildHiddenLayers() {
        Layer[] result = new Layer[numberOfHiddenLayers];
        for (int i = 0; i < numberOfHiddenLayers; i++) {
            result[i] = Layer.withNeurons("hidden(" + (i + 1) + ")", numberOfNeuronsInHiddenLayer);
        }
        return result;
    }

    public static void main(String[] args) {
        NeuralNetwork net = buildNeuralNetwork();
        DataSet[] dataSets = GetData.fromFile("src/com/our/datasources/DN80.txt");
        NeuralNetwork.MAX_ITERATIONS = 10000;
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
