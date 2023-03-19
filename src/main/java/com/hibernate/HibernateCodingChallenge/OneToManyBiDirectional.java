package com.hibernate.HibernateCodingChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.HibernateCodingChallenge.config.HibernateConfig;
import com.hibernate.HibernateCodingChallenge.entity.Address;
import com.hibernate.HibernateCodingChallenge.entity.Course;
import com.hibernate.HibernateCodingChallenge.entity.Student;

public class OneToManyBiDirectional {
	private static SessionFactory factory=HibernateConfig.getSessionFactory();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=factory.openSession(); 
		Transaction tx;
		Student student;
		Course course;
		  boolean flag=true;
		  while(flag) {
				System.out.println("choose from the details\n insertion data is hardcoded for easability\n"
						+ "1. Insert data into Student along with data in the courses.\n"
						+ "2. Remove a Course from the database without affecting students.\n"
						+ "3. exit.");
				
						Scanner sc=new Scanner(System.in);
						int choice=sc.nextInt();
						switch(choice) {
						case 1:
							List<Course> courses=new ArrayList<Course>();
							System.out.println("Insert data into Student along with data in the courses.");
							student=new Student("Manojna", "Software");
							student.setCourse(courses);
							course=new Course("JAVA", 100);
							course.setStudent(student);
							courses.add(course);
							course=new Course("Abhiram", 200);
							course.setStudent(student);
							courses.add(course);
							tx=session.beginTransaction();
							session.persist(student);
							tx.commit();
							  break;
						case 2:
							System.out.println("Below is the list of all courses\n"
									+ "enter the course id of the course that you want to delete which will\n"
									+ " not affect student");
							
							  List<Course> cours=session.createQuery("select a from Course a",Course.class).getResultList(); 
							  System.out.println(cours);
							  int id=sc.nextInt();
							  tx=session.beginTransaction();
							  course=session.get(Course.class, id);
							  session.remove(course); 
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
