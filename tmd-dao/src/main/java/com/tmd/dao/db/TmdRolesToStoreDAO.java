package com.tmd.dao.db;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdRolesToStore;

public class TmdRolesToStoreDAO {
	private static final Logger log = LoggerFactory.getLogger(TmdRolesToStoreDAO.class);
	public static final String version = "Version 1.0.0 -- 2016-01-09";

	/**
	 * get all the roles for a store
	 * @param storeid String
	 * @return roles List<TmdRolesToStore> 
	 */
	@SuppressWarnings("unchecked")
	public static List<TmdRolesToStore> getRolesForStore(String storeid){
		log.debug("finding roles for store [{}]", storeid);
		Session session = DAOHelper.openSession();
		List<TmdRolesToStore> roles = null;
		
		try{
			Query q = session.createQuery("from TmdRolesToStore where tmdStoreUid = :store_Id");
			q.setInteger("store_Id", Integer.parseInt(storeid));
			roles = q.list();
			log.debug("number of employees [{}]", roles.size());
		}catch (QuerySyntaxException ex){
			log.debug("error getting employees: [{}]", ex.getMessage());
		}finally{
			DAOHelper.closeSession(session);
		}
		
		return roles;		
	} 
}
