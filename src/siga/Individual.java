/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siga;

import java.util.Random;

/**
 *
 * @author jao
 */
public class Individual {

    private double fitness;

    int[] genes;

    private int[] amount;

    public Individual() {

        this.fitness = 0;

        this.genes = new int[12];

        this.amount = new int[12];

        Random generator = new Random();

        boolean ran = generator.nextBoolean();

        int num = (ran) ? 1 : 0;
        
        for(int i = 0; i < genes.length; i++) {
            
            genes[i] = 0;
            
        }

        for(int i = 0; i < 6; i++) {
            
            genes[i] = 1;
            
        }
        
        array_shuffle(genes);

        for (int i = 0; i < 12; i++) {

            amount[i] = 0;

        }

    }

    private void array_shuffle(int[] array) {

        int index, temp;

        Random random = new Random();

        for (int i = array.length - 1; i > 0; i--) {

            index = random.nextInt(i + 1);

            temp = array[index];

            array[index] = array[i];

            array[i] = temp;

        }

    }

    public int getGenes(int index) {

        return genes[index];

    }

    public void setGenes(int gene, int value) {

        genes[gene] = value;

    }

    public double getFitness() {

        return fitness;

    }

    public void setFitness(double fitness) {

        this.fitness = fitness;

    }

    public int ind_len() {

        return genes.length;

    }

    public int[] getAmount() {

        return amount;

    }

    public void setAmount(int[] amount) {

        this.amount = amount;

    }

}
