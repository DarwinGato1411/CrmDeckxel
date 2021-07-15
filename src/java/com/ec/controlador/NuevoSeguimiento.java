/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cliente;
import com.ec.entidad.Seguimiento;
import com.ec.servicio.ServicioCliente;
import com.ec.servicio.ServicioSeguimiento;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoSeguimiento {

    @Wire
    Window wSeguimiento;
    ServicioCliente servicioCliente = new ServicioCliente();
    ServicioSeguimiento servicioSeguimiento = new ServicioSeguimiento();

    private List<Cliente> listaClientesAll = new ArrayList<Cliente>();
    private Cliente clienteSelected = new Cliente();
    private String buscarNombre = "";

    private Seguimiento seguimiento = new Seguimiento();
    private String tipoUSuario = "1";
    private String accion = "create";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Seguimiento seguimiento, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (seguimiento != null) {
            this.seguimiento = seguimiento;
            this.clienteSelected = seguimiento.getIdCliente();

        } else {
            this.seguimiento = new Seguimiento();
            this.clienteSelected = null;

        }
        cosultarCliente("");
    }


    /*ADMINISTRAR USUARIO*/
    private void cosultarCliente(String buscar) {
        listaClientesAll = servicioCliente.findByLikeCliNombreComercial(buscar);
    }

    public List<Cliente> getListaClientesAll() {
        return listaClientesAll;
    }

    public void setListaClientesAll(List<Cliente> listaClientesAll) {
        this.listaClientesAll = listaClientesAll;
    }

    public String getBuscarNombre() {
        return buscarNombre;
    }

    public void setBuscarNombre(String buscarNombre) {
        this.buscarNombre = buscarNombre;
    }

    public Cliente getClienteSelected() {
        return clienteSelected;
    }

    public void setClienteSelected(Cliente clienteSelected) {
        this.clienteSelected = clienteSelected;
    }

    public String getTipoUSuario() {
        return tipoUSuario;
    }

    public void setTipoUSuario(String tipoUSuario) {
        this.tipoUSuario = tipoUSuario;
    }

    @Command
    @NotifyChange("dominio")
    public void guardar() {
        if (accion.equals("create")) {

            if (seguimiento != null && clienteSelected != null) {
                seguimiento.setIdCliente(clienteSelected);
                servicioSeguimiento.crear(seguimiento);
                wSeguimiento.detach();
            } else {
                Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
            }
        } else {
            if (seguimiento != null && clienteSelected != null) {
                seguimiento.setIdCliente(clienteSelected);
                servicioSeguimiento.modificar(seguimiento);
                wSeguimiento.detach();
            } else {
                Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
            }

        }
    }

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento dominio) {
        this.seguimiento = dominio;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
