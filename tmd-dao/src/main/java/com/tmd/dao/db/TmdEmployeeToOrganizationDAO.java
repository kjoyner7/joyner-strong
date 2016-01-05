package com.tmd.dao.db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tmd.dao.data.TmdEmployeeToOrganization;

public class TmdEmployeeToOrganizationDAO {
		private static final Logger log = LoggerFactory.getLogger(TmdEmployeeToOrganizationDAO.class);
		public static final String version = "Version 1.0.0 -- 2015-07-22";

		
		/**
		 * Returns User to Organization object based on user id
		 * 
		 * @param userId int
		 * 
		 * @return object TmdEmployeeToOrganization
		 */
		@SuppressWarnings("unchecked")
		public static TmdEmployeeToOrganization findByUserId(int userId){
			log.debug("Getting user2organ with user id of [{}]", userId);
			Session session = DAOHelper.openSession();
			List<TmdEmployeeToOrganization> user2organs = null;
			TmdEmployeeToOrganization user2organ = null;
			
			try{
				Query q = session.createQuery("from TmdEmployeeToOrganization where vtigerUsersId = :user_Id");
				q.setInteger("user_Id", userId);
				user2organs = q.list();
				if((user2organs != null) && (!user2organs.isEmpty())){
					log.debug("number of user2organs found: [{}]", user2organs.size());
					user2organ = user2organs.get(0);
				}
			}catch (QuerySyntaxException ex){
				log.debug("error getting user2organs: [{}]", ex.getMessage());
			}finally{
				DAOHelper.closeSession(session);
			}
			return user2organ; 
			
		}
		
		/**
		 * get all the employees for an organization
		 * @param storeid String
		 * @return users List<TmdEmployeeToOrganization> 
		 */
	/*	public List<TmdEmployeeToOrganization> getEmployees(String storeid){
			
		} */


}
