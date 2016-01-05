package com.tmd.lib;

import com.tmd.dao.db.TmdOrganizationsDAO;
import com.tmd.dao.db.TmdStoreDAO;
import com.tmd.dao.db.TmdEmployeeToOrganizationDAO;
import com.tmd.dao.db.TmdEmployeeToStoreDAO;
import com.tmd.dao.db.VtigerRoleDAO;
import com.tmd.dao.db.VtigerUser2roleDAO;
import com.tmd.dao.db.VtigerUsersDAO;
import com.tmd.dao.data.TmdEmployeeToStore;
import com.tmd.dao.data.TmdOrganizations;
import com.tmd.dao.data.TmdStore;
import com.tmd.dao.data.VtigerRole;
import com.tmd.dao.data.VtigerUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 * Class to deal with user object. Verify username and password are in vtiger_users table
 * and that they are valid.
 * 
 * @author Kent Joyner  07/14/2015
 */

public class Users {
	private static final Logger log = LoggerFactory.getLogger(Users.class);
	public static final String version = "Version 1.0.0 -- 2015-07-10";
	
	public Users(){
		
	}
	
	/*
	 * Check for valid user in vtiger_users table
	 * 
	 * @param request HttpServletRequest object
	 * @return message String in json form
	 */
	
	public String checkForUser(HttpServletRequest request){
		String userName = request.getParameter("username");
		String inputPassword = request.getParameter("password");
		log.debug("userName is [{}]", userName);
		String msg = "Good";
		VtigerUsers user = VtigerUsersDAO.findByLogin(userName);
		if(user == null){
			msg = "[{\"found\":\"no\",\"message\":\"User not found.\\nPlease ensure you have been added.\"}]";
			return msg;
		}
		log.debug("password from db: [{}}", user.getUserPassword());
	//	String inputPassword = getMD5(password);
		
		log.debug("inputPassword: [{}]", inputPassword);
		log.debug("db user hash: [{}]", user.getUserHash());
		log.debug("inputPassword = dbUserHash: [{}]", inputPassword.equals(user.getUserHash()));
		if(inputPassword.equals(user.getUserHash())){
			msg = "[{\"found\":\"yes\",\"message\":\""+user.getFirstName()+"\"}]";
			HttpSession session = request.getSession();
			log.debug("session: [{}]", session.getId());
			log.debug("Session Created: [{}]", new Date(session.getCreationTime()) );
			session.setAttribute("userId", user.getUserName());
			log.debug("userId: [{}}", session.getAttribute("userId"));
		}else{
			msg = "[{\"found\":\"no\",\"message\":\"Incorrect Password. Please try again.\"}]";
		}
		return msg;
	}
	
	/*
	 * Check for valid user in vtiger_users table
	 * 
	 * @param request HttpServletRequest username, password
	 * @return message String in json form
	 */
	
	public String checkForUser(String username, String password){
		log.debug("userName is [{}]", username);
		String msg = "Good";
		VtigerUsers user = VtigerUsersDAO.findByLogin(username);
		if(user == null){
			msg = "[{\"found\":\"no\",\"message\":\"User not found.\\nPlease ensure you have been added.\"}]";
			return msg;
		}
		log.debug("password from db: [{}}", user.getUserPassword());
		String inputPassword = password;
		log.debug("inputPassword: [{}]", inputPassword);
		log.debug("db user hash: [{}]", user.getUserHash());
		log.debug("inputPassword = dbUserHash: [{}]", inputPassword.equals(user.getUserHash()));
		log.debug("userid: [{}]", user.getId());
		if(inputPassword.equals(user.getUserHash())){
			VtigerRole role = VtigerRoleDAO.findByRoleId(VtigerUser2roleDAO.findByUserId(user.getId()).getRoleid());
			TmdOrganizations organization = TmdOrganizationsDAO.findByOrganizationId(TmdEmployeeToOrganizationDAO.findByUserId(user.getId()).getTmdOrganizationUid());
			TmdEmployeeToStore store = TmdEmployeeToStoreDAO.findStoreByUserId(user.getId());
			msg = "[{\"found\":\"yes\",\"message\":\""+user.getFirstName()+"\",\"role\":\""+role.getRolename()+"\",\"store\":\""+store.getTmdStoreUid()  +"\",\"icon\":\"http://10.0.2.2/tomcat/smarttapoutrest-0.0.1/images/"+organization.getLogoName()+"\"}]";
		}else{
			msg = "[{\"found\":\"no\",\"message\":\"Incorrect Password. Please try again.\"}]";
		}
		return msg;
	}
	
	/*
	 * creates hash code from password passed in 
	 * 
	 * @param input String submitted password
	 * 
	 * @return passwordHash String 
	 */
	public String getMD5(String input) 
	{
	    try
	    {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(input.getBytes());

	      byte byteData[] = md.digest();

	      //convert the byte to hex format method 1
	      StringBuffer sb = new StringBuffer();
	      for (int i = 0; i < byteData.length; i++)
	      {
	        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100,
	                                   16).substring(1));
	      }
	      return sb.toString();
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	      throw new RuntimeException(e);
	    }
	}

}

