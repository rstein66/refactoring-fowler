class KidsMovie extends Movie {

    private static final int PRICE_CODE = 2;

    KidsMovie(String title) {
        super(title, PRICE_CODE);
    }

    double getCost(int daysRented) {
        double cost = 1.5;
        if (daysRented > 3) {
            cost += (daysRented - 3) * 1.5;
        }
        return cost;
    }
}
