/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cliente;
import com.ec.entidad.Persona;
import com.ec.entidad.TipoServicio;
import com.ec.entidad.Usuario;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioCliente;
import com.ec.servicio.ServicioPersona;
import com.ec.servicio.ServicioTipoServicio;
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
public class NuevoTipoServicio {
    
    @Wire
    Window windowTipoServicio;
    
    ServicioTipoServicio servicioTipoServicio = new ServicioTipoServicio();
    
    private TipoServicio tipoServicio = new TipoServicio();
    private String accion = "create";
    UserCredential credential = new UserCredential();
    
    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") TipoServicio valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
        if (valor != null) {
            
            this.tipoServicio = valor;
            accion = "update";
        } else {
            this.tipoServicio = new TipoServicio();
            accion = "create";
        }
        
    }
    
    @Command
    public void guardar() {
        /*getCliNombre es el nombre comercial*/
        if (tipoServicio.getTipsDescripcion() != null) {
            if (accion.equals("create")) {
                servicioTipoServicio.crear(tipoServicio);
                windowTipoServicio.detach();
            } else {
                servicioTipoServicio.modificar(tipoServicio);
                windowTipoServicio.detach();
            }
            
        } else {
            Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
        }
    }
    
    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }
    
    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
    
    public String getAccion() {
        return accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public UserCredential getCredential() {
        return credential;
    }
    
    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }
    
}
