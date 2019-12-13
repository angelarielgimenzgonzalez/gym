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
public class Personas {
    private int id_persona;
    private String nombre_persona;
    private String apellido_persona;
    private String direccion_persona;
    private String correo_persona;
    private String ci_persona;
    private String telefono_persona;
    private String edad_persona;
    Ciudades ciudades;
    EstadosCiviles estadosciviles;
    Generos generos;
    TiposPersonas tipospersonas;

    public Personas() {
    }

    public Personas(int id_persona, String nombre_persona, String apellido_persona, String direccion_persona, String correo_persona, String ci_persona, String telefono_persona, String edad_persona, Generos generos, TiposPersonas tipospersonas) {
        this.id_persona = id_persona;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.direccion_persona = direccion_persona;
        this.correo_persona = correo_persona;
        this.ci_persona = ci_persona;
        this.telefono_persona = telefono_persona;
        this.edad_persona = edad_persona;
        this.generos = generos;
        this.tipospersonas = tipospersonas;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApellido_persona() {
        return apellido_persona;
    }

    public void setApellido_persona(String apellido_persona) {
        this.apellido_persona = apellido_persona;
    }

    public String getDireccion_persona() {
        return direccion_persona;
    }

    public void setDireccion_persona(String direccion_persona) {
        this.direccion_persona = direccion_persona;
    }

    public String getCorreo_persona() {
        return correo_persona;
    }

    public void setCorreo_persona(String correo_persona) {
        this.correo_persona = correo_persona;
    }

    public String getCi_persona() {
        return ci_persona;
    }

    public void setCi_persona(String ci_persona) {
        this.ci_persona = ci_persona;
    }

    public String getTelefono_persona() {
        return telefono_persona;
    }

    public void setTelefono_persona(String telefono_persona) {
        this.telefono_persona = telefono_persona;
    }

    public String getEdad_persona() {
        return edad_persona;
    }

    public void setEdad_persona(String edad_persona) {
        this.edad_persona = edad_persona;
    }

    public Ciudades getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public EstadosCiviles getEstadosciviles() {
        return estadosciviles;
    }

    public void setEstadosciviles(EstadosCiviles estadosciviles) {
        this.estadosciviles = estadosciviles;
    }

    public Generos getGeneros() {
        return generos;
    }

    public void setGeneros(Generos generos) {
        this.generos = generos;
    }

    public TiposPersonas getTipospersonas() {
        return tipospersonas;
    }

    public void setTipospersonas(TiposPersonas tipospersonas) {
        this.tipospersonas = tipospersonas;
    }

    
}
