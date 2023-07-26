package org.jsp.userproductapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.userproductapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	EntityManager manager;
	
	public User saveUser(User user) {
		manager.persist(user);
		EntityTransaction transaction=manager.getTransaction();
		transaction.begin();
		transaction.commit();
		return user;
		
	}
	public User updateUser(User user) {
		manager.merge(user);
		EntityTransaction transaction=manager.getTransaction();
		transaction.begin();
		transaction.commit();
		return user;
}
	public User verifyUser(long phone,String password) {
		Query q=manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		
	}
	public User verifyUser(String email,String password) {
		Query q=manager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
}
