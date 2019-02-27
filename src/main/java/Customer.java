import java.util.Enumeration;
import java.util.Vector;


class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector<>();

    Customer(String name) {
        _name = name;
    }

    void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    private String getName() {
        return _name;
    }

    String statement() {
        return getHtmlStatement();
    }

    double getTotalCharge() {
        double charge = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            charge += each.getCost();
        }
        return charge;
    }

    int getTotalFrequentRenterPoints() {
        int points = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            points += each.getFrequentRentalPoints();
        }
        return points;
    }

    private String getHtmlStatement() {
        Enumeration rentals = _rentals.elements();
        StringBuilder result =
                new StringBuilder("<h1>Rental Record for <em>")
                        .append(getName()).append("</em></h1>\n<table>\n");

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            // show figures for this rental
            result.append("<tr><td>").append(each.getMovie().getTitle())
                  .append("</td><td>").append(each.getCost())
                  .append("</td></tr>\n");
        }
        // add footer lines
        result.append("</table>\n<p>Amount owed is <b>")
              .append(getTotalCharge()).append("</b>.</p>\n");
        result.append("<p>You earned <b>")
              .append(getTotalFrequentRenterPoints())
              .append("</b> frequent renter points.</p>");

        return result.toString();
    }
}
