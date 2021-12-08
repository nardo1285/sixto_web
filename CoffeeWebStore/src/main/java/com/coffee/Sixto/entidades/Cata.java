
package com.coffee.Sixto.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cata {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String sabor;
    private String aroma;
    private String cuerpo;
    
    
    //GETTERS Y SETTERS
    public String getId() {
        return id;
    }

    public String getSabor() {
        return sabor;
    }

    public String getAroma() {
        return aroma;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    
}
