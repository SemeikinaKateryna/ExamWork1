package util;

import lombok.NoArgsConstructor;

/**
 * Class PriceConverter is designed for converting currencies USD and EUR to UAN.
 * Every price will be converted to UANs and then compare
 */

@NoArgsConstructor
public class PriceConverter {
    private static final double USD_TO_UAN = 36.74;
    private static final double EUR_TO_UAN = 38.91;

    public double usdToUan(double usd){
        return usd * USD_TO_UAN;
    }
    public double eurToUan(double eur){
        return eur * EUR_TO_UAN;
    }
}
