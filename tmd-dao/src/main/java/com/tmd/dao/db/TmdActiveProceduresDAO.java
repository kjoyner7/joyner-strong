package com.tmd.dao.db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdActiveProcedures;

public class TmdActiveProceduresDAO {

		private static final Logger log = LoggerFactory.getLogger(TmdActiveProceduresDAO.class);
		public static final String version = "Version 1.0.0  -- 2014-02-11";
		
		/*
		 * Merges the active Procedure with the Database
		 * 
		 * @param newProcedure The new active procedure to add.
		 * 
		 * @return value The new active procedure object merged
		 */
		public static TmdActiveProcedures merge(TmdActiveProcedures newProcedure) {
			if (newProcedure == null) {
				throw new IllegalArgumentException("persistent object TmdActiveProcedures can't be null");
			}
			log.debug("Merging active procedure id [{}] ", newProcedure.getTmdActiveProceduresUid());
			
			Session session = DAOHelper.openSession();
			TmdActiveProcedures proc = (TmdActiveProcedures) session.merge(newProcedure);
			log.debug("New Procedure [{}]", proc.getTmdActiveProceduresUid());
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
		public static int deleteActiveProcedure(TmdActiveProcedures activeProcedure) {
			log.debug("Deleting procedure id [{}] from tmd_active_procedures table", activeProcedure.getTmdActiveProceduresUid());
			Session session = DAOHelper.openSession();

			Query query = session.createQuery("delete TmdActiveProcedures where tmdActiveProceduresUid = :activeproceduresuid");
			query.setParameter("activeproceduresuid", activeProcedure.getTmdActiveProceduresUid());
			int result = query.executeUpdate();

			DAOHelper.closeSession(session);
			return result;
		} 


		/*
		 * Finds all Procedures based on the vtiger_users_id
		 * 
		 * @param id Integer
		 * 
		 * @return value TmdActiveProcedures
		 */

		@SuppressWarnings("unchecked")
		public static List<TmdActiveProcedures> findAllByUsersUid(int usersUid) {
			log.debug("Getting Product Info");
			Session session = DAOHelper.openSession();
			List<TmdActiveProcedures> tasks = null;

			try {
				Query q = session.createQuery("from TmdActiveProcedures t where t.vtigerUsersId = :usersUid");
				q.setInteger("usersUid", usersUid);
				tasks = q.list();
				log.debug("Number of procedures " + tasks.size());
			} catch (QuerySyntaxException ex) {
				DAOHelper.closeSession(session);
				throw new QuerySyntaxException(ex.getMessage());
			}

			DAOHelper.closeSession(session);

			return tasks;
		}  
		
		/*
		 * Finds all Completed Procedures based on the vtiger_users_id
		 * 
		 * @param id Integer
		 * 
		 * @return value TmdActiveProcedures
		 */

		@SuppressWarnings("unchecked")
		public static List<Integer> findAllCompleteByUsersUid(int usersUid) {
			log.debug("Getting Product Info");
			Session session = DAOHelper.openSession();
			//List<TmdActiveProcedures> tasks = null;
			List<Integer> tasks = null;

			try {
				Query q = session.createQuery("select tmdActiveProceduresUid from TmdActiveProcedures t where t.vtigerUsersId = :usersUid and completed = 1");
				q.setInteger("usersUid", usersUid);
				tasks = q.list();
				log.debug("Number of procedures " + tasks.size());
				log.debug("list is [{}]", tasks);
			} catch (QuerySyntaxException ex) {
				DAOHelper.closeSession(session);
				throw new QuerySyntaxException(ex.getMessage());
			}

			DAOHelper.closeSession(session);

			return tasks;
		}  
		
		/*
		 * Finds a Procedure based on the procedures id
		 * 
		 * @param id Integer
		 * 
		 * @return value TmdActiveProcedures
		 */

		@SuppressWarnings("unchecked")
		public static TmdActiveProcedures findByProceduresUid(int proceduresUid) {
			log.debug("Getting procedure_uid: [{}}", proceduresUid);
			Session session = DAOHelper.openSession();
			List<TmdActiveProcedures> tasks = null;
			TmdActiveProcedures task = null;

			try {
				Query q = session.createQuery("from TmdActiveProcedures t where t.tmdActiveProceduresUid = :proceduresuid");
				q.setInteger("proceduresuid", proceduresUid);
				tasks = q.list();
				if ((tasks != null) && (!tasks.isEmpty())) {
					log.debug("Number of tasks is [{}]", tasks.size());
					task = tasks.get(0);
				}
			} catch (QuerySyntaxException ex) {
				DAOHelper.closeSession(session);
				throw new QuerySyntaxException(ex.getMessage());
			}

			DAOHelper.closeSession(session);
			return task;
		}

}
