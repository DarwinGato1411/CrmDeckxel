/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
@Table(name = "dominio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dominio.findAll", query = "SELECT d FROM Dominio d")
    , @NamedQuery(name = "Dominio.findByIdDominio", query = "SELECT d FROM Dominio d WHERE d.idDominio = :idDominio")
    , @NamedQuery(name = "Dominio.findByDomFechaRegistro", query = "SELECT d FROM Dominio d WHERE d.domFechaRegistro = :domFechaRegistro")
    , @NamedQuery(name = "Dominio.findByDomFechaCaduca", query = "SELECT d FROM Dominio d WHERE d.domFechaCaduca = :domFechaCaduca")
    , @NamedQuery(name = "Dominio.findByDomDescripcion", query = "SELECT d FROM Dominio d WHERE d.domDescripcion = :domDescripcion")
    , @NamedQuery(name = "Dominio.findByDomEstado", query = "SELECT d FROM Dominio d WHERE d.domEstado = :domEstado")
    , @NamedQuery(name = "Dominio.findByDomPaquete", query = "SELECT d FROM Dominio d WHERE d.domPaquete = :domPaquete")
    , @NamedQuery(name = "Dominio.findByDomDominio", query = "SELECT d FROM Dominio d WHERE d.domDominio = :domDominio")
    , @NamedQuery(name = "Dominio.findByDomCosto", query = "SELECT d FROM Dominio d WHERE d.domCosto = :domCosto")})
public class Dominio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dominio")
    private Integer idDominio;
    @Column(name = "dom_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date domFechaRegistro;
    @Column(name = "dom_fecha_caduca")
    @Temporal(TemporalType.DATE)
    private Date domFechaCaduca;
    @Column(name = "dom_descripcion")
    private String domDescripcion;
    @Column(name = "dom_estado")
    private Boolean domEstado;
    @Column(name = "dom_paquete")
    private String domPaquete;
    @Column(name = "dom_dominio")
    private String domDominio;
    @Transient
    private String colorEstado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dom_costo")
    private BigDecimal domCosto;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Cliente idCliente;
    @JoinColumn(name = "is_estado_procesos", referencedColumnName = "is_estado_procesos")
    @ManyToOne
    private EstadoProcesos isEstadoProcesos;
    @JoinColumn(name = "id_tipo_servicio", referencedColumnName = "id_tipo_servicio")
    @ManyToOne
    private TipoServicio idTipoServicio;
    @OneToMany(mappedBy = "idDominio")
    private Collection<DetalleDominio> detalleDominioCollection;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public Dominio() {
    }

    public Dominio(Integer idDominio) {
        this.idDominio = idDominio;
    }

    public Integer getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(Integer idDominio) {
        this.idDominio = idDominio;
    }

    public Date getDomFechaRegistro() {
        return domFechaRegistro;
    }

    public void setDomFechaRegistro(Date domFechaRegistro) {
        this.domFechaRegistro = domFechaRegistro;
    }

    public Date getDomFechaCaduca() {
        return domFechaCaduca;
    }

    public void setDomFechaCaduca(Date domFechaCaduca) {
        this.domFechaCaduca = domFechaCaduca;
    }

    public String getDomDescripcion() {
        return domDescripcion;
    }

    public void setDomDescripcion(String domDescripcion) {
        this.domDescripcion = domDescripcion;
    }

    public Boolean getDomEstado() {
        return domEstado;
    }

    public void setDomEstado(Boolean domEstado) {
        this.domEstado = domEstado;
    }

    public String getDomPaquete() {
        return domPaquete;
    }

    public void setDomPaquete(String domPaquete) {
        this.domPaquete = domPaquete;
    }

    public String getDomDominio() {
        return domDominio;
    }

    public void setDomDominio(String domDominio) {
        this.domDominio = domDominio;
    }

    public BigDecimal getDomCosto() {
        return domCosto;
    }

    public void setDomCosto(BigDecimal domCosto) {
        this.domCosto = domCosto;
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

    public TipoServicio getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(TipoServicio idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getColorEstado() {
        long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
        Date fechaInicial = new Date();

        int dias = (int) ((domFechaCaduca.getTime() - fechaInicial.getTime()) / MILLSECS_PER_DAY);
//        System.out.println("dias  "+dias +" fecha caduca "+domFechaCaduca+" fecha actual "+fechaInicial);
        if (dias > 5) {
            colorEstado = "G";
        } else if (dias < 5 && dias > 2) {
            colorEstado = "Y";
        } else if (dias <= 2) {
            colorEstado = "R";
        }
        return colorEstado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

   
    

    public void setColorEstado(String colorEstado) {
        this.colorEstado = colorEstado;
    }

    @XmlTransient
    public Collection<DetalleDominio> getDetalleDominioCollection() {
        return detalleDominioCollection;
    }

    public void setDetalleDominioCollection(Collection<DetalleDominio> detalleDominioCollection) {
        this.detalleDominioCollection = detalleDominioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDominio != null ? idDominio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dominio)) {
            return false;
        }
        Dominio other = (Dominio) object;
        if ((this.idDominio == null && other.idDominio != null) || (this.idDominio != null && !this.idDominio.equals(other.idDominio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Dominio[ idDominio=" + idDominio + " ]";
    }

}
