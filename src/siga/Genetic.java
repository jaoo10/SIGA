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
public class Genetic {

    private static final double mutRate = 0.015;
    private static final int tournament_size = 5;
    private static final boolean elitism = true;

    public Population evolvePop(Population pop, double sum, int day) {

        Population newPop = new Population(pop.indSize(), false);

        //keep the best individual in the population
        if (elitism) {

            newPop.saveInd(0, pop.getFittest());

        }

        int offset_elitism;
        if (elitism) {

            offset_elitism = 1;

        } else {

            offset_elitism = 0;

        }
        // crossover
        for (int i = offset_elitism; i < pop.indSize(); i++) {

            Individual ind1 = tournament_Sel(pop);
            Individual ind2 = tournament_Sel(pop);
            Individual offspring = crossover(pop, ind1, ind2, sum, day);
            newPop.saveInd(i, offspring);

        }

        return newPop;

    }

    // crossover between 2 best candidates
    public static Individual crossover(Population pop, Individual ind1, Individual ind2, double sum, int day) {

        Individual offspring = new Individual();

        double portion = Math.random();

        if (portion <= 0.3) {

            for (int i = 0; i < ind1.ind_len(); i++) {

                if ((i >= 0) && (i < 4)) {

                    offspring.setGenes(i, ind1.getGenes(i));

                } else {

                    offspring.setGenes(i, ind2.getGenes(i));

                }

            }

        } else if ((portion > 0.3) && (portion <= 0.6)) {

            for (int i = 0; i < ind1.ind_len(); i++) {

                if ((i >= 4) && (i < 8)) {

                    offspring.setGenes(i, ind1.getGenes(i));

                } else {

                    offspring.setGenes(i, ind2.getGenes(i));

                }

            }

        } else {

            for (int i = 0; i < ind1.ind_len(); i++) {

                if (i >= 8) {

                    offspring.setGenes(i, ind1.getGenes(i));

                } else {

                    offspring.setGenes(i, ind2.getGenes(i));

                }

            }

        }

        int count = 0;

        for (int i = 0; i < offspring.ind_len(); i++) {

            if (offspring.genes[i] == 1) {

                count++;

            }

        }

        if (count < 6) {

            for (int i = 0; i < offspring.ind_len(); i++) {

                if (count == 6) {

                    break;

                }

                if ((offspring.genes[i] == 0) && (ind1.genes[i] == 1)) {

                    offspring.genes[i] = 1;
                    count++;

                }

            }
            
        }
        
        if (count > 6) {

            for (int i = 0; i < offspring.ind_len(); i++) {

                if (count == 6) {

                    break;

                }

                if ((offspring.genes[i] == 1) && (ind1.genes[i] == 0)) {

                    offspring.genes[i] = 0;
                    count--;

                }

            }
            
        }

        buy_stocks(pop, offspring, sum, day);
        return offspring;

    }

    private static Individual tournament_Sel(Population pop) {

        Population t_sel = new Population(tournament_size, false);

        for (int i = 0; i < tournament_size; i++) {

            int id_rand = (int) (Math.random() * pop.indSize());

            t_sel.saveInd(i, pop.getInd(id_rand));

        }

        Individual fittest = t_sel.getFittest();

        return fittest;

    }

    public static void buy_stocks(Population pop, Individual ind, double sum, int day) {

        Stock[] stocks = new Stock[12];

        double[] prices;

        int[] amount;

        double price = 0;

        while (sum > 0) {

            for (int j = 0; j < 12; j++) {

                stocks = pop.getStocks();

                prices = stocks[j].getPrices();

                amount = ind.getAmount();

                price = prices[day];

                if ((ind.getGenes(j) == 1) && (sum > prices[day])) {

                    sum = sum - prices[day];

                    amount[j]++;

                    ind.setAmount(amount);

                } else if ((ind.getGenes(j) == 0) && sum > prices[day]) {

                } else {

                    break;
                }

            }

            if (sum < price) {

                break;

            }

        }

    }

    public double calcStockFitness(Stock st, Individual ind, int day, int stock_number, double sum) {

        double[] prices = st.getPrices();

        double initial_price = prices[0];

        int[] amount = ind.getAmount();

        double stock_amount = amount[stock_number];

        double stock_fitness = 0;

        double daily_price = prices[day];

        stock_fitness = (((initial_price * stock_amount) / sum) * (daily_price / initial_price));

        return stock_fitness;

    }

    void calcIndFitness(Population pop, Individual ind, int day, double sum) {

        double ind_fitness = 0;

        Stock[] stocks = pop.getStocks();

        for (int i = 0; i < 12; i++) {

            if (ind.genes[i] == 1) {

                ind_fitness += calcStockFitness(stocks[i], ind, day, i, sum);

            }

        }

        ind.setFitness(ind_fitness);

    }

}
