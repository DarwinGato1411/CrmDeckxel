/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.dao;

import com.ec.entidad.Cotizacion;
import com.ec.entidad.Producto;
import java.math.BigDecimal;

/**
 *
 * @author gato
 */
public class DetalleFacturaDAO {

    private String codigo = "";
    private Cotizacion cotizacion = null;
    private Producto producto = null;
    private BigDecimal cantidad = BigDecimal.ZERO;
    private String descripcion = "";
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal subTotalPorCantidad = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private String elementoNuevo = "SI";
    private String tipoVenta = "";
    private BigDecimal detIva = BigDecimal.ZERO;
    private BigDecimal detTotalconiva = BigDecimal.ZERO;

    public DetalleFacturaDAO() {
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getElementoNuevo() {
        return elementoNuevo;
    }

    public void setElementoNuevo(String elementoNuevo) {
        this.elementoNuevo = elementoNuevo;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public BigDecimal getDetIva() {
        return detIva;
    }

    public void setDetIva(BigDecimal detIva) {
        this.detIva = detIva;
    }

    public BigDecimal getSubTotalPorCantidad() {
        return subTotalPorCantidad;
    }

    public void setSubTotalPorCantidad(BigDecimal subTotalPorCantidad) {
        this.subTotalPorCantidad = subTotalPorCantidad;
    }

    public BigDecimal getDetTotalconiva() {
        return detTotalconiva;
    }

    public void setDetTotalconiva(BigDecimal detTotalconiva) {
        this.detTotalconiva = detTotalconiva;
    }

}
