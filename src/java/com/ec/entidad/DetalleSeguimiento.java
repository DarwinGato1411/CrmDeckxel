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
@Table(name = "detalle_seguimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSeguimiento.findAll", query = "SELECT d FROM DetalleSeguimiento d")
    , @NamedQuery(name = "DetalleSeguimiento.findByIdDetalleSeguimiento", query = "SELECT d FROM DetalleSeguimiento d WHERE d.idDetalleSeguimiento = :idDetalleSeguimiento")
    , @NamedQuery(name = "DetalleSeguimiento.findByDetsObservacion", query = "SELECT d FROM DetalleSeguimiento d WHERE d.detsObservacion = :detsObservacion")
    , @NamedQuery(name = "DetalleSeguimiento.findByDetsFechaContacto", query = "SELECT d FROM DetalleSeguimiento d WHERE d.detsFechaContacto = :detsFechaContacto")
    , @NamedQuery(name = "DetalleSeguimiento.findByDetsFechaProxima", query = "SELECT d FROM DetalleSeguimiento d WHERE d.detsFechaProxima = :detsFechaProxima")
    , @NamedQuery(name = "DetalleSeguimiento.findByDetsTipoContacto", query = "SELECT d FROM DetalleSeguimiento d WHERE d.detsTipoContacto = :detsTipoContacto")
    , @NamedQuery(name = "DetalleSeguimiento.findByDetsEstadoSeguimiento", query = "SELECT d FROM DetalleSeguimiento d WHERE d.detsEstadoSeguimiento = :detsEstadoSeguimiento")})
public class DetalleSeguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_seguimiento")
    private Integer idDetalleSeguimiento;
    @Column(name = "dets_observacion")
    private String detsObservacion;
    @Column(name = "dets_fecha_contacto")
    @Temporal(TemporalType.DATE)
    private Date detsFechaContacto;
    @Column(name = "dets_fecha_proxima")
    @Temporal(TemporalType.DATE)
    private Date detsFechaProxima;
    @Column(name = "dets_tipo_contacto")
    private String detsTipoContacto;
    @Column(name = "dets_estado_seguimiento")
    private String detsEstadoSeguimiento;
    @JoinColumn(name = "id_seguimiento", referencedColumnName = "id_seguimiento")
    @ManyToOne
    private Seguimiento idSeguimiento;


    public DetalleSeguimiento() {
    }

    public DetalleSeguimiento(Integer idDetalleSeguimiento) {
        this.idDetalleSeguimiento = idDetalleSeguimiento;
    }

    public Integer getIdDetalleSeguimiento() {
        return idDetalleSeguimiento;
    }

    public void setIdDetalleSeguimiento(Integer idDetalleSeguimiento) {
        this.idDetalleSeguimiento = idDetalleSeguimiento;
    }

    public String getDetsObservacion() {
        return detsObservacion;
    }

    public void setDetsObservacion(String detsObservacion) {
        this.detsObservacion = detsObservacion;
    }

    public Date getDetsFechaContacto() {
        return detsFechaContacto;
    }

    public void setDetsFechaContacto(Date detsFechaContacto) {
        this.detsFechaContacto = detsFechaContacto;
    }

    public Date getDetsFechaProxima() {
        return detsFechaProxima;
    }

    public void setDetsFechaProxima(Date detsFechaProxima) {
        this.detsFechaProxima = detsFechaProxima;
    }

    public String getDetsTipoContacto() {
        return detsTipoContacto;
    }

    public void setDetsTipoContacto(String detsTipoContacto) {
        this.detsTipoContacto = detsTipoContacto;
    }

    public String getDetsEstadoSeguimiento() {
        return detsEstadoSeguimiento;
    }

    public void setDetsEstadoSeguimiento(String detsEstadoSeguimiento) {
        this.detsEstadoSeguimiento = detsEstadoSeguimiento;
    }

    public Seguimiento getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(Seguimiento idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleSeguimiento != null ? idDetalleSeguimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleSeguimiento)) {
            return false;
        }
        DetalleSeguimiento other = (DetalleSeguimiento) object;
        if ((this.idDetalleSeguimiento == null && other.idDetalleSeguimiento != null) || (this.idDetalleSeguimiento != null && !this.idDetalleSeguimiento.equals(other.idDetalleSeguimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DetalleSeguimiento[ idDetalleSeguimiento=" + idDetalleSeguimiento + " ]";
    }
    
}
