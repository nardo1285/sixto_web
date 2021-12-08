
package com.coffee.Sixto.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Foto {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    
    //Con 'mime' vamos a decirle que tipo de imagen es desde el navegador
    private String mime;
    
    //Lob identifica que el tipo de datos es pesado y en la base de datos va a utilizar un tipo de
    //datos específico para tal archivo
    //El fetchType Lazy indica que se cargue solo el contenido cuando lo pidamos con un Get
    //Haciendo que los qeryes sean mas livianos, trayendo sólo los atributos 'viber' (los atributos comunes)
    //y mediante otro Get, traigo los atributos Lazy
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;

    
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMime() {
        return mime;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
    
    
}
