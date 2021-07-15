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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByUsuNombre", query = "SELECT u FROM Usuario u WHERE u.usuNombre = :usuNombre")
    , @NamedQuery(name = "Usuario.findByLikeUsuNombre", query = "SELECT u FROM Usuario u WHERE u.usuNombre LIKE :usuNombre")
    , @NamedQuery(name = "Usuario.findByUsuLogin", query = "SELECT u FROM Usuario u WHERE u.usuLogin = :usuLogin")
    , @NamedQuery(name = "Usuario.findByUsuPassword", query = "SELECT u FROM Usuario u WHERE u.usuPassword = :usuPassword")
    , @NamedQuery(name = "Usuario.findByUsuCorreo", query = "SELECT u FROM Usuario u WHERE u.usuCorreo = :usuCorreo")
    , @NamedQuery(name = "Usuario.findByUsuNivel", query = "SELECT u FROM Usuario u WHERE u.usuNivel = :usuNivel")
    , @NamedQuery(name = "Usuario.findByUsuFoto", query = "SELECT u FROM Usuario u WHERE u.usuFoto = :usuFoto")
    , @NamedQuery(name = "Usuario.findByUsuTipoUsuario", query = "SELECT u FROM Usuario u WHERE u.usuTipoUsuario = :usuTipoUsuario")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "usu_nombre")
    private String usuNombre;
    @Column(name = "usu_login")
    private String usuLogin;
    @Column(name = "usu_password")
    private String usuPassword;
    @Column(name = "usu_correo")
    private String usuCorreo;
    @Column(name = "usu_nivel")
    private Integer usuNivel;
    @Column(name = "usu_foto")
    private String usuFoto;
    @Column(name = "usu_tipo_usuario")
    private String usuTipoUsuario;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Cliente> clienteCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Cotizacion> cotizacionCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Seguimiento> seguimientoCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Dominio> dominioCollection;
    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public Integer getUsuNivel() {
        return usuNivel;
    }

    public void setUsuNivel(Integer usuNivel) {
        this.usuNivel = usuNivel;
    }

    public String getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(String usuFoto) {
        this.usuFoto = usuFoto;
    }

    public String getUsuTipoUsuario() {
        return usuTipoUsuario;
    }

    public void setUsuTipoUsuario(String usuTipoUsuario) {
        this.usuTipoUsuario = usuTipoUsuario;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<Cotizacion> getCotizacionCollection() {
        return cotizacionCollection;
    }

    public void setCotizacionCollection(Collection<Cotizacion> cotizacionCollection) {
        this.cotizacionCollection = cotizacionCollection;
    }

 
    
      @XmlTransient
    public Collection<Seguimiento> getSeguimientoCollection() {
        return seguimientoCollection;
    }

    public void setSeguimientoCollection(Collection<Seguimiento> seguimientoCollection) {
        this.seguimientoCollection = seguimientoCollection;
    }

    public Collection<Dominio> getDominioCollection() {
        return dominioCollection;
    }

    public void setDominioCollection(Collection<Dominio> dominioCollection) {
        this.dominioCollection = dominioCollection;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Usuario[ idUsuario=" + idUsuario + " ]";
    }

}
