/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.neuralnetwork;

import com.our.neuralnetwork.activation.ActivationFunction;
import com.our.neuralnetwork.activation.HiperbolicTangent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Arthur Mauricio Delgadillo
 */
public class Neuron {

    static ActivationFunction activationFunction = new HiperbolicTangent();

    public List<Connection> inputs = new ArrayList();
    public List<Connection> outputs = new ArrayList();
    public Double error = 0.0;
    public String name;
    public double activation;

    public Neuron(String name) {
        this.name = name;
        //asiganrlo aca 
    }

    public double calculateActivation() {
        this.activation = activationFunction.execute(sumSynapses());
        this.outputs.stream().forEach((outputConnection) -> {
            outputConnection.value = this.activation;
        });

        return activation;
    }

    private double sumSynapses() {
        double sum = 0;
        sum = this.inputs
                .stream()
                .map((input) -> input.synapse())
                .reduce(sum, (accumulator, _item) -> accumulator + _item);
        return sum;
    }

    public String[] inputNeurons() {
        return this.inputs.stream().map(n -> n.from)
                .map(from -> from.name).toArray(String[]::new);
    }

    public String[] outputNeurons() {
        return this.outputs.stream().map(n -> n.to)
                .map(to -> to.name).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return this.toJSON();
    }
    
    public String toJSON() {
        return "{\"name\":\"" + name + "\","
                + " \"inputs\":" + this.inputs + ","
                + " \"outputs\":" + this.outputs + '}';
    }

    void updateError() {
        for (Connection outputConnections : this.outputs) {
            this.error += outputConnections.to.error * outputConnections.weight;
        }

    }

    void updateWeight(double learnFactor) {
        for (Connection outputConnections : this.outputs) {
            Neuron outNeuron = outputConnections.to;
            outputConnections.weight += learnFactor
                    * outNeuron.error
                    * activationFunction.derived(outNeuron.activation)
                    * outputConnections.value;

            //System.out.println(name + "" + outputConnections.weight);
        }
    }

    public boolean hasError() {
        return this.error != 0;
    }
}
