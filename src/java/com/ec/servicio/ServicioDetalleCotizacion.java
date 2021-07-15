/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.dao.DetalleFacturaDAO;
import com.ec.entidad.Cotizacion;
import com.ec.entidad.DetalleCotizacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioDetalleCotizacion {

    
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(DetalleCotizacion cotizacion) {

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

    public void eliminar(DetalleCotizacion cotizacion) {

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

    public void modificar(DetalleCotizacion cotizacion) {

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

    public List<DetalleCotizacion> findAll() {

        List<DetalleCotizacion> listaDetalleCotizacions = new ArrayList<DetalleCotizacion>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("SELECT d FROM DetalleCotizacion d");
//            query.setParameter("perNombre", "%" + valor + "%");
            listaDetalleCotizacions = (List<DetalleCotizacion>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cotizacions" + e.getMessage());
        } finally {
            em.close();
        }

        return listaDetalleCotizacions;
    }

   
    
}
