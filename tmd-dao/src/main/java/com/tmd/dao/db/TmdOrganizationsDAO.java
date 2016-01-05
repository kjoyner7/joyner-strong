package com.tmd.dao.db;

import java.util.List;

import javax.naming.InitialContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tmd.dao.data.TmdOrganizations;

public class TmdOrganizationsDAO {
		private static final Logger log = LoggerFactory.getLogger(TmdOrganizationsDAO.class);
		public static final String version = "Version 1.0.0 -- 2015-07-21";

		
		/**
		 * Returns Role object based on role id
		 * 
		 * @param roleId String
		 * 
		 * @return object VtigerRole
		 */
		@SuppressWarnings("unchecked")
		public static TmdOrganizations findByOrganizationId(int organizationId){
			log.debug("Getting organizations with organization_uid of [{}]", organizationId);
			Session session = DAOHelper.openSession();
			List<TmdOrganizations> organizations = null;
			TmdOrganizations organization = null;
			
			try{
				Query q = session.createQuery("from TmdOrganizations where tmdOrganizationUid = :organization_id");
				q.setInteger("organization_id", organizationId);
				organizations = q.list();
				if((organizations != null) && (!organizations.isEmpty())){
					log.debug("number of organizations found: [{}]", organizations.size());
					organization = organizations.get(0);
				}
			}catch (QuerySyntaxException ex){
				log.debug("error getting organizations: [{}]", ex.getMessage());
			}finally{
				DAOHelper.closeSession(session);
			}
			return organization; 
			
		}

}
