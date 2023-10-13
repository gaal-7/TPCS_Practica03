package org.uv.tpcs_practica03;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Date;

public class TPCS_Practica03 {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        
        // Ejemplo de creación de un cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setRFC("ABC12345");
        
        // Ejemplo de creación de un producto
        Producto producto = new Producto();
        producto.setDescripcion("Producto de prueba");
        producto.setPrecio(50.0);
        producto.setExistencia(100);
        producto.setCosto(25.0);
        
        // Ejemplo de creación de una venta
        Venta venta = new Venta();
        venta.setFecha(new Date());
        venta.setCliente("Juan Perez");
        venta.setTotal(100.0);
        
        // Ejemplo de creación de un detalle de venta
        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setVenta(venta);
        detalleVenta.setProducto(producto);
        detalleVenta.setCantidad(2);
        detalleVenta.setDescripcion("Venta de prueba");
        detalleVenta.setPrecio(50.0);
        
        // Iniciar una sesión de Hibernate y una transacción
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            
            // Guardar los objetos en la base de datos
            session.save(cliente);
            session.save(producto);
            session.save(venta);
            session.save(detalleVenta);
            
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Recuperar un cliente por su ID
        try (Session session = sessionFactory.openSession()) {
            Cliente clienteRecuperado = session.get(Cliente.class, 1L); // 1L es el ID del cliente a recuperar
            if (clienteRecuperado != null) {
                System.out.println("Cliente recuperado: " + clienteRecuperado.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Realizar otras operaciones como actualización y eliminación si es necesario
        
        // Cerrar la sesión de Hibernate y el sessionFactory
        sessionFactory.close();
    }
}
