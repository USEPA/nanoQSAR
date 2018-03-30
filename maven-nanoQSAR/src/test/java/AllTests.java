import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({datamineTests.AllDatamineTests.class,
		nanoQSARTests.AllNanoQSARTests.class, predictionTests.AllPredictionTests.class, plsrTests.AllPLSRTests.class})
public class AllTests {
	
	private static final Logger log = Logger.getGlobal();
	
	@Before
	void SetUp () {
		log.setLevel(Level.WARNING);
	}
	
}

