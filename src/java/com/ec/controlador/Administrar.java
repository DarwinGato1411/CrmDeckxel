/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cliente;
import com.ec.entidad.Usuario;
import com.ec.servicio.ServicioUsuario;
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
public class Administrar {
   

    ServicioUsuario servicioUsuario = new ServicioUsuario();
    private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    private List<Cliente> listaClientesAll = new ArrayList<Cliente>();
    private String buscarNombre = "";
    private String buscarRazonSocial = "";
    private String buscarCedula = "";
    private String buscarCorreo = "";
    private List<Usuario> listaUsuario2= new ArrayList<Usuario>();
    //mailing
    private String buscarEmail = "";

    public Administrar() {

        cosultarUsuarios("");
    }

    /*ADMINISTRAR USUARIO*/
    private void cosultarUsuarios(String buscar) {
        listaUsuarios = servicioUsuario.findByLikeUsuNombre(buscar);
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    //usuarios
    @Command
    @NotifyChange("listaUsuarios")
    public void agregarUsario() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/usuario.zul", null, null);
        window.doModal();
        cosultarUsuarios("");
    }

    @Command
    @NotifyChange("listaUsuarios")
    public void modificarUsario(@BindingParam("valor") Usuario usuario) {
        final HashMap<String, Usuario> map = new HashMap<String, Usuario>();
        map.put("usuario", usuario);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/usuario.zul", null, map);
        window.doModal();
        cosultarUsuarios("");
    }
    
    @Command
    @NotifyChange({"listaUsuarios","buscarCorreo"})
    public void bucarPorCorreo() {
        listaUsuarios=servicioUsuario.findByCorreo(buscarCorreo);
    }

    public List<Usuario> getListaUsuario2() {
        return listaUsuario2;
    }

    public void setListaUsuario2(List<Usuario> listaUsuario2) {
        this.listaUsuario2 = listaUsuario2;
    }
    
    
}
