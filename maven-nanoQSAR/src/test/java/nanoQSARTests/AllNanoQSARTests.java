package nanoQSARTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	NanoMaterialTest.class, NanoMaterialsTest.class,
	DefaultUnitsTest.class, NanoQSARTest.class })
public class AllNanoQSARTests {

}
