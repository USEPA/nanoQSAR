package nanoQSARTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	NanoQSARTest.class, NanoMaterialTest.class, NanoMaterialsTest.class,
	DefaultUnitsTest.class })
public class AllNanoQSARTests {

}
