
package com.coffee.Sixto.entidades;

import com.coffee.Sixto.enumeracion.Especie;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cafe {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    private Especie especie;
    private String variedad;
    private String origen;
    private Boolean blend; 
    private String tueste;
    private String beneficio;
    private Integer altitud;
    private Boolean certificado;
    private Integer cantidadTostador; //Existente en el tostador
    private Integer cantidadPV; //Existente en el PV
    private Integer cantidadConsumidor; //Solicita el Consumidor
    
    
    @ManyToOne
    private Cata cata;
    
    @ManyToOne
    private Tostador tostador;
    
    @ManyToOne
    private PV puntoVenta;
    
    @OneToOne
    private Consumidor consumidor;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    
    //GETTERS Y SETTERS
    public Integer getCantidadTostador() {    
        return cantidadTostador;
    }

    public void setCantidadTostador(Integer cantidadTostador) {
        this.cantidadTostador = cantidadTostador;
    }

    public Integer getCantidadPV() {
        return cantidadPV;
    }

    public void setCantidadPV(Integer cantidadPV) {
        this.cantidadPV = cantidadPV;
    }

    public Integer getCantidadConsumidor() {
        return cantidadConsumidor;
    }

    
    public void setCantidadConsumidor(Integer cantidadConsumidor) {
        this.cantidadConsumidor = cantidadConsumidor;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Especie getEspecie() {
        return especie;
    }

    public String getVariedad() {
        return variedad;
    }

    public String getOrigen() {
        return origen;
    }

    public Boolean getBlend() {
        return blend;
    }

    public String getTueste() {
        return tueste;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public Integer getAltitud() {
        return altitud;
    }

    public Boolean getCertificado() {
        return certificado;
    }

    public Cata getCata() {
        return cata;
    }

    public Tostador getTostador() {
        return tostador;
    }

    public PV getPuntoVenta() {
        return puntoVenta;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public Date getAlta() {
        return alta;
    }

    public Date getBaja() {
        return baja;
    }
    public Integer getCantidad() {
        return cantidadTostador;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setBlend(Boolean blend) {
        this.blend = blend;
    }

    public void setTueste(String tueste) {
        this.tueste = tueste;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public void setAltitud(Integer altitud) {
        this.altitud = altitud;
    }

    public void setCertificado(Boolean certificado) {
        this.certificado = certificado;
    }

    public void setCata(Cata cata) {
        this.cata = cata;
    }

    public void setTostador(Tostador tostador) {
        this.tostador = tostador;
    }

    public void setPuntoVenta(PV puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }
    
    public void setCantidad(Integer cantidad) {    
        this.cantidadTostador = cantidad;
    }
    
    
}
