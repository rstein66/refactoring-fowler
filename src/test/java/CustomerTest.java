import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/* Tests for the example in chapter 1 of "Refactoring" by Fowler */
class CustomerTest {

    private Customer customer;
    private static final String TEST_CUSTOMER = "John Smith";

    private static final int ONE_DAY    = 1;
    private static final int TWO_DAYS   = 2;
    private static final int THREE_DAYS = 3;
    private static final int FOUR_DAYS  = 4;

    private static final String DIE_HARD_TITLE = "Die Hard";
    private static final String THE_INCIDENT_TITLE = "The Incident";
    private static final String KUNG_FU_PANDA_TITLE = "Kung Fu Panda";
    private static final String STAR_WARS_TITLE = "Star Wars";

    private Movie dieHard, theIncident, kungFuPanda, starWars;

    @BeforeEach
    void setUp() {
        customer = new Customer(TEST_CUSTOMER);

        dieHard     = new RegularMovie(DIE_HARD_TITLE);
        theIncident = new NewMovie(THE_INCIDENT_TITLE);
        kungFuPanda = new KidsMovie(KUNG_FU_PANDA_TITLE);
        starWars    = new RegularMovie(STAR_WARS_TITLE);
    }

    @Test
    void testStatementOneRegular() {
        customer.addRental(new Rental(dieHard, TWO_DAYS));
        String expectedStatement =
                ExpectedStatement.intro(TEST_CUSTOMER) +
                ExpectedStatement.item(DIE_HARD_TITLE, 2.0) +
                ExpectedStatement.owes(2.0) +
                ExpectedStatement.earns(1);
        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementOneRegularMoreThanTwoDays() {
        customer.addRental(new Rental(dieHard, THREE_DAYS));
        String expectedStatement =
                ExpectedStatement.intro(TEST_CUSTOMER) +
                ExpectedStatement.item(DIE_HARD_TITLE, 3.5) +
                ExpectedStatement.owes(3.5) +
                ExpectedStatement.earns(1);
        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementOneForChildren() {
        customer.addRental(new Rental(kungFuPanda, TWO_DAYS));
        String expectedStatement =
                ExpectedStatement.intro(TEST_CUSTOMER) +
                ExpectedStatement.item(KUNG_FU_PANDA_TITLE, 1.5) +
                ExpectedStatement.owes(1.5) +
                ExpectedStatement.earns(1);
        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementOneForChildrenMoreThanThreeDays() {
        customer.addRental(new Rental(kungFuPanda, FOUR_DAYS));
        String expectedStatement =
                ExpectedStatement.intro(TEST_CUSTOMER) +
                ExpectedStatement.item(KUNG_FU_PANDA_TITLE, 3.0) +
                ExpectedStatement.owes(3.0) +
                ExpectedStatement.earns(1);
        assertEquals(expectedStatement, customer.statement());
    }

    @Test void testStatementOneRentalNewRelease() {
        customer.addRental(new Rental(theIncident, TWO_DAYS));
        String expectedStatement =
                ExpectedStatement.intro(TEST_CUSTOMER) +
                ExpectedStatement.item(THE_INCIDENT_TITLE, 6.0) +
                ExpectedStatement.owes(6.0) +
                ExpectedStatement.earns(2);
        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementNoRentals() {
        String expectedStatement =
                ExpectedStatement.intro(TEST_CUSTOMER) +
                ExpectedStatement.owes(0.0) +
                ExpectedStatement.earns(0);
        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementFourRentals() {
        customer.addRental(new Rental(dieHard, TWO_DAYS));
        customer.addRental(new Rental(theIncident, TWO_DAYS));
        customer.addRental(new Rental(kungFuPanda, THREE_DAYS));
        customer.addRental(new Rental(starWars, ONE_DAY));
        String expectedStatement =
                ExpectedStatement.intro(TEST_CUSTOMER) +
                ExpectedStatement.item(DIE_HARD_TITLE, 2.0) +
                ExpectedStatement.item(THE_INCIDENT_TITLE, 6.0) +
                ExpectedStatement.item(KUNG_FU_PANDA_TITLE, 1.5) +
                ExpectedStatement.item(STAR_WARS_TITLE, 2.0) +
                ExpectedStatement.owes(11.5) +
                ExpectedStatement.earns(5);
        assertEquals(expectedStatement, customer.statement());
    }
}
