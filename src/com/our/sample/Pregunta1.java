package com.our.sample;

import com.our.neuralnetwork.DataSet;
import com.our.neuralnetwork.Layer;
import com.our.neuralnetwork.NeuralNetwork;
import com.our.neuralnetwork.Neuron;
import com.our.neuralnetwork.Result;
import com.our.datasources.GetData;
import java.util.Arrays;
/**
 *
 * @author Arthur Mauricio Delgadillo
 * @author Diana Quintanilla Perez
 */
public class Pregunta1 { 
    static Double defaultWeight = 0.0;
    static Integer numberOfInputs = 6;  
    static Integer numberOfOutputs = 1;
    static Integer numberOfHiddenLayers = 5;
    static Integer numberOfNeuronsInHiddenLayer = numberOfInputs;
    
    public static NeuralNetwork buildNeuralNetwork() {
        Layer interfaceLayer = new Layer(makeNeurons(numberOfInputs));
        Layer[] hiddenLayers = buildHiddenLayers();
        Layer outputLayer = new Layer(makeNeurons(numberOfOutputs));
        
        interfaceLayer.connect(hiddenLayers[0], defaultWeight); 
        for(int i=1; i< hiddenLayers.length - 1; i++){
            hiddenLayers[i].connect(hiddenLayers[i+1], defaultWeight);
        }
        hiddenLayers[hiddenLayers.length-1].connect(outputLayer, defaultWeight);
        outputLayer.connect(new Layer(new Neuron("out")), defaultWeight);
        
        return new NeuralNetwork(interfaceLayer, hiddenLayers, outputLayer);
    }
    
    private static Layer[] buildHiddenLayers(){
        Layer[] result = new Layer[numberOfHiddenLayers];
        for(int i=0; i< result.length; i++){
            result[i]  = new Layer(makeNeurons(numberOfNeuronsInHiddenLayer));
        }
        return result;
    }
    
    
    private static Neuron[] makeNeurons(int numberOfNeurons){
        Neuron[] neurons = new Neuron[numberOfNeurons+1];
        for(int i=0;i<numberOfNeurons; i++){
             neurons[i] = new Neuron("n"+i);
        }
        neurons[numberOfNeurons] = new Neuron("bias");
        return neurons;
    }
    
    public static void main(String[] args) {
        NeuralNetwork net = buildNeuralNetwork();
        DataSet[] dataSets = GetData.fromFile("src/com/our/datasources/DN80.txt");
        net.train(dataSets);
        
        Result result = net.test(new DataSet(1.0, 1.0).out(-1.0));
        if (result.ok()) {
            System.out.println("OK!");
        } else {
            System.out.println("Expected:" + Arrays.toString(result.expected)
                    + "but get" + Arrays.toString(result.actual));
        }
    }
}
