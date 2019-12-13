
package modelos;



public class DetalleAgendamientos {
    private int id_detalleagendamiento;
    private Agendamientos agendamiento;
    private Actividades actividad;

    public DetalleAgendamientos() {
    }

    public DetalleAgendamientos(int id_detalleagendamiento, Agendamientos agendamiento, Actividades actividad) {
        this.id_detalleagendamiento = id_detalleagendamiento;
        this.agendamiento = agendamiento;
        this.actividad = actividad;
    }

    public int getId_detalleagendamiento() {
        return id_detalleagendamiento;
    }

    public void setId_detalleagendamiento(int id_detalleagendamiento) {
        this.id_detalleagendamiento = id_detalleagendamiento;
    }

    public Agendamientos getAgendamiento() {
        return agendamiento;
    }

    public void setAgendamiento(Agendamientos agendamiento) {
        this.agendamiento = agendamiento;
    }

    public Actividades getActividad() {
        return actividad;
    }

    public void setActividad(Actividades actividad) {
        this.actividad = actividad;
    }
    
    
    
}
    
