package com.tmd.dao.db;

import java.util.List;

import javax.naming.InitialContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tmd.dao.data.TmdProcedures;

public class TmdProceduresDAO {

			private static final Logger log = LoggerFactory.getLogger(TmdProceduresDAO.class);
			public static final String version = "Version 1.0.0 -- 2015-07-21";

			
			/**
			 * Returns List of Procedures based on organization and role id
			 * 
			 * @param organizationId int
			 * @param roleId String
			 * 
			 * @return object List<TmdProcedures
			 */
			@SuppressWarnings("unchecked")
			public static List<TmdProcedures> findByOrganizationId(int organizationId, String roleId){
				log.debug("Getting procedures with organization_uid of [{}] and role of [{}]", organizationId, roleId);
				Session session = DAOHelper.openSession();
				List<TmdProcedures> procedures = null;
				
				try{
					Query q = session.createQuery("from TmdProcedures where tmdOrganizationUid = :organization_id and vtigerRoleid = :role_id");
					q.setInteger("organization_id", organizationId);
					q.setString("role_id", roleId);
					procedures = q.list();
					log.debug("found [{}] procedures", procedures.size());
				}catch (QuerySyntaxException ex){
					log.debug("error getting organizations: [{}]", ex.getMessage());
				}finally{
					DAOHelper.closeSession(session);
				}
				return procedures; 
				
			}

			/*
			 * Merges the passed in Procedure with the Database
			 * 
			 * @param newProcedure The new procedure to add.
			 * 
			 * @return value The new procedure object merged
			 */
			public static TmdProcedures merge(TmdProcedures newProcedure) {
				if (newProcedure == null) {
					throw new IllegalArgumentException("persistent object TmdProcedures can't be null");
				}
				log.debug("Merging procedure id [{}] ", newProcedure.getTmdProceduresUid());
				
				Session session = DAOHelper.openSession();
				TmdProcedures proc = (TmdProcedures) session.merge(newProcedure);
				log.debug("New Procedure [{}]", proc.getTmdProceduresUid());
				DAOHelper.closeSession(session);
				
				return proc;
			}

			/*
			 * deletes item from procedures table by procedures id
			 * 
			 * @param id Integer
			 * 
			 * @return value CsProducts
			 */

			@SuppressWarnings("unchecked")
			public static int deleteById(int procedures_uid) {
				log.debug("Deleting procedure with id of [{}]", procedures_uid);
				Session session = DAOHelper.openSession();

				Query query = session.createQuery("delete TmdProcedures where tmdProceduresUid = :proceduresuid");
				query.setParameter("proceduresuid", procedures_uid);
				int result = query.executeUpdate();

				DAOHelper.closeSession(session);
				return result;
			}  
	

}
