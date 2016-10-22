/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.our.neuralnetwork.activation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arthur Mauricio Delgadillo
 */
public class SigmoidTest {
    
    private ActivationFunction activationFunction;
    
    @Before
    public void setUp() {
        this.activationFunction = new Sigmoid();
    }

    @Test
    public void testExecute() {
        System.out.println("execute");
        double x = 1;
        
        double expResult = 0.73105;
        double result = this.activationFunction.execute(x);
        assertEquals(expResult, result, 0.00001);
    }

    @Test
    public void testNormalize() {
        System.out.println("normalize");
        double amount = 0.5;
        double expResult = 1.0;
        double result = this.activationFunction.normalize(amount);
        assertEquals(expResult, result, 0.0);
    }
    
}
