package org.jsp.userproductapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.userproductapp.dto.Product;
import org.jsp.userproductapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	EntityManager manager;
	
	public Product addProduct(Product product,int user_id) {
		User u=manager.find(User.class, user_id);
		if(u!=null) {
			u.getProducts().add(product);
			product.setUser(u);
			manager.persist(product);
			EntityTransaction transaction=manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}
	public List<Product> findProductByUserId(int user_id){
		Query q=manager.createQuery("select u.products from User u where u.id=?1");
		q.setParameter(1, user_id);
		return q.getResultList();
	}
	public List<Product> findProductByCategory(String category){
		Query q=manager.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, category);
		return q.getResultList();
	}

}
