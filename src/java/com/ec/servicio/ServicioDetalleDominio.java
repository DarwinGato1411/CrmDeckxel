/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.DetalleDominio;
import com.ec.entidad.Dominio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioDetalleDominio {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(DetalleDominio dominio) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(dominio);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar dominio " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(DetalleDominio dominio) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(dominio));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  dominio" + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(DetalleDominio dominio) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(dominio);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar dominio " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<DetalleDominio> findDetalleForDominio(Dominio idDominio) {

        List<DetalleDominio> listaDominios = new ArrayList<DetalleDominio>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM DetalleDominio d WHERE d.idDominio=:idDominio");
            query.setParameter("idDominio", idDominio);
           
            listaDominios = (List<DetalleDominio>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta dominios" + e.getMessage());
        } finally {
            em.close();
        }

        return listaDominios;
    }

}
