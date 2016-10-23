package com.our.sample;

import com.our.neuralnetwork.Connection;
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
    static Double defaultWeight = 0;
    static Double numberOfInputs = 6;
    static Double numberOfOutputs = 1;
    static Double numberOfHiddenLayers = 5;
    static Double numberOfNeuronsInHiddenLayer = numberOfInputs;
    
    public static NeuralNetwork buildNeuralNetwork() {
        Layer interfaceLayer = new Layer(makeNeurons(numberOfInputs));
        Layer[] hiddenLayers = buildHiddenLayers();
        Layer outputLayer = new Layer(makeNeurons(numberOfOutputs));
        
        interfaceLayer.connect(hiddenLayer[0], defaultWeight); 
        for(int i=1; i< hiddenLayers.lenght - 1; i++){
            hiddenLayer[i].connect(hiddenLayer[i+1]);
        }
        hiddenLayer[hiddenLayer.lenght-1].connect(outputLayer);
        outputLayer.connect(null);
        
        return new NeuralNetwork(interfaceLayer, hiddenLayers, outputLayer);
    }
    
    static buildHiddenLayers(){
        Layer[] result = new Layers[numberOfHiddenLayers] 
        for(int i=0; i< hiddenLayers.lenght; i++){
            result[i]  = new Layer(makeNeurons(numberOfNeuronsInHiddenLayer));
        }
        return result;
    }
    
    
    private Neuron[] makeNeurons(int numbersOfNeurons){
        Neuron[] neurons = new Neuron[numbersOfNeurons];
        for(int i=0;i<numbersOfNeurons,i++){
             neurons[i] = new Neuron("n"+i);
        }
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
