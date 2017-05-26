package nanoQSARTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	CsvFileWriterTest.class, DefaultUnitsTest.class,
		IllegalUnitsExceptionTest.class, NanoMaterialTest.class, NanoMaterialsTest.class })
public class AllNanoQSARTests {

}
