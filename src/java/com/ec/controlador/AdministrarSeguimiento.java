/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.DetalleSeguimiento;
import com.ec.entidad.Seguimiento;
import static com.ec.entidad.Seguimiento_.segFechaProxima;
import com.ec.servicio.ServicioSeguimiento;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;

/**
 *
 * @author gato
 */
public class AdministrarSeguimiento {

    ServicioSeguimiento servicioSeguimiento = new ServicioSeguimiento();

    private List<Seguimiento> listaSeguimiento = new ArrayList<Seguimiento>();
    private Date inicio = new Date();
    private Date fin = new Date();

    private String buscarNombre = "";
    private String buscarCedula = "";
    private String buscarRazon = "";

    public AdministrarSeguimiento() {

        findByLikeNombreComercial();
    }
    //buscar por like de nombre comercial

    @Command
    @NotifyChange({"listaSeguimiento", "buscarNombre",})
    public void likeCliNombreComercial() {

        findByLikeNombreComercial();
    }

    @Command
    @NotifyChange({"listaSeguimiento", "buscarRazon",})
    public void likeCliRazon() {

        findByLikeRazonSocial();
    }

    @Command
    @NotifyChange({"listaSeguimiento", "buscarCedula",})
    public void buscarForCedula() {

        findByCedula();
    }

    @Command
    @NotifyChange({"listaSeguimiento", })
    public void betweenFechaRegistro() {

        findByBetweenFechaRegistro();
    }

    //usuarios
    @Command
    @NotifyChange("listaSeguimiento")
    public void nuevo() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/seguimiento.zul", null, null);
        window.doModal();
        findByLikeNombreComercial();
    }

    @Command
    @NotifyChange("listaSeguimiento")
    public void modificar(@BindingParam("valor") Seguimiento valor) {
        final HashMap<String, Seguimiento> map = new HashMap<String, Seguimiento>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/seguimiento.zul", null, map);
        window.doModal();
        findByLikeNombreComercial();
    }

    @Command
    @NotifyChange("listaSeguimiento")
    public void detalleSeguimiento(@BindingParam("valor") Seguimiento valor) {
        final HashMap<String, Seguimiento> map = new HashMap<String, Seguimiento>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/detseguimiento.zul", null, map);
        window.doModal();
    } 
    
    @Command
    @NotifyChange("listaSeguimiento")
    public void renovaplan(@BindingParam("valor") Seguimiento valor) {
        final HashMap<String, Seguimiento> map = new HashMap<String, Seguimiento>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/renovarplan.zul", null, map);
        window.doModal();
    } 
    

    /*ADMINISTRAR USUARIO*/
    private void findByBetweenFechaRegistro() {
//        listaSeguimiento = servicioSeguimiento.findByBetweenFechaRegistro(inicio, fin);
    }

    private void findByLikeNombreComercial() {
        listaSeguimiento = servicioSeguimiento.findByLikeNombreComercial(buscarNombre);
    }

    private void findByLikeRazonSocial() {
        listaSeguimiento = servicioSeguimiento.findByLikeRazonSocial(buscarRazon);
    }

    private void findByCedula() {
        listaSeguimiento = servicioSeguimiento.findByCedula(buscarCedula);
    }

    public List<Seguimiento> getListaSeguimiento() {
        return listaSeguimiento;
    }

    public void setListaSeguimiento(List<Seguimiento> listaSeguimiento) {
        this.listaSeguimiento = listaSeguimiento;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getBuscarNombre() {
        return buscarNombre;
    }

    public void setBuscarNombre(String buscarNombre) {
        this.buscarNombre = buscarNombre;
    }

    public String getBuscarCedula() {
        return buscarCedula;
    }

    public void setBuscarCedula(String buscarCedula) {
        this.buscarCedula = buscarCedula;
    }

    public String getBuscarRazon() {
        return buscarRazon;
    }

    public void setBuscarRazon(String buscarRazon) {
        this.buscarRazon = buscarRazon;
    }

}
