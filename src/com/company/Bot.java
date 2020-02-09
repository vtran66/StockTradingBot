package com.company;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Bot {
    List<String> str = new ArrayList<>(List.of("Alpha", "Beta", "Delta", "Epsilon", "Gamma", "Lambda", "Omega", "Sigma", "Theta", "Zeta"));
    Stock[] stockArr = new Stock[5];
    ArrayList<Double>[] dataArray = new ArrayList[stockArr.length];//An array of Arraylists<Doubles>
    double[] averages = new double[stockArr.length]; //need to set length of this array
    double investmentValue = 0;

    public void generateStocks()
    {
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
        for(int i = 0; i < stockArr.length; i++)
        {
            stockArr[i].pricesArray(1);
        }
    }
    public void generateData()
    {
        for(int i = 0; i < stockArr.length; i++)
        {
            //For every stock, prices for 100 days are created
            dataArray[i] = stockArr[i].pricesArray(100);
            //System.out.println(Arrays.toString(dataArray[i].toArray()));
        }
    }
    public void generateStockAverages()
    {
        double total = 0;
        generateData();
        for (int i = 0; i < stockArr.length; i++)
        {
            for (int j = 0; j < dataArray.length; j++)
            {
                total += dataArray[i].get(j);
            }
            averages[i] = total/dataArray[i].size();
        }
    }

    public void analysis()
    {
        Investor Frank = new Investor("Frank", 10000);
        Investor John = new Investor("John", 10000);
        double investFivePercent;
        int dayCounter = 0;


        while (dayCounter < 100)
        {
            generateStockAverages();
            oneDaySim();
            for(int i = 0; i < stockArr.length; i++)
            {
                if(stockArr[i].price < averages[i])
                {
                    investFivePercent = Frank.balance*0.05;
                    Frank.buyStock(stockArr[i], (int)(investFivePercent/stockArr[i].price));
                }
                else if(stockArr[i].price > averages[i])
                {
                    if (Frank.stockList.containsKey(stockArr[i]))
                    {
                        Frank.sellStock(stockArr[i], Frank.stockList.get(stockArr[i]));
                    }
                }
            }
            dayCounter++;
        }
        Frank.printStockList();
        System.out.printf("Your cash at hand is " + "%.2f", Frank.balance);
        Frank.stockList.forEach((k, v) -> {
            investmentValue += k.price*v;
        });
        System.out.println();
        System.out.printf("Total value of assets is " + "%.2f", (Frank.balance + investmentValue));
    }
}
