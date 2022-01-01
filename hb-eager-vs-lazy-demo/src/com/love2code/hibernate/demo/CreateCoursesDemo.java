package com.love2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.love2code.hibernate.demo.entity.Course;
import com.love2code.hibernate.demo.entity.Instructor;
import com.love2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			//get the instructor from the db
			
			int theId =2;
			Instructor tempInstr = session.load(Instructor.class, theId);
			
			//create some course
			
			Course tempCourse = new Course("Air Guitar - The Ultimate Guide");			//add courses to the instructor
			Course tempCourse1 = new Course("Ultimate Pinball Master");
			//save the course
			
			tempInstr.add(tempCourse);
			tempInstr.add(tempCourse1);
			
			session.save(tempCourse);
			session.save(tempCourse1);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");

		} finally {
			factory.close();
			session.close();
		}

	}

}
