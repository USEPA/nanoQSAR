package datamineTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ConnectionManagerTest.class,	MySqlQueryTest.class,
		DBUtilTest.class, Datamine_NanoQSARTest.class })
public class AllDatamineTests {

}
