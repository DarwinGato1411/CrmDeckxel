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
@Table(name = "convenio_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConvenioPago.findAll", query = "SELECT c FROM ConvenioPago c")
    , @NamedQuery(name = "ConvenioPago.findByIdConvenio", query = "SELECT c FROM ConvenioPago c WHERE c.idConvenio = :idConvenio")
    , @NamedQuery(name = "ConvenioPago.findByConDescripcion", query = "SELECT c FROM ConvenioPago c WHERE c.conDescripcion = :conDescripcion")
    , @NamedQuery(name = "ConvenioPago.findByConValor", query = "SELECT c FROM ConvenioPago c WHERE c.conValor = :conValor")
    , @NamedQuery(name = "ConvenioPago.findByConFechaPago", query = "SELECT c FROM ConvenioPago c WHERE c.conFechaPago = :conFechaPago")})
public class ConvenioPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_convenio")
    private Integer idConvenio;
    @Column(name = "con_descripcion")
    private String conDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "con_valor")
    private BigDecimal conValor;
    @Column(name = "con_fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date conFechaPago;
    @JoinColumn(name = "id_cotizacion", referencedColumnName = "id_cotizacion")
    @ManyToOne
    private Cotizacion idCotizacion;

    public ConvenioPago() {
    }

    public ConvenioPago(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getConDescripcion() {
        return conDescripcion;
    }

    public void setConDescripcion(String conDescripcion) {
        this.conDescripcion = conDescripcion;
    }

    public BigDecimal getConValor() {
        return conValor;
    }

    public void setConValor(BigDecimal conValor) {
        this.conValor = conValor;
    }

    public Date getConFechaPago() {
        return conFechaPago;
    }

    public void setConFechaPago(Date conFechaPago) {
        this.conFechaPago = conFechaPago;
    }

    public Cotizacion getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Cotizacion idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConvenio != null ? idConvenio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConvenioPago)) {
            return false;
        }
        ConvenioPago other = (ConvenioPago) object;
        if ((this.idConvenio == null && other.idConvenio != null) || (this.idConvenio != null && !this.idConvenio.equals(other.idConvenio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.ConvenioPago[ idConvenio=" + idConvenio + " ]";
    }
    
}
