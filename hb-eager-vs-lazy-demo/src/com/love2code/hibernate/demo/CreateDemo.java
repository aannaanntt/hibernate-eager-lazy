package com.love2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.love2code.hibernate.demo.entity.Instructor;
import com.love2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			/*// create the objects
			Instructor tempInst= 
					new Instructor("chad","Darby","darby@gmail.com");
			// associate the objects
			InstructorDetail tempInstDetail = 
					new InstructorDetail("http://youtube.com/buildjava","Luv Java");*/
			
			Instructor tempInst= 
					new Instructor("Maddy","Jhonson","Maddy@gmail.com");
			// associate the objects
			InstructorDetail tempInstDetail = 
					new InstructorDetail("http://youtube.com/buildjavasolutions","Luv  core Java");
			
			System.out.println("Adding object to instructor"+ tempInstDetail);
			tempInst.setInstructorDetail(tempInstDetail);
			session.beginTransaction();
			//Note this will also save instructoe detail
			//beacuse of cascade type is ALL
			System.out.println("Saving instructor and instructore detail " + tempInst);
			session.save(tempInst);

			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

}
