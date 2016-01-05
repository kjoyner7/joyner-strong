package com.tmd.dao.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdEmployeeToOrganization;
import com.tmd.dao.data.TmdEmployeeToStore;

public class TmdEmployeeToStoreDAO {
	private static final Logger log = LoggerFactory.getLogger(TmdEmployeeToStoreDAO.class);
	public static final String version = "Version 1.0.0 -- 2015-12-19";
	
	/**
	 * get all the employees for an organization
	 * @param storeid String
	 * @return users List<TmdEmployeeToStore> 
	 */
	@SuppressWarnings("unchecked")
	public static List<TmdEmployeeToStore> getEmployees(String storeid){
		log.debug("finding employees for store [{}]", storeid);
		Session session = DAOHelper.openSession();
		List<TmdEmployeeToStore> employees = null;
		
		try{
			Query q = session.createQuery("from TmdEmployeeToStore where tmdStoreUid = :store_Id");
			q.setInteger("store_Id", Integer.parseInt(storeid));
			employees = q.list();
			log.debug("number of employees [{}]", employees.size());
		}catch (QuerySyntaxException ex){
			log.debug("error getting employees: [{}]", ex.getMessage());
		}finally{
			DAOHelper.closeSession(session);
		}
		
		return employees;		
	} 
	
	/**
	 * Returns Store object based on user id
	 * 
	 * @param userId int
	 * 
	 * @return object TmdEmployeeToStore
	 */
	@SuppressWarnings("unchecked")
	public static TmdEmployeeToStore findStoreByUserId(int userId){
		log.debug("Getting user2store with user id of [{}]", userId);
		Session session = DAOHelper.openSession();
		List<TmdEmployeeToStore> user2stores = null;
		TmdEmployeeToStore user2store = null;
		
		try{
			Query q = session.createQuery("from TmdEmployeeToStore where vtigerUsersId = :user_Id");
			q.setInteger("user_Id", userId);
			user2stores = q.list();
			if((user2stores != null) && (!user2stores.isEmpty())){
				log.debug("number of user2stores found: [{}]", user2stores.size());
				user2store = user2stores.get(0);
			}
		}catch (QuerySyntaxException ex){
			log.debug("error getting user2stores: [{}]", ex.getMessage());
		}finally{
			DAOHelper.closeSession(session);
		}
		return user2store; 
		
	}

}
