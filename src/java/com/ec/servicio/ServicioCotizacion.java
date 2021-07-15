/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.dao.DetalleFacturaDAO;
import com.ec.entidad.Cotizacion;
import com.ec.entidad.DetalleCotizacion;
import com.ec.entidad.EstadoCotizacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioCotizacion {

    ServicioDetalleCotizacion servicioDetalleCotizacion = new ServicioDetalleCotizacion();
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Cotizacion cotizacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(cotizacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar cotizacion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Cotizacion cotizacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(cotizacion));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  cotizacion" + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Cotizacion cotizacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(cotizacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar cotizacion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Cotizacion> findAll() {

        List<Cotizacion> listaCotizacions = new ArrayList<Cotizacion>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cotizacion c");
//            query.setParameter("perNombre", "%" + valor + "%");
            listaCotizacions = (List<Cotizacion>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cotizacions" + e.getMessage());
        } finally {
            em.close();
        }

        return listaCotizacions;
    }

   

    public Cotizacion findUltimaCotNumero() {
        Cotizacion cotizacion = null;
        List<Cotizacion> listaCotizacions = new ArrayList<Cotizacion>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cotizacion c ORDER BY c.cotNumero DESC");
//            query.setParameter("perNombre", "%" + valor + "%");
            listaCotizacions = (List<Cotizacion>) query.getResultList();
            if (listaCotizacions.size() > 0) {
                cotizacion = listaCotizacions.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cotizacions" + e.getMessage());
        } finally {
            em.close();
        }

        return cotizacion;
    }

    public void guardarCotizacion(List<DetalleFacturaDAO> detalleFacturaDAOs, Cotizacion cotizacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(cotizacion);
            em.flush();
            DetalleCotizacion detalleCotizacion = null;
            for (DetalleFacturaDAO item : detalleFacturaDAOs) {
                detalleCotizacion = new DetalleCotizacion(item.getDescripcion(),
                        item.getCantidad(),
                        item.getSubTotal(),
                        item.getTotal(), cotizacion, item.getProducto());
                servicioDetalleCotizacion.crear(detalleCotizacion);
                em.persist(detalleCotizacion);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar factura GUARDAR CON DETALLE " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Cotizacion> findBetweenFecha(Date inicio, Date fin, EstadoCotizacion estado) {

        List<Cotizacion> listaCotizacions = new ArrayList<Cotizacion>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cotizacion c WHERE c.cotFechaCotizacion BETWEEN :inicio AND :fin  AND c.idEstadoCotizacion=:idEstadoCotizacion");
            query.setParameter("inicio", inicio);
            query.setParameter("fin", fin);
            query.setParameter("idEstadoCotizacion",estado);
            listaCotizacions = (List<Cotizacion>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cotizacions" + e.getMessage());
        } finally {
            em.close();
        }

        return listaCotizacions;
    }

    public List<Cotizacion> findCotNumero(Integer num, EstadoCotizacion estado) {

        List<Cotizacion> listaCotizacions = new ArrayList<Cotizacion>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cotizacion c WHERE c.cotNumero =:numero  and c.idEstadoCotizacion=:idEstadoCotizacion");
            query.setParameter("numero", num);
            query.setParameter("idEstadoCotizacion",estado);
            listaCotizacions = (List<Cotizacion>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cotizacions" + e.getMessage());
        } finally {
            em.close();
        }

        return listaCotizacions;
    }

    public List<Cotizacion> findLikeNombreComercial(String valor, EstadoCotizacion estado) {

        List<Cotizacion> listaCotizacions = new ArrayList<Cotizacion>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cotizacion c WHERE c.cliIdCliente.cliNombreComercial LIKE :cliNombreComercial and c.idEstadoCotizacion=:idEstadoCotizacion");
            query.setParameter("cliNombreComercial", "%" + valor + "%");
            query.setParameter("idEstadoCotizacion",estado);
            listaCotizacions = (List<Cotizacion>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cotizacions" + e.getMessage());
        } finally {
            em.close();
        }

        return listaCotizacions;
    }
 public List<Cotizacion> findLikePerNombre(String valor , EstadoCotizacion estado) {

        List<Cotizacion> listaCotizacions = new ArrayList<Cotizacion>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cotizacion c WHERE c.cliIdCliente.idPersona.perNombre LIKE :perNombre and c.idEstadoCotizacion=:idEstadoCotizacion");
            query.setParameter("perNombre", "%" + valor + "%");
            query.setParameter("idEstadoCotizacion",estado);
            listaCotizacions = (List<Cotizacion>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cotizacions" + e.getMessage());
        } finally {
            em.close();
        }

        return listaCotizacions;
    }

    public List<Cotizacion> findAllGenerada(EstadoCotizacion estadoCotizacion) {

        List<Cotizacion> listaCotizacions = new ArrayList<Cotizacion>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cotizacion c WHERE c.idEstadoCotizacion=:idEstadoCotizacion");
            query.setParameter("idEstadoCotizacion", estadoCotizacion);
            listaCotizacions = (List<Cotizacion>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cotizacions" + e.getMessage());
        } finally {
            em.close();
        }

        return listaCotizacions;
    }
}
