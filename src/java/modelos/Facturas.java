/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author angel
 */
public class Facturas {
    private int id_factura;
    private Date fecha_factura;
    private int numero_factura;
    private Personas persona;
    private String estado_factura;
    private int total;
    private Agendamientos agendamiento;

    public Facturas() {
    }

    public Facturas(int id_factura, Date fecha_factura, int numero_factura, Personas persona, String estado_factura, int total, Agendamientos agendamiento) {
        this.id_factura = id_factura;
        this.fecha_factura = fecha_factura;
        this.numero_factura = numero_factura;
        this.persona = persona;
        this.estado_factura = estado_factura;
        this.total = total;
        this.agendamiento = agendamiento;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public int getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura = numero_factura;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public String getEstado_factura() {
        return estado_factura;
    }

    public void setEstado_factura(String estado_factura) {
        this.estado_factura = estado_factura;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Agendamientos getAgendamiento() {
        return agendamiento;
    }

    public void setAgendamiento(Agendamientos agendamiento) {
        this.agendamiento = agendamiento;
    }

   
}
