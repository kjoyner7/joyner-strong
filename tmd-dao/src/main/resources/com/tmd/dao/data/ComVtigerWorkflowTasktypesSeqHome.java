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
 * Home object for domain model class ComVtigerWorkflowTasktypesSeq.
 * @see com.tmd.dao.data.ComVtigerWorkflowTasktypesSeq
 * @author Hibernate Tools
 */
public class ComVtigerWorkflowTasktypesSeqHome {

	private static final Log log = LogFactory.getLog(ComVtigerWorkflowTasktypesSeqHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(ComVtigerWorkflowTasktypesSeq transientInstance) {
		log.debug("persisting ComVtigerWorkflowTasktypesSeq instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ComVtigerWorkflowTasktypesSeq instance) {
		log.debug("attaching dirty ComVtigerWorkflowTasktypesSeq instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ComVtigerWorkflowTasktypesSeq instance) {
		log.debug("attaching clean ComVtigerWorkflowTasktypesSeq instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ComVtigerWorkflowTasktypesSeq persistentInstance) {
		log.debug("deleting ComVtigerWorkflowTasktypesSeq instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ComVtigerWorkflowTasktypesSeq merge(ComVtigerWorkflowTasktypesSeq detachedInstance) {
		log.debug("merging ComVtigerWorkflowTasktypesSeq instance");
		try {
			ComVtigerWorkflowTasktypesSeq result = (ComVtigerWorkflowTasktypesSeq) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ComVtigerWorkflowTasktypesSeq findById(int id) {
		log.debug("getting ComVtigerWorkflowTasktypesSeq instance with id: " + id);
		try {
			ComVtigerWorkflowTasktypesSeq instance = (ComVtigerWorkflowTasktypesSeq) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.ComVtigerWorkflowTasktypesSeq", id);
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

	public List findByExample(ComVtigerWorkflowTasktypesSeq instance) {
		log.debug("finding ComVtigerWorkflowTasktypesSeq instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.ComVtigerWorkflowTasktypesSeq").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
