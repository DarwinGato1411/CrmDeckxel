/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    ,@NamedQuery(name = "Producto.findCountPrincipal", query = "SELECT p FROM Producto p WHERE p.prodPrincipal=1")
    , @NamedQuery(name = "Producto.findByProdSerie", query = "SELECT p FROM Producto p WHERE p.prodSerie = :prodSerie")
    , @NamedQuery(name = "Producto.findByProdNombre", query = "SELECT p FROM Producto p WHERE p.prodNombre = :prodNombre")
    , @NamedQuery(name = "Producto.findByProdCostoCompra", query = "SELECT p FROM Producto p WHERE p.prodCostoCompra = :prodCostoCompra")
    , @NamedQuery(name = "Producto.findByProdCostoVenta", query = "SELECT p FROM Producto p WHERE p.prodCostoVenta = :prodCostoVenta")
    , @NamedQuery(name = "Producto.findByProdValorIncial", query = "SELECT p FROM Producto p WHERE p.prodValorIncial = :prodValorIncial")
    , @NamedQuery(name = "Producto.findByProdUnidadMedida", query = "SELECT p FROM Producto p WHERE p.prodUnidadMedida = :prodUnidadMedida")
    , @NamedQuery(name = "Producto.findByProdFechaRegistro", query = "SELECT p FROM Producto p WHERE p.prodFechaRegistro = :prodFechaRegistro")
    , @NamedQuery(name = "Producto.findByProdCantidadMinima", query = "SELECT p FROM Producto p WHERE p.prodCantidadMinima = :prodCantidadMinima")
    , @NamedQuery(name = "Producto.findByProdMarca", query = "SELECT p FROM Producto p WHERE p.prodMarca = :prodMarca")
    , @NamedQuery(name = "Producto.findByCodCategoria", query = "SELECT p FROM Producto p WHERE p.codCategoria = :codCategoria")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto")
    private Integer idProducto;
    @Size(max = 50)
    @Column(name = "prod_serie")
    private String prodSerie;
    @Size(max = 100)
    @Column(name = "prod_nombre")
    private String prodNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prod_costo_compra")
    private BigDecimal prodCostoCompra;
    @Column(name = "prod_costo_venta")
    private BigDecimal prodCostoVenta;
    @Column(name = "prod_valor_incial")
    private BigDecimal prodValorIncial;
    @Size(max = 50)
    @Column(name = "prod_unidad_medida")
    private String prodUnidadMedida;
    @Column(name = "prod_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date prodFechaRegistro;
    @Column(name = "prod_cantidad_minima")
    private BigDecimal prodCantidadMinima;
    @Size(max = 100)
    @Column(name = "prod_marca")
    private String prodMarca;
    @Column(name = "cod_categoria")
    private Integer codCategoria;
    @Lob
    @Column(name = "prod_qr")
    private byte[] prodQr;
    @Column(name = "prod_principal")
    private Integer prodPrincipal;
    @Column(name = "prod_isPrincipal")
    private Boolean prodIsPrincipal;
    @Column(name = "pord_costo_compra")
    private BigDecimal pordCostoCompra;
    @Column(name = "prod_abreviado")
    private String prodAbreviado;
    @OneToMany(mappedBy = "idProducto")
    private Collection<DetalleCotizacion> detalleCotizacionCollection;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getProdSerie() {
        return prodSerie;
    }

    public void setProdSerie(String prodSerie) {
        this.prodSerie = prodSerie;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public BigDecimal getProdCostoCompra() {
        return prodCostoCompra;
    }

    public void setProdCostoCompra(BigDecimal prodCostoCompra) {
        this.prodCostoCompra = prodCostoCompra;
    }

    public BigDecimal getProdCostoVenta() {
        return prodCostoVenta;
    }

    public void setProdCostoVenta(BigDecimal prodCostoVenta) {
        this.prodCostoVenta = prodCostoVenta;
    }

    public BigDecimal getProdValorIncial() {
        return prodValorIncial;
    }

    public void setProdValorIncial(BigDecimal prodValorIncial) {
        this.prodValorIncial = prodValorIncial;
    }

    public String getProdUnidadMedida() {
        return prodUnidadMedida;
    }

    public void setProdUnidadMedida(String prodUnidadMedida) {
        this.prodUnidadMedida = prodUnidadMedida;
    }

    public Date getProdFechaRegistro() {
        return prodFechaRegistro;
    }

    public void setProdFechaRegistro(Date prodFechaRegistro) {
        this.prodFechaRegistro = prodFechaRegistro;
    }

    public BigDecimal getProdCantidadMinima() {
        return prodCantidadMinima;
    }

    public void setProdCantidadMinima(BigDecimal prodCantidadMinima) {
        this.prodCantidadMinima = prodCantidadMinima;
    }

    public String getProdMarca() {
        return prodMarca;
    }

    public void setProdMarca(String prodMarca) {
        this.prodMarca = prodMarca;
    }

    public Integer getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public byte[] getProdQr() {
        return prodQr;
    }

    public void setProdQr(byte[] prodQr) {
        this.prodQr = prodQr;
    }

    public Integer getProdPrincipal() {
        return prodPrincipal;
    }

    public void setProdPrincipal(Integer prodPrincipal) {
        this.prodPrincipal = prodPrincipal;
    }

    public Boolean getProdIsPrincipal() {
        return prodIsPrincipal;
    }

    public void setProdIsPrincipal(Boolean prodIsPrincipal) {
        this.prodIsPrincipal = prodIsPrincipal;
    }

    public BigDecimal getPordCostoCompra() {
        return pordCostoCompra;
    }

    public void setPordCostoCompra(BigDecimal pordCostoCompra) {
        this.pordCostoCompra = pordCostoCompra;
    }

    public String getProdAbreviado() {
        return prodAbreviado;
    }

    public void setProdAbreviado(String prodAbreviado) {
        this.prodAbreviado = prodAbreviado;
    }


    @XmlTransient
    public Collection<DetalleCotizacion> getDetalleCotizacionCollection() {
        return detalleCotizacionCollection;
    }

    public void setDetalleCotizacionCollection(Collection<DetalleCotizacion> detalleCotizacionCollection) {
        this.detalleCotizacionCollection = detalleCotizacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Producto[ idProducto=" + idProducto + " ]";
    }

}
