/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioPersona {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Persona persona) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar persona");
        } finally {
            em.close();
        }

    }

    public void eliminar(Persona persona) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(persona));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  persona" + e);
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }

    }

    public void modificar(Persona persona) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(persona);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar persona");
        } finally {
            em.close();
        }

    }

    public Persona findByPerDni(String dni) {
        Persona persona = null;
        List<Persona> listaPersonas = new ArrayList<Persona>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Persona p WHERE p.perDni = :perDni");
            query.setParameter("perDni", dni);
            listaPersonas = (List<Persona>) query.getResultList();
            if (listaPersonas.size() > 0) {
                persona = listaPersonas.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta personas" + e.getMessage());
        } finally {
            em.close();
        }

        return persona;
    }
}
