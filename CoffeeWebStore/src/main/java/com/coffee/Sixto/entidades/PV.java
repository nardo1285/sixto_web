
package com.coffee.Sixto.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PV {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String responsableCuenta;
    private String email;
    private String password;
    
    @ManyToOne
    private Zona zona;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;
    
    public String getResponsableCuenta() {
        return responsableCuenta;
    }

    @OneToOne
    private Foto foto;
    
    //GETTERS Y SETTERS
    public void setResponsableCuenta(String responsableCuenta) {
        this.responsableCuenta = responsableCuenta;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Zona getZona() {
        return zona;
    }

    public Date getAlta() {
        return alta;
    }

    public Date getBaja() {
        return baja;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    
}
