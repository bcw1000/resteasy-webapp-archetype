#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.resources.ResourceModule;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class RootModule extends AbstractModule {
    private static final Logger LOG = LoggerFactory.getLogger(RootModule.class);
    static final String CONF_FILE = "app.properties";
    static final String CONF_PARAM = "app.configurationFile";

    @Override
    protected void configure() {
        // Read properties
        try {
            Properties props = loadProperties();
            LOG.info("Application Properties: {}", props);
            Names.bindProperties(binder(), props);
        } catch(Exception e) {
            LOG.error("Error during application configuration: ", e);
        }
        
        // Install other modules
        install(new ResourceModule());
    }
    
    

    private static Properties loadProperties() throws Exception {
        Properties properties = new Properties();

        // Load from class-path
        ClassLoader loader = RootModule.class.getClassLoader();
        URL url = loader.getResource(CONF_FILE);
        properties.load(url.openStream());

        // from file
        String propFile = System.getProperty(CONF_PARAM);
        if(null != propFile) {
            properties.load(new FileInputStream(propFile));
        }

        // Override explicitly provided properties
        for(Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = (String) entry.getKey();
            properties.put(key, System.getProperty(key, (String) entry.getValue()));
        }
        return properties;
    }
}
