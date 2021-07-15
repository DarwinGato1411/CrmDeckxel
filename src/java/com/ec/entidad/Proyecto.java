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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findByIdProyecto", query = "SELECT p FROM Proyecto p WHERE p.idProyecto = :idProyecto")
    , @NamedQuery(name = "Proyecto.findByProyInicio", query = "SELECT p FROM Proyecto p WHERE p.proyInicio = :proyInicio")
    , @NamedQuery(name = "Proyecto.findByProyFin", query = "SELECT p FROM Proyecto p WHERE p.proyFin = :proyFin")
    , @NamedQuery(name = "Proyecto.findByProyDescripcion", query = "SELECT p FROM Proyecto p WHERE p.proyDescripcion = :proyDescripcion")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Column(name = "proy_inicio")
    @Temporal(TemporalType.DATE)
    private Date proyInicio;
    @Column(name = "proy_fin")
    @Temporal(TemporalType.DATE)
    private Date proyFin;
    @Column(name = "proy_descripcion")
    private String proyDescripcion;
    @OneToMany(mappedBy = "idProyecto")
    private Collection<DetalleProyecto> detalleProyectoCollection;
    @JoinColumn(name = "id_cotizacion", referencedColumnName = "id_cotizacion")
    @ManyToOne
    private Cotizacion idCotizacion;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado idEmpleado;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Date getProyInicio() {
        return proyInicio;
    }

    public void setProyInicio(Date proyInicio) {
        this.proyInicio = proyInicio;
    }

    public Date getProyFin() {
        return proyFin;
    }

    public void setProyFin(Date proyFin) {
        this.proyFin = proyFin;
    }

    public String getProyDescripcion() {
        return proyDescripcion;
    }

    public void setProyDescripcion(String proyDescripcion) {
        this.proyDescripcion = proyDescripcion;
    }

    @XmlTransient
    public Collection<DetalleProyecto> getDetalleProyectoCollection() {
        return detalleProyectoCollection;
    }

    public void setDetalleProyectoCollection(Collection<DetalleProyecto> detalleProyectoCollection) {
        this.detalleProyectoCollection = detalleProyectoCollection;
    }

    public Cotizacion getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Cotizacion idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Proyecto[ idProyecto=" + idProyecto + " ]";
    }
    
}
