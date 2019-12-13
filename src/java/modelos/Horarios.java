/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;


/**
 *
 * @author ALUMNO
 */
public class Horarios {
    int id_horario;
    String nombre_horario;
    String horain_horario;
    String horafin_horario;

    public Horarios() {
    }

    public Horarios(int id_horario, String nombre_horario, String horain_horario, String horafin_horario) {
        this.id_horario = id_horario;
        this.nombre_horario = nombre_horario;
        this.horain_horario = horain_horario;
        this.horafin_horario = horafin_horario;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public String getNombre_horario() {
        return nombre_horario;
    }

    public void setNombre_horario(String nombre_horario) {
        this.nombre_horario = nombre_horario;
    }

    public String getHorain_horario() {
        return horain_horario;
    }

    public void setHorain_horario(String horain_horario) {
        this.horain_horario = horain_horario;
    }

    public String getHorafin_horario() {
        return horafin_horario;
    }

    public void setHorafin_horario(String horafin_horario) {
        this.horafin_horario = horafin_horario;
    }

    
   
}
