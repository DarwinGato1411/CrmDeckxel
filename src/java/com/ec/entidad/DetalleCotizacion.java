/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "detalle_cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCotizacion.findAll", query = "SELECT d FROM DetalleCotizacion d")
    , @NamedQuery(name = "DetalleCotizacion.findByIdDetalleCotizacion", query = "SELECT d FROM DetalleCotizacion d WHERE d.idDetalleCotizacion = :idDetalleCotizacion")
    , @NamedQuery(name = "DetalleCotizacion.findByDetcDescripcion", query = "SELECT d FROM DetalleCotizacion d WHERE d.detcDescripcion = :detcDescripcion")
    , @NamedQuery(name = "DetalleCotizacion.findByDetcCantidad", query = "SELECT d FROM DetalleCotizacion d WHERE d.detcCantidad = :detcCantidad")
    , @NamedQuery(name = "DetalleCotizacion.findByDetcSubtotal", query = "SELECT d FROM DetalleCotizacion d WHERE d.detcSubtotal = :detcSubtotal")
    , @NamedQuery(name = "DetalleCotizacion.findByDetcTotal", query = "SELECT d FROM DetalleCotizacion d WHERE d.detcTotal = :detcTotal")})
public class DetalleCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_cotizacion")
    private Integer idDetalleCotizacion;
    @Column(name = "detc_descripcion")
    private String detcDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "detc_cantidad")
    private BigDecimal detcCantidad;
    @Column(name = "detc_subtotal")
    private BigDecimal detcSubtotal;
    @Column(name = "detc_total")
    private BigDecimal detcTotal;
    @JoinColumn(name = "id_cotizacion", referencedColumnName = "id_cotizacion")
    @ManyToOne
    private Cotizacion idCotizacion;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne
    private Producto idProducto;

    public DetalleCotizacion() {
    }

    public DetalleCotizacion(String detcDescripcion, BigDecimal detcCantidad, BigDecimal detcSubtotal, BigDecimal detcTotal, Cotizacion idCotizacion,Producto idProducto) {
        this.detcDescripcion = detcDescripcion;
        this.detcCantidad = detcCantidad;
        this.detcSubtotal = detcSubtotal;
        this.detcTotal = detcTotal;
        this.idCotizacion = idCotizacion;
        this.idProducto = idProducto;
    }

    public DetalleCotizacion(Integer idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public Integer getIdDetalleCotizacion() {
        return idDetalleCotizacion;
    }

    public void setIdDetalleCotizacion(Integer idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public String getDetcDescripcion() {
        return detcDescripcion;
    }

    public void setDetcDescripcion(String detcDescripcion) {
        this.detcDescripcion = detcDescripcion;
    }

    public BigDecimal getDetcCantidad() {
        return detcCantidad;
    }

    public void setDetcCantidad(BigDecimal detcCantidad) {
        this.detcCantidad = detcCantidad;
    }

    public BigDecimal getDetcSubtotal() {
        return detcSubtotal;
    }

    public void setDetcSubtotal(BigDecimal detcSubtotal) {
        this.detcSubtotal = detcSubtotal;
    }

    public BigDecimal getDetcTotal() {
        return detcTotal;
    }

    public void setDetcTotal(BigDecimal detcTotal) {
        this.detcTotal = detcTotal;
    }

    public Cotizacion getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Cotizacion idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleCotizacion != null ? idDetalleCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCotizacion)) {
            return false;
        }
        DetalleCotizacion other = (DetalleCotizacion) object;
        if ((this.idDetalleCotizacion == null && other.idDetalleCotizacion != null) || (this.idDetalleCotizacion != null && !this.idDetalleCotizacion.equals(other.idDetalleCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DetalleCotizacion[ idDetalleCotizacion=" + idDetalleCotizacion + " ]";
    }

}
