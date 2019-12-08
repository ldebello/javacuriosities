package ar.com.javacuriosities.hibernate.utils;

import ar.com.javacuriosities.hibernate.model.Brand;
import ar.com.javacuriosities.hibernate.model.Product;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class QueriesUtils {

    public static void nativeQuery() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("Native Query");
            String nativeQueryAsString = "SELECT * FROM products";
            NativeQuery nativeQuery = session.createSQLQuery(nativeQueryAsString);
            List objects = nativeQuery.list();

            for (Object object : objects) {
                Object[] data = (Object[]) object;
                System.out.println(data[0]);
                System.out.println(data[1]);
                System.out.println(data[2]);
                System.out.println("*************************");
            }
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public static void nativeQueryWithScalarValues() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("Native Query with scalar values (Data types from ResultSetMetadata)");
            String nativeQueryAsString = "SELECT id_product, description FROM products";
            NativeQuery nativeQuery = session.createSQLQuery(nativeQueryAsString);
            List objects = nativeQuery.list();

            for (Object object : objects) {
                Object[] data = (Object[]) object;
                System.out.println(data[0]);
                System.out.println(data[1]);
                System.out.println("*************************");
            }

            System.out.println("Native Query with scalar values and custom data types (We can only use predefined types)");
            nativeQueryAsString = "SELECT * FROM products";
            nativeQuery = session.createSQLQuery(nativeQueryAsString).addScalar("id_product", StandardBasicTypes.LONG).addScalar("description", StandardBasicTypes.STRING);
            objects = nativeQuery.list();

            for (Object object : objects) {
                Object[] data = (Object[]) object;
                System.out.println(data[0]);
                System.out.println(data[1]);
                System.out.println("*************************");
            }

            System.out.println("Native Query with transformers (To List)");
            nativeQueryAsString = "SELECT * FROM products";
            nativeQuery = session.createSQLQuery(nativeQueryAsString);
            // El método setResultTransformer esta deprecado pero no hay ninguna alternativa.
            nativeQuery.setResultTransformer(Transformers.TO_LIST);

            objects = nativeQuery.list();

            for (Object object : objects) {
                List<Object> list = (List<Object>) object;
                System.out.println(list.get(0));
                System.out.println(list.get(1));
                System.out.println(list.get(2));
                System.out.println("*************************");
            }

            System.out.println("Native Query with transformers (Alias To Entity Map)");
            nativeQueryAsString = "SELECT * FROM products";
            nativeQuery = session.createSQLQuery(nativeQueryAsString);
            nativeQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            objects = nativeQuery.list();

            for (Object object : objects) {
                Map<String, Object> map = (Map<String, Object>) object;
                System.out.println(map.get("id_product"));
                System.out.println(map.get("description"));
                System.out.println(map.get("price"));
                System.out.println("*************************");
            }

            System.out.println("Native Query with transformers (Alias To Bean)");
            nativeQueryAsString = "SELECT id_product AS idProduct, description FROM products";
            nativeQuery = session.createSQLQuery(nativeQueryAsString).addScalar("idProduct", StandardBasicTypes.LONG).addScalar("description", StandardBasicTypes.STRING);
            nativeQuery.setResultTransformer(Transformers.aliasToBean(ProductBean.class));

            objects = nativeQuery.list();

            for (Object object : objects) {
                ProductBean bean = (ProductBean) object;
                System.out.println(bean.getIdProduct());
                System.out.println(bean.getDescription());
                System.out.println("*************************");
            }

            System.out.println("Native Query with scalar values and AVG function");
            nativeQueryAsString = "SELECT AVG(price) AS result FROM products";
            Double averagePrice = (Double) session.createSQLQuery(nativeQueryAsString).addScalar("result", StandardBasicTypes.DOUBLE).uniqueResult();

            System.out.println("Average price: " + averagePrice);
            System.out.println("*************************");

            System.out.println("Native Query with scalar values and SUM function");
            nativeQueryAsString = "SELECT SUM(price) AS result FROM products";
            Double sum = (Double) session.createSQLQuery(nativeQueryAsString).addScalar("result", StandardBasicTypes.DOUBLE).uniqueResult();

            System.out.println("Sum price: " + sum);
            System.out.println("*************************");

            System.out.println("Native Query with scalar values and COUNT function");
            nativeQueryAsString = "SELECT COUNT(*) AS result FROM products";
            Integer count = (Integer) session.createSQLQuery(nativeQueryAsString).addScalar("result", StandardBasicTypes.INTEGER).uniqueResult();

            System.out.println("Count produces: " + count);
            System.out.println("*************************");
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public static void nativeQueryWithEntities() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("Native Query with entities");
            String nativeQueryAsString = "SELECT * FROM products";
            NativeQuery nativeQuery = session.createSQLQuery(nativeQueryAsString).addEntity(Product.class);
            List objects = nativeQuery.list();

            for (Object object : objects) {
                Product product = (Product) object;
                System.out.println(product.getId());
                System.out.println(product.getDescription());
                System.out.println(product.getPrice());
                System.out.println("*************************");
            }

            System.out.println("Native Query with multiples entities");
            nativeQueryAsString = "SELECT {p.*}, {b.*} FROM products p INNER JOIN brands_products bp INNER JOIN brands b WHERE p.id_product = bp.products_id_product AND b.id_brand = bp.brands_id_brand";
            nativeQuery = session.createSQLQuery(nativeQueryAsString).addEntity("p", Product.class).addEntity("b", Brand.class);
            objects = nativeQuery.list();

            for (Object object : objects) {
                Object[] data = (Object[]) object;
                Product product = (Product) data[0];
                Brand brand = (Brand) data[1];

                System.out.println(product.getId());
                System.out.println(product.getDescription());
                System.out.println(product.getPrice());

                System.out.println(brand.getId());
                System.out.println(brand.getName());
                System.out.println("*************************");
            }

            System.out.println("Native Query with SQL Result Set Mappings");
            nativeQueryAsString = "SELECT id_product AS my_id, description AS my_description, price AS my_price, creation_date AS my_creation_date FROM products";
            nativeQuery = session.createSQLQuery(nativeQueryAsString).setResultSetMapping(Product.ROW_MAPPER_ALL_COLUMNS);
            objects = nativeQuery.list();

            for (Object object : objects) {
                Product product = (Product) object;
                System.out.println(product.getId());
                System.out.println(product.getDescription());
                System.out.println(product.getPrice());
                System.out.println("*************************");
            }

            System.out.println("Named Native Query");
            objects = session.getNamedQuery(Product.NATIVE_QUERY_SELECT_ALL_ROWS).list();

            for (Object object : objects) {
                Product product = (Product) object;
                System.out.println(product.getId());
                System.out.println(product.getDescription());
                System.out.println(product.getPrice());
                System.out.println("*************************");
            }

            System.out.println("Native query with entities and filter by name");
            nativeQueryAsString = "SELECT * FROM products WHERE id_product = :id_product";
            objects = session.createSQLQuery(nativeQueryAsString).addEntity(Product.class).setParameter("id_product", 1L).list();

            for (Object object : objects) {
                Product product = (Product) object;
                System.out.println(product.getId());
                System.out.println(product.getDescription());
                System.out.println(product.getPrice());
                System.out.println("*************************");
            }

            System.out.println("Native query with entities and filter by position");
            nativeQueryAsString = "SELECT * FROM products WHERE id_product = ?";
            objects = session.createSQLQuery(nativeQueryAsString).addEntity(Product.class).setParameter(1, 1L).list();

            for (Object object : objects) {
                Product product = (Product) object;
                System.out.println(product.getId());
                System.out.println(product.getDescription());
                System.out.println(product.getPrice());
                System.out.println("*************************");
            }
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public static void hqlDMLQueries() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("HQL Select");

            Query query = session.createQuery("FROM Product");

            List<Product> products = query.list();

            for (Product product : products) {
                System.out.println(product);
            }

            System.out.println("HQL Update");
            session.getTransaction().begin();

            query = session.createQuery("UPDATE Product SET price = :new_precio" + " WHERE id = :id_product");
            query.setParameter("id_product", 1L, LongType.INSTANCE);
            query.setParameter("new_precio", 12.3, DoubleType.INSTANCE);

            Integer rowCount = query.executeUpdate();
            System.out.println("Updated rows: " + rowCount);
            session.getTransaction().commit();

            /*
             * El insert es soportado por HQL no hay equivalente en Java Persistence Query Language (JPQL)
             */
            System.out.println("HQL Insert/Select");
            session.getTransaction().begin();

            query = session.createQuery("INSERT INTO Product(description, price, creationDate)" + " SELECT description, price, creationDate FROM Product WHERE id = 2");

            rowCount = query.executeUpdate();
            System.out.println("Inserted rows: " + rowCount);

            session.getTransaction().commit();

            System.out.println("HQL Delete");
            session.getTransaction().begin();

            query = session.createQuery("DELETE Product WHERE id = :id_product");
            query.setParameter("id_product", 4L, LongType.INSTANCE);

            rowCount = query.executeUpdate();
            System.out.println("Deleted rows: " + rowCount);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public static void hqlQueries() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("HQL with positional parameter in JPQL style");
            Query query = session.createQuery("FROM Product p WHERE p.description = ?1");
            query.setParameter(1, "Apple");

            List<Product> products = query.list();

            for (Product product : products) {
                System.out.println(product);
            }

            System.out.println("HQL with name parameter");
            query = session.createQuery("FROM Product p WHERE p.description = :description");
            query.setParameter("description", "Apple");

            products = query.list();

            for (Product product : products) {
                System.out.println(product);
            }

            System.out.println("HQL with pagination");
            query = session.createQuery("FROM Product p");
            query.setFirstResult(1);
            query.setMaxResults(2);

            products = query.list();

            for (Product product : products) {
                System.out.println(product);
            }

            System.out.println("HQL Unique Result");
            Brand brandWithoutAddress = (Brand) session.createQuery("SELECT b FROM Brand b WHERE b.address IS NULL").uniqueResult();

            System.out.println(brandWithoutAddress);

            /*
             * Podemos usar los siguientes condicionales =, >, <, >=, <= [NOT]
             * BETWEEN [NOT] LIKE [NOT] IN IS [NOT] EMPTY IS [NOT] NULL
             */
            System.out.println("HQL with conditions");
            query = session.createQuery("FROM Product p WHERE p.price >= 14");

            products = query.list();

            for (Product product : products) {
                System.out.println(product);
            }

            /*
             * Podemos usar las siguientes funciones (Los indexes empiezan de 1)
             * CONCAT(cadena1, cadena1) SUBSTRING(cadena, initialIndex, longitud)
             * TRIM([LEADING | TRAILING | BOTH] [letra FROM] cadena) LOWER(cadena)
             * UPPER(cadena) LENGTH(cadena) LOCATE(cadenaBuscada, cadena [,
             * initialIndex]) ABS(numero) SQRT(numero) MOD(numero, divisor)
             * CURRENT_DATE CURRENT_TIME CURRENT_TIMESTAMP
             */
            System.out.println("HQL with functions");
            query = session.createQuery("FROM Product p WHERE UPPER(p.description) = 'LEMON'");

            products = query.list();

            for (Product product : products) {
                System.out.println(product);
            }

            System.out.println("HQL Select with alias");

            /*
             * La clausula AS es opcional
             */
            query = session.createQuery("SELECT 'Description: ' || p.description FROM Product AS p");

            List<String> productsWithDescription = query.list();

            for (String productDescription : productsWithDescription) {
                System.out.println(productDescription);
            }

            System.out.println("HQL Polymorphic Select");
            query = session.createQuery("SELECT o FROM java.lang.Object o");

            List<Object> objects = query.list();

            for (Object object : objects) {
                System.out.println(object);
            }

            System.out.println("HQL Select with custom object result");
            query = session.createQuery("SELECT NEW ar.com.javacuriosities.hibernate.utils.PartialProduct(id, description) FROM Product");

            List<PartialProduct> partialProducts = query.list();

            for (PartialProduct partialProduct : partialProducts) {
                System.out.println(partialProduct);
            }

            System.out.println("HQL INNER JOIN");
            query = session.createQuery("SELECT b FROM Brand b INNER JOIN b.address a");

            List<Brand> brands = query.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("HQL LEFT JOIN");
            query = session.createQuery("SELECT b FROM Brand b LEFT JOIN b.address a");

            brands = query.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("HQL RIGHT JOIN");
            query = session.createQuery("SELECT b FROM Brand b RIGHT JOIN b.address a");

            brands = query.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("HQL with fetch");
            query = session.createQuery("SELECT b FROM Brand b INNER JOIN FETCH b.products p");

            brands = query.list();

            for (Brand brand : brands) {
                System.out.println("Number of products: " + brand.getProducts().size());
            }

            /*
             * Tenemos las siguientes funciones agregadas: COUNT: Retorna un Long
             * MAX, MIN: Retorna un objeto del tipo de dato de la columna AVG:
             * Retorna Double SUM: Retorna un objeto dependiendo el tipo de la
             * columna
             *
             */
            System.out.println("HQL Grouping");
            query = session.createQuery("SELECT b.name, COUNT(p) AS productCount FROM Brand b LEFT JOIN b.products p GROUP BY b.id ORDER BY b.name DESC");

            List<Object[]> brandGrouped = query.list();

            for (Object[] data : brandGrouped) {
                System.out.println("Product: " + data[0]);
                System.out.println("Count: " + data[1]);
                System.out.println("*************************");
            }

            System.out.println("HQL Pagination");
            int pageSize = 2;
            String countQ = "SELECT COUNT(b.id) FROM Brand b";
            Query countQuery = session.createQuery(countQ);
            Long countResults = (Long) countQuery.uniqueResult();
            int lastPageNumber = (int) (Math.ceil(countResults / pageSize));

            System.out.println("Number of records: " + countResults);
            System.out.println("Page Size: " + pageSize);
            System.out.println("Last Page: " + lastPageNumber);

            for (int page = 0; page <= lastPageNumber; page++) {
                System.out.println("Query Page: " + page);
                query = session.createQuery("FROM Brand ");
                query.setFirstResult(page * pageSize);
                query.setMaxResults(pageSize);

                brands = query.list();

                for (Brand brand : brands) {
                    System.out.println("Brand : " + brand);
                }
            }
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public static void criteriaHibernateAPIQueries() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Criteria Simple");

            Criteria criteria = session.createCriteria(Brand.class);
            List<Brand> brands = criteria.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("Criteria Restrictions (Equal)");

            criteria = session.createCriteria(Brand.class);
            criteria.add(Restrictions.eq("name", "Brand 1"));
            brands = criteria.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("Criteria Restrictions (Or)");

            criteria = session.createCriteria(Brand.class);
            criteria.add(Restrictions.or(Restrictions.eq("name", "Brand 1"), Restrictions.eq("name", "Brand 2")));
            brands = criteria.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("Criteria Restrictions (Property)");

            criteria = session.createCriteria(Brand.class);
            Property nameProperty = Property.forName("name");
            Criterion criterion = nameProperty.in(new String[]{"Brand 1", "Brand X", "Brand 3"});
            criteria.add(criterion);
            brands = criteria.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("Criteria with Order");

            criteria = session.createCriteria(Brand.class);
            criteria.addOrder(Order.desc("name"));
            brands = criteria.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("Criteria with associations");

            criteria = session.createCriteria(Brand.class);
            criteria.createAlias("address", "a");
            criteria.add(Restrictions.eq("a.street", "Lombard"));
            brands = criteria.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("Criteria with fetch");

            /*
             * Podemos configurar el tipo de Fetch, ahora se utiliza JOIN = EAGER
             * SELECT = LAZY
             */
            criteria = session.createCriteria(Brand.class);
            criteria.setFetchMode("products", FetchMode.JOIN);
            criteria.setFetchMode("address", FetchMode.JOIN);
            brands = criteria.list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }

            System.out.println("Criteria projections");

            criteria = session.createCriteria(Brand.class);
            criteria.createAlias("products", "p");
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.rowCount())
                    .add(Projections.avg("p.price"))
                    .add(Projections.max("name"))
                    .add(Projections.groupProperty("id"))
            );

            List<Object[]> result = criteria.list();

            for (Object[] data : result) {
                System.out.println(Arrays.toString(data));
            }

            System.out.println("Criteria By Example");

            Product productByExample = new Product("Apple", 0.0, new Date());
            Example example = Example.create(productByExample)
                    .excludeZeroes() // Excluimos ceros
                    .excludeProperty("creationDate") // Excluimos cierta property
                    .ignoreCase() // Ignorar case sensitive
                    .enableLike(); // Usar LIKE para comparaciones (Dependiendo del Match Mode tenemos que agregar el % o no)

            criteria = session.createCriteria(Product.class);
            criteria.add(example);

            List<Product> products = criteria.list();

            for (Product product : products) {
                System.out.println(product);
            }

            System.out.println("Criteria Detached");

            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Brand.class);
            detachedCriteria.add(Property.forName("name").eq("Brand 1")); // La clase Property es una factoria de Criterion y Projection

            brands = detachedCriteria.getExecutableCriteria(session).list();

            for (Brand brand : brands) {
                System.out.println(brand);
            }
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public static void criteriaJPAAPIQueries() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("JPA Criteria Simple");

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Brand> criteria = criteriaBuilder.createQuery(Brand.class);
            Root<Brand> brandRoot = criteria.from(Brand.class);
            CriteriaQuery<Brand> select = criteria.select(brandRoot);

            Query<Brand> query = session.createQuery(select);
            List<Brand> brands = query.getResultList();

            for (Brand brand : brands) {
                System.out.println(brand);
            }
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public static final class ProductBean {

        private Long idProduct;
        private String description;

        public ProductBean() {
        }

        public Long getIdProduct() {
            return idProduct;
        }

        public void setIdProduct(Long idProduct) {
            this.idProduct = idProduct;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
