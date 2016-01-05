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
 * Home object for domain model class TmdUserToOrganization.
 * @see com.tmd.dao.data.TmdUserToOrganization
 * @author Hibernate Tools
 */
public class TmdUserToOrganizationHome {

	private static final Log log = LogFactory.getLog(TmdUserToOrganizationHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TmdUserToOrganization transientInstance) {
		log.debug("persisting TmdUserToOrganization instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TmdUserToOrganization instance) {
		log.debug("attaching dirty TmdUserToOrganization instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TmdUserToOrganization instance) {
		log.debug("attaching clean TmdUserToOrganization instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TmdUserToOrganization persistentInstance) {
		log.debug("deleting TmdUserToOrganization instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TmdUserToOrganization merge(TmdUserToOrganization detachedInstance) {
		log.debug("merging TmdUserToOrganization instance");
		try {
			TmdUserToOrganization result = (TmdUserToOrganization) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TmdUserToOrganization findById(java.lang.Integer id) {
		log.debug("getting TmdUserToOrganization instance with id: " + id);
		try {
			TmdUserToOrganization instance = (TmdUserToOrganization) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.TmdUserToOrganization", id);
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

	public List findByExample(TmdUserToOrganization instance) {
		log.debug("finding TmdUserToOrganization instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.tmd.dao.data.TmdUserToOrganization")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
