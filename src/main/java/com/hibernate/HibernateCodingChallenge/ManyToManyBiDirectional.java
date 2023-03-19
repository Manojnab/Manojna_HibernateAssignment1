package com.hibernate.HibernateCodingChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.HibernateCodingChallenge.config.HibernateConfig;
import com.hibernate.HibernateCodingChallenge.entity.Course;
import com.hibernate.HibernateCodingChallenge.entity.Employee;
import com.hibernate.HibernateCodingChallenge.entity.Project;
import com.hibernate.HibernateCodingChallenge.entity.Student;

public class ManyToManyBiDirectional {
	private static SessionFactory factory = HibernateConfig.getSessionFactory();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx;
		Project project;
		Employee employee;
		int id;
		boolean flag = true;
		while (flag) {
			System.out.println("choose from the details\n insertion data is hardcoded for easability\n"
					+ "1. Insert data into the Employee along with the details of the project assigned to that employee.\n"
					+ "2. Fetch all employee names for a given project Id\n"
					+ "3. Fetch all project names for a given employee Id\n" + "4. exit.");

			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				List<Employee> employees = new ArrayList<Employee>();
				List<Project> projects = new ArrayList<Project>();
				System.out.println("Insert data into Employee along with data in the Projects details.");
				Employee employee1 = new Employee("Manojna", "dataEngineer");
				employee1.setProjects(projects);
				employees.add(employee1);
				employee = new Employee("Abhiram", "dataEngineer");
				employee.setProjects(projects);
				employees.add(employee);
				project = new Project("starts");
				project.setEmployees(employees);
				projects.add(project);
				project = new Project("planet7");
				project.setEmployees(employees);
				projects.add(project);

				tx = session.beginTransaction();
				session.persist(employee1);
				session.persist(employee);
				tx.commit();
				break;
			case 2:
				System.out.println("Below is the list of all projects\n"
						+ "enter the project id of the project to see the employees\n"
						+ " working under that project\n");
				List<Project> proj = session.createQuery("select a from Project a", Project.class).getResultList();
				System.out.println(proj);
				id = sc.nextInt();

				project = session.get(Project.class, id);
				System.out.println(project.getEmployees());

				break;
			case 3:
				System.out.println("Below is the list of all Employee\n"
						+ "enter the Employee id of the Employee to see the Projects\n"
						+ "that he/she is working on\n");
				List<Employee> emp = session.createQuery("select a from Employee a", Employee.class).getResultList();
				System.out.println(emp);
				id = sc.nextInt();

				employee = session.get(Employee.class, id);
				System.out.println(employee.getProjects());

				break;
			case 4:
				flag = false;
				break;

			}

		}
		session.close();

	}

}
