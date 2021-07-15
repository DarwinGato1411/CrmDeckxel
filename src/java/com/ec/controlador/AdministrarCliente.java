/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cliente;
import com.ec.entidad.Usuario;
import com.ec.servicio.ServicioCliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author gato
 */
public class AdministrarCliente {

    ServicioCliente servicioCliente = new ServicioCliente();
    private List<Cliente> listaClientesAll = new ArrayList<Cliente>();
    private String buscarNombre = "";
    //mailing

    public AdministrarCliente() {

        findByLikeCliNombreComercial();
    }

    /*ADMINISTRAR USUARIO*/
    private void findByLikeCliNombreComercial() {
        listaClientesAll = servicioCliente.findByLikeCliNombreComercial(buscarNombre);
    }

    //buscar por like de nombre comercial
    @Command
    @NotifyChange({"listaClientesAll","buscarNombre",})
    public void likeCliNombreComercial() {
        
        findByLikeCliNombreComercial();
    }
    //usuarios
    @Command
    @NotifyChange("listaClientesAll")
    public void nuevoCliente() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cliente.zul", null, null);
        window.doModal();
        findByLikeCliNombreComercial();
    }

    @Command
    @NotifyChange("listaClientesAll")
    public void modificarCliente(@BindingParam("valor") Cliente valor) {
        final HashMap<String, Cliente> map = new HashMap<String, Cliente>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cliente.zul", null, map);
        window.doModal();
        findByLikeCliNombreComercial();
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

}
