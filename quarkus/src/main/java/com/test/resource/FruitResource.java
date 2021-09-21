package com.test.resource;

import com.test.entity.Fruit;
import com.test.service.FruitService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fruit")
@Transactional
public class FruitResource {

    @Inject
    FruitService fruitService;

    @GET
    public List<Fruit> list() {
        return fruitService.list();
    }

    @GET
    @Path("{id}")
    public Fruit find(int id) {
        return fruitService.find(id);
    }

    @POST
    public Response save(Fruit fruit) {
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
