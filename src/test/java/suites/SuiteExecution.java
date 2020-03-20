package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import services.CalculationRentValueTest;
import services.CalculatorTest;
import services.ExampleSortedTest;
import services.LocationServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculationRentValueTest.class,
        CalculatorTest.class,
        ExampleSortedTest.class,
        LocationServiceTest.class
})
public class SuiteExecution {
}
