/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Darwin
 */
@Embeddable
public class EmpleadoClientePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private int idEmpleado;

    public EmpleadoClientePK() {
    }

    public EmpleadoClientePK(int idCliente, int idEmpleado) {
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCliente;
        hash += (int) idEmpleado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadoClientePK)) {
            return false;
        }
        EmpleadoClientePK other = (EmpleadoClientePK) object;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.idEmpleado != other.idEmpleado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.EmpleadoClientePK[ idCliente=" + idCliente + ", idEmpleado=" + idEmpleado + " ]";
    }
    
}
