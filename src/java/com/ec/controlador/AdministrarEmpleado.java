/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cliente;
import com.ec.entidad.Empleado;
import com.ec.servicio.ServicioEmpleado;
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
public class AdministrarEmpleado {

    ServicioEmpleado servicioEmpleado = new ServicioEmpleado();
    private List<Empleado> listaEmpleado = new ArrayList<Empleado>();
    private String buscarNombre = "";
    //mailing

    public AdministrarEmpleado() {

        findByLikeCliNombreComercial();
    }

    /*ADMINISTRAR USUARIO*/
    private void findByLikeCliNombreComercial() {
        listaEmpleado = servicioEmpleado.findByLikePerNombre(buscarNombre);
    }

    //buscar por like de nombre comercial
    @Command
    @NotifyChange({"listaEmpleado", "buscarNombre",})
    public void LikePerNombre() {

        findByLikeCliNombreComercial();
    }

    //usuarios
    @Command
    @NotifyChange("listaEmpleado")
    public void nuevoEmpleado() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/empleado.zul", null, null);
        window.doModal();
        findByLikeCliNombreComercial();
    }

    @Command
    @NotifyChange("listaEmpleado")
    public void modificarEmpleado(@BindingParam("valor") Empleado valor) {
        final HashMap<String, Empleado> map = new HashMap<String, Empleado>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/empleado.zul", null, map);
        window.doModal();
        findByLikeCliNombreComercial();
    }

    public List<Empleado> getListaEmpleado() {
        return listaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public String getBuscarNombre() {
        return buscarNombre;
    }

    public void setBuscarNombre(String buscarNombre) {
        this.buscarNombre = buscarNombre;
    }

}
