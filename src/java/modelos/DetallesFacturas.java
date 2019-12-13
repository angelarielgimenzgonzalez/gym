/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

public class DetallesFacturas {

    private int id_detallefactura;
    private Facturas factura;
    private Actividades actividad;
    private int precio_total_factura;
    private int cantidad_factura;

    public DetallesFacturas() {
    }

    public DetallesFacturas(int id_detallefactura, Facturas factura, Actividades actividad, int precio_total_factura, int cantidad_factura) {
        this.id_detallefactura = id_detallefactura;
        this.factura = factura;
        this.actividad = actividad;
        this.precio_total_factura = precio_total_factura;
        this.cantidad_factura = cantidad_factura;
    }

    public int getId_detallefactura() {
        return id_detallefactura;
    }

    public void setId_detallefactura(int id_detallefactura) {
        this.id_detallefactura = id_detallefactura;
    }

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public Actividades getActividad() {
        return actividad;
    }

    public void setActividad(Actividades actividad) {
        this.actividad = actividad;
    }

    public int getPrecio_total_factura() {
        return precio_total_factura;
    }

    public void setPrecio_total_factura(int precio_total_factura) {
        this.precio_total_factura = precio_total_factura;
    }

    public int getCantidad_factura() {
        return cantidad_factura;
    }

    public void setCantidad_factura(int cantidad_factura) {
        this.cantidad_factura = cantidad_factura;
    }

   
   
}
