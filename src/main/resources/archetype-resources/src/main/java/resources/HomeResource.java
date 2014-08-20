#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.resources;

import ${package}.responses.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 */
@Path("/")
@Produces(value = {MediaType.APPLICATION_JSON})
public class HomeResource {
    @GET
    public Response get() {
        return new Response().setSuccess(true).setMessage("Gotten!");
    }
}