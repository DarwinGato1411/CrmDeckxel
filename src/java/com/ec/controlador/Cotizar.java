/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.dao.DetalleFacturaDAO;
import com.ec.entidad.Cliente;
import com.ec.entidad.Cotizacion;
import com.ec.entidad.Empresa;
import com.ec.entidad.Producto;

import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.HelperPersistencia;
import com.ec.servicio.ServicioCliente;
import com.ec.servicio.ServicioCotizacion;
import com.ec.servicio.ServicioEmpresa;
import com.ec.servicio.ServicioEstadoCotizacion;
import com.ec.servicio.ServicioProducto;
import com.ec.untilitario.ArchivoUtils;
import com.ec.untilitario.MailerClass;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class Cotizar {

    @Wire
    Window windowClienteBuscar;
    @Wire
    Window windowProductoBuscar;

    //buscar cliente
    ServicioEmpresa servicioEmpresa = new ServicioEmpresa();
    ServicioCliente servicioCliente = new ServicioCliente();
    ServicioCotizacion servicioCotizacion = new ServicioCotizacion();
    ServicioEstadoCotizacion servicioEstadoCotizacion = new ServicioEstadoCotizacion();
    public Cliente clienteBuscado = new Cliente("");
    private List<Cliente> listaClientesAll = new ArrayList<Cliente>();
    private String buscarNombre = "";
    private String buscarRazonSocial = "";
    private String buscarCedula = "";
    public static String buscarCliente = "";
    //busacar producto
    ServicioProducto servicioProducto = new ServicioProducto();
    private List<Producto> listaProducto = new ArrayList<Producto>();
    private String buscarNombreProd = "";
    private String buscarCodigoProd = "";
    private Producto productoBuscado = new Producto();
    public static String codigoBusqueda = "";
    //crear un factura nueva
    private Cotizacion factura = new Cotizacion();
    private DetalleFacturaDAO detalleFacturaDAO = new DetalleFacturaDAO();
    private ListModelList<DetalleFacturaDAO> listaDetalleFacturaDAOMOdel;
    private List<DetalleFacturaDAO> listaDetalleFacturaDAODatos = new ArrayList<DetalleFacturaDAO>();
    private Set<DetalleFacturaDAO> registrosSeleccionados = new HashSet<DetalleFacturaDAO>();
    //valorTotalCotizacion
    private BigDecimal valorTotalCotizacion = BigDecimal.ZERO;
    private BigDecimal subTotalCotizacion = BigDecimal.ZERO;
    private BigDecimal ivaCotizacion = BigDecimal.ZERO;
    //Cabecera de la factura
    private String estdoFactura = "PA";
    private String tipoVenta = "FACT";
    private String facturaDescripcion = "";
    private Integer numeroFactura = 0;
    private String numeroFacturaText = "";
    private Integer numeroProforma = 0;
    private Date fechafacturacion = new Date();
    //usuario que factura
    UserCredential credential = new UserCredential();

//reporte
    AMedia fileContent = null;
    Connection con = null;
    //cambio
    private BigDecimal cobro = BigDecimal.ZERO;
    private BigDecimal cambio = BigDecimal.ZERO;
    // OBJETOS PARA LOS BOTONES DEL MENU PRINCIPAL
    private Producto p1 = new Producto();
    private Producto p2 = new Producto();
    private Producto p3 = new Producto();
    private Producto p4 = new Producto();
    private Producto p5 = new Producto();
    private Producto p6 = new Producto();
    private Producto p7 = new Producto();
    private Producto p8 = new Producto();
    private Producto p9 = new Producto();
    private Producto p10 = new Producto();
    private Producto p11 = new Producto();
    private Producto p12 = new Producto();
    private Producto p13 = new Producto();
    private Producto p14 = new Producto();
    private Producto p15 = new Producto();
    private Producto p16 = new Producto();
    private Producto p17 = new Producto();
    private Producto p18 = new Producto();
    private Producto p19 = new Producto();
    private Producto p20 = new Producto();
    private Producto p21 = new Producto();
    private Producto p22 = new Producto();
    private Producto p23 = new Producto();
    private Producto p24 = new Producto();

    /*Ruta de PDF fisico*/
    private static String FOLDERPDF = "COTIZACIONESPDF";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") String valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor == null) {
            ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).clear();
        } else {
        }

        FindClienteLikeRazon();
        //para establecer el cliente final
        verificarSecNumeracion();
        agregarRegistroVacio();
        fechafacturacion = new Date();
        cargarProductosPrincipales();

    }
//  OBTIENE LOS PRODUCTO PARA MOSTRAR EN EL PANEL PRINCIPAL

    private void cargarProductosPrincipales() {

        List<Producto> lstPrincipal = servicioProducto.findProductoPrincipal();
        if (lstPrincipal.size() > 0) {
            p1 = lstPrincipal.get(0);
        }
        if (lstPrincipal.size() > 1) {
            p2 = lstPrincipal.get(1);
        }
        if (lstPrincipal.size() > 2) {
            p3 = lstPrincipal.get(2);
        }
        if (lstPrincipal.size() > 3) {
            p4 = lstPrincipal.get(3);
        }
        if (lstPrincipal.size() > 4) {
            p5 = lstPrincipal.get(4);
        }
        if (lstPrincipal.size() > 5) {
            p6 = lstPrincipal.get(5);
        }
        if (lstPrincipal.size() > 6) {
            p7 = lstPrincipal.get(6);
        }
        if (lstPrincipal.size() > 7) {
            p8 = lstPrincipal.get(7);
        }
        if (lstPrincipal.size() > 8) {
            p9 = lstPrincipal.get(8);
        }
        if (lstPrincipal.size() > 9) {
            p10 = lstPrincipal.get(9);
        }
        if (lstPrincipal.size() > 10) {
            p11 = lstPrincipal.get(10);
        }
        if (lstPrincipal.size() > 11) {
            p12 = lstPrincipal.get(11);
        }
        if (lstPrincipal.size() > 12) {
            p13 = lstPrincipal.get(12);
        }
        if (lstPrincipal.size() > 13) {
            p14 = lstPrincipal.get(13);
        }
        if (lstPrincipal.size() > 14) {
            p15 = lstPrincipal.get(14);
        }
        if (lstPrincipal.size() > 15) {
            p16 = lstPrincipal.get(15);
        }
        if (lstPrincipal.size() > 16) {
            p17 = lstPrincipal.get(16);
        }
        if (lstPrincipal.size() > 17) {
            p18 = lstPrincipal.get(17);
        }
        if (lstPrincipal.size() > 18) {
            p19 = lstPrincipal.get(18);
        }
        if (lstPrincipal.size() > 19) {
            p20 = lstPrincipal.get(19);
        }
        if (lstPrincipal.size() > 20) {
            p21 = lstPrincipal.get(20);
        }
        if (lstPrincipal.size() > 21) {
            p22 = lstPrincipal.get(21);
        }
        if (lstPrincipal.size() > 22) {
            p23 = lstPrincipal.get(22);
        }
        if (lstPrincipal.size() > 23) {
            p24 = lstPrincipal.get(23);
        }

    }

    @Command
    @NotifyChange({"listaDetalleFacturaDAOMOdel", "subTotalCotizacion", "ivaCotizacion", "valorTotalCotizacion"})
    public void agregarItemPrincipal(@BindingParam("valor") String bandera) {

        BigDecimal factorIva = (BigDecimal.valueOf(14L).divide(BigDecimal.valueOf(100.0)));
        List<DetalleFacturaDAO> listaPedido = listaDetalleFacturaDAOMOdel.getInnerList();

        for (DetalleFacturaDAO item : listaPedido) {
            if (item.getProducto() == null) {
                ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).remove(item);
                break;
            }
        }

        codigoBusqueda = bandera;
        productoBuscado = servicioProducto.findByProdCodigo(codigoBusqueda);
        System.out.println("cliente panel  " + clienteBuscado);
        if (productoBuscado != null) {
            DetalleFacturaDAO nuevoRegistro = new DetalleFacturaDAO();
            nuevoRegistro.setCantidad(BigDecimal.ONE);
            nuevoRegistro.setProducto(productoBuscado);
            nuevoRegistro.setDescripcion(productoBuscado.getProdNombre());
//            nuevoRegistro.setCantidad(BigDecimal.ZERO);
            nuevoRegistro.setCodigo(productoBuscado.getProdSerie());

            nuevoRegistro.setSubTotal(productoBuscado.getProdCostoVenta());
            nuevoRegistro.setTotal(nuevoRegistro.getCantidad().multiply(nuevoRegistro.getSubTotal()));
            nuevoRegistro.setDetIva(nuevoRegistro.getSubTotal().multiply(factorIva));
            nuevoRegistro.setDetTotalconiva(nuevoRegistro.getTotal().add(nuevoRegistro.getDetIva()));
            nuevoRegistro.setTipoVenta("NORMAL");

            //nuevoRegistro.setSubTotal(productoBuscado.getPordCostoVentaFinal());
            ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).add(nuevoRegistro);

            //ingresa un registro vacio
            boolean registroVacio = true;
            List<DetalleFacturaDAO> listaPedidoPost = listaDetalleFacturaDAOMOdel.getInnerList();

            for (DetalleFacturaDAO item : listaPedidoPost) {
                if (item.getProducto() == null) {
                    registroVacio = false;
                    break;
                }
            }

            System.out.println("existe un vacio " + registroVacio);
            if (registroVacio) {
                DetalleFacturaDAO nuevoRegistroPost = new DetalleFacturaDAO();
                nuevoRegistroPost.setProducto(productoBuscado);
                nuevoRegistroPost.setCantidad(BigDecimal.ZERO);
                nuevoRegistroPost.setSubTotal(BigDecimal.ZERO);
                nuevoRegistroPost.setDetIva(BigDecimal.ZERO);
                nuevoRegistroPost.setDetTotalconiva(BigDecimal.ZERO);
                nuevoRegistroPost.setDescripcion("");
                nuevoRegistroPost.setProducto(null);
                ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).add(nuevoRegistroPost);
            }
        }
        calcularValoresTotales();
        codigoBusqueda = "";
    }

    public Cotizar() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
        getDetallefactura();

        numeroFactura();
        clienteBuscado = servicioCliente.findClienteLikeCedula("999999999");
    }

    @Command
    @NotifyChange({"numeroFactura"})
    public void calcularNumeroFactTexto() {
        numeroFacturaTexto();
    }

    private void numeroFacturaTexto() {
        numeroFacturaText = "";
        for (int i = numeroFactura.toString().length(); i < 9; i++) {
            numeroFacturaText = numeroFacturaText + "0";
        }
        numeroFacturaText = numeroFacturaText + numeroFactura;
        System.out.println("nuemro texto " + numeroFacturaText);
    }

    private void numeroFactura() {
        Cotizacion recuperada = servicioCotizacion.findUltimaCotNumero();
        if (recuperada != null) {
            // System.out.println("numero de factura " + recuperada);
            numeroFactura = recuperada.getCotNumero() + 1;
            numeroFacturaTexto();
        } else {
            numeroFactura = 1;
            numeroFacturaText = "000000001";
        }
    }

    private void verificarSecNumeracion() {

        numeroFactura();

    }

    @Command
    @NotifyChange({"numeroFactura", "numeroProforma", "clienteBuscado"})
    public void verificarNumeracion() {
        verificarSecNumeracion();
    }

    private void getDetallefactura() {
        setListaDetalleFacturaDAOMOdel(new ListModelList<DetalleFacturaDAO>(getListaDetalleFacturaDAODatos()));
        ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).setMultiple(true);
    }

    @Command
    public void seleccionarRegistros() {
        registrosSeleccionados = ((ListModelList<DetalleFacturaDAO>) getListaDetalleFacturaDAOMOdel()).getSelection();
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public String getBuscarNombreProd() {
        return buscarNombreProd;
    }

    public void setBuscarNombreProd(String buscarNombreProd) {
        this.buscarNombreProd = buscarNombreProd;
    }

    private void FindClienteLikeRazon() {
        listaClientesAll = servicioCliente.findByLikeCliNombreComercial(buscarRazonSocial);
    }

    private void FindClienteLikeCedula() {
        listaClientesAll = servicioCliente.findByCliCedula(buscarCedula);
    }

    public Cliente getClienteBuscado() {
        return clienteBuscado;
    }

    public void setClienteBuscado(Cliente clienteBuscado) {
        this.clienteBuscado = clienteBuscado;
    }

    public String getBuscarNombre() {
        return buscarNombre;
    }

    public void setBuscarNombre(String buscarNombre) {
        this.buscarNombre = buscarNombre;
    }

    public String getBuscarRazonSocial() {
        return buscarRazonSocial;
    }

    public void setBuscarRazonSocial(String buscarRazonSocial) {
        this.buscarRazonSocial = buscarRazonSocial;
    }

    public String getBuscarCedula() {
        return buscarCedula;
    }

    public void setBuscarCedula(String buscarCedula) {
        this.buscarCedula = buscarCedula;
    }

    public static String getBuscarCliente() {
        return buscarCliente;
    }

    public static void setBuscarCliente(String buscarCliente) {
        Cotizar.buscarCliente = buscarCliente;
    }

    public List<Cliente> getListaClientesAll() {
        return listaClientesAll;
    }

    public void setListaClientesAll(List<Cliente> listaClientesAll) {
        this.listaClientesAll = listaClientesAll;
    }

    public DetalleFacturaDAO getDetalleFacturaDAO() {
        return detalleFacturaDAO;
    }

    public void setDetalleFacturaDAO(DetalleFacturaDAO detalleFacturaDAO) {
        this.detalleFacturaDAO = detalleFacturaDAO;
    }

    public ListModelList<DetalleFacturaDAO> getListaDetalleFacturaDAOMOdel() {
        return listaDetalleFacturaDAOMOdel;
    }

    public void setListaDetalleFacturaDAOMOdel(ListModelList<DetalleFacturaDAO> listaDetalleFacturaDAOMOdel) {
        this.listaDetalleFacturaDAOMOdel = listaDetalleFacturaDAOMOdel;
    }

    public List<DetalleFacturaDAO> getListaDetalleFacturaDAODatos() {
        return listaDetalleFacturaDAODatos;
    }

    public void setListaDetalleFacturaDAODatos(List<DetalleFacturaDAO> listaDetalleFacturaDAODatos) {
        this.listaDetalleFacturaDAODatos = listaDetalleFacturaDAODatos;
    }

    public Set<DetalleFacturaDAO> getRegistrosSeleccionados() {
        return registrosSeleccionados;
    }

    public void setRegistrosSeleccionados(Set<DetalleFacturaDAO> registrosSeleccionados) {
        this.registrosSeleccionados = registrosSeleccionados;
    }

    public Producto getProductoBuscado() {
        return productoBuscado;
    }

    public void setProductoBuscado(Producto productoBuscado) {
        this.productoBuscado = productoBuscado;
    }

    public static String getCodigoBusqueda() {
        return codigoBusqueda;
    }

    public static void setCodigoBusqueda(String codigoBusqueda) {
        Cotizar.codigoBusqueda = codigoBusqueda;
    }

    public String getBuscarCodigoProd() {
        return buscarCodigoProd;
    }

    public void setBuscarCodigoProd(String buscarCodigoProd) {
        this.buscarCodigoProd = buscarCodigoProd;
    }

    public BigDecimal getValorTotalCotizacion() {
        return valorTotalCotizacion;
    }

    public void setValorTotalCotizacion(BigDecimal valorTotalCotizacion) {
        this.valorTotalCotizacion = valorTotalCotizacion;
    }

    public BigDecimal getSubTotalCotizacion() {
        return subTotalCotizacion;
    }

    public void setSubTotalCotizacion(BigDecimal subTotalCotizacion) {
        this.subTotalCotizacion = subTotalCotizacion;
    }

    public BigDecimal getIvaCotizacion() {
        return ivaCotizacion;
    }

    public void setIvaCotizacion(BigDecimal ivaCotizacion) {
        this.ivaCotizacion = ivaCotizacion;
    }

    public String getEstdoFactura() {
        return estdoFactura;
    }

    public void setEstdoFactura(String estdoFactura) {
        this.estdoFactura = estdoFactura;
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechafacturacion() {
        return fechafacturacion;
    }

    public void setFechafacturacion(Date fechafacturacion) {
        this.fechafacturacion = fechafacturacion;
    }

    public AMedia getFileContent() {
        return fileContent;
    }

    public void setFileContent(AMedia fileContent) {
        this.fileContent = fileContent;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public BigDecimal getCobro() {
        return cobro;
    }

    public void setCobro(BigDecimal cobro) {
        this.cobro = cobro;
    }

    public BigDecimal getCambio() {
        return cambio;
    }

    public void setCambio(BigDecimal cambio) {
        this.cambio = cambio;
    }

    public String getFacturaDescripcion() {
        return facturaDescripcion;
    }

    public void setFacturaDescripcion(String facturaDescripcion) {
        this.facturaDescripcion = facturaDescripcion;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    @Command
    @NotifyChange({"listaClientesAll", "clienteBuscado", "fechaEmision"})
    public void buscarClienteEnLista() {
        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("valor", "cliente");
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/venta/buscarcliente.zul", null, map);
        window.doModal();
        System.out.println("clinete de la lsitas buscarCliente " + buscarCliente);
        clienteBuscado = servicioCliente.findByCliforCedula(buscarCliente);
    }

    @Command
    @NotifyChange({"listaClientesAll", "buscarRazonSocial"})
    public void buscarClienteRazon() {

        FindClienteLikeRazon();
    }

    @Command
    @NotifyChange({"listaClientesAll", "buscarCedula"})
    public void buscarClienteCedula() {

        FindClienteLikeCedula();
    }

    @Command
    @NotifyChange("clienteBuscado")
    public void seleccionarClienteLista(@BindingParam("cliente") Cliente valor) {
        System.out.println("cliente seleccionado " + valor.getIdPersona().getPerDni());
        buscarCliente = valor.getIdPersona().getPerDni();
        windowClienteBuscar.detach();

    }

    //busqueda del producto
    @Command
    @NotifyChange({"listaDetalleFacturaDAOMOdel", "subTotalCotizacion", "ivaCotizacion", "valorTotalCotizacion"})
    public void eliminarRegistros() {
        if (registrosSeleccionados.size() > 0) {
            ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).removeAll(registrosSeleccionados);
            calcularValoresTotales();

        } else {
            Messagebox.show("Seleccione al menos un registro para eliminar", "Atención", Messagebox.OK, Messagebox.ERROR);
        }

    }

    @Command
    @NotifyChange({"listaDetalleFacturaDAOMOdel"})
    public void agregarRegistroVacio() {

        DetalleFacturaDAO nuevoRegistro = new DetalleFacturaDAO();
        nuevoRegistro.setProducto(productoBuscado);
        nuevoRegistro.setCantidad(BigDecimal.ZERO);
        nuevoRegistro.setSubTotal(BigDecimal.ZERO);
        nuevoRegistro.setDetIva(BigDecimal.ZERO);
        nuevoRegistro.setDetTotalconiva(BigDecimal.ZERO);
        nuevoRegistro.setDescripcion("");
        nuevoRegistro.setProducto(null);
        ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).add(nuevoRegistro);

    }

    @Command
    @NotifyChange({"listaDetalleFacturaDAOMOdel"})
    public void nuevaDescripcionGeneral() {
        BigDecimal factorIva = (BigDecimal.valueOf(14L).divide(BigDecimal.valueOf(100.0)));
        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("valor", "producto");
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/venta/buscarproducto.zul", null, map);
        window.doModal();
        productoBuscado = servicioProducto.findByProdCodigo(codigoBusqueda);
        if (productoBuscado != null) {
            DetalleFacturaDAO nuevoRegistro = new DetalleFacturaDAO();
            nuevoRegistro.setProducto(productoBuscado);
            nuevoRegistro.setDescripcion(productoBuscado.getProdNombre());
            nuevoRegistro.setCantidad(BigDecimal.ZERO);
            nuevoRegistro.setCodigo(productoBuscado.getProdSerie());
            //  nuevoRegistro.setSubTotal(productoBuscado.getPordCostoVentaFinal());

            nuevoRegistro.setSubTotal(productoBuscado.getProdCostoVenta());
            nuevoRegistro.setTotal(nuevoRegistro.getCantidad().multiply(nuevoRegistro.getSubTotal()));
            nuevoRegistro.setDetIva(nuevoRegistro.getSubTotal().multiply(factorIva));
            nuevoRegistro.setDetTotalconiva(nuevoRegistro.getTotal().add(nuevoRegistro.getDetIva()));

            ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).add(nuevoRegistro);
        }
        codigoBusqueda = "";
    }

    @Command
    @NotifyChange({"listaDetalleFacturaDAOMOdel"})
    public void cambiarRegistro(@BindingParam("valor") DetalleFacturaDAO valor) {
        BigDecimal factorIva = (BigDecimal.valueOf(14L).divide(BigDecimal.valueOf(100.0)));
        if (!clienteBuscado.getIdPersona().getPerDni().equals("")) {
            final HashMap<String, String> map = new HashMap<String, String>();
            map.put("valor", "producto");
            org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                    "/venta/buscarproducto.zul", null, map);
            window.doModal();
            productoBuscado = servicioProducto.findByProdCodigo(codigoBusqueda);
            if (productoBuscado != null) {
                valor.setCantidad(BigDecimal.ONE);
                valor.setProducto(productoBuscado);
                valor.setCodigo(productoBuscado.getProdSerie());
                valor.setDescripcion(productoBuscado.getProdNombre());
                //  valor.setSubTotal(productoBuscado.getPordCostoVentaFinal());

                valor.setSubTotal(productoBuscado.getProdCostoVenta());
                valor.setTotal(valor.getCantidad().multiply(valor.getSubTotal()));
                valor.setDetIva(valor.getSubTotal().multiply(factorIva));
                valor.setDetTotalconiva(valor.getTotal().add(valor.getDetIva()));
                valor.setTipoVenta("NORMAL");

            }
            codigoBusqueda = "";
        } else {
            Messagebox.show("Verifique el cliente", "Atención", Messagebox.OK, Messagebox.INFORMATION);
        }
    }

    private void calcularValoresTotales() {
        BigDecimal valorTotal = BigDecimal.ZERO;

        List<DetalleFacturaDAO> listaPedido = listaDetalleFacturaDAOMOdel.getInnerList();
        if (listaPedido.size() > 0) {
            for (DetalleFacturaDAO item : listaPedido) {

                if (item.getProducto() != null) {
                    valorTotal = valorTotal.add(item.getTotal());
                }
            }
            System.out.println("**********************************************************");
            System.out.println("valor total:::: " + valorTotal);
            valorTotal.setScale(4, RoundingMode.UP);
            try {
                subTotalCotizacion = valorTotal;
                subTotalCotizacion.setScale(4, RoundingMode.UP);
                /*Obtiene el porcentaje del IVA*/
                BigDecimal valorIva = subTotalCotizacion.multiply(BigDecimal.valueOf(14L));

                ivaCotizacion = valorIva.divide(BigDecimal.valueOf(100.0));
                valorTotalCotizacion = subTotalCotizacion.add(ivaCotizacion);
                valorTotalCotizacion.setScale(4, RoundingMode.UP);
                ivaCotizacion.setScale(4, RoundingMode.UP);
//                subTotalCotizacion = valorTotal.divide(BigDecimal.valueOf(1.14), 4, RoundingMode.UP);
//                subTotalCotizacion.setScale(4, RoundingMode.UP);
//                BigDecimal valorIva = subTotalCotizacion.multiply(BigDecimal.valueOf(0.14));
//                ivaCotizacion = valorIva;
//                valorTotalCotizacion = valorTotal;
//                valorTotalCotizacion.setScale(4, RoundingMode.UP);
//                ivaCotizacion.setScale(4, RoundingMode.UP);
            } catch (Exception e) {
                System.out.println("error de calculo de valores " + e);
            }

        }
    }

    private void guardarFactura() {

        try {
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setCliIdCliente(clienteBuscado);
            cotizacion.setCotFechaCotizacion(fechafacturacion);
            cotizacion.setCotNumero(numeroFactura);
            cotizacion.setCotSubtotal(subTotalCotizacion);
            cotizacion.setCotIva(ivaCotizacion);
            cotizacion.setCotTotal(valorTotalCotizacion);
            cotizacion.setCotNumeroText(numeroFacturaText);
            cotizacion.setIdUsuario(credential.getUsuarioSistema());
            cotizacion.setIdEstadoCotizacion(servicioEstadoCotizacion.findBySigla("GN"));
            /*VERIFICA SI ES FACTURA O PROFORMA Y COLOCAL EL NUMERO*/
            verificarSecNumeracion();

            //armar el detalle de la factura
            List<DetalleFacturaDAO> detalleFactura = new ArrayList<DetalleFacturaDAO>();
            List<DetalleFacturaDAO> listaPedido = listaDetalleFacturaDAOMOdel.getInnerList();
            if (listaPedido.size() > 0) {
                for (DetalleFacturaDAO item : listaPedido) {
                    if (item.getProducto() != null) {
                        detalleFactura.add(item);
                    }

                }

            }
            servicioCotizacion.guardarCotizacion(detalleFactura, cotizacion);
            //ejecutamos el mensaje 
//            Clients.showNotification("Factura registrada con éxito", Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, true);
//            Messagebox.show("Factura registrada correctamente", "Atención", Messagebox.OK, Messagebox.INFORMATION);
            reporteGeneral(cotizacion);
            Executions.sendRedirect("/venta/cotizar.zul");
        } catch (IOException e) {
            System.out.println("ERROR FACTURA " + e.getMessage());
            Messagebox.show("Ocurrio un error guardar la factura ", "Atención", Messagebox.OK, Messagebox.ERROR);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR FACTURA " + e.getMessage());
            Messagebox.show("Ocurrio un error guardar la factura ", "Atención", Messagebox.OK, Messagebox.ERROR);
        } catch (IllegalAccessException e) {
            System.out.println("ERROR FACTURA " + e.getMessage());
            Messagebox.show("Ocurrio un error guardar la factura ", "Atención", Messagebox.OK, Messagebox.ERROR);
        } catch (InstantiationException e) {
            System.out.println("ERROR FACTURA " + e.getMessage());
            Messagebox.show("Ocurrio un error guardar la factura ", "Atención", Messagebox.OK, Messagebox.ERROR);
        } catch (SQLException e) {
            System.out.println("ERROR FACTURA " + e.getMessage());
            Messagebox.show("Ocurrio un error guardar la factura ", "Atención", Messagebox.OK, Messagebox.ERROR);
        } catch (NamingException e) {
            System.out.println("ERROR FACTURA " + e.getMessage());
            Messagebox.show("Ocurrio un error guardar la factura ", "Atención", Messagebox.OK, Messagebox.ERROR);
        } catch (JRException e) {
            System.out.println("ERROR FACTURA " + e.getMessage());
            Messagebox.show("Ocurrio un error guardar la factura ", "Atención", Messagebox.OK, Messagebox.ERROR);
        }

    }

    @Command
    @NotifyChange({"listaDetalleFacturaDAOMOdel", "subTotalCotizacion", "ivaCotizacion", "valorTotalCotizacion"})
    public void Guardar() {

        if (clienteBuscado.getIdPersona().getPerDni().equals("")) {
            if (listaDetalleFacturaDAOMOdel.size() > 0) {
                if (!listaDetalleFacturaDAOMOdel.get(0).getDescripcion().equals("")) {
                    guardarFactura();
                } else {
                    Messagebox.show("Registre un CLIENTE para proceder a la facturación", "Atención", Messagebox.OK, Messagebox.ERROR);
                }

            } else {
                Messagebox.show("Registre un PRODUCTO para proceder a la facturación", "Atención", Messagebox.OK, Messagebox.ERROR);
            }

        } else {
            Messagebox.show("Verifique el cliente y la forma de pago", "Atención", Messagebox.OK, Messagebox.ERROR);
        }

    }

    @Command
    @NotifyChange({"listaProducto", "buscarNombreProd"})
    public void buscarLikeNombreProd() {

        findProductoLikeNombre();
    }

    @Command
    @NotifyChange({"listaProducto", "buscarCodigoProd"})
    public void buscarLikeCodigoProd() {

        findProductoLikeCodigo();
    }

    private void findProductoLikeNombre() {
        listaProducto = servicioProducto.findLikeProdNombre(buscarNombreProd);
    }

    private void findProductoLikeCodigo() {
        listaProducto = servicioProducto.findLikeProdCodigo(buscarCodigoProd);
    }

    @Command
    @NotifyChange("clienteBuscado")
    public void seleccionarProductoLista(@BindingParam("valor") Producto valor) {
        System.out.println("producto seleccionado " + valor.getProdSerie());
        codigoBusqueda = valor.getProdSerie();
        windowProductoBuscar.detach();

    }

    @Command
    @NotifyChange("clienteBuscado")
    public void mensaje(@BindingParam("valor") DetalleFacturaDAO valor) {
        Messagebox.show("Fucniona " + valor.getDescripcion(), "Atención", Messagebox.OK, Messagebox.INFORMATION);

    }

    @Command
    @NotifyChange({"listaDetalleFacturaDAOMOdel", "subTotalCotizacion", "ivaCotizacion", "valorTotalCotizacion"})
    public void calcularValores(@BindingParam("valor") DetalleFacturaDAO valor) {
        try {
            BigDecimal factorIva = (BigDecimal.valueOf(14L).divide(BigDecimal.valueOf(100.0)));
            if (valor.getCantidad().intValue() > 0) {
                valor.setTotal(valor.getCantidad().multiply(valor.getSubTotal()));
                valor.setDetIva(valor.getTotal().multiply(factorIva));
                valor.setDetTotalconiva(valor.getTotal().add(valor.getDetIva()));
                calcularValoresTotales();
                //nuevo registro
                Producto buscadoPorCodigo = servicioProducto.findByProdCodigo(valor.getCodigo());
                if (buscadoPorCodigo != null) {
                    valor.setDescripcion(buscadoPorCodigo.getProdNombre());

                    valor.setSubTotal(buscadoPorCodigo.getProdCostoVenta());

                    valor.setProducto(buscadoPorCodigo);

                    //ingresa un registro vacio
                    boolean registroVacio = true;
                    List<DetalleFacturaDAO> listaPedido = listaDetalleFacturaDAOMOdel.getInnerList();

                    for (DetalleFacturaDAO item : listaPedido) {
                        if (item.getProducto() == null) {
                            registroVacio = false;
                            break;
                        }
                    }

                    System.out.println("existe un vacio " + registroVacio);
                    if (registroVacio) {
                        DetalleFacturaDAO nuevoRegistro = new DetalleFacturaDAO();
                        nuevoRegistro.setProducto(productoBuscado);
                        nuevoRegistro.setCantidad(BigDecimal.ZERO);
                        nuevoRegistro.setSubTotal(BigDecimal.ZERO);
                        nuevoRegistro.setDetIva(BigDecimal.ZERO);
                        nuevoRegistro.setDetTotalconiva(BigDecimal.ZERO);
                        nuevoRegistro.setDescripcion("");
                        nuevoRegistro.setProducto(null);
                        ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).add(nuevoRegistro);
                    }

                }
            }
            calcularValoresTotales();
        } catch (Exception e) {
            Messagebox.show("Ocurrio un error al calcular los valores" + e, "Atención", Messagebox.OK, Messagebox.ERROR);
        }
    }

    @Command
    @NotifyChange("listaDetalleFacturaDAOMOdel")
    public void buscarPorCodigo(@BindingParam("valor") DetalleFacturaDAO valor) {
//        valorSystem.out.println("producto seleccionado " + valor.getProdCodigo());
        BigDecimal factorIva = (BigDecimal.valueOf(14L).divide(BigDecimal.valueOf(100.0)));
        Producto buscadoPorCodigo = servicioProducto.findByProdCodigo(valor.getCodigo());
        if (buscadoPorCodigo != null) {
            valor.setDescripcion(buscadoPorCodigo.getProdNombre());
            valor.setCantidad(BigDecimal.ONE);
            valor.setProducto(buscadoPorCodigo);

            // valor.setSubTotal(buscadoPorCodigo.getPordCostoVentaFinal());
            valor.setSubTotal(buscadoPorCodigo.getProdCostoVenta());
            valor.setTotal(valor.getCantidad().multiply(valor.getSubTotal()));
            valor.setDetIva(valor.getSubTotal().multiply(factorIva));
            valor.setDetTotalconiva(valor.getTotal().add(valor.getDetIva()));

            valor.setProducto(buscadoPorCodigo);

            //ingresa un registro vacio
            boolean registroVacio = true;
            List<DetalleFacturaDAO> listaPedido = listaDetalleFacturaDAOMOdel.getInnerList();

            for (DetalleFacturaDAO item : listaPedido) {
                if (item.getProducto() == null) {
                    registroVacio = false;
                    break;
                }
            }

            System.out.println("existe un vacio " + registroVacio);
            if (registroVacio) {
                DetalleFacturaDAO nuevoRegistro = new DetalleFacturaDAO();
                nuevoRegistro.setProducto(productoBuscado);
                nuevoRegistro.setCantidad(BigDecimal.ZERO);
                nuevoRegistro.setDetTotalconiva(BigDecimal.ZERO);
                nuevoRegistro.setSubTotal(BigDecimal.ZERO);
                nuevoRegistro.setDetIva(BigDecimal.ZERO);
                nuevoRegistro.setDescripcion("");
                nuevoRegistro.setProducto(null);
                ((ListModelList<DetalleFacturaDAO>) listaDetalleFacturaDAOMOdel).add(nuevoRegistro);
            }
            calcularValoresTotales();

        }
    }

    public void reporteGeneral(Cotizacion valor) throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, NamingException {
        EntityManager emf = HelperPersistencia.getEMF();

        try {

            /*GENERA EL PDF FISICO*/
            Empresa emp = servicioEmpresa.obtenerEmpresaActiva();
            String folderGenerados = emp.getEmpDirectorioCotizaciones() + File.separator + FOLDERPDF
                    + File.separator + new Date().getMonth();
            /*EN EL CASO DE NO EXISTIR LOS DIRECTORIOS LOS CREA*/
            File folderGen = new File(folderGenerados);
            if (!folderGen.exists()) {
                folderGen.mkdirs();
            }
            String pathDFPFisico = folderGenerados + File.separator + "COT-" + numeroFactura + ".pdf";
            ArchivoUtils.reporteGeneralPdfMail(pathDFPFisico, numeroFactura);
            String[] attachFiles = new String[2];
            attachFiles[0] = pathDFPFisico;
            attachFiles[1] = "";
            MailerClass mail = new MailerClass();
            if (valor.getCliIdCliente().getCliClave() == null) {
                Cliente mod = valor.getCliIdCliente();
                mod.setCliClave(ArchivoUtils.generaraClaveTemporal());
                servicioCliente.modificar(mod);
            }
            if (valor.getCliIdCliente().getIdPersona().getPerCorreo() != null) {
                System.out.println("CORREO DESTINO " + valor.getCliIdCliente().getIdPersona().getPerCorreo());
                mail.sendMailSimple(valor.getCliIdCliente().getIdPersona().getPerCorreo(),
                        "Archivo enviado desde CRM " + emp.getEmpNombre(),
                        attachFiles,
                        "Cotizacion-" + valor.getCotNumeroText(), valor);
            }

            emf.getTransaction().begin();
            con = emf.unwrap(Connection.class);
            if (!tipoVenta.equals("SINF")) {

                //  con = emf.unwrap(Connection.class);
                String reportFile = Executions.getCurrent().getDesktop().getWebApp()
                        .getRealPath("/reportes");
                String reportPath = "";
//                con = ConexionReportes.Conexion.conexion();

                reportPath = reportFile + File.separator + "cotizacion.jasper";

                Map<String, Object> parametros = new HashMap<String, Object>();

                //  parametros.put("codUsuario", String.valueOf(credentialLog.getAdUsuario().getCodigoUsuario()));
                parametros.put("numcotizacion", numeroFactura);

                if (con != null) {
                    System.out.println("Conexión Realizada Correctamenteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                }
                FileInputStream is = null;
                is = new FileInputStream(reportPath);

                byte[] buf = JasperRunManager.runReportToPdf(is, parametros, con);
                InputStream mediais = new ByteArrayInputStream(buf);
                AMedia amedia = new AMedia("Reporte", "pdf", "application/pdf", mediais);
                fileContent = amedia;
                final HashMap<String, AMedia> map = new HashMap<String, AMedia>();
//para pasar al visor
                map.put("pdf", fileContent);
                org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                        "/venta/ContenedorReporte.zul", null, map);
                window.doModal();
                con.close();

            }
        } catch (IOException e) {
            System.out.println("Error en generar el reporte " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error en generar el reporte " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println("Error en generar el reporte " + e.getMessage());
        } catch (InstantiationException e) {
            System.out.println("Error en generar el reporte " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error en generar el reporte " + e.getMessage());
        } catch (NamingException e) {
            System.out.println("Error en generar el reporte " + e.getMessage());
        } catch (JRException e) {
            System.out.println("Error en generar el reporte " + e.getMessage());
        } finally {
            if (emf != null) {
                emf.close();
                System.out.println("cerro entity");
            }
        }

    }

    public void reporteGeneralPdfMail(String pathPDF) throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, NamingException {
        EntityManager emf = HelperPersistencia.getEMF();

        try {
            emf.getTransaction().begin();
            con = emf.unwrap(Connection.class);
            if (!tipoVenta.equals("SINF")) {

                //  con = emf.unwrap(Connection.class);
                String reportFile = Executions.getCurrent().getDesktop().getWebApp()
                        .getRealPath("/reportes");
                String reportPath = "";
//                con = ConexionReportes.Conexion.conexion();

                if (tipoVenta.equals("FACT")) {
                    reportPath = reportFile + File.separator + "notaventa.jasper";
                } else if (tipoVenta.equals("PROF")) {
                    reportPath = reportFile + File.separator + "proforma.jasper";
                }

                Map<String, Object> parametros = new HashMap<String, Object>();

                //  parametros.put("codUsuario", String.valueOf(credentialLog.getAdUsuario().getCodigoUsuario()));
                parametros.put("cot_numero", numeroFactura);

                if (con != null) {
                    System.out.println("Conexión Realizada Correctamenteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                }
                FileInputStream is = null;
                is = new FileInputStream(reportPath);

//                byte[] buf = JasperRunManager.runReportToPdf(is, parametros, con);
                JasperPrint print = JasperFillManager.fillReport(reportPath, parametros, con);
                JasperExportManager.exportReportToPdfFile(print, pathPDF);
            }
        } catch (Exception e) {
            System.out.println("Error en generar el reporte " + e.getMessage());
        } finally {
            if (emf != null) {
                emf.close();
                System.out.println("cerro entity");
            }
        }

    }

    @Command
    @NotifyChange({"cambio"})
    public void calcularCambio() {
        cambio = cobro.add(subTotalCotizacion.negate());
    }

    public Integer getNumeroProforma() {
        return numeroProforma;
    }

    public void setNumeroProforma(Integer numeroProforma) {
        this.numeroProforma = numeroProforma;
    }

    public Producto getP1() {
        return p1;
    }

    public void setP1(Producto p1) {
        this.p1 = p1;
    }

    public Producto getP2() {
        return p2;
    }

    public void setP2(Producto p2) {
        this.p2 = p2;
    }

    public Producto getP3() {
        return p3;
    }

    public void setP3(Producto p3) {
        this.p3 = p3;
    }

    public Producto getP4() {
        return p4;
    }

    public void setP4(Producto p4) {
        this.p4 = p4;
    }

    public Producto getP5() {
        return p5;
    }

    public void setP5(Producto p5) {
        this.p5 = p5;
    }

    public Producto getP6() {
        return p6;
    }

    public void setP6(Producto p6) {
        this.p6 = p6;
    }

    public Producto getP7() {
        return p7;
    }

    public void setP7(Producto p7) {
        this.p7 = p7;
    }

    public Producto getP8() {
        return p8;
    }

    public void setP8(Producto p8) {
        this.p8 = p8;
    }

    public Producto getP9() {
        return p9;
    }

    public void setP9(Producto p9) {
        this.p9 = p9;
    }

    public Producto getP10() {
        return p10;
    }

    public void setP10(Producto p10) {
        this.p10 = p10;
    }

    public Producto getP11() {
        return p11;
    }

    public void setP11(Producto p11) {
        this.p11 = p11;
    }

    public Producto getP12() {
        return p12;
    }

    public void setP12(Producto p12) {
        this.p12 = p12;
    }

    public Producto getP13() {
        return p13;
    }

    public void setP13(Producto p13) {
        this.p13 = p13;
    }

    public Producto getP14() {
        return p14;
    }

    public void setP14(Producto p14) {
        this.p14 = p14;
    }

    public Producto getP15() {
        return p15;
    }

    public void setP15(Producto p15) {
        this.p15 = p15;
    }

    public Producto getP16() {
        return p16;
    }

    public void setP16(Producto p16) {
        this.p16 = p16;
    }

    public Producto getP17() {
        return p17;
    }

    public void setP17(Producto p17) {
        this.p17 = p17;
    }

    public Producto getP18() {
        return p18;
    }

    public void setP18(Producto p18) {
        this.p18 = p18;
    }

    public Producto getP19() {
        return p19;
    }

    public void setP19(Producto p19) {
        this.p19 = p19;
    }

    public Producto getP20() {
        return p20;
    }

    public void setP20(Producto p20) {
        this.p20 = p20;
    }

    public Producto getP21() {
        return p21;
    }

    public void setP21(Producto p21) {
        this.p21 = p21;
    }

    public Producto getP22() {
        return p22;
    }

    public void setP22(Producto p22) {
        this.p22 = p22;
    }

    public Producto getP23() {
        return p23;
    }

    public void setP23(Producto p23) {
        this.p23 = p23;
    }

    public Producto getP24() {
        return p24;
    }

    public void setP24(Producto p24) {
        this.p24 = p24;
    }

}
