/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siga;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jao
 */
public class Stock {

    private double[] prices;

    public Stock(File namefile) {

        this.prices = new double[502];

        Scanner scan = null;

        try {

            scan = new Scanner(namefile);

        } catch (FileNotFoundException ex) {

            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        scan.useLocale(Locale.US);

        while (scan.hasNext()) {

            for (int i = 0; i < prices.length; i++) {

                this.prices[i] = scan.nextDouble();
                             
            }

        }
        
        for (int i = 0; i < prices.length / 2; i++) {
            
            double temp = prices[i];
            
            prices[i] = prices[prices.length - 1 - i];
            
            prices[prices.length - 1 - i] = temp;
        }
        
    }

    public double[] getPrices() {

        return prices;

    }


    public void setPrices(double[] prices) {

        this.prices = prices;

    }
    


}
