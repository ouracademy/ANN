/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.neuralnetwork;

/**
 *
 * @author Arthur Mauricio Delgadillo
 */
public class DataSet {
    Double[] inputs;
    Double[] out;
    
    public DataSet(Double ...args){
        this.inputs = args;
    }
    
    public DataSet out(Double ...args){
        this.out = args;
        return this;
    }
}
