/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
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
@Table(name = "detalle_proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleProyecto.findAll", query = "SELECT d FROM DetalleProyecto d")
    , @NamedQuery(name = "DetalleProyecto.findByIdDetalleProyecto", query = "SELECT d FROM DetalleProyecto d WHERE d.idDetalleProyecto = :idDetalleProyecto")
    , @NamedQuery(name = "DetalleProyecto.findByDetpFecha", query = "SELECT d FROM DetalleProyecto d WHERE d.detpFecha = :detpFecha")
    , @NamedQuery(name = "DetalleProyecto.findByDetpDescripcion", query = "SELECT d FROM DetalleProyecto d WHERE d.detpDescripcion = :detpDescripcion")
    , @NamedQuery(name = "DetalleProyecto.findByDetpAdjunto", query = "SELECT d FROM DetalleProyecto d WHERE d.detpAdjunto = :detpAdjunto")
    , @NamedQuery(name = "DetalleProyecto.findByDetPathAdjunto", query = "SELECT d FROM DetalleProyecto d WHERE d.detPathAdjunto = :detPathAdjunto")})
public class DetalleProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_proyecto")
    private Integer idDetalleProyecto;
    @Column(name = "detp_fecha")
    @Temporal(TemporalType.DATE)
    private Date detpFecha;
    @Column(name = "detp_descripcion")
    private String detpDescripcion;
    @Column(name = "detp_adjunto")
    private String detpAdjunto;
    @Column(name = "det_path_adjunto")
    private String detPathAdjunto;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne
    private Proyecto idProyecto;

    public DetalleProyecto() {
    }

    public DetalleProyecto(Integer idDetalleProyecto) {
        this.idDetalleProyecto = idDetalleProyecto;
    }

    public Integer getIdDetalleProyecto() {
        return idDetalleProyecto;
    }

    public void setIdDetalleProyecto(Integer idDetalleProyecto) {
        this.idDetalleProyecto = idDetalleProyecto;
    }

    public Date getDetpFecha() {
        return detpFecha;
    }

    public void setDetpFecha(Date detpFecha) {
        this.detpFecha = detpFecha;
    }

    public String getDetpDescripcion() {
        return detpDescripcion;
    }

    public void setDetpDescripcion(String detpDescripcion) {
        this.detpDescripcion = detpDescripcion;
    }

    public String getDetpAdjunto() {
        return detpAdjunto;
    }

    public void setDetpAdjunto(String detpAdjunto) {
        this.detpAdjunto = detpAdjunto;
    }

    public String getDetPathAdjunto() {
        return detPathAdjunto;
    }

    public void setDetPathAdjunto(String detPathAdjunto) {
        this.detPathAdjunto = detPathAdjunto;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleProyecto != null ? idDetalleProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleProyecto)) {
            return false;
        }
        DetalleProyecto other = (DetalleProyecto) object;
        if ((this.idDetalleProyecto == null && other.idDetalleProyecto != null) || (this.idDetalleProyecto != null && !this.idDetalleProyecto.equals(other.idDetalleProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DetalleProyecto[ idDetalleProyecto=" + idDetalleProyecto + " ]";
    }
    
}
