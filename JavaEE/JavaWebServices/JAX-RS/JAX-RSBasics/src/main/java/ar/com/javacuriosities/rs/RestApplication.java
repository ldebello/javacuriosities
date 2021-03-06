package ar.com.javacuriosities.rs;

import ar.com.javacuriosities.rs.filters.AdminAuthenticationFilter;
import ar.com.javacuriosities.rs.mappers.AnyExceptionMapper;
import ar.com.javacuriosities.rs.mappers.CustomerNotFoundExceptionMapper;
import ar.com.javacuriosities.rs.resources.AdminResource;
import ar.com.javacuriosities.rs.resources.CustomerResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(AdminResource.class);
        resources.add(CustomerResource.class);

        resources.add(CustomerNotFoundExceptionMapper.class);
        resources.add(AnyExceptionMapper.class);

        resources.add(AdminAuthenticationFilter.class);
        return resources;
    }
}
