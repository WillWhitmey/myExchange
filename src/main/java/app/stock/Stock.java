package app.stock;

import lombok.*;

@Value
public class Stock {
    public String company;
    public Integer price;

    public Stock(String company, Integer price) {
        this.company = company;
        this.price = price;
    }
}
