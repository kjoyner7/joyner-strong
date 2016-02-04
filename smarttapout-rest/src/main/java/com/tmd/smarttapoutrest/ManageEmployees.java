package com.tmd.smarttapoutrest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdEmployeeToStore;
import com.tmd.dao.data.TmdRolesToStore;
import com.tmd.dao.data.VtigerRole;
import com.tmd.dao.data.VtigerUser2role;
import com.tmd.dao.data.VtigerUsers;
import com.tmd.dao.data.TmdEmployeeStatus;
import com.tmd.dao.db.TmdEmployeeStatusDAO;
import com.tmd.dao.db.TmdEmployeeToStoreDAO;
import com.tmd.dao.db.TmdRolesToStoreDAO;
import com.tmd.dao.db.VtigerRoleDAO;
import com.tmd.dao.db.VtigerUser2roleDAO;
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
		//List<TmdRolesToStore> roles = TmdRolesToStoreDAO.getRolesForStore(storeid);
		//VtigerUsers user = null;
		if(null != employees){
			retJson = "[{\"found\":\"yes\",\"name\":[";
			retJson = retJson + buildJson(storeid);
		//	for(TmdEmployeeToStore employee : employees){
		//		user = VtigerUsersDAO.findById(employee.getVtigerUsersId());
				/*
				 * build json with first,last names				 
				 */
		//		retJson = retJson + "{\"employee\":\""+user.getFirstName()+" "+user.getLastName()+"\"},";
		//		log.debug("added user [{}]", user.getFirstName());
		//	}
		/*	retJson = retJson + getEmployeeList(employees);
			retJson = retJson.substring(0, retJson.length()-1);
			// we have all the employees, now let's get all the roles for this store in case we need them
			retJson = retJson + "],\"roles\":[";
			//for(TmdRolesToStore role : roles){
			//	VtigerRole roleName = VtigerRoleDAO.findByRoleId(role.getRoleId());
			//	retJson = retJson + "{\"role\":\"" + roleName.getRolename() + "\"},";
			//}
			retJson = retJson + getRoleList(storeid);
			retJson = retJson.substring(0, retJson.length()-1);
			retJson = retJson + "]}]";  */
			log.debug("retJson: [{}]", retJson);
		}else{
			// employee not found in db
			retJson = "[{\"found\":\"no\"}]";
			log.debug("did not find user");
		}
		
		return retJson;
	}
	//returns json list of employees for a store.
	private String getEmployeeList(List<TmdEmployeeToStore> employees){
		String retJson = "";
		VtigerUsers user = null;
		
		for(TmdEmployeeToStore employee : employees){
			if(TmdEmployeeStatusDAO.findByUserId(employee.getVtigerUsersId()).getEmployeeStatus() ==1){
				user = VtigerUsersDAO.findById(employee.getVtigerUsersId());
				/*
				 * build json with first,last names				 
				 */
				retJson = retJson + "{\"employee\":\""+user.getFirstName()+" "+user.getLastName()+"\",\"id\":\""+user.getId()+"\"},";
				log.debug("added user [{}]", user.getFirstName());
			}
		}
		
		return retJson;		
	}
	// returns check for duplicates
	private boolean hasExisting(List<TmdEmployeeToStore> employees, VtigerUsers newUser){
		boolean exists = false;
		VtigerUsers user = null;
		//retJson = "[{\"found\":\"yes\",\"name\":[";
		for(TmdEmployeeToStore employee : employees){
			user = VtigerUsersDAO.findById(employee.getVtigerUsersId());
				/*
				 * build json with first,last names				 
				 */
			if(user.getFirstName().equalsIgnoreCase(newUser.getFirstName()) && user.getLastName().equalsIgnoreCase(newUser.getLastName())){
				log.debug("user [{}] has existing: ", user.getFirstName(), exists);
				exists = true;
			}
		//	retJson = retJson + "{\"employee\":\""+user.getFirstName()+" "+user.getLastName()+"\"},";
			log.debug("user [{}] has existing: ", user.getFirstName(), exists);
		}
		
		return exists;		
	}
	// returns list of roles for store
	private String getRoleList(String storeId){
		String retJson = "";
		List<TmdRolesToStore> roles = TmdRolesToStoreDAO.getRolesForStore(storeId);
		for(TmdRolesToStore role : roles){
			VtigerRole roleName = VtigerRoleDAO.findByRoleId(role.getRoleId());
			retJson = retJson + "{\"role\":\"" + roleName.getRolename() + "\"},";
		}
		return retJson;
	}
	
	/**
	 * Add new employee
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public String addEmployee(@FormParam("first") String fname, @FormParam("last") String lname, 
			@FormParam("user") String uName, @FormParam("password") String pword, 
			@FormParam("role") String role, @FormParam("store") String store){
		String retJson="[{\"added\":";
		String email = "info@tmd.com";
		log.debug("made it to addEmployee");
		VtigerUsers newUser = null;
		List<TmdEmployeeToStore> employees = TmdEmployeeToStoreDAO.getEmployees(store);
		// add to vtiger_users table
		try{
			VtigerUsers user = new VtigerUsers();
			user.setFirstName(fname);
			user.setLastName(lname);
			if(hasExisting(employees, user)){
				retJson = retJson + "\"no\",\"message\":\"exists\",\"name\":[";
				retJson = retJson + buildJson(store);
				return retJson;
			}
			user.setUserName(uName);
			user.setUserHash(pword);
			user.setDateEntered(new DateTime().toDate());
			user.setDateModified(new DateTime().toDate());
			user.setInternalMailer("0");
			user.setCryptType("PHP5.3MD5");
			newUser = VtigerUsersDAO.addUser(user);
		}catch(Exception ex){
			log.error("Problem adding user to VtigerUsers table");
			log.error(ex.getMessage());
			retJson =  retJson + "\"no\",\"message\":\"Employee not added to database\"}]";
		}
		// add to user2role
		try{
		VtigerUser2role user2role = new VtigerUser2role();
		user2role.setUserid(newUser.getId());
		user2role.setRoleid(VtigerRoleDAO.findByRoleName(role).getRoleid());
		VtigerUser2roleDAO.addUser2Role(user2role);
		}catch(Exception ex){
			log.error("Problem adding employee's role");
			log.error(ex.getMessage());
			retJson =  retJson + "\"no\",\"message\":\"Employee's role not added to database\"}]";
		}
		// add to employee status
		try{
		TmdEmployeeStatus empStatus = new TmdEmployeeStatus();
		empStatus.setVtigerUsersId(newUser.getId());
		empStatus.setEmployeeStatus(1);
		TmdEmployeeStatus newEmpStatus = TmdEmployeeStatusDAO.addUserStatus(empStatus);
		log.debug("newEmpStatus: [{}]", newEmpStatus.getTmdEmployeeStatusUid());
		}catch(Exception ex){
			log.error("Problem adding employee's status");
			log.error(ex.getMessage());
			retJson =  retJson + "\"no\",\"message\":\"Employee's status not added to database\"}]";
		}
		// add employee to store
		try{
			TmdEmployeeToStore empToStore  = new TmdEmployeeToStore();
			empToStore.setVtigerUsersId(newUser.getId());
			empToStore.setTmdStoreUid(Integer.parseInt(store));
			TmdEmployeeToStore newEmp2Store = TmdEmployeeToStoreDAO.addUserToStore(empToStore);
			log.debug("newEmp2Store: [{}]", newEmp2Store.getTmdEmployeeToStoreUid());
			retJson = retJson + "\"yes\",\"name\":[";
			retJson = retJson + buildJson(store);
		}catch(Exception ex){
			log.error("Problem adding employee to store");
			log.error(ex.getMessage());
			retJson =  retJson + "\"no\",\"message\":\"Problem adding Employee to store\"}]";
		}
		return retJson;
	}
	
	private String buildJson(String store){
		String retJson = "";
		retJson = retJson + getEmployeeList(TmdEmployeeToStoreDAO.getEmployees(store));
		retJson = retJson.substring(0, retJson.length()-1);
		retJson = retJson + "],\"roles\":[";
		retJson = retJson + getRoleList(store);
		retJson = retJson.substring(0, retJson.length()-1);
		retJson = retJson + "],\"store\":\""+store+"\"}]";
		return retJson;
	}
	
	/**
	 * Make employee inactive
	 */
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public String makeEmployeeInactive(@FormParam("userid") String userid, @FormParam("status") String status){
		String retJson="[{\"changed\":\"yes\",\"message\":\"changed\",";
		boolean changed = true;
		int intUserid = Integer.parseInt(userid);
		int intStatus = Integer.parseInt(status);
		log.debug("uid: [{}] status: [{}]", intUserid, intStatus);
		try{
			changed = TmdEmployeeStatusDAO.changeEmployeeStatus(intUserid, intStatus);
			int storeid = TmdEmployeeToStoreDAO.findStoreByUserId(intUserid).getTmdStoreUid();
			retJson = retJson + "\"name\":[";
			retJson = retJson + buildJson(Integer.toString(storeid));
		}catch(Exception ex){
			log.error("error changing employee status to [{}]", status);
			retJson="[{\"changed\":\"no\",\"message\":\"notchanged\"}]";
		}
		return retJson;
	}
}
