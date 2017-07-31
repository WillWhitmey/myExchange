package app.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {

    private Integer id;
    private String name;

    //BigDecimal
    public Integer price;

    public Stock(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPriceAtDate() {
        ///do something
        return BigDecimal.valueOf(1);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
