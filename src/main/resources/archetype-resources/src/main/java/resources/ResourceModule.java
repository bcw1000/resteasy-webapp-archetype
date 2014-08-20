#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.resources;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * 
 */
public class ResourceModule implements Module {
    
    @Override
    public void configure(final Binder binder) {
        binder.bind(HomeResource.class).asEagerSingleton();
    }
    
}