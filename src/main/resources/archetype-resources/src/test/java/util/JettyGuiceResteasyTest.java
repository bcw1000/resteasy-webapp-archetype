#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import com.google.inject.Binder;
import com.google.inject.Guice;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.junit.After;
import org.junit.Before;

/**
 *
 */
public abstract class JettyGuiceResteasyTest {

    private Server server;

    @Before
    public void setUp() throws Exception {
        server = new Server(8080);
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addEventListener(Guice.createInjector(new TestModule()).getInstance((GuiceResteasyBootstrapServletContextListener.class)));
        servletHandler.addServlet(HttpServletDispatcher.class, "/*");
        server.setHandler(servletHandler);
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    abstract protected void configure(final Binder binder);
    
    protected void configureRequestContext() {}

    private class TestModule extends RequestScopeModule {

        @Override
        protected void configure() {
            super.configure();
            bind(ContainerRequestFilter.class).toInstance(new ContainerRequestFilterImpl());
            JettyGuiceResteasyTest.this.configure(this.binder());
        }
    }

    @Provider
    private class ContainerRequestFilterImpl implements ContainerRequestFilter {
        @Override
        public void filter(ContainerRequestContext requestContext) throws IOException {
            configureRequestContext();
        }
    }
}