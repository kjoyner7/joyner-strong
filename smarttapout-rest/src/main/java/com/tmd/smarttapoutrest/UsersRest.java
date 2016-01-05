package com.tmd.smarttapoutrest;

//import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

//import com.tmd.dao.db.VtigerUsersDAO;
//import com.tmd.dao.data.VtigerUsers;
import com.tmd.lib.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("user")
@Produces({ "application/json", "application/xml", "multipart/form-data", "text/html" })
@Consumes({ "application/json", "application/xml", "multipart/form-data", "text/html" })

public class UsersRest {
	private static final Logger log = LoggerFactory.getLogger(UsersRest.class);
	public static final String version = "Version 1.0.0 -- 2015-07-20";
	
	public UsersRest(){
		
	}
	

	@GET	
	@Produces("application/json")
	public String checkUser(@Context UriInfo ui){ 
		String msg="message";
		Users user = new Users();
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		log.debug("qp size: [{}]", queryParams.size());
		String username = queryParams.getFirst("username");
		String password = queryParams.getFirst("password");
		log.debug("username: [{}] with password: [{}]", username, password);
		msg = user.checkForUser(username, password);
		
		return msg;
	}

}
