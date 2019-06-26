package javatraining;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class Helloworld {
	@GET
	@Produces({"text/plain"})
	
public String greet() {
	
	return "Hello world how are you?";
}
	@GET
	@Produces({"application/xml"})
	public String getXML() {
		return "<?xml version = \"1.0\" ?>\r\n" + 
				"<greeting>\r\n" + 
				"<message>Hello World</message>\r\n" + 
				"<from>Ashwini Dear</from>\r\n" + 
				"</greeting>";
	}
	@GET
	@Produces({"application/json"})
	public String getJSON() {
		return "{  \"message\":\"Hello world\",\r\n" + 
				"\"from\":\"Ashwini Dear\"\r\n" + 
				"}";
	}
}
