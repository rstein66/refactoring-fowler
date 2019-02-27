import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Tests for the example in chapter 1 of "Refactoring" by Fowler.
 *
 */
class CustomerTest {

    private static final String TEST_CUSTOMER = "John Smith";
    private static final int ONE_DAY = 1;
    private static final int TWO_DAYS = 2;
    private static final int THREE_DAYS = 3;
    private static final int FOUR_DAYS = 4;

    @Test
    void testStatementOneRegular() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental = createRental(customer, "Die Hard", Movie.REGULAR, TWO_DAYS);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement =
                "<h1>Rental Record for <em>John Smith</em></h1>\n<table>\n" +
                "<tr><td>Die Hard</td><td>2.0</td></tr>\n" +
                "</table>\n<p>Amount owed is <b>2.0</b>.</p>\n" +
                "<p>You earned <b>1</b> frequent renter points.</p>";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    @Test void testStatementOneRegularMoreThanTwoDays() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental;
        aRental = createRental(customer, "Die Hard", Movie.REGULAR, THREE_DAYS);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement =
                "<h1>Rental Record for <em>John Smith</em></h1>\n<table>\n" +
                "<tr><td>Die Hard</td><td>3.5</td></tr>\n" +
                "</table>\n<p>Amount owed is <b>3.5</b>.</p>\n" +
                "<p>You earned <b>1</b> frequent renter points.</p>";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }


    @Test
    void testStatementOneForChildren() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental = createRental(customer, "Kung Fu Panda", Movie.CHILDRENS, TWO_DAYS);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement =
                "<h1>Rental Record for <em>John Smith</em></h1>\n<table>\n" +
                "<tr><td>Kung Fu Panda</td><td>1.5</td></tr>\n" +
                "</table>\n<p>Amount owed is <b>1.5</b>.</p>\n" +
                "<p>You earned <b>1</b> frequent renter points.</p>";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    @Test
    void testStatementOneForChildrenMoreThanThreeDays() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental = createRental(customer, "Kung Fu Panda", Movie.CHILDRENS, FOUR_DAYS);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement =
                "<h1>Rental Record for <em>John Smith</em></h1>\n<table>\n" +
                "<tr><td>Kung Fu Panda</td><td>3.0</td></tr>\n" +
                "</table>\n<p>Amount owed is <b>3.0</b>.</p>\n" +
                "<p>You earned <b>1</b> frequent renter points.</p>";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    @Test void testStatementOneRentalNewRelease() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental = createRental(customer, "The Incident", Movie.NEW_RELEASE, TWO_DAYS);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement =
                "<h1>Rental Record for <em>John Smith</em></h1>\n<table>\n" +
                "<tr><td>The Incident</td><td>6.0</td></tr>\n" +
                "</table>\n<p>Amount owed is <b>6.0</b>.</p>\n" +
                "<p>You earned <b>2</b> frequent renter points.</p>";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    @Test
    void testStatementNoRentals() {
        Customer customer = new Customer(TEST_CUSTOMER);
        String statementResult = customer.statement();
        String expectedStatement =
                "<h1>Rental Record for <em>John Smith</em></h1>\n<table>\n" +
                "</table>\n<p>Amount owed is <b>0.0</b>.</p>\n" +
                "<p>You earned <b>0</b> frequent renter points.</p>";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    @Test
    void testStatementFourRentals() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental;
        aRental = createRental(customer, "Die Hard", Movie.REGULAR, TWO_DAYS);
        customer.addRental(aRental);
        aRental = createRental(customer, "The Incident", Movie.NEW_RELEASE, TWO_DAYS);
        customer.addRental(aRental);
        aRental = createRental(customer, "Kung Fu Panda", Movie.CHILDRENS, THREE_DAYS);
        customer.addRental(aRental);
        aRental = createRental(customer, "Star Wars", Movie.REGULAR, ONE_DAY);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement =
                "<h1>Rental Record for <em>John Smith</em></h1>\n<table>\n" +
                "<tr><td>Die Hard</td><td>2.0</td></tr>\n" +
                "<tr><td>The Incident</td><td>6.0</td></tr>\n" +
                "<tr><td>Kung Fu Panda</td><td>1.5</td></tr>\n" +
                "<tr><td>Star Wars</td><td>2.0</td></tr>\n" +
                "</table>\n<p>Amount owed is <b>11.5</b>.</p>\n" +
                "<p>You earned <b>5</b> frequent renter points.</p>";

        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    private Rental createRental(Customer customer, String movieTitle, int moviePriceType, int rentalDays) {
        Movie aMovie = new Movie(movieTitle, moviePriceType);
        return new Rental(aMovie, rentalDays);
    }

}
