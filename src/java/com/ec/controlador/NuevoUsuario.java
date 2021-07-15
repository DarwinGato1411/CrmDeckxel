/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Usuario;
import com.ec.servicio.ServicioUsuario;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class NuevoUsuario {

    ServicioUsuario servicioUsuario = new ServicioUsuario();
    private Usuario usuarioSistema = new Usuario();
    private String tipoUSuario = "1";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("usuario") Usuario usuarioSistema, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (usuarioSistema != null) {
            this.usuarioSistema = usuarioSistema;
            tipoUSuario = this.usuarioSistema.getUsuNivel().toString();
        } else {
            this.usuarioSistema = new Usuario();

        }
    }

    public Usuario getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(Usuario usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

  

    public String getTipoUSuario() {
        return tipoUSuario;
    }

    public void setTipoUSuario(String tipoUSuario) {
        this.tipoUSuario = tipoUSuario;
    }

    @Command
    @NotifyChange("usuarioSistema")
    public void guardar() {
        if (usuarioSistema != null && !usuarioSistema.getUsuNombre().equals("")) {
            usuarioSistema.setUsuNivel(Integer.valueOf(tipoUSuario));

            if (Integer.valueOf(tipoUSuario) == 1) {
                usuarioSistema.setUsuTipoUsuario("ADMINISTRADOR");
            } else if (Integer.valueOf(tipoUSuario) == 2) {
                usuarioSistema.setUsuTipoUsuario("VENTAS");
            }
            servicioUsuario.modificar(usuarioSistema);
            usuarioSistema = new Usuario();
//            Messagebox.show("Guardado con exito");

        } else {
              Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
        }
    }
}
