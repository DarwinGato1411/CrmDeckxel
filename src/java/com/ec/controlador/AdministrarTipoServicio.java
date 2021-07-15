/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cliente;
import com.ec.entidad.TipoServicio;
import com.ec.servicio.ServicioTipoServicio;
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
public class AdministrarTipoServicio {

    ServicioTipoServicio servicioTipoServicio = new ServicioTipoServicio();
    private List<TipoServicio> listaTipoServicios = new ArrayList<TipoServicio>();
    private String buscarNombre = "";
    //mailing

    public AdministrarTipoServicio() {

        findByLikeCliNombreComercial();
    }

    /*ADMINISTRAR USUARIO*/
    private void findByLikeCliNombreComercial() {
        listaTipoServicios = servicioTipoServicio.findByLikeTipsDescripcion(buscarNombre);
    }

    //buscar por like de nombre comercial
    @Command
    @NotifyChange({"listaTipoServicios","buscarNombre",})
    public void likeCliNombreComercial() {
        
        findByLikeCliNombreComercial();
    }
    //usuarios
    @Command
    @NotifyChange("listaTipoServicios")
    public void nuevoTipoServicio() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/tiposervicio.zul", null, null);
        window.doModal();
        findByLikeCliNombreComercial();
    }

    @Command
    @NotifyChange("listaTipoServicios")
    public void modificarTipoServicio(@BindingParam("valor") TipoServicio valor) {
        final HashMap<String, TipoServicio> map = new HashMap<String, TipoServicio>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/tiposervicio.zul", null, map);
        window.doModal();
        findByLikeCliNombreComercial();
    }

    public List<TipoServicio> getListaTipoServicios() {
        return listaTipoServicios;
    }

    public void setListaTipoServicios(List<TipoServicio> listaTipoServicios) {
        this.listaTipoServicios = listaTipoServicios;
    }



    public String getBuscarNombre() {
        return buscarNombre;
    }

    public void setBuscarNombre(String buscarNombre) {
        this.buscarNombre = buscarNombre;
    }

}
