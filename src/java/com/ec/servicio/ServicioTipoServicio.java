/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.TipoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioTipoServicio {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(TipoServicio tipoServicio) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(tipoServicio);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar tipoServicio");
        } finally {
            em.close();
        }

    }

    public void eliminar(TipoServicio tipoServicio) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(tipoServicio));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  tipoServicio" + e);
        } finally {
            em.close();
        }

    }

    public void modificar(TipoServicio tipoServicio) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(tipoServicio);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar tipoServicio");
        } finally {
            em.close();
        }

    }

    public List<TipoServicio> findByLikeTipsDescripcion(String nombre) {

        List<TipoServicio> listaTipoServicios = new ArrayList<TipoServicio>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM TipoServicio c WHERE c.tipsDescripcion LIKE :tipsDescripcion");
            query.setParameter("tipsDescripcion", "%" + nombre + "%");
            listaTipoServicios = (List<TipoServicio>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta tipoServicios" + e.getMessage());
        } finally {
            em.close();
        }

        return listaTipoServicios;
    }

}
