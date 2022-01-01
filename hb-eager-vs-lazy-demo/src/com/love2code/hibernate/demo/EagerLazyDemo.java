package com.love2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.love2code.hibernate.demo.entity.Course;
import com.love2code.hibernate.demo.entity.Instructor;
import com.love2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			// get the instructor from the db

			int theId = 2;
			Instructor tempInstr = session.load(Instructor.class, theId);

			// create some course

			System.out.println("Here is the instructor " + tempInstr);

			System.out.println("Here are the courses of the instructor " + tempInstr.getCourses());
			// commit transaction
			session.getTransaction().commit();
			session.close();
			//call getter method when session is open
		
			System.out.println("\n\tHey Session is closed\n");
			
			System.out.println("Here are the courses of the instructor " + tempInstr.getCourses());
			System.out.println("Done!");

		} finally {
			factory.close();
			
		}

	}

}
