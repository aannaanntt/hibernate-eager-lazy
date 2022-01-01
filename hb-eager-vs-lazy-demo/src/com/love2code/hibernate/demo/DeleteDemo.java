package com.love2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.love2code.hibernate.demo.entity.Instructor;
import com.love2code.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			// get inst by id
			int theId = 1;

			Instructor tempInstr = session.get(Instructor.class, theId);

			if (tempInstr == null) {
				System.out.println("NULL occured");
			} else {
				System.out.println("Deleting the instructor" + tempInstr);
				// will also delete details
				session.delete(tempInstr);
			}

			// delete the inst

			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

}
