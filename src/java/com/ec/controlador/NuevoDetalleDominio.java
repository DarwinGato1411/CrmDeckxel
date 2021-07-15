/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.DetalleDominio;
import com.ec.entidad.Dominio;
import com.ec.servicio.ServicioDetalleDominio;
import com.ec.servicio.ServicioDominio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoDetalleDominio {

    @Wire
    Window wDetalleDominio;
    ServicioDominio servicioDominio = new ServicioDominio();
    ServicioDetalleDominio servicioDetalleDominio = new ServicioDetalleDominio();

    private List<DetalleDominio> listaDetalleDominios = new ArrayList<DetalleDominio>();

    private Dominio dominio = new Dominio();
    private DetalleDominio detalleDominio = new DetalleDominio();
    private String accion = "create";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Dominio dominio, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);

        this.dominio = dominio;
        cosultarDetalles();

    }


    /*ADMINISTRAR USUARIO*/
    private void cosultarDetalles() {
        listaDetalleDominios = servicioDetalleDominio.findDetalleForDominio(dominio);

    }

    public List<DetalleDominio> getListaDetalleDominios() {
        return listaDetalleDominios;
    }

    public void setListaDetalleDominios(List<DetalleDominio> listaDetalleDominios) {
        this.listaDetalleDominios = listaDetalleDominios;
    }

    @Command
    @NotifyChange({"dominio","detalleDominio", "listaDetalleDominios"})
    public void guardar() {
//        System.out.println("dominio " + dominio);
//        System.out.println("detalleDominio " + detalleDominio.getDetcTotal());
        if (dominio != null
                && detalleDominio.getDetcTotal() != null) {
            detalleDominio.setIdDominio(dominio);
            servicioDetalleDominio.crear(detalleDominio);
            /*PROMIXA RENOVACION*/
            Date nuevaFecha = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(detalleDominio.getDetdFechaRenovacion());
            cal.add(Calendar.YEAR, 1);
            nuevaFecha = cal.getTime();
            dominio.setDomFechaCaduca(nuevaFecha);
            dominio.setDomDescripcion(detalleDominio.getDetcDescripcion());
            servicioDominio.modificar(dominio);
            cosultarDetalles();
            detalleDominio= new DetalleDominio();

        } else {
            Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
        }

    }

    @Command
    @NotifyChange("listaDetalleDominios")
    public void eliminarDetalle(@BindingParam("valor") DetalleDominio valor) {
//        if (Messagebox.show("¿Esta seguro desea consolidar?", "Atención", Messagebox.YES | Messagebox.NO, Messagebox.INFORMATION) == Messagebox.YES) {
        servicioDetalleDominio.eliminar(valor);
        Clients.showNotification("Eliminado con exito",
                Clients.NOTIFICATION_TYPE_INFO, null, "after_center", 1000, true);
        cosultarDetalles();
//        }
    }

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public DetalleDominio getDetalleDominio() {
        return detalleDominio;
    }

    public void setDetalleDominio(DetalleDominio detalleDominio) {
        this.detalleDominio = detalleDominio;
    }

}
