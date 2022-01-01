package com.love2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.love2code.hibernate.demo.entity.Course;
import com.love2code.hibernate.demo.entity.Instructor;
import com.love2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			// get the instructor from the db

			int theId = 2;
			Query<Instructor> ins=	session.createQuery("select i from Instructor i" + 
																"JOIN FETCH i.courses" + 
																		"where i.id = :theInstructorId",
																		Instructor.class);

			ins.setParameter("theInstructorId", theId);
			// create some course
			
			Instructor tempIns = ins.getSingleResult();
			System.out.println("Here is the instructor " + ins);
			System.out.println("Here is the instructor " + tempIns);

		
			// commit transaction
			session.getTransaction().commit();
			session.close();
			//call getter method when session is open
		
			System.out.println("\n\tHey Session is closed\n");
			

			System.out.println("Done!");

		} finally {
			factory.close();
			
		}

	}

}
