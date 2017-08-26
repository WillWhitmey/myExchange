package app.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class StockSummary {

    private final List<Stock> stockSummaries;

}
