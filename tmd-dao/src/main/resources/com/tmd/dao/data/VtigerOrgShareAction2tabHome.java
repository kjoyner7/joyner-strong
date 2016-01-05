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
 * Home object for domain model class VtigerOrgShareAction2tab.
 * @see com.tmd.dao.data.VtigerOrgShareAction2tab
 * @author Hibernate Tools
 */
public class VtigerOrgShareAction2tabHome {

	private static final Log log = LogFactory.getLog(VtigerOrgShareAction2tabHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerOrgShareAction2tab transientInstance) {
		log.debug("persisting VtigerOrgShareAction2tab instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerOrgShareAction2tab instance) {
		log.debug("attaching dirty VtigerOrgShareAction2tab instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerOrgShareAction2tab instance) {
		log.debug("attaching clean VtigerOrgShareAction2tab instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerOrgShareAction2tab persistentInstance) {
		log.debug("deleting VtigerOrgShareAction2tab instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerOrgShareAction2tab merge(VtigerOrgShareAction2tab detachedInstance) {
		log.debug("merging VtigerOrgShareAction2tab instance");
		try {
			VtigerOrgShareAction2tab result = (VtigerOrgShareAction2tab) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerOrgShareAction2tab findById(com.tmd.dao.data.VtigerOrgShareAction2tabId id) {
		log.debug("getting VtigerOrgShareAction2tab instance with id: " + id);
		try {
			VtigerOrgShareAction2tab instance = (VtigerOrgShareAction2tab) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerOrgShareAction2tab", id);
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

	public List findByExample(VtigerOrgShareAction2tab instance) {
		log.debug("finding VtigerOrgShareAction2tab instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerOrgShareAction2tab").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
