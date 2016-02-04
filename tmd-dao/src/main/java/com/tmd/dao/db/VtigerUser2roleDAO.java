package com.tmd.dao.db;

import java.util.List;

import javax.naming.InitialContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tmd.dao.data.VtigerUser2role;
import com.tmd.dao.data.VtigerUsers;

public class VtigerUser2roleDAO {
	private static final Logger log = LoggerFactory.getLogger(VtigerUser2roleDAO.class);
	public static final String version = "Version 1.0.0 -- 2015-07-9";

	/**
	 * Returns User to role object based on user id
	 * 
	 * @param userId int
	 * 
	 * @return object VtigerUser2role
	 */
	@SuppressWarnings("unchecked")
	public static VtigerUser2role findByUserId(int userId){
		log.debug("Getting user role with user id of [{}]", userId);
		Session session = DAOHelper.openSession();
		List<VtigerUser2role> users = null;
		VtigerUser2role user = null;
		
		try{
			Query q = session.createQuery("from VtigerUser2role where userid = :user_id");
			q.setInteger("user_id", userId);
			users = q.list();
			if((users != null) && (!users.isEmpty())){
				log.debug("number of users found: [{}]", users.size());
				user = users.get(0);
			}
		}catch (QuerySyntaxException ex){
			log.debug("error getting user: [{}]", ex.getMessage());
		}finally{
			DAOHelper.closeSession(session);
		}
		return user; 

	}
	
	/**
	 * Adds user to VtigerUser2Role table
	 * 
	 * @param VtigerUser2role userid
	 * 
	 * @return VtigerUsers object
	 */
	public static VtigerUser2role addUser2Role(VtigerUser2role userRole){
		if (userRole == null) {
			throw new IllegalArgumentException("persistent object useRole can't be null");
		}
		log.debug("Merging user [{}] ", userRole.getUserid());
		
		Session session = DAOHelper.openSession();
		VtigerUser2role newUsers = (VtigerUser2role) session.merge(userRole);
		log.debug("New user id [{}]", newUsers.getUserid());
		DAOHelper.closeSession(session);
		return newUsers;
		
	}
	

}
