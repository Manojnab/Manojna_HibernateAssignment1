package com.hibernate.HibernateCodingChallenge;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.HibernateCodingChallenge.config.HibernateConfig;
import com.hibernate.HibernateCodingChallenge.entity.Address;
import com.hibernate.HibernateCodingChallenge.entity.Student;
import com.hibernate.HibernateCodingChallenge.entity.Teacher;

public class OneToOneUniDirectional {
	private static SessionFactory factory=HibernateConfig.getSessionFactory();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=factory.openSession(); 
		Transaction tx;
		Student student;
		  boolean flag=true;
		 while(flag) {
		System.out.println("choose from the details\n insertion data is hardcoded for easability\n"
				+ "1. Insert into student along with required address\n"
				+ "2. delete a student with cascading effect on address table\n"
				+ "3. exit");
		
				Scanner sc=new Scanner(System.in);
				int choice=sc.nextInt();
				switch(choice) {
				case 1:
					System.out.println("Inserting harcoded data address has homeno,city and country harcoded and\n"
							+ " student has name and technology harcoded");
					
					  Address address=new Address("banadakoppa","India"); 
					  student=new Student("Manojna", "JAVACORE"); student.setAddress(address);
					  tx=session.beginTransaction();
					  session.persist(student); tx.commit();
					  break;
				case 2:
					System.out.println("Below is the list of all the students\n"
							+ "enter the id of the student that u want to delete");
					List<Student> students=session.createQuery("select a from Student a",Student.class).getResultList();
					System.out.println(students);
					int id=sc.nextInt();
					tx=session.beginTransaction();
					student=session.get(Student.class, id); 
					session.remove(student);
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
