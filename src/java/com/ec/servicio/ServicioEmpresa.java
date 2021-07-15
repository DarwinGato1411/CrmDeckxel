/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Empresa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioEmpresa {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Empresa empresa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(empresa);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar empresa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Empresa empresa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(empresa));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  empresa" + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Empresa empresa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(empresa);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar empresa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Empresa> findByBetweenFechaRegistro(Date inicio, Date fin) {

        List<Empresa> listaEmpresas = new ArrayList<Empresa>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM Empresa d WHERE d.domFechaRegistro BETWEEN :inicio and :fin ORDER BY d.domFechaRegistro DESC");
            query.setParameter("inicio", inicio);
            query.setParameter("fin", fin);
            listaEmpresas = (List<Empresa>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta empresas" + e.getMessage());
        } finally {
            em.close();
        }

        return listaEmpresas;
    }

    public Empresa obtenerEmpresaActiva() {

        List<Empresa> listaEmpresas = new ArrayList<Empresa>();
        Empresa empresa = null;

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM Empresa d WHERE d.domEstado=TRUE");
            listaEmpresas = (List<Empresa>) query.getResultList();
            if (listaEmpresas.size() > 0) {
                empresa = listaEmpresas.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta empresas" + e.getMessage());
        } finally {
            em.close();
        }

        return empresa;
    }
}
