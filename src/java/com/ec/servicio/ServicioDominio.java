/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Dominio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioDominio {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Dominio dominio) {

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

    public void eliminar(Dominio dominio) {

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

    public void modificar(Dominio dominio) {

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

    public List<Dominio> findByBetweenFechaRegistro(Date inicio, Date fin) {

        List<Dominio> listaDominios = new ArrayList<Dominio>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM Dominio d WHERE d.domFechaRegistro BETWEEN :inicio and :fin ORDER BY d.domFechaRegistro DESC");
            query.setParameter("inicio", inicio);
            query.setParameter("fin", fin);
            listaDominios = (List<Dominio>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta dominios" + e.getMessage());
        } finally {
            em.close();
        }

        return listaDominios;
    }

    public List<Dominio> findByLikeNombreComercial(String valor) {

        List<Dominio> listaDominios = new ArrayList<Dominio>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM Dominio d WHERE d.idCliente.cliNombreComercial LIKE :cliNombreComercial ORDER BY d.domFechaCaduca ASC");
            query.setParameter("cliNombreComercial", "%" + valor + "%");
            query.setMaxResults(500);
            listaDominios = (List<Dominio>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta dominios" + e.getMessage());
        } finally {
            em.close();
        }

        return listaDominios;
    }

    public List<Dominio> findByLikeRazonSocial(String valor) {

        List<Dominio> listaDominios = new ArrayList<Dominio>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM Dominio d WHERE d.idCliente.idPersona.perNombre LIKE :perNombre ORDER BY d.domFechaCaduca ASC");
            query.setParameter("perNombre", "%" + valor + "%");
            query.setMaxResults(500);
            listaDominios = (List<Dominio>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta dominios" + e.getMessage());
        } finally {
            em.close();
        }

        return listaDominios;
    }

    public List<Dominio> findByCedula(String valor) {

        List<Dominio> listaDominios = new ArrayList<Dominio>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM Dominio d WHERE d.idCliente.idPersona.perDni = :perDni ORDER BY d.domFechaCaduca ASC");
            query.setParameter("perDni", valor);
            query.setMaxResults(500);
            listaDominios = (List<Dominio>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta dominios" + e.getMessage());
        } finally {
            em.close();
        }

        return listaDominios;
    }
}
