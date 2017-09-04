package app.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Purchase {

    private String id;
    private Integer price;
    private Integer time;
    private String companyID;

    public Purchase(String id, Integer price, Integer time, String companyID) {
        this.id = id;
        this.price = price;
        this.time = time;
        this.companyID = companyID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
