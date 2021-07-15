/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "Empresa.findByEmpRuc", query = "SELECT e FROM Empresa e WHERE e.empRuc = :empRuc")
    , @NamedQuery(name = "Empresa.findByEmpNombre", query = "SELECT e FROM Empresa e WHERE e.empNombre = :empNombre")
    , @NamedQuery(name = "Empresa.findByEmpDireccion", query = "SELECT e FROM Empresa e WHERE e.empDireccion = :empDireccion")
    , @NamedQuery(name = "Empresa.findByEmpContacto", query = "SELECT e FROM Empresa e WHERE e.empContacto = :empContacto")
    , @NamedQuery(name = "Empresa.findByEmpMovil", query = "SELECT e FROM Empresa e WHERE e.empMovil = :empMovil")
    , @NamedQuery(name = "Empresa.findByEmpMail", query = "SELECT e FROM Empresa e WHERE e.empMail = :empMail")
    , @NamedQuery(name = "Empresa.findByEmpRepresentante", query = "SELECT e FROM Empresa e WHERE e.empRepresentante = :empRepresentante")
    , @NamedQuery(name = "Empresa.findByDomEstado", query = "SELECT e FROM Empresa e WHERE e.domEstado = :domEstado")
    , @NamedQuery(name = "Empresa.findByEmpUsuarioMail", query = "SELECT e FROM Empresa e WHERE e.empUsuarioMail = :empUsuarioMail")
    , @NamedQuery(name = "Empresa.findByEmpPasswordMail", query = "SELECT e FROM Empresa e WHERE e.empPasswordMail = :empPasswordMail")
    , @NamedQuery(name = "Empresa.findByEmpHostMail", query = "SELECT e FROM Empresa e WHERE e.empHostMail = :empHostMail")
    , @NamedQuery(name = "Empresa.findByEmpPortMail", query = "SELECT e FROM Empresa e WHERE e.empPortMail = :empPortMail")
    , @NamedQuery(name = "Empresa.findByEmpProtocolMail", query = "SELECT e FROM Empresa e WHERE e.empProtocolMail = :empProtocolMail")
    , @NamedQuery(name = "Empresa.findByEmpUsuariosmptMail", query = "SELECT e FROM Empresa e WHERE e.empUsuariosmptMail = :empUsuariosmptMail")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private Integer idEmpresa;
    @Size(max = 13)
    @Column(name = "emp_ruc")
    private String empRuc;
    @Size(max = 100)
    @Column(name = "emp_nombre")
    private String empNombre;
    @Size(max = 100)
    @Column(name = "emp_direccion")
    private String empDireccion;
    @Size(max = 100)
    @Column(name = "emp_contacto")
    private String empContacto;
    @Size(max = 100)
    @Column(name = "emp_movil")
    private String empMovil;
    @Size(max = 100)
    @Column(name = "emp_mail")
    private String empMail;
    @Size(max = 100)
    @Column(name = "emp_representante")
    private String empRepresentante;
    @Column(name = "dom_estado")
    private Boolean domEstado;
    @Size(max = 100)
    @Column(name = "emp_usuario_mail")
    private String empUsuarioMail;
    @Size(max = 100)
    @Column(name = "emp_password_mail")
    private String empPasswordMail;
    @Size(max = 100)
    @Column(name = "emp_host_mail")
    private String empHostMail;
    @Size(max = 100)
    @Column(name = "emp_port_mail")
    private String empPortMail;
    @Size(max = 100)
    @Column(name = "emp_protocol_mail")
    private String empProtocolMail;
    @Size(max = 100)
    @Column(name = "emp_usuariosmpt_mail")
    private String empUsuariosmptMail;
    @Column(name = "emp_directorio_cotizaciones")
    private String empDirectorioCotizaciones;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEmpRuc() {
        return empRuc;
    }

    public void setEmpRuc(String empRuc) {
        this.empRuc = empRuc;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpDireccion() {
        return empDireccion;
    }

    public void setEmpDireccion(String empDireccion) {
        this.empDireccion = empDireccion;
    }

    public String getEmpContacto() {
        return empContacto;
    }

    public void setEmpContacto(String empContacto) {
        this.empContacto = empContacto;
    }

    public String getEmpMovil() {
        return empMovil;
    }

    public void setEmpMovil(String empMovil) {
        this.empMovil = empMovil;
    }

    public String getEmpMail() {
        return empMail;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }

    public String getEmpRepresentante() {
        return empRepresentante;
    }

    public void setEmpRepresentante(String empRepresentante) {
        this.empRepresentante = empRepresentante;
    }

    public Boolean getDomEstado() {
        return domEstado;
    }

    public void setDomEstado(Boolean domEstado) {
        this.domEstado = domEstado;
    }

    public String getEmpUsuarioMail() {
        return empUsuarioMail;
    }

    public void setEmpUsuarioMail(String empUsuarioMail) {
        this.empUsuarioMail = empUsuarioMail;
    }

    public String getEmpPasswordMail() {
        return empPasswordMail;
    }

    public void setEmpPasswordMail(String empPasswordMail) {
        this.empPasswordMail = empPasswordMail;
    }

    public String getEmpHostMail() {
        return empHostMail;
    }

    public void setEmpHostMail(String empHostMail) {
        this.empHostMail = empHostMail;
    }

    public String getEmpPortMail() {
        return empPortMail;
    }

    public void setEmpPortMail(String empPortMail) {
        this.empPortMail = empPortMail;
    }

    public String getEmpProtocolMail() {
        return empProtocolMail;
    }

    public void setEmpProtocolMail(String empProtocolMail) {
        this.empProtocolMail = empProtocolMail;
    }

    public String getEmpUsuariosmptMail() {
        return empUsuariosmptMail;
    }

    public void setEmpUsuariosmptMail(String empUsuariosmptMail) {
        this.empUsuariosmptMail = empUsuariosmptMail;
    }

    public String getEmpDirectorioCotizaciones() {
        return empDirectorioCotizaciones;
    }

    public void setEmpDirectorioCotizaciones(String empDirectorioCotizaciones) {
        this.empDirectorioCotizaciones = empDirectorioCotizaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Empresa[ idEmpresa=" + idEmpresa + " ]";
    }

}
