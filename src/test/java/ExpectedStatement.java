/* CustomerTest Helper */
class ExpectedStatement {

    static String intro(String customerName) {
        return "<h1>Rental Record for <em>" + customerName +
                "</em></h1>\n<table>\n";
    }

    static String item(String movieTitle, double movieCharge) {
        return "<tr><td>" + movieTitle + "</td><td>" + movieCharge +
                "</td></tr>\n";
    }

    static String owes(double totalCharge) {
        return "</table>\n<p>Amount owed is <b>" + totalCharge + "</b>.</p>\n";
    }


    static String earns(int points) {
        return "<p>You earned <b>" + points +
                "</b> frequent renter points.</p>";
    }

}
