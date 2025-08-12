public class Task_4 {

    /**
     * Converts a value using a given exchange rate.
     *
     * @param amount The amount of money to convert.
     * @param exchangeRate The target currency's exchange rate.
     * @return The resulting converted amount.
     */
    public static double convert(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    public static void main(String[] args) {
        // --- Example: Convert 150 US Dollars (USD) to Indian Rupees (INR) ---

        // 1. Define the input values
        double amountInUSD = 150.0;
        double exchangeRateUSDToINR = 83.55; // As an example, 1 USD = 83.55 INR

        // 2. Call the conversion function
        double amountInINR = convert(amountInUSD, exchangeRateUSDToINR);

        // 3. Display the result, formatted to two decimal places for currency
        System.out.println("--- Conversion Result ---");
        System.out.printf("$%.2f USD is equal to ₹%.2f INR.%n", amountInUSD, amountInINR);
    }
}