package com.ritik.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ritik.models.User;

@Repository
public class SignupRepository
{
	private Session session;
	private Transaction transaction;
	
	@Autowired
	public SignupRepository(SessionFactory factory)
	{
		session=factory.openSession();
		transaction=session.getTransaction();
	}

	public void saveUser(User user)
	{
		transaction.begin();
		session.save(user);
		transaction.commit();
	}

	public User findUserByEmail(String email)
	{
		transaction.begin();
		Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();
		transaction.commit();
		return user;
	}
	

}
