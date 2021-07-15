/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.DetalleSeguimiento;
import com.ec.entidad.Seguimiento;
import com.ec.servicio.ServicioDetalleSeguimiento;
import com.ec.servicio.ServicioSeguimiento;
import java.util.ArrayList;
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
public class NuevoDetalleSeguimiento {

    @Wire
    Window wSeguimiento;
    ServicioSeguimiento servicioSeguimiento = new ServicioSeguimiento();
    ServicioDetalleSeguimiento servicioDetalleSeguimiento = new ServicioDetalleSeguimiento();

    private List<DetalleSeguimiento> listaDetalleSeguimientos = new ArrayList<DetalleSeguimiento>();

    private Seguimiento seguimiento = new Seguimiento();
    private DetalleSeguimiento detalleSeguimiento = new DetalleSeguimiento();
    private String accion = "create";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Seguimiento valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);

        this.seguimiento = valor;
        cosultarDetalles();

    }


    /*ADMINISTRAR USUARIO*/
    private void cosultarDetalles() {
        listaDetalleSeguimientos = servicioDetalleSeguimiento.findDetalleForDominio(seguimiento);

    }

    public List<DetalleSeguimiento> getListaDetalleSeguimientos() {
        return listaDetalleSeguimientos;
    }

    public void setListaDetalleSeguimientos(List<DetalleSeguimiento> listaDetalleSeguimientos) {
        this.listaDetalleSeguimientos = listaDetalleSeguimientos;
    }

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public DetalleSeguimiento getDetalleSeguimiento() {
        return detalleSeguimiento;
    }

    public void setDetalleSeguimiento(DetalleSeguimiento detalleSeguimiento) {
        this.detalleSeguimiento = detalleSeguimiento;
    }

    @Command
    @NotifyChange({"seguimiento", "detalleSeguimiento", "listaDetalleSeguimientos"})
    public void guardar() {
//        System.out.println("dominio " + dominio);
//        System.out.println("detalleSeguimiento " + detalleSeguimiento.getDetcTotal());
        if (seguimiento != null
                && detalleSeguimiento.getDetsObservacion() != null) {
            detalleSeguimiento.setIdSeguimiento(seguimiento);
            servicioDetalleSeguimiento.crear(detalleSeguimiento);
            /*PROMIXA RENOVACION*/

            seguimiento.setSegFechaProxima(detalleSeguimiento.getDetsFechaProxima());
            seguimiento.setSegDescripcion(detalleSeguimiento.getDetsObservacion());
            servicioSeguimiento.modificar(seguimiento);
            cosultarDetalles();
            detalleSeguimiento = new DetalleSeguimiento();
        } else {
            Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
        }

    }

    @Command
    @NotifyChange("listaDetalleSeguimientos")
    public void eliminarDetalle(@BindingParam("valor") DetalleSeguimiento valor) {
//        if (Messagebox.show("¿Esta seguro desea consolidar?", "Atención", Messagebox.YES | Messagebox.NO, Messagebox.INFORMATION) == Messagebox.YES) {
        servicioDetalleSeguimiento.eliminar(valor);
        Clients.showNotification("Eliminado con exito",
                Clients.NOTIFICATION_TYPE_INFO, null, "after_center", 1000, true);
        cosultarDetalles();
//        }
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
