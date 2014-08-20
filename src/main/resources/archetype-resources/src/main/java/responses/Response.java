#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.responses;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Response implements Serializable {
    public boolean success = false;
    public String message = "";
    public Object result = null;
    public Collection<Object> results = Collections.EMPTY_LIST;

    public boolean isSuccess() {
        return success;
    }

    public Response setSuccess(final boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(final String message) {
        this.message = message;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public Response setResult(final Object result) {
        this.result = result;
        return this;
    }

    public Response addResult(final Object result) {
        this.results.add(result);
        return this;
    }
    
    public Collection<Object> getResults() {
        return results;
    }

    public Response setResults(final Collection<Object> results) {
        this.results = results;
        return this;
    }

}