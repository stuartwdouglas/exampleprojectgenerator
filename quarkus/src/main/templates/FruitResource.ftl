package com.test.resource;

import com.test.entity.Fruit${no};
import com.test.service.FruitService${no};

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fruit${no}")
@Transactional
public class FruitResource${no} {

    @Inject
    FruitService${no} fruitService;

    @GET
    public List<Fruit${no}> list() {
        return fruitService.list();
    }

    @GET
    @Path("{id}")
    public Fruit${no} find(int id) {
        return fruitService.find(id);
    }

    @POST
    public Response save(Fruit${no} fruit) {
        fruitService.save(fruit);
        return Response.status(201).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(int id) {
        fruitService.delete(fruitService.find(id));
        return Response.noContent().build();
    }

}
