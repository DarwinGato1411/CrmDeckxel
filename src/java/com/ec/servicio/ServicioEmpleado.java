/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioEmpleado {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Empleado empleado) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar empleado " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Empleado empleado) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(empleado));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  empleado" + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Empleado empleado) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(empleado);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar empleado " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Empleado> findByLikePerNombre(String valor) {

        List<Empleado> listaEmpleados = new ArrayList<Empleado>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT e FROM Empleado e WHERE e.idPersona.perNombre LIKE :perNombre");
            query.setParameter("perNombre", "%" + valor + "%");
            listaEmpleados = (List<Empleado>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta empleados" + e.getMessage());
        } finally {
            em.close();
        }

        return listaEmpleados;
    }
}
