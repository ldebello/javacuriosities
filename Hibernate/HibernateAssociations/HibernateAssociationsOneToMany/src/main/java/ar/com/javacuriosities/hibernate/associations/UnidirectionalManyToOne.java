package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.unidirectional.Comment;
import ar.com.javacuriosities.hibernate.model.unidirectional.Post;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UnidirectionalManyToOne {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Post post = new Post();
            post.setTitle("Root Post");

            Comment comment1 = new Comment();
            comment1.setComment("First Comment");

            Comment comment2 = new Comment();
            comment2.setComment("First Comment");

            comment1.setPost(post);
            comment2.setPost(post);

            session.save(post);

            session.save(comment1);
            session.save(comment2);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
