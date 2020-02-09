package com.company;
import java.util.Random;

public class Stock {
    String name;
    double price;

    public Stock(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double[] pricesArray(int days)
    {
        double[] array = new double[days];
        Random rand = new Random();
        boolean bool;
        for(int i = 0; i < days; i++)
        {
            bool = rand.nextBoolean();
            if(bool)
            {
                price += ((rand.nextDouble()/12)*price)*rand.nextDouble();
            }
            else
            {
                price -= ((rand.nextDouble()/12)*price)*rand.nextDouble();
            }
            array[i] = price;
        }
        return array;
    }
    public void printArray(double[] array2)
    {
        int length = array2.length;
        for(int i = 0; i < length; i++)
        {
            System.out.printf("%.2f", array2[i]);
            System.out.println();
        }
    }


}
