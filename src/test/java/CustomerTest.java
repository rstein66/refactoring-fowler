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
        String expectedStatement = "Rental Record for John Smith\n" +
                "\tDie Hard\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    @Test void testStatementOneRegularMoreThanTwoDays() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental;
        aRental = createRental(customer, "Die Hard", Movie.REGULAR, THREE_DAYS);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement = "Rental Record for John Smith\n" +
                "\tDie Hard\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }


    @Test
    void testStatementOneForChildren() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental = createRental(customer, "Kung Fu Panda", Movie.CHILDRENS, TWO_DAYS);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement = "Rental Record for John Smith\n" +
                "\tKung Fu Panda\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    @Test
    void testStatementOneForChildrenMoreThanThreeDays() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental = createRental(customer, "Kung Fu Panda", Movie.CHILDRENS, FOUR_DAYS);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement = "Rental Record for John Smith\n" +
                "\tKung Fu Panda\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    @Test void testStatementOneRentalNewRelease() {
        Customer customer = new Customer(TEST_CUSTOMER);
        Rental aRental = createRental(customer, "The Incident", Movie.NEW_RELEASE, TWO_DAYS);
        customer.addRental(aRental);
        String statementResult = customer.statement();
        String expectedStatement = "Rental Record for John Smith\n" +
                "\tThe Incident\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    @Test
    void testStatementNoRentals() {
        Customer customer = new Customer(TEST_CUSTOMER);
        String statementResult = customer.statement();
        MatcherAssert.assertThat(statementResult,
                                 is("Rental Record for John Smith\nAmount owed is 0.0\nYou earned 0 frequent renter points"));
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
        String expectedStatement = "Rental Record for John Smith\n" +
                "\tDie Hard\t2.0\n" +
                "\tThe Incident\t6.0\n" +
                "\tKung Fu Panda\t1.5\n" +
                "\tStar Wars\t2.0\n" +
                "Amount owed is 11.5\n" +
                "You earned 5 frequent renter points";
        MatcherAssert.assertThat(statementResult, is(expectedStatement));
    }

    private Rental createRental(Customer customer, String movieTitle, int moviePriceType, int rentalDays) {
        Movie aMovie = new Movie(movieTitle, moviePriceType);
        return new Rental(aMovie, rentalDays);
    }

}
