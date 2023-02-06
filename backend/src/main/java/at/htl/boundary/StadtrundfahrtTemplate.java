package at.htl.boundary;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/stadtrundfahrt")
public class StadtrundfahrtTemplate {
    @Inject
    Template stadtrundfahrt;

    @GET
    @Produces
    public TemplateInstance get(@QueryParam("name") String name) {
        return stadtrundfahrt.data("name", name);
    }

}
