public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title;
    private int _priceCode;

    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(int arg) {
        _priceCode = arg;
    }

    public String getTitle() {
        return _title;
    }

    double getCost(int daysRented) {
        double cost;
        switch (getPriceCode()) {
            case Movie.REGULAR:
                cost = 2;
                if (daysRented > 2)
                    cost += (daysRented - 2) * 1.5;
                return cost;
            case Movie.NEW_RELEASE:
                return daysRented * 3;
            case Movie.CHILDRENS:
                cost = 1.5;
                if (daysRented > 3)
                    cost += (daysRented - 3) * 1.5;
                return cost;
            default:
                return 0;
        }
    }

    int getFrequentRentalPoints(int daysRented) {
        // add bonus for a two day new release rental
        if ((getPriceCode() == Movie.NEW_RELEASE) && (daysRented > 1)) {
            return 2;
        } else {
            return 1;
        }
    }
}
