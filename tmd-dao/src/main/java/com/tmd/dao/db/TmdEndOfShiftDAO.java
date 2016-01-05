package com.tmd.dao.db;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdEndOfShift;

public class TmdEndOfShiftDAO {
	private static final Logger log = LoggerFactory.getLogger(TmdProceduresHistoryDAO.class);
	public static final String version = "Version 1.0.0  -- 2014-02-11";
	
	/*
	 * Merges the active Procedure with the Database
	 * 
	 * @param newProcedure The new active procedure to add.
	 * 
	 * @return value The new active procedure object merged
	 */
	public static TmdEndOfShift merge(TmdEndOfShift newProcedure) {
		if (newProcedure == null) {
			throw new IllegalArgumentException("persistent object TmdEndOfShift can't be null");
		}
		log.debug("Merging procedure id [{}] to end of shift table ", newProcedure.getTmdActiveProceduresUid());
		
		Session session = DAOHelper.openSession();
		TmdEndOfShift proc = (TmdEndOfShift) session.merge(newProcedure);
		log.debug("New Procedure [{}]", proc.getTmdEndOfShiftUid());
		DAOHelper.closeSession(session);
		
		return proc;
	}

}
