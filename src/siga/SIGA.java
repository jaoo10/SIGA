/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siga;

import java.util.Arrays;

/**
 *
 * @author jao
 */
public class SIGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double sum = 100000;
       
        Population pop = new Population(100, true);

        Genetic ga = new Genetic();

        int day = 0;
        
        siga_alg_init(pop,sum,day,ga);

       
    }
    
    public static void siga_alg_init(Population pop, double sum, int day, Genetic ga) {
        
        for (int i = 0; i < pop.indSize(); i++) {
            
            ga.buy_stocks(pop, pop.inds[i], sum, day);
            ga.calcIndFitness(pop, pop.inds[i], 0, sum);
            
        }
                
        for (int i = 0; i < 252; i++) {

            for (int j = 0; j < pop.indSize(); j++) {

                ga.calcIndFitness(pop, pop.inds[j], day, sum);

            }

            pop = ga.evolvePop(pop, sum, day);

            day++;

        }

        Individual fittest;

        fittest = pop.getFittest();

        int[] genes;

        genes = fittest.genes;
        
        System.out.println("------------------------------------------------------------------------------------------------------------");

        System.out.println("\nIndivíduo mais apto: " + Arrays.toString(genes));

        siga_alg_eval(ga,pop,fittest,sum);
        
    }
    
    public static void siga_alg_eval(Genetic ga, Population pop, Individual fittest, double sum) {
        
        Evaluation ev_week = new Evaluation(ga,pop,fittest,sum,252,257,pop.getStocks(), "Semanal");
        Evaluation ev_month = new Evaluation(ga,pop,fittest,sum,252,273,pop.getStocks(), "Mensal");
        Evaluation ev_two_months = new Evaluation(ga,pop,fittest,sum,252,294,pop.getStocks(), "Bimestral");
        Evaluation ev_six_months = new Evaluation(ga,pop,fittest,sum,252,378,pop.getStocks(), "Semestral");
        Evaluation ev_year = new Evaluation(ga,pop,fittest,sum,252,501,pop.getStocks(), "Anual");
       
    }

}
