package com.tmd.smarttapoutrest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.db.VtigerRoleDAO;
import com.tmd.dao.db.VtigerUser2roleDAO;
import com.tmd.dao.db.VtigerUsersDAO;
import com.tmd.lib.UserTasks;
import com.tmd.dao.db.TmdActiveProceduresDAO;
import com.tmd.dao.db.TmdEndOfShiftDAO;
import com.tmd.dao.db.TmdOrganizationsDAO;
import com.tmd.dao.db.TmdProceduresDAO;
import com.tmd.dao.db.TmdProceduresHistoryDAO;
import com.tmd.dao.db.TmdStoreDAO;
import com.tmd.dao.db.TmdEmployeeToOrganizationDAO;
import com.tmd.dao.db.TmdEmployeeToStoreDAO;
import com.tmd.dao.data.TmdActiveProcedures;
import com.tmd.dao.data.TmdEndOfShift;
import com.tmd.dao.data.TmdOrganizations;
import com.tmd.dao.data.TmdProcedures;
import com.tmd.dao.data.TmdProceduresHistory;
import com.tmd.dao.data.TmdStore;
import com.tmd.dao.data.TmdEmployeeToOrganization;
import com.tmd.dao.data.TmdEmployeeToStore;
import com.tmd.dao.data.VtigerRole;
import com.tmd.dao.data.VtigerUsers;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import com.google.gson.*;
	

@Path("/tasks")

public class ProceduresList {
	private static final Logger log = LoggerFactory.getLogger(ProceduresList.class);
	public static final String version = "Version 1.0.0 -- 2015-07-22";
	
	private VtigerUsers user = null; 
	private VtigerRole role = null;
	private TmdEmployeeToOrganization u2o = null;
	TmdOrganizations organization = null; 
	
	public ProceduresList(){
		
	}
	
	@GET
	@Path("/{username}")
	@Produces("text/html")
	public String getProcedureList(@PathParam("username") String userName){
		String returnHtml = "";
		this.user = VtigerUsersDAO.findByLogin(userName);
		this.role = VtigerRoleDAO.findByRoleId(VtigerUser2roleDAO.findByUserId(user.getId()).getRoleid());
		this.u2o = TmdEmployeeToOrganizationDAO.findByUserId(user.getId());
		this.organization = TmdOrganizationsDAO.findByOrganizationId(u2o.getTmdOrganizationUid());
		List<TmdActiveProcedures> existingTasks = TmdActiveProceduresDAO.findAllByUsersUid(user.getId());
		
		returnHtml = returnHtml + "<img src=\"images/"+organization.getLogoName()+"\"/><br>"+organization.getOrganizationName();
		returnHtml = returnHtml + "<h1>"+user.getFirstName()+" "+user.getLastName()+": "+role.getRolename()+"</h1>";
		returnHtml = returnHtml + "<form id=\"frmTasks\" action=\"javascript:doUpdate()\" method=\"POST\"><input type=\"hidden\" name=\"user\" id=\"user\" value=\""+user.getId()+"\"/><table>";
		if(existingTasks != null && existingTasks.size() > 0){
			for(TmdActiveProcedures procedure : existingTasks){
				if(procedure.getCompleted() == 1){
					returnHtml = returnHtml + "<tr><td><input type=\"checkbox\" name=\"completedTasks\" id=\"completedTasks\" value=\""+procedure.getTmdActiveProceduresUid()+"\" checked>"+procedure.getProcedures()+"</input></td></tr>";
				}else{				
					returnHtml = returnHtml + "<tr><td><input type=\"checkbox\" name=\"completedTasks\" id=\"completedTasks\" value=\""+procedure.getTmdActiveProceduresUid()+"\" >"+procedure.getProcedures()+"</input></td></tr>";
				}
			}
		}else{
			List<TmdProcedures> procedures = TmdProceduresDAO.findByOrganizationId(organization.getTmdOrganizationUid(), role.getRoleid());
		// load all the procedures for display and enter in active_procedures table
			for(TmdProcedures procedure : procedures){
				TmdActiveProcedures activeProcedures = new TmdActiveProcedures();
				activeProcedures.setVtigerRoleid(role.getRoleid());
				activeProcedures.setVtigerUsersId(user.getId());
				activeProcedures.setTmdOrganizationUid(organization.getTmdOrganizationUid());
				activeProcedures.setProcedures(procedure.getProcedures());
				activeProcedures.setRepeatInterval(procedure.getRepeatInterval());
				activeProcedures.setCompleted(0);
				activeProcedures.setRepeatInterval(0);
				TmdActiveProcedures newActive = TmdActiveProceduresDAO.merge(activeProcedures);
				returnHtml = returnHtml + "<tr><td><input type=\"checkbox\" name=\"completedTasks\" id=\"completedTasks\" value=\""+newActive.getTmdActiveProceduresUid()+"\">"+procedure.getProcedures()+"</input></td></tr>";
			}
		}
		returnHtml = returnHtml + "</table><button id=\"btnUpdate\" >Update</button></form><button id=\"btnFinish\">End Of Shift</button>";
		returnHtml = returnHtml + "<script type=\"text/javascript\">"
		+ "$('#btnFinish').click(function(){"
		+ "$.ajax({"
		+ "url: \"rest/tasks/shiftend/\"+$('#user').val(),"
		+ "type: \"POST\","
		+ "success: function(msg){"
		+	"$('#mainContent').html(msg[0].message);"
		+	"},"
		+ "error:function(jqXHR, textStatus, errorThrown) {"
		       //     alert(jqXHR);
		       //     alert(textStatus); // this comes back as "error"
		+       	    "alert(errorThrown);"
		+				"alert(jqXHR.responseText);"
		+				"alert(\"There was a problem the list from the server.\\nPlease try again later or contact your support.\");"	                   
		+      	"}"
		+ "});" 	
		+ "});</script>";  
	
		return returnHtml;
	}
	/**
	 * Android method
	 * @param userName
	 * @return
	 */
	@GET
	@Path("/android/{username}")
	@Produces("application/json")
	public String getProcedureListAndroid(@PathParam("username") String userName){
		//String returnHtml = "";
		String returnJson = "[{\"found\":";
		this.user = VtigerUsersDAO.findByLogin(userName);
		this.role = VtigerRoleDAO.findByRoleId(VtigerUser2roleDAO.findByUserId(user.getId()).getRoleid());
		this.u2o = TmdEmployeeToOrganizationDAO.findByUserId(user.getId());
		this.organization = TmdOrganizationsDAO.findByOrganizationId(u2o.getTmdOrganizationUid());
		TmdEmployeeToStore store = TmdEmployeeToStoreDAO.findStoreByUserId(user.getId());
		log.debug("store: [{}}", store.getTmdStoreUid());
		List<TmdActiveProcedures> existingTasks = TmdActiveProceduresDAO.findAllByUsersUid(user.getId());
		log.debug("existingTasks size: [{}]", existingTasks.size());
		if(existingTasks != null && existingTasks.size() > 0){
			int times = 0;
			returnJson = returnJson + "\"yes\",\"icon\":\"http://10.0.2.2/tomcat/smarttapoutrest-0.0.1/images/"+organization.getLogoName()+"\",";
			returnJson = returnJson + "\"name\":\""+user.getFirstName()+" "+user.getLastName()+"\",\"store\":\""+store.getTmdStoreUid()+"\",\"userid\":\""+user.getId()+"\",\"role\":\""+ role.getRolename()+"\",\"checkbox\":[";
			for(TmdActiveProcedures procedure : existingTasks){
				if(procedure.getCompleted() == 1){
					returnJson = returnJson + "{\"box\":\""+procedure.getTmdActiveProceduresUid()+"\", \"procedure\":\""+procedure.getProcedures()+"\", \"checked\":\"yes\"},";
				}else{				
					returnJson = returnJson + "{\"box\":\""+procedure.getTmdActiveProceduresUid()+"\", \"procedure\":\""+procedure.getProcedures()+"\", \"checked\":\"no\"},";
				}
			}
		}else{
			List<TmdProcedures> procedures = TmdProceduresDAO.findByOrganizationId(organization.getTmdOrganizationUid(), role.getRoleid());
			//returnJson = returnJson + ",\"checkbox\":[";
		// load all the procedures for display and enter in active_procedures table
			log.debug("procedures: [{}]", procedures.size());
			if(procedures != null && procedures.size() > 0){
				returnJson = returnJson + "\"yes\",\"icon\":\"http://10.0.2.2/tomcat/smarttapoutrest-0.0.1/images/"+organization.getLogoName()+"\",";
				returnJson = returnJson + "\"name\":\""+user.getFirstName()+" "+user.getLastName()+"\",\"userid\":\""+user.getId()+"\",\"role\":\""+ role.getRolename()+"\",\"checkbox\":[";
				for(TmdProcedures procedure : procedures){
					TmdActiveProcedures activeProcedures = new TmdActiveProcedures();
					activeProcedures.setVtigerRoleid(role.getRoleid());
					activeProcedures.setVtigerUsersId(user.getId());
					activeProcedures.setTmdOrganizationUid(organization.getTmdOrganizationUid());
					activeProcedures.setProcedures(procedure.getProcedures());
					activeProcedures.setRepeatInterval(procedure.getRepeatInterval());
					activeProcedures.setCompleted(0);
					activeProcedures.setRepeatInterval(0);
					activeProcedures.setTmdStoreUid(procedure.getTmdStoreUid());
					activeProcedures.setTmdTasksUid(procedure.getTmdTasksUid());
					TmdActiveProcedures newActive = TmdActiveProceduresDAO.merge(activeProcedures);
					returnJson = returnJson + "{\"box\":\""+newActive.getTmdActiveProceduresUid()+"\", \"procedure\":\""+procedure.getProcedures()+"\", \"checked\":\"no\"},"; 
				}
			}else{
				returnJson = returnJson + "\"no\",\"icon\":\"http://10.0.2.2/tomcat/smarttapoutrest-0.0.1/images/"+organization.getLogoName()+"\",\"checkbox\":[{\"box\":\"none\"},";
			}
		}
		
	/*	
		returnHtml = returnHtml + "</table><button id=\"btnUpdate\" >Update</button></form><button id=\"btnFinish\">End Of Shift</button>";
		returnHtml = returnHtml + "<script type=\"text/javascript\">"
		+ "$('#btnFinish').click(function(){"
		+ "$.ajax({"
		+ "url: \"rest/tasks/shiftend/\"+$('#user').val(),"
		+ "type: \"POST\","
		+ "success: function(msg){"
		+	"$('#mainContent').html(msg[0].message);"
		+	"},"
		+ "error:function(jqXHR, textStatus, errorThrown) {"
		       //     alert(jqXHR);
		       //     alert(textStatus); // this comes back as "error"
		+       	    "alert(errorThrown);"
		+				"alert(jqXHR.responseText);"
		+				"alert(\"There was a problem the list from the server.\\nPlease try again later or contact your support.\");"	                   
		+      	"}"
		+ "});" 	
		+ "});</script>";  */
	
		//return returnHtml;
		// close json
		returnJson = returnJson.substring(0, returnJson.length()-1);
		returnJson = returnJson + "]}]";
		return returnJson;
	}
	
	@POST
	@Path("/updateTasks")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public String doUpdate(@FormParam("completedTasks") List<String> completedTasks, @FormParam("user") String user){
		String msg="[{\"found\":\"yes\",\"message\":\"updated\"}]";
		// get all complete than do comparison so don't change complete time of one already completed.
		List<Integer> intTasks = TmdActiveProceduresDAO.findAllCompleteByUsersUid(Integer.parseInt(user));
		log.debug("returned int: [{}]", intTasks);
		// clear completed flag if not set by checkbox
		for(Integer intTask : intTasks){
			if(!completedTasks.contains(Integer.toString(intTask))){
				TmdActiveProcedures unTask = TmdActiveProceduresDAO.findByProceduresUid(intTask);
				unTask.setCompleted(0);
				unTask.setCompletedTime(null);
				TmdActiveProceduresDAO.merge(unTask);
			}
		} 
		// set completed flag based on the checkbox
		for(String comp : completedTasks){
			log.debug("tsask: [{}]", comp);
			if(!intTasks.contains(Integer.parseInt(comp))){
				TmdActiveProcedures task = TmdActiveProceduresDAO.findByProceduresUid(Integer.parseInt(comp));
				task.setCompleted(1);
				task.setCompletedTime(new DateTime().toDate());
				TmdActiveProceduresDAO.merge(task);
			}
		}
		log.debug("in updateTasks completeTasks size: [{}]", completedTasks.size());
		log.debug("user [{}]", user);
		return msg;
	} 
	
	//@GET
	@POST
	@Path("/android/updateTasks")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/json")
	public String doUpdateAndroid(@FormParam("user") String user, @FormParam("completedTasks") String completedTasks){
	String retJson = "[{\"found\":";
		String firstRound = completedTasks.substring(completedTasks.indexOf("[")+1);
		String secondRound = firstRound.substring(0, firstRound.indexOf("]"));
		String[] ct = secondRound.split(",");
		log.debug("ct length: [{}]", ct.length);
		List<Integer> checkedList = new ArrayList<Integer>();
		for(String test1 : ct){
			String test = test1.trim();
			checkedList.add(Integer.parseInt(test));
		}
	//	log.debug("ct: [{}]", ct.toString().toString());
	
		// get all complete than do comparison so don't change complete time of one already completed.
		log.debug("user: [{}]", user);
		List<Integer> intTasks = TmdActiveProceduresDAO.findAllCompleteByUsersUid(Integer.parseInt(user));
		log.debug("android/updateTasks returned int: [{}]", intTasks);
		// clear completed flag if not set by checkbox
		for(Integer intTask : intTasks){
			//if(!completedTasks.contains(Integer.toString(intTask))){
			if(!checkedList.contains(intTask)){
			//for(String test1 : ct){
			//	String test = test1.trim();
			//	log.debug("testing [{}] compared to intTask [{}]", test, Integer.toString(intTask));
			//	if(test.equals(Integer.toString(intTask))){
			//	if(intTasks.contains(test)){
					log.debug("inside equals with [{}]", intTask);
					TmdActiveProcedures unTask = TmdActiveProceduresDAO.findByProceduresUid(intTask);
					unTask.setCompleted(0);
					unTask.setCompletedTime(null);
					TmdActiveProceduresDAO.merge(unTask);
				//}
			}
		}  
		// set completed flag based on the checkbox
	//	log.debug("completedTasks size: [{}]", completedTasks.size());
		log.debug("competedTask: [{}]", completedTasks);
		for(String comp1 : ct){
			String comp = comp1.trim();
			log.debug("comp: [{}]", comp);
			if(!intTasks.contains(Integer.parseInt(comp))){
				TmdActiveProcedures task = TmdActiveProceduresDAO.findByProceduresUid(Integer.parseInt(comp));
				task.setCompleted(1);
				task.setCompletedTime(new DateTime().toDate());
				TmdActiveProceduresDAO.merge(task);
			}
		}  
	//	log.debug("in android/updateTasks completeTasks size: [{}]", completedTasks.size());  
		retJson = retJson + "\"yes\"}]";	
		log.debug("retJson: [{}]", retJson);
		return retJson;		
	}  
	 
	@POST
	@Path("/shiftend/{userid}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public String postForm(@PathParam("userid") String userid, @FormParam("completedTasks") String completedTasks) {
		log.debug("in shiftend completedTasks: [{}]", completedTasks);
		String msg="[{\"found\":\"yes\",\"message\":\"Shift Tasks Finished\"}]";
		List<Integer> completed = getCompletedTasks(completedTasks.substring(completedTasks.indexOf("[")+1));
		log.debug("completed size: [{}]", completed.size());
		log.debug("in shiftend with user [{}]", userid);
		List<TmdActiveProcedures> actTasks = TmdActiveProceduresDAO.findAllByUsersUid(Integer.parseInt(userid));
		TmdEndOfShift history = null;
		for(TmdActiveProcedures actTask : actTasks){
			if(completed.contains(actTask.getTmdActiveProceduresUid())){
				if(actTask.getCompleted() == 0){
					actTask.setCompleted(1);
					actTask.setCompletedTime(new DateTime().toDate());
				}
			}
			history = new TmdEndOfShift();
			history.setCompleted(actTask.getCompleted());
			history.setCompletedTime(actTask.getCompletedTime());
			history.setProcedures(actTask.getProcedures());
			history.setRepeatInterval(actTask.getRepeatInterval());
			history.setStartTime(actTask.getStartTime());
			history.setTmdActiveProceduresUid(actTask.getTmdActiveProceduresUid());
			history.setTmdOrganizationUid(actTask.getTmdOrganizationUid());
			history.setVtigerRoleid(actTask.getVtigerRoleid());
			history.setVtigerUsersId(actTask.getVtigerUsersId());
			history.setTmdStoreUid(actTask.getTmdStoreUid());
			history.setTmdTasksUid(actTask.getTmdTasksUid());
			try{
				TmdEndOfShiftDAO.merge(history);
				TmdActiveProceduresDAO.deleteActiveProcedure(actTask);
			}catch(Exception ex){
				log.error("Error moving record from tmd_active_procedures id [{}] to tmd_procedures_history", actTask.getTmdActiveProceduresUid());
				msg="[{\"found\":\"yes\",\"message\":\"Problem Saving Records\"}]";
			}
			
		}
		return msg;
	}
	
	private List<Integer> getCompletedTasks(String completedTasks){
		String firstRound = completedTasks.substring(completedTasks.indexOf("[")+1);
		String secondRound = firstRound.substring(0, firstRound.indexOf("]"));
		String[] ct = secondRound.split(",");
		log.debug("ct length: [{}]", ct.length);
		List<Integer> checkedList = new ArrayList<Integer>();
		for(String test1 : ct){
			String test = test1.trim();
			checkedList.add(Integer.parseInt(test));
		}
		return checkedList;
	}

}
