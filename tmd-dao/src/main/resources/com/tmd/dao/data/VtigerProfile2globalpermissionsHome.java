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
 * Home object for domain model class VtigerProfile2globalpermissions.
 * @see com.tmd.dao.data.VtigerProfile2globalpermissions
 * @author Hibernate Tools
 */
public class VtigerProfile2globalpermissionsHome {

	private static final Log log = LogFactory.getLog(VtigerProfile2globalpermissionsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerProfile2globalpermissions transientInstance) {
		log.debug("persisting VtigerProfile2globalpermissions instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerProfile2globalpermissions instance) {
		log.debug("attaching dirty VtigerProfile2globalpermissions instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerProfile2globalpermissions instance) {
		log.debug("attaching clean VtigerProfile2globalpermissions instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerProfile2globalpermissions persistentInstance) {
		log.debug("deleting VtigerProfile2globalpermissions instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerProfile2globalpermissions merge(VtigerProfile2globalpermissions detachedInstance) {
		log.debug("merging VtigerProfile2globalpermissions instance");
		try {
			VtigerProfile2globalpermissions result = (VtigerProfile2globalpermissions) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerProfile2globalpermissions findById(com.tmd.dao.data.VtigerProfile2globalpermissionsId id) {
		log.debug("getting VtigerProfile2globalpermissions instance with id: " + id);
		try {
			VtigerProfile2globalpermissions instance = (VtigerProfile2globalpermissions) sessionFactory
					.getCurrentSession().get("com.tmd.dao.data.VtigerProfile2globalpermissions", id);
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

	public List findByExample(VtigerProfile2globalpermissions instance) {
		log.debug("finding VtigerProfile2globalpermissions instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerProfile2globalpermissions").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
