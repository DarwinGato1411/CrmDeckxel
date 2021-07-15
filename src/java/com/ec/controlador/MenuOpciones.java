/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

/**
 *
 * @author gato
 */
public class MenuOpciones extends SelectorComposer<Component> {

   
    UserCredential credential = new UserCredential();
    private String acceso = "";

    public MenuOpciones() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);


    }

   
    @Listen("onClick = #btnAdministrar")
    public void btnAdministrar() {
        Executions.sendRedirect("/administrar/administrar.zul");
    }
    @Listen("onClick = #btnDominio")
    public void btnDominio() {
        Executions.sendRedirect("/crm/admdominio.zul");
    }
    @Listen("onClick = #btnSeguimiento")
    public void btnSeguimiento() {
        Executions.sendRedirect("/crm/admseguimiento.zul");
    }
    @Listen("onClick = #btnCotizacion")
    public void btnCotizacion() {
        Executions.sendRedirect("/venta/cotizar.zul");
    }
    @Listen("onClick = #btnCotizacionGenerada")
    public void btnCotizacionGenerada() {
//           CotizacionGenerada.SIGLAESTADO="GN";
        Executions.sendRedirect("/venta/generadas.zul");
    }
   @Listen("onClick = #btnCotizacionEnviada")
    public void btnCotizacionEnviada() {
//        CotizacionGenerada.SIGLAESTADO="EN";
        Executions.sendRedirect("/venta/enviada.zul");
    }
   @Listen("onClick = #btnCotizacionAprobada")
    public void btnCotizacionAprobada() {
//        CotizacionGenerada.SIGLAESTADO="EN";
        Executions.sendRedirect("/venta/aprobadas.zul");
    }
    @Listen("onClick = #btnCotizacionFinalizada")
    public void btnCotizacionFinalizada() {
//        CotizacionGenerada.SIGLAESTADO="EN";
        Executions.sendRedirect("/venta/finalizadas.zul");
    }
    @Listen("onClick = #btnCotizacionFacturada")
    public void btnCotizacionFacturada() {
//        CotizacionGenerada.SIGLAESTADO="EN";
        Executions.sendRedirect("/venta/facturada.zul");
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }
}
