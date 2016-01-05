package com.tmd.dao.db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmd.dao.data.TmdStore;

public class TmdStoreDAO {
	private static final Logger log = LoggerFactory.getLogger(TmdStoreDAO.class);
	public static final String version = "Version 1.0.0 -- 2015-12-22";
	
	
	/**
	 * Returns User to Organization object based on user id
	 * 
	 * @param userId int
	 * 
	 * @return object VtigerRole
	 */
	@SuppressWarnings("unchecked")
	public static TmdStore findByStoreId(int storeId){
		log.debug("Getting store for id of [{}]", storeId);
		Session session = DAOHelper.openSession();
		List<TmdStore> stores = null;
		TmdStore store = null;
		
		try{
			Query q = session.createQuery("from TmdStore where tmdStoreUid = :store_Id");
			q.setInteger("store_Id", storeId);
			stores = q.list();
			if((stores != null) && (!stores.isEmpty())){
				log.debug("number of user2organs found: [{}]", stores.size());
				store = stores.get(0);
			}
		}catch (QuerySyntaxException ex){
			log.debug("error getting store for user: [{}]", ex.getMessage());
		}finally{
			DAOHelper.closeSession(session);
		}
		return store; 
		
	}

}
