/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Empleado;
import com.ec.entidad.Persona;
import com.ec.entidad.Usuario;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioEmpleado;
import com.ec.servicio.ServicioPersona;
import com.ec.servicio.ServicioUsuario;
import java.util.Date;
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
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoEmpleado {
    
    @Wire
    Window windowEmpleadoDOs;
    
    ServicioEmpleado servicioEmpleado = new ServicioEmpleado();
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioPersona servicioPersona = new ServicioPersona();
    private Empleado empleado = new Empleado();
    private Persona idPersona = new Persona();
    private Usuario idUsuario = new Usuario();
    private String accion = "create";
    private String clietipo = "0"; //0 normal 1 preferencial 1 -> 2 preferencial 2 -> 3 - preferencial 3
    private Date fechaReg = new Date();
    UserCredential credential = new UserCredential();
    
    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Empleado valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
        if (valor != null) {
            idPersona = valor.getIdPersona();
            this.empleado = valor;
            accion = "update";
        } else {
            this.idPersona = new Persona();
            this.empleado = new Empleado();
            accion = "create";
        }
        
    }
    
    @Command
    @NotifyChange({"empleado"})
    public void actualizarTipo() {
    }
    
    @Command
    public void guardar() {
        /*getCliNombre es el nombre comercial*/
        if (idPersona.getPerDni() != null
                && idPersona.getPerNombre() != null
                && idPersona.getPerApellido() != null
                && idPersona.getPrDireccion() != null
                && empleado.getEmpCargo() != null) {
            
            Persona vaerifica = servicioPersona.findByPerDni(idPersona.getPerDni());
            if (accion.equals("create")) {
                
                if (vaerifica == null) {
                    empleado.setIdPersona(idPersona);
                    servicioPersona.crear(idPersona);
                    servicioEmpleado.crear(empleado);
                    windowEmpleadoDOs.detach();
                } else {
                    idPersona.setIdPersona(vaerifica.getIdPersona());
                    empleado.setIdPersona(idPersona);
                    servicioPersona.modificar(idPersona);
                    servicioEmpleado.crear(empleado);
                    windowEmpleadoDOs.detach();
                }
                
            } else {
                servicioPersona.modificar(idPersona);
                servicioEmpleado.modificar(empleado);
                windowEmpleadoDOs.detach();
            }
            
        } else {
              Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
        }
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
