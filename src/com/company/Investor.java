package com.company;
import java.util.Hashtable;

public class Investor {
    String name;
    double balance;
    Hashtable<Stock, Integer> stockList = new Hashtable<Stock, Integer>();

    public Investor(String name, double balance)
    {
        this.name = name;
        this.balance = balance;
    }

    public void buyStock(Stock stock, int shares)
    {
      if(balance >= stock.price*shares) {
          balance -= (stock.price * shares);
          if (stockList.containsKey(stock)) {
              stockList.put(stock, stockList.get(stock)+shares);
          } else {
              stockList.put(stock, shares);
          }
      }
      else
          System.out.println("You don't have sufficient funds to buy "+stock.name);

    }
    public void sellStock(Stock stock, int shares)
    {
            if (stockList.containsKey(stock)) {
                if (stockList.get(stock) >= shares) {
                    balance += (stock.price * shares);
                    stockList.put(stock, stockList.get(stock) - shares);
                } else
                    System.out.println("You only have " + stockList.get(stock) + " shares of " + stock.name + "!");
            } else
                System.out.println("You don't have shares of " + stock.name + " to sell!");

    }


    public void printStockList()
    {
        stockList.forEach((k, v) -> {
        System.out.println(k.name + ": You have " + v +" shares");
        });


    }

}
