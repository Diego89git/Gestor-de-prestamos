/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_prestamos;

import Listas.ListaDE;
import java.util.Calendar;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 *
 * @author COMPUTER
 */
public class Prestamo {

    int identificador;
    Date fecha_entrega;
    Date fecha_devolucion;
    Cliente cliente;
    ListaDE equipos;
    String estado;
    double total;
    public static final String ENTREGADO = "ENT";
    public static final String DEVUELTO = "DEV";

    public Prestamo(int identificador, Date fecha_entrega, Date fecha_devolucion, Cliente cliente, ListaDE equipos) {
        this.identificador = identificador;
        this.fecha_entrega = fecha_entrega;
        this.fecha_devolucion = fecha_devolucion;
        this.cliente = cliente;
        this.equipos = equipos;
        this.estado = ENTREGADO;
        this.total = 0;
        calcularTotal();
    }

    public void calcularTotal() {
        int i;
        int diferencia = (int) (this.fecha_devolucion.getTime() - this.fecha_entrega.getTime()) / 1000 / 60 / 60 / 24;
        this.total = 0;
        if (equipos.tamaño() == 0) {
        this.total = 0;   
        } else {
            for (i = 0; i < equipos.tamaño(); i++) {

                this.total = this.total + (((Equipo) equipos.buscar(i)).precio * diferencia);
            }
        }
    }
    
    public double devolverTotal() {
        calcularTotal();
        return total;
    }

    
    public double deudaActual(Date fecha_dev) {
        int i;
        int diferencia = (int) (fecha_dev.getTime() - this.fecha_entrega.getTime()) / 1000 / 60 / 60 / 24;
        double deuda = 0;
        if (equipos.tamaño() == 0) {
            deuda = 0;
        } else {
            for (i = 0; i < equipos.tamaño(); i++) {
                deuda = deuda + (((Equipo) equipos.buscar(i)).precio * diferencia);
            }
        }
        return deuda;
    }

    @Override
    public String toString() {
        Format formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return "     "+this.identificador+"\t" + cliente.cedula+"\t"  + formato.format(this.fecha_entrega) + 
                "\t"+ formato.format(this.fecha_devolucion)+"\t" + this.estado+"\t" + this.total;
    }

    public void imprimirPrestamo() {
        System.out.println("\n\n\n***************************  PRESTAMO DE EQUIPO          N°000-:" + this.identificador);
        System.out.println("---Datos de Cliente----------------------------------------------");
        System.out.printf("%-12S%-15S%-15S%-20s\n", "C.I.     :", this.cliente.cedula, "Nombre.   :", this.cliente.nombre);
        System.out.printf("%-12S%-15S%-15S%-20s\n", "Telefono.:", this.cliente.telefono, "Direccion.:", this.cliente.direccion);
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-15S%-15S%-15s%-15s\n", "Equipo", "Tipo", "Marca", "Prec.x.Día");
        System.out.println("-----------------------------------------------------------------");
        Calendar fecha = Calendar.getInstance();
        Format formato = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        int dias = (int) ((this.fecha_devolucion.getTime() - this.fecha_entrega.getTime()) / 1000 / 60 / 60 / 24);
        int i = 0;
        while (i < equipos.tamaño()) {
            ((Equipo) equipos.buscar(i)).imprimirDetallePrestamo();
            i++;
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-20S%-20S%-15S%-20S\n", "FECHA DE ENTREGA   .:", formato.format(this.fecha_entrega), "N° DIAS.:", dias);
        System.out.printf("%-20S%-20S%-15S%-20S\n", "FECHA DE DEVOLUCION.:", formato.format(this.fecha_devolucion), "TOTAL  .:", this.total);
        System.out.println("-----------------------------------------------------------------");
    }

    public void imprimirDatos() {
        Calendar fecha = Calendar.getInstance();
        Format formato = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        System.out.printf("%-15S%-20S%-20s%-15s%8d%8.2f%15S\n",
                this.identificador,
                formato.format(this.fecha_entrega),
                formato.format(this.fecha_devolucion),
                cliente.cedula,
                equipos.tamaño(),
                this.total,
                this.estado);
    }

    public void imprimirDatos(Date fecha_consulta) {
        Calendar fecha = Calendar.getInstance();
        Format formato = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        int diasMora = (int) ((fecha_consulta.getTime() - this.fecha_devolucion.getTime()) / 1000 / 60 / 60 / 24);
        System.out.printf("%-15S%-20S%-20s%-15s%8d%8.2f%15d\n",
                this.identificador,
                formato.format(this.fecha_entrega),
                formato.format(this.fecha_devolucion),
                cliente.cedula,
                equipos.tamaño(),
                deudaActual(fecha_consulta),
                diasMora);
    }

    public void devolver(Date fecha_dev) {
        this.estado = DEVUELTO;
        this.fecha_devolucion = fecha_dev;
        calcularTotal();
        int i = 0;
        int tamaño = this.equipos.tamaño();
        while (i < tamaño) {
            ((Equipo) equipos.buscar(i)).devolver();
            i++;
        }
        
    }

    //METODOS PARA EDITAR
    public void actualizarFechaEntrega(Date fechaEntrega) {
        this.fecha_entrega = fechaEntrega;
        calcularTotal();
    }

    public void actualizarFechaDevolucion(Date fechaDevolucion) {
        this.fecha_devolucion = fechaDevolucion;
        calcularTotal();
    }

    public void añadirEquipo(Equipo equipo) {
        equipos.insertar(equipo);
        calcularTotal();

    }

    public void eliminarEquipo(int pos) {
        equipos.borrarPos(pos);
        calcularTotal();
    }
    
    public void listarEquipos(){
        int i=0;
        int cantidad=this.equipos.tamaño();
        System.out.println("\n----     Equipo Registrados     ----");
        System.out.println("------------------------------------");
        System.out.println("IDENTIFICADOR  \tTIPO DE EQUIPO");
        System.out.println("------------------------------------");
        while(i<cantidad){
            System.out.println(((Equipo) this.equipos.buscar(i)).toString());
            i++;
        }
    }
    
    public int buscarEquipo(String identificador) {
        int resp = -1;

        if (equipos.tamaño() == 0) {
            return -1;
        }

        int i = 0;
        int contEquipo = equipos.tamaño();
        contEquipo = --contEquipo;
        if (((Equipo) equipos.buscar(i)).equals(identificador)) {
            return 0;
        }
        while (i < contEquipo & !((Equipo) equipos.buscar(i)).equals(identificador)) {
            i++;

        }
        if (((Equipo) equipos.buscar(i)).equals(identificador)) {
            resp = i;
        }
        return resp;
    }
    
    public boolean verificarEquipo(String identificador){
        if(buscarEquipo(identificador)==-1){
            return false;
        }
        return true;
    }
    public Equipo obtenerEquipo(int pos){
        return (Equipo)equipos.buscar(pos);
    }


}
