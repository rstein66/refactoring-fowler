class RegularMovie extends Movie {

    private static final int PRICE_CODE = 0;

    RegularMovie(String title) {
        super(title, PRICE_CODE);
    }

    double getCost(int daysRented) {
        double cost = 2;
        if (daysRented > 2)
            cost += (daysRented - 2) * 1.5;
        return cost;
    }

}
