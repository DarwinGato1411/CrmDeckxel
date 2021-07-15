/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "Empleado.findByLikePerNombre", query = "SELECT e FROM Empleado e WHERE e.idPersona.perNombre LIKE :perNombre")
    , @NamedQuery(name = "Empleado.findByEmpSueldo", query = "SELECT e FROM Empleado e WHERE e.empSueldo = :empSueldo")
    , @NamedQuery(name = "Empleado.findByEmpCargo", query = "SELECT e FROM Empleado e WHERE e.empCargo = :empCargo")
    , @NamedQuery(name = "Empleado.findByEmpFoto", query = "SELECT e FROM Empleado e WHERE e.empFoto = :empFoto")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "emp_sueldo")
    private BigDecimal empSueldo;
    @Column(name = "emp_cargo")
    private String empCargo;
    @Column(name = "emp_foto")
    private String empFoto;
    @OneToMany(mappedBy = "idEmpleado")
    private Collection<Proyecto> proyectoCollection;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<EmpleadoCliente> empleadoClienteCollection;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getEmpSueldo() {
        return empSueldo;
    }

    public void setEmpSueldo(BigDecimal empSueldo) {
        this.empSueldo = empSueldo;
    }

    public String getEmpCargo() {
        return empCargo;
    }

    public void setEmpCargo(String empCargo) {
        this.empCargo = empCargo;
    }

    public String getEmpFoto() {
        return empFoto;
    }

    public void setEmpFoto(String empFoto) {
        this.empFoto = empFoto;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public Collection<EmpleadoCliente> getEmpleadoClienteCollection() {
        return empleadoClienteCollection;
    }

    public void setEmpleadoClienteCollection(Collection<EmpleadoCliente> empleadoClienteCollection) {
        this.empleadoClienteCollection = empleadoClienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Empleado[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
