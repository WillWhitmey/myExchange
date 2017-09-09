package app.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {

    private String id;
    private Integer price;
    private Long time;
    private String companyID;

    public Price(String id, Integer price, Long time) {
        this.id = id;
        this.price = price;
        this.time = time;
//        this.companyID = companyID;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
