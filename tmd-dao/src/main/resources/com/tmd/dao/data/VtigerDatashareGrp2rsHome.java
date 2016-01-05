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
 * Home object for domain model class VtigerDatashareGrp2rs.
 * @see com.tmd.dao.data.VtigerDatashareGrp2rs
 * @author Hibernate Tools
 */
public class VtigerDatashareGrp2rsHome {

	private static final Log log = LogFactory.getLog(VtigerDatashareGrp2rsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerDatashareGrp2rs transientInstance) {
		log.debug("persisting VtigerDatashareGrp2rs instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerDatashareGrp2rs instance) {
		log.debug("attaching dirty VtigerDatashareGrp2rs instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerDatashareGrp2rs instance) {
		log.debug("attaching clean VtigerDatashareGrp2rs instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerDatashareGrp2rs persistentInstance) {
		log.debug("deleting VtigerDatashareGrp2rs instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerDatashareGrp2rs merge(VtigerDatashareGrp2rs detachedInstance) {
		log.debug("merging VtigerDatashareGrp2rs instance");
		try {
			VtigerDatashareGrp2rs result = (VtigerDatashareGrp2rs) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerDatashareGrp2rs findById(int id) {
		log.debug("getting VtigerDatashareGrp2rs instance with id: " + id);
		try {
			VtigerDatashareGrp2rs instance = (VtigerDatashareGrp2rs) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerDatashareGrp2rs", id);
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

	public List findByExample(VtigerDatashareGrp2rs instance) {
		log.debug("finding VtigerDatashareGrp2rs instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.tmd.dao.data.VtigerDatashareGrp2rs")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
