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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente")
    , @NamedQuery(name = "Cliente.findByCliNombreComercial", query = "SELECT c FROM Cliente c WHERE c.cliNombreComercial = :cliNombreComercial")
    , @NamedQuery(name = "Cliente.findByCliCedula", query = "SELECT c FROM Cliente c WHERE c.idPersona.perDni = :perDni")
    , @NamedQuery(name = "Cliente.findLikeCliCedula", query = "SELECT c FROM Cliente c WHERE c.idPersona.perDni LIKE :perDni")
    , @NamedQuery(name = "Cliente.findByLikeCliNombreComercial", query = "SELECT c FROM Cliente c WHERE c.cliNombreComercial LIKE :cliNombreComercial")
    , @NamedQuery(name = "Cliente.findByCliFechaRegistro", query = "SELECT c FROM Cliente c WHERE c.cliFechaRegistro = :cliFechaRegistro")
    , @NamedQuery(name = "Cliente.findByCliFechaActualizacion", query = "SELECT c FROM Cliente c WHERE c.cliFechaActualizacion = :cliFechaActualizacion")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Column(name = "cli_nombre_comercial")
    private String cliNombreComercial;
    @Column(name = "cli_clave")
    private String cliClave;
    @Column(name = "persona_contacto")
    private String personaContacto;
    @Column(name = "per_web")
    private String perWeb;
    @Column(name = "per_industria")
    private String perIndustria;
    @Column(name = "cli_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date cliFechaRegistro;
    @Column(name = "cli_fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date cliFechaActualizacion;
    @OneToMany(mappedBy = "idCliente")
    private Collection<Seguimiento> seguimientoCollection;
    @OneToMany(mappedBy = "idCliente")
    private Collection<Dominio> dominioCollection;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "id_tipo_cliente", referencedColumnName = "id_tipo_cliente")
    @ManyToOne
    private TipoCliente idTipoCliente;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "cliIdCliente")
    private Collection<Cotizacion> cotizacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<EmpleadoCliente> empleadoClienteCollection;

    public Cliente() {
    }

    public Cliente(String cliNombreComercial) {
        this.cliNombreComercial = cliNombreComercial;
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliNombreComercial() {
        return cliNombreComercial;
    }

    public void setCliNombreComercial(String cliNombreComercial) {
        this.cliNombreComercial = cliNombreComercial;
    }

    public Date getCliFechaRegistro() {
        return cliFechaRegistro;
    }

    public void setCliFechaRegistro(Date cliFechaRegistro) {
        this.cliFechaRegistro = cliFechaRegistro;
    }

    public Date getCliFechaActualizacion() {
        return cliFechaActualizacion;
    }

    public void setCliFechaActualizacion(Date cliFechaActualizacion) {
        this.cliFechaActualizacion = cliFechaActualizacion;
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

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public TipoCliente getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(TipoCliente idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCliClave() {
        return cliClave;
    }

    public void setCliClave(String cliClave) {
        this.cliClave = cliClave;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getPerWeb() {
        return perWeb;
    }

    public void setPerWeb(String perWeb) {
        this.perWeb = perWeb;
    }

    public String getPerIndustria() {
        return perIndustria;
    }

    public void setPerIndustria(String perIndustria) {
        this.perIndustria = perIndustria;
    }
    

    @XmlTransient
    public Collection<Cotizacion> getCotizacionCollection() {
        return cotizacionCollection;
    }

    public void setCotizacionCollection(Collection<Cotizacion> cotizacionCollection) {
        this.cotizacionCollection = cotizacionCollection;
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
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Cliente[ idCliente=" + idCliente + " ]";
    }

}
