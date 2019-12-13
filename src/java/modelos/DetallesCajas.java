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
public class DetallesCajas {

    private int id_detallecaja;
    private Facturas factura;
    //private Pagos pago;
    private Cajas caja;
    private int importe_caja;
    private int importe;
    private int vuelto;

    public DetallesCajas() {
    }

    public DetallesCajas(int id_detallecaja, Facturas factura, Cajas caja, int importe_caja, int importe, int vuelto) {
        this.id_detallecaja = id_detallecaja;
        this.factura = factura;
        this.caja = caja;
        this.importe_caja = importe_caja;
        this.importe = importe;
        this.vuelto = vuelto;
    }

    public int getId_detallecaja() {
        return id_detallecaja;
    }

    public void setId_detallecaja(int id_detallecaja) {
        this.id_detallecaja = id_detallecaja;
    }

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

    public int getImporte_caja() {
        return importe_caja;
    }

    public void setImporte_caja(int importe_caja) {
        this.importe_caja = importe_caja;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getVuelto() {
        return vuelto;
    }

    public void setVuelto(int vuelto) {
        this.vuelto = vuelto;
    }

    
}
