/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cliente;
import com.ec.entidad.Persona;
import com.ec.entidad.Usuario;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioCliente;
import com.ec.servicio.ServicioPersona;
import com.ec.servicio.ServicioUsuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoCliente {

    @Wire
    Window windowClienteCrm;

    ServicioCliente servicioCliente = new ServicioCliente();
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioPersona servicioPersona = new ServicioPersona();
    private Cliente cliente = new Cliente();
    private Persona idPersona = new Persona();
    private Usuario idUsuario = new Usuario();
    private String accion = "create";
    private String clietipo = "0"; //0 normal 1 preferencial 1 -> 2 preferencial 2 -> 3 - preferencial 3
    private Date fechaReg = new Date();
    UserCredential credential = new UserCredential();

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Cliente cliente, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
        if (cliente != null) {
            idPersona = cliente.getIdPersona();
            this.cliente = cliente;
            accion = "update";
        } else {
            this.idPersona = new Persona();
            this.cliente = new Cliente();
            accion = "create";
        }

    }

    @Command
    @NotifyChange({"cliente"})
    public void actualizarTipo() {
    }

    @Command
    public void guardar() {
        /*getCliNombre es el nombre comercial*/
        if (idPersona.getPerDni() != null
                && idPersona.getPerNombre() != null
                && idPersona.getPrDireccion() != null
                && cliente.getCliNombreComercial() != null) {

            Persona vaerifica = servicioPersona.findByPerDni(idPersona.getPerDni());
            if (accion.equals("create")) {

                if (vaerifica == null) {

                    cliente.setCliFechaRegistro(new Date());
                    cliente.setIdPersona(idPersona);
                    cliente.setIdUsuario(credential.getUsuarioSistema());
                    servicioPersona.crear(idPersona);
                    servicioCliente.crear(cliente);
                    windowClienteCrm.detach();
                } else {
                    idPersona.setIdPersona(vaerifica.getIdPersona());
                    cliente.setCliFechaActualizacion(new Date());
                    cliente.setIdPersona(idPersona);
                    cliente.setIdUsuario(credential.getUsuarioSistema());
                    servicioPersona.modificar(idPersona);
                    servicioCliente.crear(cliente);
                    windowClienteCrm.detach();
                }

            } else {
                cliente.setCliFechaActualizacion(new Date());
                servicioPersona.modificar(idPersona);
                servicioCliente.modificar(cliente);
                windowClienteCrm.detach();
            }

        } else {
            Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getClietipo() {
        return clietipo;
    }

    public void setClietipo(String clietipo) {
        this.clietipo = clietipo;
    }

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

}
