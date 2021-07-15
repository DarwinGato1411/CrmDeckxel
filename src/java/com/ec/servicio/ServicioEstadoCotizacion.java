/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.EstadoCotizacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioEstadoCotizacion {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(EstadoCotizacion estadoCotizacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(estadoCotizacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar estadoCotizacion");
        } finally {
            em.close();
        }

    }

    public void eliminar(EstadoCotizacion estadoCotizacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(estadoCotizacion));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  estadoCotizacion" + e);
        } finally {
            em.close();
        }

    }

    public void modificar(EstadoCotizacion estadoCotizacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(estadoCotizacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar estadoCotizacion");
        } finally {
            em.close();
        }

    }

    public EstadoCotizacion findBySigla(String nombre) {
        EstadoCotizacion estadoCotizacion = null;
        List<EstadoCotizacion> listaEstadoCotizacions = new ArrayList<EstadoCotizacion>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT e FROM EstadoCotizacion e WHERE e.estcSigla=:sigla");
            query.setParameter("sigla", nombre);
            listaEstadoCotizacions = (List<EstadoCotizacion>) query.getResultList();
            if (listaEstadoCotizacions.size() > 0) {
                estadoCotizacion = listaEstadoCotizacions.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta estadoCotizacions" + e.getMessage());
        } finally {
            em.close();
        }

        return estadoCotizacion;
    }

}
