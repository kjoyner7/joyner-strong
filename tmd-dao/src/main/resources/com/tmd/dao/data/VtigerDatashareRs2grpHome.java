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
 * Home object for domain model class VtigerDatashareRs2grp.
 * @see com.tmd.dao.data.VtigerDatashareRs2grp
 * @author Hibernate Tools
 */
public class VtigerDatashareRs2grpHome {

	private static final Log log = LogFactory.getLog(VtigerDatashareRs2grpHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerDatashareRs2grp transientInstance) {
		log.debug("persisting VtigerDatashareRs2grp instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerDatashareRs2grp instance) {
		log.debug("attaching dirty VtigerDatashareRs2grp instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerDatashareRs2grp instance) {
		log.debug("attaching clean VtigerDatashareRs2grp instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerDatashareRs2grp persistentInstance) {
		log.debug("deleting VtigerDatashareRs2grp instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerDatashareRs2grp merge(VtigerDatashareRs2grp detachedInstance) {
		log.debug("merging VtigerDatashareRs2grp instance");
		try {
			VtigerDatashareRs2grp result = (VtigerDatashareRs2grp) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerDatashareRs2grp findById(int id) {
		log.debug("getting VtigerDatashareRs2grp instance with id: " + id);
		try {
			VtigerDatashareRs2grp instance = (VtigerDatashareRs2grp) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerDatashareRs2grp", id);
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

	public List findByExample(VtigerDatashareRs2grp instance) {
		log.debug("finding VtigerDatashareRs2grp instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.tmd.dao.data.VtigerDatashareRs2grp")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
