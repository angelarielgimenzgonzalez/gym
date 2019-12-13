/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Administrator
 */
public class TiposPersonas {
    private int id_tipopersona;
    private String nombre_tipopersona;

    public TiposPersonas() {
    }

    public TiposPersonas(int id_tipopersona, String nombre_tipopersona) {
        this.id_tipopersona = id_tipopersona;
        this.nombre_tipopersona = nombre_tipopersona;
    }

    public int getId_tipopersona() {
        return id_tipopersona;
    }

    public void setId_tipopersona(int id_tipopersona) {
        this.id_tipopersona = id_tipopersona;
    }

    public String getNombre_tipopersona() {
        return nombre_tipopersona;
    }

    public void setNombre_tipopersona(String nombre_tipopersona) {
        this.nombre_tipopersona = nombre_tipopersona;
    }
    
    
}
