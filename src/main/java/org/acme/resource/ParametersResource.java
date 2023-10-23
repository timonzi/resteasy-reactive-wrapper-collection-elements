package org.acme.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.StatusEnum;
import org.acme.WrapperClass;

import java.util.List;

@Path("/parameters")
public class ParametersResource {

    @GET
    @Path("/wrapperList")
    public String wrapperList(@QueryParam("wrapperList") final List<WrapperClass<StatusEnum>> wrapperList) {
        return wrapperList.stream().map(WrapperClass::getValue).toList().toString();
    }


    @GET
    @Path("/wrapper")
    public String wrapper(@QueryParam("wrapper") final WrapperClass<StatusEnum> wrapper) {
        return wrapper.getValue().toString();
    }
}
