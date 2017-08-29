package app.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {

    private UUID id;
    private String name;

    //BigDecimal
    public Integer price;
    public String image;

    public Stock(UUID id, String name, Integer price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public BigDecimal getPriceAtDate() {
        ///do something
        return BigDecimal.valueOf(1);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
