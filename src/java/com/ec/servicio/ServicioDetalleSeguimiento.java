/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.DetalleSeguimiento;
import com.ec.entidad.Seguimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioDetalleSeguimiento {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(DetalleSeguimiento detalleSeguimiento) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(detalleSeguimiento);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar detalleSeguimiento " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(DetalleSeguimiento detalleSeguimiento) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(detalleSeguimiento));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  detalleSeguimiento" + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(DetalleSeguimiento detalleSeguimiento) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(detalleSeguimiento);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar detalleSeguimiento " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<DetalleSeguimiento> findDetalleForDominio(Seguimiento idSeguimineto) {

        List<DetalleSeguimiento> listaDominios = new ArrayList<DetalleSeguimiento>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM DetalleSeguimiento d WHERE d.idSeguimiento=:idSeguimiento");
            query.setParameter("idSeguimiento", idSeguimineto);
           
            listaDominios = (List<DetalleSeguimiento>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta detalleSeguimientos" + e.getMessage());
        } finally {
            em.close();
        }

        return listaDominios;
    }

}
