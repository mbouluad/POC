package fr.helloworldrestmicroservice.service;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.helloworldrestmicroservice.dao.IHelloWorldDAO;
import fr.helloworldrestmicroservice.event.IHelloWorldEventProducer;
import fr.helloworldrestmicroservice.model.HelloWorld;

/**
 * @author Mickael BARON 
 */
@Path("/helloworld")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

	@Inject
	@Named("redis")
	private IHelloWorldDAO currentDAO;
	
	@Inject
	private IHelloWorldEventProducer currentProducer;
	
	@GET
	public Response getHelloWorlds() {
		return Response.ok(currentDAO.getHelloWorlds()).build();
	}
	
	@POST
	public Response addHelloWorld(HelloWorld newHelloWorld) {
		if (newHelloWorld != null) {
			newHelloWorld.setStartDate(new Date().toString());
		}
		
		currentDAO.addHelloWorld(newHelloWorld);
		currentProducer.sendMessage(newHelloWorld);
		
		return Response.status(Status.CREATED).build();
	}
}
