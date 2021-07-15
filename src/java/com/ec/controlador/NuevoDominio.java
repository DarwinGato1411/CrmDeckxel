/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cliente;
import com.ec.entidad.DetalleDominio;
import com.ec.entidad.Dominio;
import com.ec.entidad.TipoServicio;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioCliente;
import com.ec.servicio.ServicioDetalleDominio;
import com.ec.servicio.ServicioDominio;
import com.ec.servicio.ServicioTipoServicio;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoDominio {

    @Wire
    Window wDominio;
    ServicioCliente servicioCliente = new ServicioCliente();
    ServicioDominio servicioDominio = new ServicioDominio();
    ServicioTipoServicio servicioTipoServicio = new ServicioTipoServicio();
    ServicioDetalleDominio servicioDetalleDominio = new ServicioDetalleDominio();

    private List<Cliente> listaClientesAll = new ArrayList<Cliente>();
    private List<TipoServicio> listaTipoServicios = new ArrayList<TipoServicio>();
    private Cliente clienteSelected = new Cliente();
    private TipoServicio tipoServicioSelected = new TipoServicio();
    private String buscarNombre = "";
    private DetalleDominio detalleDominio = new DetalleDominio();
    private Dominio dominio = new Dominio();
    private String tipoUSuario = "1";
    private String accion = "create";
    private BigDecimal costo = BigDecimal.ZERO;

    UserCredential credential = new UserCredential();

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Dominio dominio, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (dominio != null) {
            this.dominio = dominio;
            this.clienteSelected = dominio.getIdCliente();
            this.tipoServicioSelected = dominio.getIdTipoServicio();
            accion = "update";

        } else {
            this.dominio = new Dominio();
            this.clienteSelected = null;
            this.clienteSelected = null;

            accion = "create";

        }
        cosultarCliente("");
    }

    public NuevoDominio() {

        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
    }


    /*ADMINISTRAR USUARIO*/
    private void cosultarCliente(String buscar) {
        listaClientesAll = servicioCliente.findByLikeCliNombreComercial(buscar);
        listaTipoServicios = servicioTipoServicio.findByLikeTipsDescripcion(buscar);
    }

    public List<Cliente> getListaClientesAll() {
        return listaClientesAll;
    }

    public void setListaClientesAll(List<Cliente> listaClientesAll) {
        this.listaClientesAll = listaClientesAll;
    }

    public String getBuscarNombre() {
        return buscarNombre;
    }

    public void setBuscarNombre(String buscarNombre) {
        this.buscarNombre = buscarNombre;
    }

    public Cliente getClienteSelected() {
        return clienteSelected;
    }

    public void setClienteSelected(Cliente clienteSelected) {
        this.clienteSelected = clienteSelected;
    }

    public String getTipoUSuario() {
        return tipoUSuario;
    }

    public void setTipoUSuario(String tipoUSuario) {
        this.tipoUSuario = tipoUSuario;
    }

    @Command
    @NotifyChange("dominio")
    public void guardar() {
        if (accion.equals("create")) {

            if (dominio != null && clienteSelected != null) {

                Date nuevaFecha = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(dominio.getDomFechaRegistro());
                cal.add(Calendar.YEAR, 1);
                nuevaFecha = cal.getTime();               
                dominio.setIdCliente(clienteSelected);
                dominio.setDomFechaCaduca(nuevaFecha);
                dominio.setIdTipoServicio(tipoServicioSelected);

                servicioDominio.crear(dominio);
                detalleDominio.setIdDominio(dominio);
                detalleDominio.setDetdFechaRenovacion(dominio.getDomFechaRegistro());
                detalleDominio.setDetcTotal(costo);
                detalleDominio.setDetcDescripcion(dominio.getDomDescripcion());
                servicioDetalleDominio.crear(detalleDominio);
                wDominio.detach();
            } else {
                Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
            }
        } else {
            if (dominio != null && clienteSelected != null) {
                dominio.setIdCliente(clienteSelected);
                detalleDominio.setDetcTotal(costo);
                dominio.setIdTipoServicio(tipoServicioSelected);
                servicioDominio.modificar(dominio);
                wDominio.detach();
            } else {
                Clients.showNotification("Verifique su informacion", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
            }

        }
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

    public List<TipoServicio> getListaTipoServicios() {
        return listaTipoServicios;
    }

    public void setListaTipoServicios(List<TipoServicio> listaTipoServicios) {
        this.listaTipoServicios = listaTipoServicios;
    }

    public TipoServicio getTipoServicioSelected() {
        return tipoServicioSelected;
    }

    public void setTipoServicioSelected(TipoServicio tipoServicioSelected) {
        this.tipoServicioSelected = tipoServicioSelected;
    }

    public DetalleDominio getDetalleDominio() {
        return detalleDominio;
    }

    public void setDetalleDominio(DetalleDominio detalleDominio) {
        this.detalleDominio = detalleDominio;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

}
