import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ConnectionManagerTest.class,
		CsvFileWriterTest.class, DefaultUnitsTest.class,
		IllegalUnitsExceptionTest.class, MySqlQueryTest.class,
		NanoMaterialTest.class })
public class AllTests {

}
