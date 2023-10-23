package org.acme.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.StatusEnum;
import org.acme.WrapperClass;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/parameters")
@RegisterRestClient(configKey = "parameters")
public interface ParameterResourceClientApi {


    @GET
    @Path("/wrapper")
    String wrapper(@QueryParam("wrapper") final WrapperClass<StatusEnum> wrapper);


    @GET
    @Path("/wrapperList")
    String wrapperList(@QueryParam("wrapperList") final List<WrapperClass<StatusEnum>> wrapperList);

}