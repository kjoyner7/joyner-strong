package com.tmd.dao.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdActiveProcedures;
import com.tmd.dao.data.TmdEmployeeStatus;

public class TmdEmployeeStatusDAO {
	private static final Logger log = LoggerFactory.getLogger(TmdEmployeeStatusDAO.class);
	public static final String version = "Version 1.0.0 -- 2016-01-09";
	
	/**
	 * Change status of employee
	 * 
	 */
	public boolean changeEmployeeStatus(int userid, int status){
		boolean success = false;
		log.debug("change the status of employee: [{}]", userid);
		Session session = DAOHelper.openSession();
		TmdEmployeeStatus empStatus = new TmdEmployeeStatus();
		empStatus.setVtigerUsersId(userid);
		empStatus.setEmployeeStatus(status);
		
		try{
			TmdEmployeeStatus newStatus = (TmdEmployeeStatus) session.merge(empStatus);
			log.debug("New Procedure [{}]", newStatus.getTmdEmployeeStatusUid());
			if(newStatus != null){
				success = true;
			}
		}catch(Exception ex){
			log.error("Error merging the employee new status for employee [{}]", userid);
		}finally{
			DAOHelper.closeSession(session);
		}
		return success;
	}

}
