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
        double cost = 0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                cost += 2;
                if (getDaysRented() > 2)
                    cost += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                cost += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                cost += 1.5;
                if (getDaysRented() > 3)
                    cost += (getDaysRented() - 3) * 1.5;
                break;
        }
        return cost;
    }
}
