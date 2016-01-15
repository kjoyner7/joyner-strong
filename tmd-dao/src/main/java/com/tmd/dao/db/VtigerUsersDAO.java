package com.tmd.dao.db;

import java.util.List;

import javax.naming.InitialContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdActiveProcedures;
import com.tmd.dao.data.VtigerUsers;

public class VtigerUsersDAO {
	private static final Logger log = LoggerFactory.getLogger(VtigerUsersDAO.class);
	public static final String version = "Version 1.0.0 -- 2015-07-9";
/*	private final static SessionFactory sessionFactory = getSessionFactory();

	protected static SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	} */
	
	/**
	 * 
	 * Returns User object based on login id
	 * 
	 * @param user_name String
	 * 
	 * @return object VtigerUsers
	 */
	@SuppressWarnings("unchecked")
	public static VtigerUsers findByLogin(String userName){
		log.debug("Getting user with login id of [{}]", userName);
		Session session = DAOHelper.openSession();
		List<VtigerUsers> users = null;
		VtigerUsers user = null;
		
		try{
			Query q = session.createQuery("from VtigerUsers where userName = :username");
			q.setString("username", userName);
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
	 * 
	 * Returns User object based on id
	 * 
	 * @param userId int
	 * 
	 * @return object VtigerUsers
	 */
	@SuppressWarnings("unchecked")
	public static VtigerUsers findById(int userId){
		log.debug("Getting user with id of [{}]", userId);
		Session session = DAOHelper.openSession();
		List<VtigerUsers> users = null;
		VtigerUsers user = null;
		
		try{
			Query q = session.createQuery("from VtigerUsers where id = :userid");
			q.setInteger("userid", userId);
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
	 * Adds user to VtigerUsers table
	 * 
	 * @param VtigerUsers user
	 * 
	 * @return VtigerUsers object
	 */
	public static VtigerUsers addUser(VtigerUsers user){
		if (user == null) {
			throw new IllegalArgumentException("persistent object VtigerUsers can't be null");
		}
		log.debug("Merging user [{}] ", user.getLastName());
		
		Session session = DAOHelper.openSession();
		VtigerUsers newUsers = (VtigerUsers) session.merge(user);
		log.debug("New user id [{}]", newUsers.getId());
		DAOHelper.closeSession(session);
		return newUsers;
		
	}

}
