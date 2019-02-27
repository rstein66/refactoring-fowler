class NewMovie extends Movie {

    private static final int PRICE_CODE = 1;

    NewMovie(String title) {
        super(title, PRICE_CODE);
    }

    double getCost(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRentalPoints(int daysRented) {
        if (daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
