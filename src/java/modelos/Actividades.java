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
public class Actividades {
    private int id_actividad;
    private String nombre_actividad;
    private int precio_actividad;
    private String turno_actividad;
    Horarios horario;
    private int iva_actividad;

    public Actividades() {
    }

    public Actividades(int id_actividad, String nombre_actividad, int precio_actividad, String turno_actividad, Horarios horario, int iva_actividad) {
        this.id_actividad = id_actividad;
        this.nombre_actividad = nombre_actividad;
        this.precio_actividad = precio_actividad;
        this.turno_actividad = turno_actividad;
        this.horario = horario;
        this.iva_actividad = iva_actividad;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    public int getPrecio_actividad() {
        return precio_actividad;
    }

    public void setPrecio_actividad(int precio_actividad) {
        this.precio_actividad = precio_actividad;
    }

    public String getTurno_actividad() {
        return turno_actividad;
    }

    public void setTurno_actividad(String turno_actividad) {
        this.turno_actividad = turno_actividad;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    public int getIva_actividad() {
        return iva_actividad;
    }

    public void setIva_actividad(int iva_actividad) {
        this.iva_actividad = iva_actividad;
    }

   
  
}
