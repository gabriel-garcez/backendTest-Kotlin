package healthcheck

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite.class)
    @Suite.SuiteClasses({healthcheck.Healthcheck.class, healthcheck.HealthcheckComplete.class})
public class AllTests{

    }