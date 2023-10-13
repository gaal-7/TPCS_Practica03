package org.uv.tpcs_practica03;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties settings = new Properties();
            Configuration configuration = new Configuration();

            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/ventas"); // Reemplaza "your_database_name" con el nombre de tu base de datos.
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, ""); // Reemplaza "your_password" con la contraseña de tu base de datos.

            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "update"); // Cambiado a "update" para no perder datos en cada reinicio.

            configuration.setProperties(settings);

            // Agregar los POJOs como entidades
            configuration.addAnnotatedClass(Cliente.class);
            configuration.addAnnotatedClass(Venta.class);
            configuration.addAnnotatedClass(DetalleVenta.class);
            configuration.addAnnotatedClass(Producto.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
