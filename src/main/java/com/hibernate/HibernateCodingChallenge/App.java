package com.hibernate.HibernateCodingChallenge;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.HibernateCodingChallenge.config.HibernateConfig;
import com.hibernate.HibernateCodingChallenge.entity.Teacher;

/**
 * Hello world!
 *
 */
public class App {
	private static SessionFactory factory = HibernateConfig.getSessionFactory();

	public static void main(String[] args) {
		Session session = factory.openSession();
		boolean flag = true;
		while (flag) {
			System.out.println("Enter The Options\n" + "1. Show all teacher ids and name\n" + "2. Insert Teacher data\n"
					+ "3. update teacher data\n" + "4. delete teacher data\n" + "5. show details of perticulare id\n"
					+ "6. read all teachers");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			String email, fname, lname;
			int id;
			Teacher teacher;

			switch (choice) {
			case 1:
				System.out.println("below if the id of all the teachers with first names");
				getAllIdName();
				break;
			case 2:
				System.out.println("Enter the teacher data");
				System.out.println("Enter email id");
				email = sc.next();
				System.out.println("Enter First name");
				fname = sc.next();
				System.out.println("Enter last name");
				lname = sc.next();
				teacher = new Teacher(fname, lname, email);
				insertTeacher(teacher);
				break;
			case 3:
				System.out.println("update Teacher");
				System.out.println("Enter the id of the teacher to be updated");
				id = sc.nextInt();
				System.out.println("Enter email");
				email = sc.next();
				System.out.println("Enter fname");
				fname = sc.next();
				System.out.println("Enter lname");
				lname = sc.next();
				teacher = new Teacher(id, fname, lname, email);
				updateTeacher(teacher);
				break;
			case 4:
				System.out.println("Delete author");
				System.out.println("Enter the id to be deleted");
				id = sc.nextInt();
				teacher = new Teacher();
				teacher.setId(id);
				deleteTeacher(teacher);
				break;
			case 5:
				System.out.println("Enter the teacher id to get details");
				id = sc.nextInt();
				getTeachersById(id);
				break;
			case 6:
				System.out.println("Details of all the teachers");
				getAllTeachres();
				break;
			case 7:
				flag = false;
				break;

			}
		}
		session.close();
		System.out.println("Hello World!");
	}

	public static int insertTeacher(Teacher teacher) {
		// create a sesiion for that query
		// DML Commit is required
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(teacher);
		tx.commit();
		session.close();
		return teacher.getId();

	}

	public static Teacher updateTeacher(Teacher teacher) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Teacher updateTeacher = session.merge(teacher);
		tx.commit();
		session.close();
		return updateTeacher;
	}

	public static void deleteTeacher(Teacher teacher) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.remove(teacher);
		tx.commit();
		session.close();
	}

	public static void getAllIdName() {
		Session session = factory.openSession();
		List<Object[]> teachers = session.createQuery("select id,fName from Teacher", Object[].class).list();
		session.close();
		System.out.println("id fName");
		for (Object[] user : teachers) {
			System.out.println((Integer) user[0] + "  " + (String) user[1]);
			System.out.println();
		}
	}

	public static void getAllTeachres() {
		Session session = factory.openSession();
		List<Teacher> teachers = session.createQuery("select a from Teacher a", Teacher.class).getResultList();
		session.close();
		System.out.println(teachers);
	}

	public static void getTeachersById(int id) {
		Session session = factory.openSession();
		Teacher teacher = session.get(Teacher.class, id);
		session.close();
		System.out.println(teacher);
	}
}
