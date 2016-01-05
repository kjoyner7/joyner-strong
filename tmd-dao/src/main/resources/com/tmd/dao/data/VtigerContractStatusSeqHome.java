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
 * Home object for domain model class VtigerContractStatusSeq.
 * @see com.tmd.dao.data.VtigerContractStatusSeq
 * @author Hibernate Tools
 */
public class VtigerContractStatusSeqHome {

	private static final Log log = LogFactory.getLog(VtigerContractStatusSeqHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerContractStatusSeq transientInstance) {
		log.debug("persisting VtigerContractStatusSeq instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerContractStatusSeq instance) {
		log.debug("attaching dirty VtigerContractStatusSeq instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerContractStatusSeq instance) {
		log.debug("attaching clean VtigerContractStatusSeq instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerContractStatusSeq persistentInstance) {
		log.debug("deleting VtigerContractStatusSeq instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerContractStatusSeq merge(VtigerContractStatusSeq detachedInstance) {
		log.debug("merging VtigerContractStatusSeq instance");
		try {
			VtigerContractStatusSeq result = (VtigerContractStatusSeq) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerContractStatusSeq findById(int id) {
		log.debug("getting VtigerContractStatusSeq instance with id: " + id);
		try {
			VtigerContractStatusSeq instance = (VtigerContractStatusSeq) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerContractStatusSeq", id);
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

	public List findByExample(VtigerContractStatusSeq instance) {
		log.debug("finding VtigerContractStatusSeq instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.tmd.dao.data.VtigerContractStatusSeq")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
