package ar.com.javacuriosities.hibernate.basics.states;

import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class States {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Cuando queremos interactuar con la DB debemos obtener una Session
        Session session = sessionFactory.openSession();

        // Siempre que salvamos, modificamos, eliminamos debemos estar dentro de una transacción
        session.beginTransaction();

        /*
         * Creamos un objeto, este se encuentra en estado Transient.
         * Un objeto estará en estado Transient cuando acabe de ser creado en Java mediante el operador new.
         * Es decir cuando este recién fue creado por nosotros. Este estado tiene la característica de que hibernate no sabe nada de nuestro objeto.
         * Quizás el objeto ya este guardado en base de datos, o sea nuevo y tengamos que insertarlo.
         */
        Product product = new Product();
        product.setDescription("Product 1");
        product.setPrice(100.5);
        product.setDate(new Date());

        /*
         * Aca el objeto pasa a estado Persistent.
         * Un objeto esta en estado Persistent cuando ya esta guardado en la base de datos y además Hibernate también es consciente de ello.
         * Es importante notar la diferencia con el estado anterior en el que el objeto podía estar persistido pero Hibernate lo desconocía.
         * Hibernate en ese caso guarda el objeto en la cache interna que posee. También es importante destacar que para una misma fila de la base de datos solo puede haber un objeto en estado Persistent.
         */
        session.save(product);

        product.setPrice(90.5);

        // Finalizamos la transacción para persistir los datos
        session.getTransaction().commit();

        /*
         * Cuando cerramos la session los objetos pasan a estado Detached.
         * Este estado es similar al estado Transient solo que se produce cuando cerramos la sesion mediante Session.close() o
         * llamamos al método Session.evict(Object objeto) para el objeto que queremos pasar a este estado.
         * En ese caso Hibernate vuelve a olvidar en que estado se encontraban los objetos borrándolos de su cache interna.
         * También podemos usar Session.clear() y pasar todos los objetos a estado Detached o podemos usar Session.setReadOnly(object, true).
         * Los objetos en estado Detached tiene un valor para el campo ID pero no están sincronizados con la DB
         */
        session.close();

        session = sessionFactory.openSession();

        session.beginTransaction();

        product = (Product) session.load(Product.class, 1L);

        /*
         * Aquí el objeto pasa a estado Removed.
         * A este estado pasan los objetos que se han borrado de la base de datos mediante el método delete().
         */
        session.delete(product);

        session.getTransaction().commit();

        session.close();

        sessionFactory.close();
    }
}
