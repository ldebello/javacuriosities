package ar.com.javacuriosities.hibernate.basics.persistence;

import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class Persistence {

    public static void main(String[] args) {
        try {
            // Obtenemos una Session Factory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                System.out.println("***** Save Method *****");
                Product product = new Product();
                product.setId(Long.valueOf(1));
                product.setDescription("Product Save");

                /*
                 * El método save persiste el objeto en la session y le asigna un identificador,
                 * si salvamos otro objeto con el mismo identificador arroja una exception del NonUniqueObjectException.
                 *
                 * org.hibernate.NonUniqueObjectException: A different object with the same identifier value was already associated with the session
                 *
                 * Además el save deja el objeto asociado a la session por eso si ejecutamos un cambio en el objeto, este cambio sera tenido
                 * en cuenta para enviarse a la DB.
                 *
                 * Este método devuelve un objeto Serializable, devuelve el identificador generado
                 */
                Serializable identifier = session.save(product);

                System.out.println("Identifier: " + identifier);

                // product.setDescription("Product updated because dirty check");
                session.getTransaction().commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                System.out.println("***** Update Method *****");
                Product product = new Product();
                product.setId(Long.valueOf(1));
                product.setDescription("Product Update");

                /*
                 * El método update actualiza un objeto y lo asocia a la session, si el ID existe lo actualiza sino arroja exception.
                 *
                 * org.hibernate.StaleStateException: Batch update returned unexpected row count from update [0]; actual row count: 0; expected: 1
                 *
                 * Si antes se salvo o actualizo un objeto en esta session con el mismo identificador arroja la exception
                 *
                 * org.hibernate.NonUniqueObjectException: A different object with the same identifier value was already associated with the session
                 *
                 * Este método devuelve void
                 */
                session.update(product);

                /*
                 * El método flush sincroniza el estado In-Memory mantenido por la Session con la DB.
                 */
                // session.flush();

                // product.setDescription("Product Updated");
                session.getTransaction().commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                System.out.println("***** Persist Method *****");
                Product product = new Product();
                product.setId(Long.valueOf(2));
                product.setDescription("Product Save");

                /*
                 * El método persist es similar al save pero su semántica esta basado en la especificación JSR-220.
                 *
                 * Asocia un objeto con el contexto persistente.
                 *
                 * - Si el objeto estaba en estado Transient pasa a Persistent.
                 * - Si el objeto ya estaba en estado Persistent sigue en el mismo estado.
                 * - Si el objeto estaba en estado Detached obtendremos una exception.
                 *
                 */
                session.persist(product);

                session.getTransaction().commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                System.out.println("***** SaveOrUpdate Method *****");
                Product product = new Product();
                product.setDescription("Product SaveOrUpdate");

                /*
                 * El método saveOrUpdate ejecuta las siguientes acciones
                 * - Si el objeto ya es persistente en la sesión, no hace nada
                 * - Si otro objeto asociado con la sesión tiene el mismo identificador, arroja una exception
                 *      org.hibernate.NonUniqueObjectException: A different object with the same identifier value was already associated with the session
                 * - Si el objeto es nuevo lo persiste
                 * - En otro caso actualiza el objeto
                 *
                 * El objeto queda asociado a la session
                 *
                 * Este método devuelve void
                 */
                session.saveOrUpdate(product);

                // product.setDescription("Product SaveOrUpdate Updated");
                session.getTransaction().commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                System.out.println("***** Merge Method *****");
                Product product = new Product();
                product.setId(Long.valueOf(4));
                product.setDescription("Product Merge");

                /*
                 * El método merge ejecuta las siguientes acciones
                 * - Si existe una instancia persistente con el mismo identificador asociado con la sesión,
                 *      copia el estado del objeto dado en la instancia persistente
                 * - Si no existe ninguna instancia persistente asociada a la sesión actualmente,
                 *      intenta cargarla desde la base de datos, o crea una nueva.
                 * - La instancia persistente es retornada por el método
                 * - La instancia dada (O sea el parámetro) no se asocia a la sesión, permanece en estado detached.
                 */
                Product productFromDB = (Product) session.merge(product);

                // product.setDescription("Product Merge Updated");
                // productFromDB.setDescription("Product Merge Updated");
                session.getTransaction().commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
