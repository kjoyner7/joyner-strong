package com.tmd.dao.db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdProceduresHistory;

public class TmdProceduresHistoryDAO {
	private static final Logger log = LoggerFactory.getLogger(TmdProceduresHistoryDAO.class);
	public static final String version = "Version 1.0.0  -- 2014-02-11";
	
	/*
	 * Merges the active Procedure with the Database
	 * 
	 * @param newProcedure The new active procedure to add.
	 * 
	 * @return value The new active procedure object merged
	 */
	public static TmdProceduresHistory merge(TmdProceduresHistory newProcedure) {
		if (newProcedure == null) {
			throw new IllegalArgumentException("persistent object TmdActiveProcedures can't be null");
		}
		log.debug("Merging procedure history id [{}] ", newProcedure.getTmdProceduresHistoryUid());
		
		Session session = DAOHelper.openSession();
		TmdProceduresHistory proc = (TmdProceduresHistory) session.merge(newProcedure);
		log.debug("New Procedure [{}]", proc.getTmdProceduresHistoryUid());
		DAOHelper.closeSession(session);
		
		return proc;
	}
	
	/*
	 * deletes active procedure from table 
	 * 
	 * @param id Integer
	 * 
	 * @return value int
	 */

	@SuppressWarnings("unchecked")
	public static int deleteProcedureHistory(TmdProceduresHistory procedure) {
		log.debug("Deleting product from table by procedure id");
	/*	Session session = DAOHelper.openSession();

		Query query = session.createQuery("delete TmdProceduresHistory where tmdProceduresHistoryUid = :proceduresuid");
		query.setParameter("proceduresuid", procedure.getTmdActiveProceduresUid());
		int result = query.executeUpdate();

		DAOHelper.closeSession(session); */
		//return result;
		return 1;
	} 

}
