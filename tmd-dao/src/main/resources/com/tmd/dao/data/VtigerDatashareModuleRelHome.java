package com.tmd.dao.data;
// Generated Jul 9, 2015 2:35:38 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class VtigerDatashareModuleRel.
 * @see com.tmd.dao.data.VtigerDatashareModuleRel
 * @author Hibernate Tools
 */
public class VtigerDatashareModuleRelHome {

	private static final Log log = LogFactory.getLog(VtigerDatashareModuleRelHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerDatashareModuleRel transientInstance) {
		log.debug("persisting VtigerDatashareModuleRel instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerDatashareModuleRel instance) {
		log.debug("attaching dirty VtigerDatashareModuleRel instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerDatashareModuleRel instance) {
		log.debug("attaching clean VtigerDatashareModuleRel instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerDatashareModuleRel persistentInstance) {
		log.debug("deleting VtigerDatashareModuleRel instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerDatashareModuleRel merge(VtigerDatashareModuleRel detachedInstance) {
		log.debug("merging VtigerDatashareModuleRel instance");
		try {
			VtigerDatashareModuleRel result = (VtigerDatashareModuleRel) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerDatashareModuleRel findById(int id) {
		log.debug("getting VtigerDatashareModuleRel instance with id: " + id);
		try {
			VtigerDatashareModuleRel instance = (VtigerDatashareModuleRel) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerDatashareModuleRel", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VtigerDatashareModuleRel instance) {
		log.debug("finding VtigerDatashareModuleRel instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerDatashareModuleRel").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
