/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siga;

import java.io.File;

/**
 *
 * @author jao
 */
public class Population {
    
    Individual[] inds;
    
    private Stock[] stocks;
    
    public Population(int popSize, boolean init) {
        
        inds = new Individual[popSize];
        
        this.stocks = new Stock[12];
        
        if (init) {
            
            for (int i = 0; i < indSize(); i++) {
                
                Individual newInd = new Individual();
            
                saveInd(i, newInd);
                
            }
            
        }
        
        this.stocks = new Stock[12];
        
        File st_file = new File("stocks/ko.txt");
        
        Stock ko = new Stock(st_file);
        
        stocks[0] = ko;
        
        st_file = new File("stocks/t.txt");
        
        Stock t = new Stock(st_file);
        
        stocks[1] = t;
        
        st_file = new File("stocks/pep.txt");
        
        Stock pep = new Stock(st_file);
        
        stocks[2] = pep;
        
        st_file = new File("stocks/vz.txt");
        
        Stock vz = new Stock(st_file);
        
        stocks[3] = vz;
        
        st_file = new File("stocks/msft.txt");
        
        Stock msft = new Stock(st_file);
        
        stocks[4] = msft;
        
        st_file = new File("stocks/aapl.txt");
        
        Stock aapl = new Stock(st_file);
        
        stocks[5] = aapl;
        
        st_file = new File("stocks/nflx.txt");
        
        Stock nflx = new Stock(st_file);
        
        stocks[6] = nflx;
        
        st_file = new File("stocks/amd.txt");
        
        Stock amd = new Stock(st_file);
        
        stocks[7] = amd;
        
        st_file = new File("stocks/drwi.txt");
        
        Stock drwi = new Stock(st_file);
        
        stocks[8] = drwi;
        
        st_file = new File("stocks/vtl.txt");
        
        Stock vtl = new Stock(st_file);
        
        stocks[9] = vtl;
        
        st_file = new File("stocks/tlog.txt");
        
        Stock tlog = new Stock(st_file);
        
        stocks[10] = tlog;
        
        st_file = new File("stocks/aqxp.txt");
        
        Stock aqxp = new Stock(st_file);
        
        stocks[11] = aqxp;
        
    }
    
    public Individual getInd(int index) {
        
        return inds[index];
        
    }
    
    public Individual getFittest() {
        
        int res;
        
        Individual fittest = inds[0];
        
        for (int i = 0; i < indSize(); i++) {
            
            res = Double.compare(fittest.getFitness(), getInd(i).getFitness());
            
            if (res <= 0) {
                 
                fittest = getInd(i);
                
            }
        }
        
        return fittest;
        
    }
    
    public int indSize() {
        
        return inds.length;
        
    }
    
    public void saveInd(int index, Individual ind) {
        
        inds[index] = ind;
        
    }
    
    public Stock[] getStocks() {

        return stocks;

    }
     
    public void setStocks(Stock[] stocks) {

        this.stocks = stocks;

    }


    
}
