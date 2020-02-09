package com.company;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Bot {
    List<String> str = new ArrayList<>(List.of("Alpha", "Beta", "Delta", "Epsilon", "Gamma", "Lambda", "Omega", "Sigma", "Theta", "Zeta"));

    public void generateStocks()
    {
        Stock[] stockArr = new Stock[5];
        Random rand = new Random();
        int random;

        for(int i = 0; i < stockArr.length; i++)
        {
            random = rand.nextInt(str.size());
            stockArr[i] = new Stock(str.get(random), rand.nextInt(500)*rand.nextDouble());
            str.remove(random);
        }
    }
    public void oneDaySim()
    {
        Investor person1 = new Investor("Frank", 5000);
        Stock stock1 = new Stock("Alpha", 100);
        Stock stock2 = new Stock("Beta", 95.0);
        Stock stock3 = new Stock("Delta", 98.0);
        stock1.printArray(stock1.pricesArray(100));
        person1.buyStock(stock1, 5);
        stock2.pricesArray(12);
        person1.buyStock(stock2, 3);
        stock3.pricesArray(15);
        person1.buyStock(stock3, 10);

        person1.sellStock(stock1, 3);
        person1.sellStock(stock2, 15);
        person1.sellStock(stock3, 8);
    }
}
