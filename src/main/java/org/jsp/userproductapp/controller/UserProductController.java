package org.jsp.userproductapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userproductapp.dao.ProductDao;
import org.jsp.userproductapp.dao.UserDao;
import org.jsp.userproductapp.dto.Product;
import org.jsp.userproductapp.dto.User;
import org.jsp.userproductapp.userconfig.UserConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class UserProductController {
	static Scanner sc=new Scanner(System.in);
	static UserDao udao;
	static ProductDao pdao;
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(UserConfig.class);
		udao=context.getBean(UserDao.class);
		pdao=context.getBean(ProductDao.class);
	}
	public static void main(String[] args) {
		System.out.println("1.Save User: ");
		System.out.println("2.Update User: ");
		System.out.println("3.Add Product: ");
		System.out.println("4.Find Products By User ID: ");
		System.out.println("5.Find Products By Category: ");
		System.out.println("6.Verify User By Phone And Password: ");
		System.out.println("7.Verify User By Email And Password: ");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:{
			saveUser();
			break;
			
		}
		case 2:{
			updateUser();
			break;
		}
		case 3:{
			addProduct();
			break;
		}
		case 4:{
			findProductByUserId();
			break;
		}
		case 5:{
			findProductByCategory();
			break;
		}
		case 6:{
		verifyUserByPhoneAndPassword();
			break;
		}
		case 7:{
			verifyUserByEmailAndPassword();
			break;
		}
		}
	}
	public static void saveUser() {
		System.out.println("Enter Name,Email,Phone And Password To Save User: ");
		String name=sc.next();
		String email=sc.next();
		long phone=sc.nextLong();
		String password=sc.next();
		User u=new User();
		u.setName(name);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);
		u=udao.saveUser(u);
		System.out.println("User Saved With Id : "+u.getId());
		
	}
	public static void updateUser() {
		System.out.println("Enter Your Id To Update User: ");
		int id=sc.nextInt();
		System.out.println("Enter New Name,Email,Phone And Password To Update User: ");
		String name=sc.next();
		String email=sc.next();
		long phone=sc.nextLong();
		String password=sc.next();
		User u=new User();
		u.setId(id);
		u.setName(name);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);
		u=udao.updateUser(u);
		System.out.println("User Updated Succesffully: ");
				
	}
	public static void addProduct() {
		System.out.println("Enter User Id To Add Product: ");
		int u_id=sc.nextInt();
		System.out.println("Enter Name,Brand,Description,Category And Price To Add Product: ");
		String name=sc.next();
		String brand=sc.next();
		String description=sc.next();
		String category=sc.next();
		double price=sc.nextDouble();
		Product p=new Product();
		p.setName(name);
		p.setBrand(brand);
		p.setDescription(description);
		p.setCategory(category);
		p.setPrice(price);
		p=pdao.addProduct(p, u_id);
		System.out.println("Product Added: ");
	}
    public static void findProductByUserId() {
    	System.out.println("Enter User Id To Find Product Details: ");
    	int u_id=sc.nextInt();
    	List<Product> product=pdao.findProductByUserId(u_id);
    	for(Product p:product) {
    		System.out.println(p);
    	}
    }
    public static void findProductByCategory() {
    	System.out.println("Enter Category To Find Product: ");
    	String category=sc.next();
         List<Product> product= pdao.findProductByCategory(category);
         for(Product p:product) {
        	 System.out.println(p);
         }
    }
    public static void verifyUserByPhoneAndPassword() {
    	System.out.println("Enter User Phone And Password To Verify User: ");
    	long phone=sc.nextLong();
    	String password=sc.next();
    	User u=new User();
    	u.setPhone(phone);
    	u.setPassword(password);
    	u=udao.verifyUser(phone, password);
    	if(u!=null) {
    		System.out.println(u);
    		
    	}else {
    		System.out.println("User Not Found: ");
    	}
    			
    }
    public static void verifyUserByEmailAndPassword() {
    	System.out.println("Enter User Email And Password To Verify User: ");
    	String email=sc.next();
    	String password=sc.next();
    	User u=new User();
    	u.setEmail(email);
    	u.setPassword(password);
    	u=udao.verifyUser(email, password);
    	if(u!=null) {
    		System.out.println(u);
    	}else {
    		System.out.println("User Not Found: ");
    	}
    	
    }
}

