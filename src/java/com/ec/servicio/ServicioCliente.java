/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioCliente {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Cliente cliente) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar cliente");
        } finally {
            em.close();
        }

    }

    public void eliminar(Cliente cliente) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(cliente));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  cliente" + e);
        } finally {
            em.close();
        }

    }

    public void modificar(Cliente cliente) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar cliente");
        } finally {
            em.close();
        }

    }

    public List<Cliente> findByLikeCliNombreComercial(String nombre) {

        List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.cliNombreComercial LIKE :cliNombreComercial");
            query.setParameter("cliNombreComercial", "%" + nombre + "%");
            listaClientes = (List<Cliente>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta clientes" + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public List<Cliente> findByCliCedula(String buscar) {

        Cliente cliente = new Cliente();
        List<Cliente> listaCliente = new ArrayList<Cliente>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.idPersona.perDni = :perDni");
            query.setParameter("perDni", buscar);
            listaCliente = (List<Cliente>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cliente");
        } finally {
            em.close();
        }

        return listaCliente;
    }

    public Cliente findClienteLikeCedula(String buscar) {
        Cliente cliente = null;
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.idPersona.perDni LIKE :perDni");
            query.setParameter("perDni", "%" + buscar + "%");
            listaClientes = (List<Cliente>) query.getResultList();
            if (listaClientes.size() > 0) {
                cliente = listaClientes.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cliente " + e.getMessage());
        } finally {
            em.close();
        }

        return cliente;
    }

    public Cliente findByCliforCedula(String buscar) {
        Cliente cliente = null;
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.idPersona.perDni = :perDni");
            query.setParameter("perDni", buscar);
            listaClientes = (List<Cliente>) query.getResultList();
            if (listaClientes.size() > 0) {
                cliente = listaClientes.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cliente " + e.getMessage());
        } finally {
            em.close();
        }

        return cliente;
    }
}
