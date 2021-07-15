/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cotizacion;
import com.ec.entidad.EstadoCotizacion;
import com.ec.servicio.HelperPersistencia;
import com.ec.servicio.ServicioCotizacion;
import com.ec.servicio.ServicioEstadoCotizacion;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class CotizacionGenerada {
    
    public static String SIGLAESTADO = "GN";
    private String busCliente = "";
    private String numeroFactura = "";
    private Integer busNumero = 0;
    ServicioCotizacion servicioCotizacion = new ServicioCotizacion();
    
    ServicioEstadoCotizacion servicioEstadoCotizacion = new ServicioEstadoCotizacion();
    AMedia fileContent = null;
    Connection con = null;
    //ordenes sin cotizar
    List<Cotizacion> listaCotizacionGenerada = new ArrayList<Cotizacion>();
    private EstadoCotizacion estadoCotizacion = new EstadoCotizacion();
    
    public CotizacionGenerada() {
        findAllGenerada();
        estadoCotizacion = servicioEstadoCotizacion.findBySigla(SIGLAESTADO);
    }
    
    private void findAllGenerada() {
        estadoCotizacion = servicioEstadoCotizacion.findBySigla(SIGLAESTADO);
        listaCotizacionGenerada = servicioCotizacion.findAllGenerada(estadoCotizacion);
    }
    
    @Command
    public void reporteCotizacion(@BindingParam("valor") Cotizacion valor) throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, NamingException {
        
        EntityManager emf = HelperPersistencia.getEMF();
        emf.getTransaction().begin();
        try {
            
            con = emf.unwrap(Connection.class);
            if (valor.getCotNumero() != null) {

                //con = conexionReportes.conexion();
                String reportFile = Executions.getCurrent().getDesktop().getWebApp()
                        .getRealPath("/reportes");
                String reportPath = "";
                reportPath = reportFile + "/cotizacion.jasper";
                
                Map<String, Object> parametros = new HashMap<String, Object>();

                //  parametros.put("codUsuario", String.valueOf(credentialLog.getAdUsuario().getCodigoUsuario()));
                parametros.put("numcotizacion", valor.getCotNumero());
                
                if (con != null) {
                    System.out.println("Conexión Realizada Correctamenteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                }
                FileInputStream is = null;
                is = new FileInputStream(reportPath);
                
                byte[] buf = JasperRunManager.runReportToPdf(is, parametros, con);
                InputStream mediais = new ByteArrayInputStream(buf);
                AMedia amedia = new AMedia("Reporte Orden Produccion", "pdf", "application/pdf", mediais);
                fileContent = amedia;
                final HashMap<String, AMedia> map = new HashMap<String, AMedia>();
//para pasar al visor
                map.put("pdf", fileContent);
                org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                        "/venta/ContenedorReporte.zul", null, map);
                window.doModal();
                // con.close();

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error " + e.getMessage());
        } catch (JRException e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            if (emf != null) {
                emf.getTransaction().commit();
                
            }
        }
    }
    
    @Command
    public void eliminar(@BindingParam("valor") Cotizacion valor) throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
            servicioCotizacion.eliminar(valor);
            Clients.showNotification("Eliminado con éxito",
                    "Info", null, "end_center", 3000, true);
        } catch (Exception e) {
            Messagebox.show("Eliminado con exito" + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }

    @Command
    @NotifyChange({"listaCotizacionGenerada"})
    public void cambioEstado(@BindingParam("valor") Cotizacion valor) throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
            
            if (Messagebox.show("¿Seguro que desea cambiar de estado?", "Atención", Messagebox.YES | Messagebox.NO, Messagebox.INFORMATION) == Messagebox.YES) {
                EstadoCotizacion estado = servicioEstadoCotizacion.findBySigla("EN");
                valor.setIdEstadoCotizacion(estado);
                servicioCotizacion.modificar(valor);
                  findAllGenerada();
//                Clients.showNotification("Eliminado correctamente", "Info", null, "end_center", 3000, true);
               
            } else {
                System.out.println("No entra a  borrar");
            }
        } catch (Exception e) {
            Messagebox.show("Eliminado con exito" + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    @Command
    public void cambiarEstadoCotizacion(@BindingParam("valor") Cotizacion valor) throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
            final HashMap<String, Cotizacion> map = new HashMap<String, Cotizacion>();
            
            map.put("cotizacion", valor);
            org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                    "/cotizacion/estadoCotizacion.zul", null, map);
            window.doModal();
            window.detach();
        } catch (Exception e) {
            Messagebox.show("Error " + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    @Command
    @NotifyChange({"listaCotizacionGenerada"})
    public void buscarListaPorCliente() throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
            findLikePerNombre();
        } catch (Exception e) {
            Messagebox.show("Error " + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    private void findLikePerNombre() {
        listaCotizacionGenerada = servicioCotizacion.findLikePerNombre(busCliente, estadoCotizacion);
    }
    
    @Command
    @NotifyChange({"listaOrdenProduccion"})
    public void buscarListaOrdenesAll() throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
//            consultarListaOrdenPendientes();

        } catch (Exception e) {
            Messagebox.show("Error " + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    @Command
    @NotifyChange({"listaCotizacionGenerada"})
    public void buscarListaPorNumero() throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
            buscarForNumero();
        } catch (Exception e) {
            Messagebox.show("Error " + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    private void buscarForNumero() {
        listaCotizacionGenerada = servicioCotizacion.findCotNumero(busNumero, estadoCotizacion);
    }
    
    public AMedia getFileContent() {
        return fileContent;
    }
    
    public void setFileContent(AMedia fileContent) {
        this.fileContent = fileContent;
    }

//    @Command
//    public void enviarMail() {
//
//        try {
//
//            MailerClass mailerClass = new MailerClass();
//            mailerClass.sendMail("darwinvinicio14_11@hotmail.com", "IMAGENDIGITAL");
//            Messagebox.show("Envio con exito");
//        } catch (Exception e) {
//            Messagebox.show("Fallo envio" + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
//        }
//    }
    public String getBusCliente() {
        return busCliente;
    }
    
    public void setBusCliente(String busCliente) {
        this.busCliente = busCliente;
    }
    
    public Integer getBusNumero() {
        return busNumero;
    }
    
    public void setBusNumero(Integer busNumero) {
        this.busNumero = busNumero;
    }
    
    @Command
    public void modificarCotizacion(@BindingParam("valor") Cotizacion valor) throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
//            
            final HashMap<String, Cotizacion> map = new HashMap<String, Cotizacion>();
            
            map.put("valor", valor);
            org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                    "/cotizacion/actualizarOrdenSinCotizar.zul", null, map);
            window.doModal();
            
        } catch (Exception e) {
            Messagebox.show("Error " + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    private Date fechaInicio = new Date();
    private Date fechaFin = new Date();
    
    public Date getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public Date getFechaFin() {
        return fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    @Command
    @NotifyChange({"listaCotizacionGenerada"})
    public void buscarListaPorFechas() throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
            findBetweenFecha();
        } catch (Exception e) {
            Messagebox.show("Error " + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    private void findBetweenFecha() {
        listaCotizacionGenerada = servicioCotizacion.findBetweenFecha(fechaInicio, fechaFin, estadoCotizacion);
    }
    
    @Command
    @NotifyChange({"listaCotizacionGenerada"})
    public void buscarListaPorNombreFechas() throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
            buscarForNombreFecha();
        } catch (Exception e) {
            Messagebox.show("Error " + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    private void buscarForFecha() {
//        listaCotizacionGenerada = servicioCotizacion.findAll(fechaInicio, fechaFin);
    }
    
    private void buscarForNombreFecha() {
//        listaCotizacionGenerada = servicioOrdenSinCotizar.findAllNombreFechasFacturadaGenerada(fechaInicio, fechaFin, busCliente);
    }
    
    public String getNumeroFactura() {
        return numeroFactura;
    }
    
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    
    private String nombreComercial = "";
    
    @Command
    @NotifyChange({"listaCotizacionGenerada", "listaCotizacionGeneradaModel"})
    public void buscarListaPorNombreComercial() throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        try {
            findAllGeneradanombreComercial();
        } catch (Exception e) {
            Messagebox.show("Error " + e.toString(), "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    private void findAllGeneradanombreComercial() {
        listaCotizacionGenerada = servicioCotizacion.findLikeNombreComercial(nombreComercial, estadoCotizacion);
    }
    
    public String getNombreComercial() {
        return nombreComercial;
    }
    
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }
    
    public List<Cotizacion> getListaCotizacionGenerada() {
        return listaCotizacionGenerada;
    }
    
    public void setListaCotizacionGenerada(List<Cotizacion> listaCotizacionGenerada) {
        this.listaCotizacionGenerada = listaCotizacionGenerada;
    }
    
    public EstadoCotizacion getEstadoCotizacion() {
        return estadoCotizacion;
    }
    
    public void setEstadoCotizacion(EstadoCotizacion estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }
    
}
