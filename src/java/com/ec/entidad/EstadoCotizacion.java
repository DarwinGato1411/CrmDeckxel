/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "estado_cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCotizacion.findAll", query = "SELECT e FROM EstadoCotizacion e")
    , @NamedQuery(name = "EstadoCotizacion.findByIdEstadoCotizacion", query = "SELECT e FROM EstadoCotizacion e WHERE e.idEstadoCotizacion = :idEstadoCotizacion")
    , @NamedQuery(name = "EstadoCotizacion.findByEstcDescripcion", query = "SELECT e FROM EstadoCotizacion e WHERE e.estcDescripcion = :estcDescripcion")
    , @NamedQuery(name = "EstadoCotizacion.findByEstcSigla", query = "SELECT e FROM EstadoCotizacion e WHERE e.estcSigla = :estcSigla")})
public class EstadoCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_cotizacion")
    private Integer idEstadoCotizacion;
    @Column(name = "estc_descripcion")
    private String estcDescripcion;
    @Column(name = "estc_sigla")
    private String estcSigla;
    @OneToMany(mappedBy = "idEstadoCotizacion")
    private Collection<Cotizacion> cotizacionCollection;

    public EstadoCotizacion() {
    }

    public EstadoCotizacion(Integer idEstadoCotizacion) {
        this.idEstadoCotizacion = idEstadoCotizacion;
    }

    public Integer getIdEstadoCotizacion() {
        return idEstadoCotizacion;
    }

    public void setIdEstadoCotizacion(Integer idEstadoCotizacion) {
        this.idEstadoCotizacion = idEstadoCotizacion;
    }

    public String getEstcDescripcion() {
        return estcDescripcion;
    }

    public void setEstcDescripcion(String estcDescripcion) {
        this.estcDescripcion = estcDescripcion;
    }

    public String getEstcSigla() {
        return estcSigla;
    }

    public void setEstcSigla(String estcSigla) {
        this.estcSigla = estcSigla;
    }

    @XmlTransient
    public Collection<Cotizacion> getCotizacionCollection() {
        return cotizacionCollection;
    }

    public void setCotizacionCollection(Collection<Cotizacion> cotizacionCollection) {
        this.cotizacionCollection = cotizacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCotizacion != null ? idEstadoCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCotizacion)) {
            return false;
        }
        EstadoCotizacion other = (EstadoCotizacion) object;
        if ((this.idEstadoCotizacion == null && other.idEstadoCotizacion != null) || (this.idEstadoCotizacion != null && !this.idEstadoCotizacion.equals(other.idEstadoCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.EstadoCotizacion[ idEstadoCotizacion=" + idEstadoCotizacion + " ]";
    }
    
}
