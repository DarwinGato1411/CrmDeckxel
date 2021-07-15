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
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona")
    , @NamedQuery(name = "Persona.findByPerDni", query = "SELECT p FROM Persona p WHERE p.perDni = :perDni")
    , @NamedQuery(name = "Persona.findByPerNombre", query = "SELECT p FROM Persona p WHERE p.perNombre = :perNombre")
    , @NamedQuery(name = "Persona.findByPerApellido", query = "SELECT p FROM Persona p WHERE p.perApellido = :perApellido")
    , @NamedQuery(name = "Persona.findByPerCorreo", query = "SELECT p FROM Persona p WHERE p.perCorreo = :perCorreo")
    , @NamedQuery(name = "Persona.findByPerContacto", query = "SELECT p FROM Persona p WHERE p.perContacto = :perContacto")
    , @NamedQuery(name = "Persona.findByPerMovil", query = "SELECT p FROM Persona p WHERE p.perMovil = :perMovil")
    , @NamedQuery(name = "Persona.findByPrDireccion", query = "SELECT p FROM Persona p WHERE p.prDireccion = :prDireccion")
    , @NamedQuery(name = "Persona.findByPerTieneWhatsap", query = "SELECT p FROM Persona p WHERE p.perTieneWhatsap = :perTieneWhatsap")
    , @NamedQuery(name = "Persona.findByPerFacebook", query = "SELECT p FROM Persona p WHERE p.perFacebook = :perFacebook")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "per_dni")
    private String perDni;
    @Column(name = "per_nombre")
    private String perNombre;
    @Column(name = "per_apellido")
    private String perApellido;
    @Column(name = "per_correo")
    private String perCorreo;
    @Column(name = "per_contacto")
    private String perContacto;
    @Column(name = "per_movil")
    private String perMovil;
    @Column(name = "pr_direccion")
    private String prDireccion;
    @Column(name = "per_tiene_whatsap")
    private Boolean perTieneWhatsap;
    @Column(name = "per_facebook")
    private String perFacebook;
    @OneToMany(mappedBy = "idPersona")
    private Collection<Cliente> clienteCollection;
    @OneToMany(mappedBy = "idPersona")
    private Collection<Empleado> empleadoCollection;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getPerDni() {
        return perDni;
    }

    public void setPerDni(String perDni) {
        this.perDni = perDni;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public String getPerCorreo() {
        return perCorreo;
    }

    public void setPerCorreo(String perCorreo) {
        this.perCorreo = perCorreo;
    }

    public String getPerContacto() {
        return perContacto;
    }

    public void setPerContacto(String perContacto) {
        this.perContacto = perContacto;
    }

    public String getPerMovil() {
        return perMovil;
    }

    public void setPerMovil(String perMovil) {
        this.perMovil = perMovil;
    }

    public String getPrDireccion() {
        return prDireccion;
    }

    public void setPrDireccion(String prDireccion) {
        this.prDireccion = prDireccion;
    }

    public Boolean getPerTieneWhatsap() {
        return perTieneWhatsap;
    }

    public void setPerTieneWhatsap(Boolean perTieneWhatsap) {
        this.perTieneWhatsap = perTieneWhatsap;
    }

    public String getPerFacebook() {
        return perFacebook;
    }

    public void setPerFacebook(String perFacebook) {
        this.perFacebook = perFacebook;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Persona[ idPersona=" + idPersona + " ]";
    }
    
}
