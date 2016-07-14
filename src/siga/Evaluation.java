/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siga;

/**
 *
 * @author jao
 */
public class Evaluation {

    public Evaluation(Genetic ga, Population pop, Individual fittest, double sum, int day, int day_end, Stock[] st, String period) {

        Individual new_fit = new Individual();

        new_fit.genes = fittest.genes;

        Stock[] stocks;
        
        double sum2 = sum;

        double[] prices;
        
        int[] amount;
        
        double price = 0;

        while (sum2 > 0) {

            for (int j = 0; j < 12; j++) {

                stocks = pop.getStocks();

                prices = stocks[j].getPrices();

                amount = new_fit.getAmount();

                price = prices[day];

                if ((new_fit.getGenes(j) == 1) && (sum2 > prices[day])) {

                    sum2 = sum2 - prices[day];

                    amount[j]++;

                    new_fit.setAmount(amount);
                    


                } else if ((new_fit.getGenes(j) == 0) && sum2 > prices[day]) {

                } else {

                    break;
                }

            }
            
            if(sum2 < price) {
                
                break;
                
            }
            
        }

        double final_sum_fittest = 0;
        
        for (int i = 0; i < 12; i++) {

            stocks = pop.getStocks();

            prices = stocks[i].getPrices();

            amount = new_fit.getAmount();

            if (new_fit.getGenes(i) == 1) {

                final_sum_fittest += prices[day_end] * amount[i];

            }

        }

        System.out.println("\nMontante do indivíduo mais apto no período " + period + " de investimento: " + final_sum_fittest);

        Individual new_best = new Individual();
        
        for (int i = 0; i < 12; i++) {

            new_best.genes[i] = 1;

        }

        double[] ind_fitness = new double[12];
        
        price = 0;
        
        sum2 = sum;

        while (sum2 > 0) {

            for (int j = 0; j < 12; j++) {

                stocks = pop.getStocks();

                prices = stocks[j].getPrices();

                amount = new_best.getAmount();

                price = prices[day];

                if ((new_best.getGenes(j) == 1) && (sum2 > prices[day])) {

                    sum2 = sum2 - prices[day];

                    amount[j]++;

                    new_best.setAmount(amount);
                    


                } else if ((new_best.getGenes(j) == 0) && sum2 > prices[day]) {

                } else {

                    break;
                }

            }
            
            if(sum2 < price) {
                
                break;
                
            }
            
        }

        for (int i = 0; i < 12; i++) {

            prices = st[i].getPrices();

            double initial_price = prices[252];

            amount = new_best.getAmount();

            double stock_amount = amount[i];

            sum2 = sum;

            double daily_price = prices[day];

            ind_fitness[i] = (((initial_price * stock_amount) / sum2) * (daily_price / initial_price));

        }

        int count = 0;

        int best;
        
        int res;
        
        Individual new_best2 = new Individual();

        while (count < 6) {
            
            best = 0;

            for (int i = 0; i < 12; i++) {
                
                res = Double.compare(ind_fitness[best], ind_fitness[i]);

                if ((res <= 0) && (ind_fitness[i] != 0) && (new_best.genes[best] != 0)) {
                    
                    best = i;
                    
                }

            }

            ind_fitness[best] = 0;
            
            new_best.genes[best] = 0;
            
            count++;

        }
        
        for(int i = 0; i < 12; i++) {
            
            if(new_best.genes[i] == 0) {
                
                new_best2.genes[i] = 1;
                
            } else {
                
                new_best2.genes[i] = 0;
            }
            
        }
        
        price = 0;
        
        sum2 = sum;
        
        
        
        while (sum2 > 0) {

            for (int j = 0; j < 12; j++) {

                stocks = pop.getStocks();

                prices = stocks[j].getPrices();

                amount = new_best2.getAmount();

                price = prices[day];

                if ((new_best2.getGenes(j) == 1) && (sum2 > prices[day])) {

                    sum2 = sum2 - prices[day];

                    amount[j]++;

                    new_best2.setAmount(amount);

                } else if (new_best2.getGenes(j) == 0) {

                } else {

                    break;
                }

            }

            if (sum2 < price) {

                break;

            }

        }
        
        double final_sum_real = 0;
        
        for (int i = 0; i < 12; i++) {

            stocks = pop.getStocks();

            prices = stocks[i].getPrices();

            amount = new_best2.getAmount();

            if (new_best2.getGenes(i) == 1) {

                final_sum_real += prices[day_end] * amount[i];

            }

        }
       
        System.out.println("\nMontante do indivíduo ideal no período " + period + " de investimento: " + final_sum_real);
        
        double profit = final_sum_fittest/final_sum_real;
        
        System.out.println("\nProporção entre a carteira prevista pelo AG, e a melhor carteira, no período " + period + ": " + profit);

    }

}
