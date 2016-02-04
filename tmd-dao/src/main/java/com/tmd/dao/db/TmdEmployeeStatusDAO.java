package com.tmd.dao.db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdActiveProcedures;
import com.tmd.dao.data.TmdEmployeeStatus;
import com.tmd.dao.data.VtigerUsers;

public class TmdEmployeeStatusDAO {
	private static final Logger log = LoggerFactory.getLogger(TmdEmployeeStatusDAO.class);
	public static final String version = "Version 1.0.0 -- 2016-01-09";
	
	/**
	 * Change status of employee
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static boolean changeEmployeeStatus(int userid, int status){
		boolean success = false;
		log.debug("change the status of employee: [{}]", userid);
		Session session = DAOHelper.openSession();
		
		try{
			Query q = session.createQuery("UPDATE TmdEmployeeStatus set employeeStatus = :empStatus where vtigerUsersId = :uid");
			q.setInteger("empStatus", status);
			q.setInteger("uid", userid);
			int result = q.executeUpdate();
			if(result == 1){
				success = true;
			}
		}catch(Exception ex){
			log.error(ex.getMessage());
			log.error("Error changing the employee status for employee [{}]", userid);
		}finally{
			DAOHelper.closeSession(session);
		}
		return success;
	}
	
	/**
	 * Adds user to TmdEmployeeStatus table
	 * 
	 * @param TmdEmployeeStatus user
	 * 
	 * @return TmdEmployeeStatus object
	 */
	public static TmdEmployeeStatus addUserStatus(TmdEmployeeStatus user){
		if (user == null) {
			throw new IllegalArgumentException("persistent object TmdEmployeeStatus can't be null");
		}
		log.debug("Merging user [{}] ", user.getVtigerUsersId());
		
		Session session = DAOHelper.openSession();
		TmdEmployeeStatus newUsers = (TmdEmployeeStatus) session.merge(user);
		log.debug("New user status id [{}]", newUsers.getTmdEmployeeStatusUid());
		DAOHelper.closeSession(session);
		return newUsers;
		
	}
	
	/**
	 * get employee status by vtigeruserid
	 * 
	 * @param VtigerUserId userid
	 * 
	 * @return TmdEmployeeStats
	 */
	public static TmdEmployeeStatus findByUserId(int userid){
		log.debug("getting status for user: [{}]", userid);
		List<TmdEmployeeStatus> statuss = null;
		Session session = DAOHelper.openSession();
		try{
			Query q = session.createQuery("from TmdEmployeeStatus where vtigerUsersId = :user_id");
			q.setInteger("user_id", userid);
			statuss = q.list();
			log.debug("found [{}] procedures", statuss.size());
		}catch (QuerySyntaxException ex){
			log.debug("error getting organizations: [{}]", ex.getMessage());
		}finally{
			DAOHelper.closeSession(session);
		}
		return statuss.get(0); 
	}
}
