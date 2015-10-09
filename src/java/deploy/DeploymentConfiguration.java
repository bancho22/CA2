package deploy;

import dk.cphbusiness.exceptions.AddressNotFoundException;
import dk.cphbusiness.exceptions.HobbyNotFoundException;
import dk.cphbusiness.test_data.TestDataGenerator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class DeploymentConfiguration implements ServletContextListener {

    public static String PU_NAME = "CA2PU"; //USE the RIGHT name here 

    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> env = System.getenv();     //If we are running in the OPENSHIFT environment change the pu-name     
        if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
            PU_NAME = "pu_OPENSHIFT";
            try {
                TestDataGenerator.populateTables(PU_NAME);
            } catch (AddressNotFoundException ex) {
                Logger.getLogger(DeploymentConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HobbyNotFoundException ex) {
                Logger.getLogger(DeploymentConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
