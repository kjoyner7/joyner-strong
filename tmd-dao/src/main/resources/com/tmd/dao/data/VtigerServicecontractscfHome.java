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
 * Home object for domain model class VtigerServicecontractscf.
 * @see com.tmd.dao.data.VtigerServicecontractscf
 * @author Hibernate Tools
 */
public class VtigerServicecontractscfHome {

	private static final Log log = LogFactory.getLog(VtigerServicecontractscfHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerServicecontractscf transientInstance) {
		log.debug("persisting VtigerServicecontractscf instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerServicecontractscf instance) {
		log.debug("attaching dirty VtigerServicecontractscf instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerServicecontractscf instance) {
		log.debug("attaching clean VtigerServicecontractscf instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerServicecontractscf persistentInstance) {
		log.debug("deleting VtigerServicecontractscf instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerServicecontractscf merge(VtigerServicecontractscf detachedInstance) {
		log.debug("merging VtigerServicecontractscf instance");
		try {
			VtigerServicecontractscf result = (VtigerServicecontractscf) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerServicecontractscf findById(int id) {
		log.debug("getting VtigerServicecontractscf instance with id: " + id);
		try {
			VtigerServicecontractscf instance = (VtigerServicecontractscf) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerServicecontractscf", id);
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

	public List findByExample(VtigerServicecontractscf instance) {
		log.debug("finding VtigerServicecontractscf instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerServicecontractscf").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
