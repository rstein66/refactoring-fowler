class Rental {
    private Movie _movie;
    private int _daysRented;

    Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    int getDaysRented() {
        return _daysRented;
    }

    Movie getMovie() {
        return _movie;
    }

    double getCost() {
        return getMovie().getCost(getDaysRented());
    }

    int getFrequentRentalPoints() {
        return getMovie().getFrequentRentalPoints(getDaysRented());
    }
}
