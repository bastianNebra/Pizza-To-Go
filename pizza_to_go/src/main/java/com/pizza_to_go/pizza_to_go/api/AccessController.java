package com.pizza_to_go.pizza_to_go.api;

import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/access")
public class AccessController {
    WebTarget userManagementTarget = ClientBuilder.newClient().target("http://localhost:9082/data/access");
    @POST
    public String login(String login) {
        try (Response response = userManagementTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(login, MediaType.APPLICATION_JSON))) {
            return response.readEntity(String.class);
        }
    }

    @DELETE
    public void logout(@QueryParam("token") String token) {
        try (Response response = userManagementTarget.queryParam("token", token).request(MediaType.APPLICATION_JSON).delete()) {

        }
    }
}


