package com.tmd.dao.db;

import java.util.List;

import javax.naming.InitialContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tmd.dao.data.VtigerRole;

public class VtigerRoleDAO {
	private static final Logger log = LoggerFactory.getLogger(VtigerRoleDAO.class);
	public static final String version = "Version 1.0.0 -- 2015-07-21";

	
	/**
	 * Returns Role object based on role id
	 * 
	 * @param roleId String
	 * 
	 * @return object VtigerRole
	 */
	@SuppressWarnings("unchecked")
	public static VtigerRole findByRoleId(String roleId){
		log.debug("Getting roles with role id of [{}]", roleId);
		Session session = DAOHelper.openSession();
		List<VtigerRole> roles = null;
		VtigerRole role = null;
		
		try{
			Query q = session.createQuery("from VtigerRole where roleid = :role_id");
			q.setString("role_id", roleId);
			roles = q.list();
			if((roles != null) && (!roles.isEmpty())){
				log.debug("number of roles found: [{}]", roles.size());
				role = roles.get(0);
			}
		}catch (QuerySyntaxException ex){
			log.debug("error getting role: [{}]", ex.getMessage());
		}finally{
			DAOHelper.closeSession(session);
		}
		return role; 
		
	}

}
