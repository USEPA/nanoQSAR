package nanoQSARTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	NanoToxExpTest.class, NanoToxExpsTest.class,
	DefaultUnitsTest.class, NanoQSARTest.class })
public class AllNanoQSARTests {

}
