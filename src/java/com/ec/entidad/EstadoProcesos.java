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
@Table(name = "estado_procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoProcesos.findAll", query = "SELECT e FROM EstadoProcesos e")
    , @NamedQuery(name = "EstadoProcesos.findByIsEstadoProcesos", query = "SELECT e FROM EstadoProcesos e WHERE e.isEstadoProcesos = :isEstadoProcesos")
    , @NamedQuery(name = "EstadoProcesos.findByEstpDecricpion", query = "SELECT e FROM EstadoProcesos e WHERE e.estpDecricpion = :estpDecricpion")
    , @NamedQuery(name = "EstadoProcesos.findByEstpSigla", query = "SELECT e FROM EstadoProcesos e WHERE e.estpSigla = :estpSigla")})
public class EstadoProcesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "is_estado_procesos")
    private Integer isEstadoProcesos;
    @Column(name = "estp_decricpion")
    private String estpDecricpion;
    @Column(name = "estp_sigla")
    private String estpSigla;
    @OneToMany(mappedBy = "isEstadoProcesos")
    private Collection<Seguimiento> seguimientoCollection;
    @OneToMany(mappedBy = "isEstadoProcesos")
    private Collection<Dominio> dominioCollection;
    @OneToMany(mappedBy = "isEstadoProcesos")
    private Collection<Cotizacion> cotizacionCollection;

    public EstadoProcesos() {
    }

    public EstadoProcesos(Integer isEstadoProcesos) {
        this.isEstadoProcesos = isEstadoProcesos;
    }

    public Integer getIsEstadoProcesos() {
        return isEstadoProcesos;
    }

    public void setIsEstadoProcesos(Integer isEstadoProcesos) {
        this.isEstadoProcesos = isEstadoProcesos;
    }

    public String getEstpDecricpion() {
        return estpDecricpion;
    }

    public void setEstpDecricpion(String estpDecricpion) {
        this.estpDecricpion = estpDecricpion;
    }

    public String getEstpSigla() {
        return estpSigla;
    }

    public void setEstpSigla(String estpSigla) {
        this.estpSigla = estpSigla;
    }

    @XmlTransient
    public Collection<Seguimiento> getSeguimientoCollection() {
        return seguimientoCollection;
    }

    public void setSeguimientoCollection(Collection<Seguimiento> seguimientoCollection) {
        this.seguimientoCollection = seguimientoCollection;
    }

    @XmlTransient
    public Collection<Dominio> getDominioCollection() {
        return dominioCollection;
    }

    public void setDominioCollection(Collection<Dominio> dominioCollection) {
        this.dominioCollection = dominioCollection;
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
        hash += (isEstadoProcesos != null ? isEstadoProcesos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoProcesos)) {
            return false;
        }
        EstadoProcesos other = (EstadoProcesos) object;
        if ((this.isEstadoProcesos == null && other.isEstadoProcesos != null) || (this.isEstadoProcesos != null && !this.isEstadoProcesos.equals(other.isEstadoProcesos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.EstadoProcesos[ isEstadoProcesos=" + isEstadoProcesos + " ]";
    }
    
}
