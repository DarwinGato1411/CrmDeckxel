/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c")
    , @NamedQuery(name = "Cotizacion.findByIdCotizacion", query = "SELECT c FROM Cotizacion c WHERE c.idCotizacion = :idCotizacion")
    , @NamedQuery(name = "Cotizacion.findByCotFechaCotizacion", query = "SELECT c FROM Cotizacion c WHERE c.cotFechaCotizacion = :cotFechaCotizacion")
    , @NamedQuery(name = "Cotizacion.findByCotSubtotal", query = "SELECT c FROM Cotizacion c WHERE c.cotSubtotal = :cotSubtotal")
    , @NamedQuery(name = "Cotizacion.findByCotIva", query = "SELECT c FROM Cotizacion c WHERE c.cotIva = :cotIva")
    , @NamedQuery(name = "Cotizacion.findByCotTotal", query = "SELECT c FROM Cotizacion c WHERE c.cotTotal = :cotTotal")
    , @NamedQuery(name = "Cotizacion.findByCotNumero", query = "SELECT c FROM Cotizacion c WHERE c.cotNumero = :cotNumero")
    , @NamedQuery(name = "Cotizacion.findUltimaCotNumero", query = "SELECT c FROM Cotizacion c ORDER BY c.cotNumero DESC")
    , @NamedQuery(name = "Cotizacion.findByCotLetras", query = "SELECT c FROM Cotizacion c WHERE c.cotLetras = :cotLetras")
    , @NamedQuery(name = "Cotizacion.findByCotNumeroText", query = "SELECT c FROM Cotizacion c WHERE c.cotNumeroText = :cotNumeroText")})
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cotizacion")
    private Integer idCotizacion;
    @Column(name = "cot_fecha_cotizacion")
    @Temporal(TemporalType.DATE)
    private Date cotFechaCotizacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cot_subtotal")
    private BigDecimal cotSubtotal;
    @Column(name = "cot_iva")
    private BigDecimal cotIva;
    @Column(name = "cot_total")
    private BigDecimal cotTotal;
    @Column(name = "cot_numero")
    private Integer cotNumero;
    @Column(name = "cot_letras")
    private String cotLetras;
    @Column(name = "cot_numero_text")
    private String cotNumeroText;
    @OneToMany(mappedBy = "idCotizacion")
    private Collection<Proyecto> proyectoCollection;
    @OneToMany(mappedBy = "idCotizacion")
    private Collection<ConvenioPago> convenioPagoCollection;
    @OneToMany(mappedBy = "idCotizacion")
    private Collection<DetalleCotizacion> detalleCotizacionCollection;
    @JoinColumn(name = "cli_id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Cliente cliIdCliente;
    @JoinColumn(name = "id_estado_cotizacion", referencedColumnName = "id_estado_cotizacion")
    @ManyToOne
    private EstadoCotizacion idEstadoCotizacion;
    @JoinColumn(name = "is_estado_procesos", referencedColumnName = "is_estado_procesos")
    @ManyToOne
    private EstadoProcesos isEstadoProcesos;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public Cotizacion() {
    }

    public Cotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Date getCotFechaCotizacion() {
        return cotFechaCotizacion;
    }

    public void setCotFechaCotizacion(Date cotFechaCotizacion) {
        this.cotFechaCotizacion = cotFechaCotizacion;
    }

    public BigDecimal getCotSubtotal() {
        return cotSubtotal;
    }

    public void setCotSubtotal(BigDecimal cotSubtotal) {
        this.cotSubtotal = cotSubtotal;
    }

    public BigDecimal getCotIva() {
        return cotIva;
    }

    public void setCotIva(BigDecimal cotIva) {
        this.cotIva = cotIva;
    }

    public BigDecimal getCotTotal() {
        return cotTotal;
    }

    public void setCotTotal(BigDecimal cotTotal) {
        this.cotTotal = cotTotal;
    }

    public Integer getCotNumero() {
        return cotNumero;
    }

    public void setCotNumero(Integer cotNumero) {
        this.cotNumero = cotNumero;
    }

    public String getCotLetras() {
        return cotLetras;
    }

    public void setCotLetras(String cotLetras) {
        this.cotLetras = cotLetras;
    }

    public String getCotNumeroText() {
        return cotNumeroText;
    }

    public void setCotNumeroText(String cotNumeroText) {
        this.cotNumeroText = cotNumeroText;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    @XmlTransient
    public Collection<ConvenioPago> getConvenioPagoCollection() {
        return convenioPagoCollection;
    }

    public void setConvenioPagoCollection(Collection<ConvenioPago> convenioPagoCollection) {
        this.convenioPagoCollection = convenioPagoCollection;
    }

    @XmlTransient
    public Collection<DetalleCotizacion> getDetalleCotizacionCollection() {
        return detalleCotizacionCollection;
    }

    public void setDetalleCotizacionCollection(Collection<DetalleCotizacion> detalleCotizacionCollection) {
        this.detalleCotizacionCollection = detalleCotizacionCollection;
    }

    public Cliente getCliIdCliente() {
        return cliIdCliente;
    }

    public void setCliIdCliente(Cliente cliIdCliente) {
        this.cliIdCliente = cliIdCliente;
    }

    public EstadoCotizacion getIdEstadoCotizacion() {
        return idEstadoCotizacion;
    }

    public void setIdEstadoCotizacion(EstadoCotizacion idEstadoCotizacion) {
        this.idEstadoCotizacion = idEstadoCotizacion;
    }

    public EstadoProcesos getIsEstadoProcesos() {
        return isEstadoProcesos;
    }

    public void setIsEstadoProcesos(EstadoProcesos isEstadoProcesos) {
        this.isEstadoProcesos = isEstadoProcesos;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCotizacion != null ? idCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.idCotizacion == null && other.idCotizacion != null) || (this.idCotizacion != null && !this.idCotizacion.equals(other.idCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Cotizacion[ idCotizacion=" + idCotizacion + " ]";
    }

}
