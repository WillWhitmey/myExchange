package app.stock;

import lombok.*;

@Value
public class Stock {
    public Integer id;
    public String name;
    public Integer price;

    public Stock(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
