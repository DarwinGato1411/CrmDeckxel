/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Dominio;
import com.ec.entidad.Seguimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioSeguimiento {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Seguimiento seguimiento) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(seguimiento);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar seguimiento " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Seguimiento seguimiento) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(seguimiento));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  seguimiento" + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Seguimiento seguimiento) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(seguimiento);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar seguimiento " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Seguimiento> findAll() {

        List<Seguimiento> listaSeguimientos = new ArrayList<Seguimiento>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT s FROM Seguimiento s");
//            query.setParameter("perNombre", "%" + valor + "%");
            listaSeguimientos = (List<Seguimiento>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta seguimientos" + e.getMessage());
        } finally {
            em.close();
        }

        return listaSeguimientos;
    }

    public List<Seguimiento> findByLikeNombreComercial(String valor) {

        List<Seguimiento> listaSeguimiento = new ArrayList<Seguimiento>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT s FROM Seguimiento s WHERE s.idCliente.cliNombreComercial LIKE :cliNombreComercial");
            query.setParameter("cliNombreComercial", "%" + valor + "%");
            query.setMaxResults(500);
            listaSeguimiento = (List<Seguimiento>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta seguimiento" + e.getMessage());
        } finally {
            em.close();
        }

        return listaSeguimiento;
    }

    public List<Seguimiento> findByLikeRazonSocial(String valor) {

        List<Seguimiento> listaSeguimiento = new ArrayList<Seguimiento>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT s FROM Seguimiento s WHERE s.idCliente.idPersona.perNombre LIKE :perNombre");
            query.setParameter("perNombre", "%" + valor + "%");
            query.setMaxResults(500);
            listaSeguimiento = (List<Seguimiento>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta seguimiento" + e.getMessage());
        } finally {
            em.close();
        }

        return listaSeguimiento;
    }

    public List<Seguimiento> findByCedula(String valor) {

        List<Seguimiento> listaSeguimiento = new ArrayList<Seguimiento>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT s FROM Seguimiento s WHERE s.idCliente.idPersona.perDni =:perDni");
            query.setParameter("perDni", valor);
            query.setMaxResults(500);
            listaSeguimiento = (List<Seguimiento>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta seguimiento" + e.getMessage());
        } finally {
            em.close();
        }

        return listaSeguimiento;
    }
}
