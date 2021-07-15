/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;


import com.ec.entidad.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioProducto {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Producto producto) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar producto " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Producto producto) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(producto));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  producto" + e);
        } finally {
            em.close();
        }

    }

    public void modificar(Producto producto) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar producto");
        } finally {
            em.close();
        }

    }

    public List<Producto> FindALlProducto() {

        List<Producto> listaProductos = new ArrayList<Producto>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Producto p");
//           query.setParameter("codigoUsuario", producto);
            listaProductos = (List<Producto>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta producto");
        } finally {
            em.close();
        }

        return listaProductos;
    }

    public List<Producto> findLikeProdNombre(String buscar) {

        List<Producto> listaProductos = new ArrayList<Producto>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Producto p WHERE p.prodNombre = :prodNombre");
            query.setParameter("prodNombre", "%" + buscar + "%");
            listaProductos = (List<Producto>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta producto");
        } finally {
            em.close();
        }

        return listaProductos;
    }

    public Producto findByProdCodigo(String buscar) {

        Producto producto = null;
        try {
            //Connection connection = em.unwrap(Connection.class);
            List<Producto> listaProductos = new ArrayList<Producto>();
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Producto p WHERE p.prodSerie = :prodSerie");
            query.setParameter("prodSerie", buscar);
            listaProductos = (List<Producto>) query.getResultList();
            if (listaProductos.size() > 0) {
                producto = listaProductos.get(0);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta producto " + e.getMessage());
        } finally {
            em.close();
        }

        return producto;
    }

    public List<Producto> findLikeProdCodigo(String buscar) {

        List<Producto> listaProducto = new ArrayList<Producto>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Producto.findLikeProdCodigo", Producto.class);
            query.setParameter("prodCodigo", buscar);

            listaProducto = (List<Producto>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta producto");
        } finally {
            em.close();
        }

        return listaProducto;
    }

    public int findCountPrincipal() {
        int valor = 0;
        List<Producto> listaProductoProveedor = new ArrayList<Producto>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Producto p WHERE p.prodPrincipal=1");
            // query.setParameter("prodPrincipal", "%" + buscar + "%");

            listaProductoProveedor = (List<Producto>) query.getResultList();

            valor = listaProductoProveedor.size();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en al construir la clase producto proveedor " + e.getMessage());
        } finally {
            em.close();
        }

        return valor;
    }

    public List<Producto> findProductoPrincipal() {

        List<Producto> listaProductoProveedor = new ArrayList<Producto>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Producto p WHERE p.prodPrincipal=1");
            query.setMaxResults(24);
            // query.setParameter("prodPrincipal", "%" + buscar + "%");

            listaProductoProveedor = (List<Producto>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en al construir la clase producto proveedor " + e.getMessage());
        } finally {
            em.close();
        }

        return listaProductoProveedor;
    }
}
