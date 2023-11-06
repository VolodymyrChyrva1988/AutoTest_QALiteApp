package api;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PrivatExcRateDTO {
        String date;
        String bank;
        int baseCurrency;
        String baseCurrencyLit;
        ExchangeRateDTO[] exchangeRate;

    }

