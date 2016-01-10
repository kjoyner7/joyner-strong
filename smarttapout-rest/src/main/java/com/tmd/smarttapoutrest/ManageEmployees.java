package com.tmd.smarttapoutrest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdEmployeeToStore;
import com.tmd.dao.data.TmdRolesToStore;
import com.tmd.dao.data.VtigerRole;
import com.tmd.dao.data.VtigerUsers;
import com.tmd.dao.db.TmdEmployeeToStoreDAO;
import com.tmd.dao.db.TmdRolesToStoreDAO;
import com.tmd.dao.db.VtigerRoleDAO;
import com.tmd.dao.db.VtigerUsersDAO;

@Path("/employee")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ManageEmployees {
	private static final Logger log = LoggerFactory.getLogger(ManageEmployees.class);
	public static final String version = "Version 1.0.0 -- 2015-07-22";
	
	public ManageEmployees(){
		
	}
	
	/**
	 * Get all current employees for a store
	 */
	@GET
	@Path("/{storeid}")
	@Produces("application/json")
	public String getEmployees(@PathParam("storeid") String storeid){
		log.debug("storeid: [{}]", storeid);
		String retJson = null;
		List<TmdEmployeeToStore> employees = TmdEmployeeToStoreDAO.getEmployees(storeid);
		List<TmdRolesToStore> roles = TmdRolesToStoreDAO.getRolesForStore(storeid);
		VtigerUsers user = null;
		if(null != employees){
			retJson = "[{\"found\":\"yes\",\"name\":[";
			for(TmdEmployeeToStore employee : employees){
				user = VtigerUsersDAO.findById(employee.getVtigerUsersId());
				/*
				 * build json with first,last names				 
				 */
				retJson = retJson + "{\"employee\":\""+user.getFirstName()+" "+user.getLastName()+"\"},";
				log.debug("added user [{}]", user.getFirstName());
			}
			retJson = retJson.substring(0, retJson.length()-1);
			// we have all the employees, now let's get all the roles for this store in case we need them
			retJson = retJson + "],\"roles\":[";
			for(TmdRolesToStore role : roles){
				VtigerRole roleName = VtigerRoleDAO.findByRoleId(role.getRoleId());
				retJson = retJson + "{\"role\":\"" + roleName.getRolename() + "\"},";
			}
			retJson = retJson.substring(0, retJson.length()-1);
			retJson = retJson + "]}]";
			log.debug("retJson: [{}]", retJson);
		}else{
			// employee not found in db
			retJson = retJson +"\"found\":\"no\"}]";
			log.debug("did not find user");
		}
		
		return retJson;
	}
	
	/**
	 * Add new employee
	 */
	@POST
	@Path("/add")
	@Produces("application/json")
	public String addEmployee(@FormParam("userInfo") String userInfo){
		String msg="[{\"found\":\"yes\",\"message\":\"Shift Tasks Finished\"}]";
		
		return msg;
	}
	
	/**
	 * Make employee inactive
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	public String makeEmployeeInactive(@FormParam("userid") String userid){
		String msg="[{\"found\":\"yes\",\"message\":\"Shift Tasks Finished\"}]";
		return msg;
	}
}
