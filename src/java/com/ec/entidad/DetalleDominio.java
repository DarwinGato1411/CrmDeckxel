/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "detalle_dominio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleDominio.findAll", query = "SELECT d FROM DetalleDominio d")
    , @NamedQuery(name = "DetalleDominio.findByIdDetalleDominio", query = "SELECT d FROM DetalleDominio d WHERE d.idDetalleDominio = :idDetalleDominio")
    , @NamedQuery(name = "DetalleDominio.findByDetcDescripcion", query = "SELECT d FROM DetalleDominio d WHERE d.detcDescripcion = :detcDescripcion")
    , @NamedQuery(name = "DetalleDominio.findByDetcCantidad", query = "SELECT d FROM DetalleDominio d WHERE d.detcCantidad = :detcCantidad")
    , @NamedQuery(name = "DetalleDominio.findByDetcSubtotal", query = "SELECT d FROM DetalleDominio d WHERE d.detcSubtotal = :detcSubtotal")
    , @NamedQuery(name = "DetalleDominio.findByDetcTotal", query = "SELECT d FROM DetalleDominio d WHERE d.detcTotal = :detcTotal")
    , @NamedQuery(name = "DetalleDominio.findByIdProducto", query = "SELECT d FROM DetalleDominio d WHERE d.idProducto = :idProducto")})
public class DetalleDominio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_dominio")
    private Integer idDetalleDominio;
    @Column(name = "detc_descripcion")
    private String detcDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "detc_cantidad")
    private BigDecimal detcCantidad;
    @Column(name = "detc_subtotal")
    private BigDecimal detcSubtotal;
    @Column(name = "detd_fecha_renovacion")
    @Temporal(TemporalType.DATE)
    private Date detdFechaRenovacion;
    @Column(name = "detc_total")
    private BigDecimal detcTotal;
    @Column(name = "id_producto")
    private Integer idProducto;
    @JoinColumn(name = "id_dominio", referencedColumnName = "id_dominio")
    @ManyToOne
    private Dominio idDominio;

    public DetalleDominio() {
    }

    public DetalleDominio(Integer idDetalleDominio) {
        this.idDetalleDominio = idDetalleDominio;
    }

    public Integer getIdDetalleDominio() {
        return idDetalleDominio;
    }

    public void setIdDetalleDominio(Integer idDetalleDominio) {
        this.idDetalleDominio = idDetalleDominio;
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

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Dominio getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(Dominio idDominio) {
        this.idDominio = idDominio;
    }

    public Date getDetdFechaRenovacion() {
        return detdFechaRenovacion;
    }

    public void setDetdFechaRenovacion(Date detdFechaRenovacion) {
        this.detdFechaRenovacion = detdFechaRenovacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleDominio != null ? idDetalleDominio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleDominio)) {
            return false;
        }
        DetalleDominio other = (DetalleDominio) object;
        if ((this.idDetalleDominio == null && other.idDetalleDominio != null) || (this.idDetalleDominio != null && !this.idDetalleDominio.equals(other.idDetalleDominio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DetalleDominio[ idDetalleDominio=" + idDetalleDominio + " ]";
    }

}
