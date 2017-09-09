package app.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PriceSummary {

    private final List<Price> prices;
//    private final String id;

}
