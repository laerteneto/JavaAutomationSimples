package suites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.TesteGoogle;
import tests.TesteLiferayForms;

@RunWith(Suite.class)
@SuiteClasses({
	TesteGoogle.class,
	TesteLiferayForms.class
})
public class SuiteTeste {
	

}
