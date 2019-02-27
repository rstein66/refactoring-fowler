abstract class Movie {

    private String _title;
    private int _priceCode;

    Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    String getTitle() {
        return _title;
    }

    void setPriceCode(int priceCode) {
        _priceCode = priceCode;
    }

    int getPriceCode() {
        return _priceCode;
    }

    int getFrequentRentalPoints(int daysRented) {
        return 1;
    }

    abstract double getCost(int daysRented);
}
