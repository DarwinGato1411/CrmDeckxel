/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "empleado_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpleadoCliente.findAll", query = "SELECT e FROM EmpleadoCliente e")
    , @NamedQuery(name = "EmpleadoCliente.findByIdCliente", query = "SELECT e FROM EmpleadoCliente e WHERE e.empleadoClientePK.idCliente = :idCliente")
    , @NamedQuery(name = "EmpleadoCliente.findByIdEmpleado", query = "SELECT e FROM EmpleadoCliente e WHERE e.empleadoClientePK.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "EmpleadoCliente.findByEmpcFechaAsignacion", query = "SELECT e FROM EmpleadoCliente e WHERE e.empcFechaAsignacion = :empcFechaAsignacion")})
public class EmpleadoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmpleadoClientePK empleadoClientePK;
    @Column(name = "empc_fecha_asignacion")
    @Temporal(TemporalType.DATE)
    private Date empcFechaAsignacion;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public EmpleadoCliente() {
    }

    public EmpleadoCliente(EmpleadoClientePK empleadoClientePK) {
        this.empleadoClientePK = empleadoClientePK;
    }

    public EmpleadoCliente(int idCliente, int idEmpleado) {
        this.empleadoClientePK = new EmpleadoClientePK(idCliente, idEmpleado);
    }

    public EmpleadoClientePK getEmpleadoClientePK() {
        return empleadoClientePK;
    }

    public void setEmpleadoClientePK(EmpleadoClientePK empleadoClientePK) {
        this.empleadoClientePK = empleadoClientePK;
    }

    public Date getEmpcFechaAsignacion() {
        return empcFechaAsignacion;
    }

    public void setEmpcFechaAsignacion(Date empcFechaAsignacion) {
        this.empcFechaAsignacion = empcFechaAsignacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empleadoClientePK != null ? empleadoClientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadoCliente)) {
            return false;
        }
        EmpleadoCliente other = (EmpleadoCliente) object;
        if ((this.empleadoClientePK == null && other.empleadoClientePK != null) || (this.empleadoClientePK != null && !this.empleadoClientePK.equals(other.empleadoClientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.EmpleadoCliente[ empleadoClientePK=" + empleadoClientePK + " ]";
    }
    
}
