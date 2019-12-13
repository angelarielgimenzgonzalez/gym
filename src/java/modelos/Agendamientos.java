/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Alumno
 */
public class Agendamientos {
    private int id_agendamiento;
    private Date fecha_agendamiento;
    Personas persona;

    public Agendamientos() {
    }

    public Agendamientos(int id_agendamiento, Date fecha_agendamiento, Personas personas) {
        this.id_agendamiento = id_agendamiento;
        this.fecha_agendamiento = fecha_agendamiento;
        this.persona = personas;
    }

    public int getId_agendamiento() {
        return id_agendamiento;
    }

    public void setId_agendamiento(int id_agendamiento) {
        this.id_agendamiento = id_agendamiento;
    }

    public Date getFecha_agendamiento() {
        return fecha_agendamiento;
    }

    public void setFecha_agendamiento(Date fecha_agendamiento) {
        this.fecha_agendamiento = fecha_agendamiento;
    }

    public Personas getPersonas() {
        return persona;
    }

    public void setPersonas(Personas personas) {
        this.persona = personas;
    }
    
    
}
