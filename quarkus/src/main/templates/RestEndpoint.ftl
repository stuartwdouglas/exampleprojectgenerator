package com.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;

@Path("/r${no}")
public class RestEndpoint${no} {

@Inject
public Bean messageBean;


@GET
    public String get1() {
        return "${no}";
    }

}
