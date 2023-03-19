package com.hibernate.HibernateCodingChallenge;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.HibernateCodingChallenge.config.HibernateConfig;
import com.hibernate.HibernateCodingChallenge.entity.Address;
import com.hibernate.HibernateCodingChallenge.entity.Student;

public class OneToOneBiDirectional {

	private static SessionFactory factory=HibernateConfig.getSessionFactory();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=factory.openSession(); 
		Transaction tx;
		Student student;
		Address address;
		  boolean flag=true;
		 while(flag) {
		System.out.println("choose from the details\n insertion data is hardcoded for easability\n"
				+ "1. fetch student details with the given address\n"
				+ "2. Remove an Address Record from the database along with the cascaded effect on Students.\n"
				+ "3. exit");
		
				Scanner sc=new Scanner(System.in);
				int choice=sc.nextInt();
				switch(choice) {
				case 1:
					System.out.println("fetch student details with the given address");
					System.out.println("List of address available");
					List<Address> adress=session.createQuery("select a from Address a",Address.class).getResultList();
					System.out.println(adress);
					System.out.println("Enter the house no of address to fetch the student details associated with it");
					String homeNo=sc.next();
					/*
					 * List<Student>
					 * students=session.createQuery("from Student where homeNo=10",Student.class)
					 * .getResultList(); System.out.println(students);
					 */
					/*
					 * Address address=new Address("10","banadakoppa","India"); student=new
					 * Student("Manojna", "JAVACORE"); student.setAddress(address);
					 * tx=session.beginTransaction(); session.persist(student); tx.commit();
					 */
					address=session.get(Address.class, homeNo); 
					System.out.println("Student id is "+address.getStudent().getSid());
					System.out.println("Student name is "+address.getStudent().getName());
					System.out.println("Student technology is "+address.getStudent().getTechnology());
					System.out.println("Student city is "+address.getCity());
					System.out.println("Student country is "+address.getCountry());
					  break;
				case 2:
					System.out.println("Below is the list of all address\n"
							+ "enter the homeNo of the home that you want to delete which will\n"
							+ " delete student associated with it");
					
					  List<Address> Address=session.createQuery("select a from Address a",Address.class).getResultList(); 
					  System.out.println(Address);
					  String id=sc.next();
					  tx=session.beginTransaction();
					  address=session.get(Address.class, id);
					  session.remove(address); 
					  session.getTransaction().commit();
					 
					  break;
				case 3:
					flag=false;
					break;
					 
				}
		
		 }
		 session.close();
		

	}

}
