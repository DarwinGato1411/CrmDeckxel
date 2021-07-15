/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Dominio;
import com.ec.servicio.ServicioDominio;
import java.util.ArrayList;
import java.util.Date;
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
public class AdministrarDominio {

    ServicioDominio servicioDominio = new ServicioDominio();

    private List<Dominio> listaDominio = new ArrayList<Dominio>();
    private Date inicio = new Date();
    private Date fin = new Date();

    private String buscarNombre = "";
    private String buscarCedula = "";
    private String buscarRazon = "";

    public AdministrarDominio() {

        findByLikeNombreComercial();
    }
    //buscar por like de nombre comercial

    @Command
    @NotifyChange({"listaDominio", "buscarNombre",})
    public void likeCliNombreComercial() {

        findByLikeNombreComercial();
    }

    @Command
    @NotifyChange({"listaDominio", "buscarRazon",})
    public void likeCliRazon() {

        findByLikeRazonSocial();
    }

    @Command
    @NotifyChange({"listaDominio", "buscarCedula",})
    public void buscarForCedula() {

        findByCedula();
    }

    @Command
    @NotifyChange({"listaDominio", "buscarNombre",})
    public void betweenFechaRegistro() {

        findByBetweenFechaRegistro();
    }

    //usuarios
    @Command
    @NotifyChange("listaDominio")
    public void nuevo() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/dominio.zul", null, null);
        window.doModal();
        findByLikeNombreComercial();
    }

    @Command
    @NotifyChange("listaDominio")
    public void modificar(@BindingParam("valor") Dominio valor) {
        final HashMap<String, Dominio> map = new HashMap<String, Dominio>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/dominio.zul", null, map);
        window.doModal();
        findByLikeNombreComercial();
    }

    @Command
    @NotifyChange("listaDominio")
    public void detalleDominio(@BindingParam("valor") Dominio valor) {
        final HashMap<String, Dominio> map = new HashMap<String, Dominio>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/detdominio.zul", null, map);
        window.doModal();
    }

    /*ADMINISTRAR USUARIO*/
    private void findByBetweenFechaRegistro() {
        listaDominio = servicioDominio.findByBetweenFechaRegistro(inicio, fin);
    }

    private void findByLikeNombreComercial() {
        listaDominio = servicioDominio.findByLikeNombreComercial(buscarNombre);
    }

    private void findByLikeRazonSocial() {
        listaDominio = servicioDominio.findByLikeRazonSocial(buscarRazon);
    }

    private void findByCedula() {
        listaDominio = servicioDominio.findByCedula(buscarCedula);
    }

    public List<Dominio> getListaDominio() {
        return listaDominio;
    }

    public void setListaDominio(List<Dominio> listaDominio) {
        this.listaDominio = listaDominio;
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
