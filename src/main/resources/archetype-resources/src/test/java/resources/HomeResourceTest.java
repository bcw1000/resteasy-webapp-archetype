#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.resources;

import ${package}.responses.Response;
import ${package}.util.JettyGuiceResteasyTest;
import com.google.inject.Binder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 */
public class HomeResourceTest extends JettyGuiceResteasyTest {
    @Override
    protected void configure(final Binder binder) {
        binder.bind(HomeResource.class);
    }

    /**
     * Test of get method, of class HomeResource.
     */
    @Test
    public void testGet() {
        Client client = ClientBuilder.newClient();
        Response r = client.target("http://localhost:8080").path("/").request().get(Response.class);
        assertEquals("Gotten!", r.message);
        assertEquals(true, r.success);
    }
}