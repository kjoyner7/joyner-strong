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
 * Home object for domain model class VtigerWsUserauthtoken.
 * @see com.tmd.dao.data.VtigerWsUserauthtoken
 * @author Hibernate Tools
 */
public class VtigerWsUserauthtokenHome {

	private static final Log log = LogFactory.getLog(VtigerWsUserauthtokenHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerWsUserauthtoken transientInstance) {
		log.debug("persisting VtigerWsUserauthtoken instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerWsUserauthtoken instance) {
		log.debug("attaching dirty VtigerWsUserauthtoken instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerWsUserauthtoken instance) {
		log.debug("attaching clean VtigerWsUserauthtoken instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerWsUserauthtoken persistentInstance) {
		log.debug("deleting VtigerWsUserauthtoken instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerWsUserauthtoken merge(VtigerWsUserauthtoken detachedInstance) {
		log.debug("merging VtigerWsUserauthtoken instance");
		try {
			VtigerWsUserauthtoken result = (VtigerWsUserauthtoken) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerWsUserauthtoken findById(com.tmd.dao.data.VtigerWsUserauthtokenId id) {
		log.debug("getting VtigerWsUserauthtoken instance with id: " + id);
		try {
			VtigerWsUserauthtoken instance = (VtigerWsUserauthtoken) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerWsUserauthtoken", id);
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

	public List findByExample(VtigerWsUserauthtoken instance) {
		log.debug("finding VtigerWsUserauthtoken instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.tmd.dao.data.VtigerWsUserauthtoken")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
