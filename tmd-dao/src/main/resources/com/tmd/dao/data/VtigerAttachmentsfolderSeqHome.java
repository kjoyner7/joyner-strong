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
 * Home object for domain model class VtigerAttachmentsfolderSeq.
 * @see com.tmd.dao.data.VtigerAttachmentsfolderSeq
 * @author Hibernate Tools
 */
public class VtigerAttachmentsfolderSeqHome {

	private static final Log log = LogFactory.getLog(VtigerAttachmentsfolderSeqHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerAttachmentsfolderSeq transientInstance) {
		log.debug("persisting VtigerAttachmentsfolderSeq instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerAttachmentsfolderSeq instance) {
		log.debug("attaching dirty VtigerAttachmentsfolderSeq instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerAttachmentsfolderSeq instance) {
		log.debug("attaching clean VtigerAttachmentsfolderSeq instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerAttachmentsfolderSeq persistentInstance) {
		log.debug("deleting VtigerAttachmentsfolderSeq instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerAttachmentsfolderSeq merge(VtigerAttachmentsfolderSeq detachedInstance) {
		log.debug("merging VtigerAttachmentsfolderSeq instance");
		try {
			VtigerAttachmentsfolderSeq result = (VtigerAttachmentsfolderSeq) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerAttachmentsfolderSeq findById(int id) {
		log.debug("getting VtigerAttachmentsfolderSeq instance with id: " + id);
		try {
			VtigerAttachmentsfolderSeq instance = (VtigerAttachmentsfolderSeq) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerAttachmentsfolderSeq", id);
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

	public List findByExample(VtigerAttachmentsfolderSeq instance) {
		log.debug("finding VtigerAttachmentsfolderSeq instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerAttachmentsfolderSeq").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
