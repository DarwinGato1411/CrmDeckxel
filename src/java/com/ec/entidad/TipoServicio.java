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
@Table(name = "tipo_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoServicio.findAll", query = "SELECT t FROM TipoServicio t")
    , @NamedQuery(name = "TipoServicio.findByIdTipoServicio", query = "SELECT t FROM TipoServicio t WHERE t.idTipoServicio = :idTipoServicio")
    , @NamedQuery(name = "TipoServicio.findByTipsDescripcion", query = "SELECT t FROM TipoServicio t WHERE t.tipsDescripcion = :tipsDescripcion")
    , @NamedQuery(name = "TipoServicio.findByTipsSigla", query = "SELECT t FROM TipoServicio t WHERE t.tipsSigla = :tipsSigla")})
public class TipoServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_servicio")
    private Integer idTipoServicio;
    @Column(name = "tips_descripcion")
    private String tipsDescripcion;
    @Column(name = "tips_sigla")
    private String tipsSigla;
    @OneToMany(mappedBy = "idTipoServicio")
    private Collection<Dominio> dominioCollection;

    public TipoServicio() {
    }

    public TipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getTipsDescripcion() {
        return tipsDescripcion;
    }

    public void setTipsDescripcion(String tipsDescripcion) {
        this.tipsDescripcion = tipsDescripcion;
    }

    public String getTipsSigla() {
        return tipsSigla;
    }

    public void setTipsSigla(String tipsSigla) {
        this.tipsSigla = tipsSigla;
    }

    @XmlTransient
    public Collection<Dominio> getDominioCollection() {
        return dominioCollection;
    }

    public void setDominioCollection(Collection<Dominio> dominioCollection) {
        this.dominioCollection = dominioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoServicio != null ? idTipoServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoServicio)) {
            return false;
        }
        TipoServicio other = (TipoServicio) object;
        if ((this.idTipoServicio == null && other.idTipoServicio != null) || (this.idTipoServicio != null && !this.idTipoServicio.equals(other.idTipoServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.TipoServicio[ idTipoServicio=" + idTipoServicio + " ]";
    }
    
}
