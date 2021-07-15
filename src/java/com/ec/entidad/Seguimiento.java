/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "seguimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s")
    , @NamedQuery(name = "Seguimiento.findByIdSeguimiento", query = "SELECT s FROM Seguimiento s WHERE s.idSeguimiento = :idSeguimiento")
    , @NamedQuery(name = "Seguimiento.findBySegFechaActividad", query = "SELECT s FROM Seguimiento s WHERE s.segFechaActividad = :segFechaActividad")
    , @NamedQuery(name = "Seguimiento.findBySegDescripcion", query = "SELECT s FROM Seguimiento s WHERE s.segDescripcion = :segDescripcion")
    , @NamedQuery(name = "Seguimiento.findBySegFechaProxima", query = "SELECT s FROM Seguimiento s WHERE s.segFechaProxima = :segFechaProxima")})
public class Seguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento")
    private Integer idSeguimiento;
    @Column(name = "seg_fecha_actividad")
    @Temporal(TemporalType.DATE)
    private Date segFechaActividad;
    @Column(name = "seg_descripcion")
    private String segDescripcion;
    @Column(name = "seg_fecha_proxima")
    @Temporal(TemporalType.DATE)
    private Date segFechaProxima;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Cliente idCliente;
    @JoinColumn(name = "is_estado_procesos", referencedColumnName = "is_estado_procesos")
    @ManyToOne
    private EstadoProcesos isEstadoProcesos;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idSeguimiento")
    private Collection<DetalleSeguimiento> detalleSeguimientoCollection;
    @Transient
    private String colorEstado;

    public Seguimiento() {
    }

    public String getColorEstado() {
        long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
        Date fechaInicial = new Date();

        int dias = (int) ((segFechaProxima.getTime() - fechaInicial.getTime()) / MILLSECS_PER_DAY);
//        System.out.println("dias  " + dias + " fecha caduca " + segFechaProxima + " fecha actual " + fechaInicial);
        if (dias > 5) {
            colorEstado = "G";
        } else if (dias <= 5 && dias > 2) {
            colorEstado = "Y";
        } else if (dias <= 2) {
            colorEstado = "R";
        }
        return colorEstado;
    }

    public void setColorEstado(String colorEstado) {
        this.colorEstado = colorEstado;
    }

    public Seguimiento(Integer idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Integer getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(Integer idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Date getSegFechaActividad() {
        return segFechaActividad;
    }

    public void setSegFechaActividad(Date segFechaActividad) {
        this.segFechaActividad = segFechaActividad;
    }

    public String getSegDescripcion() {
        return segDescripcion;
    }

    public void setSegDescripcion(String segDescripcion) {
        this.segDescripcion = segDescripcion;
    }

    public Date getSegFechaProxima() {
        return segFechaProxima;
    }

    public void setSegFechaProxima(Date segFechaProxima) {
        this.segFechaProxima = segFechaProxima;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
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

    @XmlTransient
    public Collection<DetalleSeguimiento> getDetalleSeguimientoCollection() {
        return detalleSeguimientoCollection;
    }

    public void setDetalleSeguimientoCollection(Collection<DetalleSeguimiento> detalleSeguimientoCollection) {
        this.detalleSeguimientoCollection = detalleSeguimientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeguimiento != null ? idSeguimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((this.idSeguimiento == null && other.idSeguimiento != null) || (this.idSeguimiento != null && !this.idSeguimiento.equals(other.idSeguimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Seguimiento[ idSeguimiento=" + idSeguimiento + " ]";
    }

}
