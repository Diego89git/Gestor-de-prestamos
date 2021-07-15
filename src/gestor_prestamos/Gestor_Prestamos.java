/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_prestamos;

import Listas.ArbolBin;
import Listas.ListaDE;
import java.util.Date;

/**
 *
 * @author COMPUTER
 */
public class Gestor_Prestamos {

    ListaDE tipos;
    ListaDE equipos;
    ListaDE clientes;
    ListaDE prestamos;

    public Gestor_Prestamos() {
        this.tipos = new ListaDE();
        this.equipos = new ListaDE();
        this.clientes = new ListaDE();
        this.prestamos = new ListaDE();
    }

    public boolean añadirTipo(int codigo, String nombre) {
        Tipo_Equipo tipo;
        try {
            tipo = new Tipo_Equipo(codigo, nombre);
        } catch (Exception e) {
            return false;
        }
        return tipos.insertar(tipo);
    }

    public boolean añadirEquipo(String identificador, Tipo_Equipo tipo, String marca, double precio) {
        Equipo equipo;
        try {
            equipo = new Equipo(identificador, tipo, marca, precio);
        } catch (Exception e) {
            return false;
        }
        equipos.insertar(equipo);
        tipo.añadirEquipo(equipo);
        return true;
    }

    public boolean añadirCliente(String cedula, String nombre, String direccion, String telefono) {
        Cliente cliente;
        try {
            cliente = new Cliente(cedula, nombre, direccion, telefono);
        } catch (Exception e) {
            return false;
        }
        clientes.insertar(cliente);
        return true;
    }

    public boolean añadirPrestamo(int identificador, Date fecha_entrega, Date fecha_devolucion, Cliente cliente, ListaDE equipos) {
        Prestamo prestamo;
        try {
            prestamo = new Prestamo(identificador, fecha_entrega, fecha_devolucion, cliente, equipos);
        } catch (Exception e) {
            return false;
        }
        prestamos.insertar(prestamo);
        prestamo.imprimirPrestamo();
        prestarEquipos(equipos);
        cliente.añadirPrestamo(prestamo);
        return true;
    }

    public Tipo_Equipo obtenerTipo(int pos) {
        return (Tipo_Equipo) tipos.buscar(pos);
    }

    public Equipo obtenerEquipo(int pos) {
        return (Equipo) equipos.buscar(pos);
    }

    public Cliente obtenerCliente(int pos) {
        return (Cliente) clientes.buscar(pos);
    }

    public Cliente obtenerCliente(String cedula) {
        int posicion = posicion = buscarCliente(cedula);
        if (posicion == -1) {
            return null;
        }
        return obtenerCliente(posicion);
    }
    public Tipo_Equipo obtenerTipoEquipo(String nombre) {
        int posicion = buscarTipoEquipo(nombre);
        if (posicion == -1) {
            return null;
        }
        return obtenerTipo(posicion);
    }

    public Cliente obtenerUltimoCliente() {
        return (Cliente) clientes.buscar(clientes.tamaño() - 1);
    }

    public Prestamo obtenerPrestamo(int pos) {
        int tamaño = verTamañoPrestamos();
        if (tamaño == 0 || pos < 0 || pos >= tamaño) {
            return null;
        }
        int i = 0;
        while (i < pos) {

            i++;
        }
        return ((Prestamo) prestamos.buscar(pos));

    }

    public int verTamañoTipos() {
        return tipos.tamaño();
    }

    public int verTamañoEquipos() {
        return equipos.tamaño();
    }

    public int verTamañoCliente() {
        return clientes.tamaño();
    }

    public int verTamañoPrestamos() {
        return prestamos.tamaño();
    }

    public void consultaTipos() {
        int i = 0;
        int contTipo = tipos.tamaño();
        System.out.println("\n----Tipos de Equipo Registrados----");
        System.out.println("------------------------------------");
        System.out.println("Código  \t  Tipo de Equipo");
        System.out.println("------------------------------------");
        while (i < contTipo) {
            System.out.println(((Tipo_Equipo) tipos.buscar(i)).toString());
            i++;
        }
        System.out.println("------------------------------------");
    }

    public void consultaEquipos() {
        int i = 0;
        int contEquipo = equipos.tamaño();
        System.out.println("\n----     Equipo Registrados     ----");
        System.out.println("------------------------------------");
        System.out.println("IDENTIFICADOR  \tTIPO DE EQUIPO");
        System.out.println("------------------------------------");
        while (i < contEquipo) {
            System.out.println(((Equipo) equipos.buscar(i)).toString());
            i++;
        }
    }

    public void consultaCliente() {
        int i = 0;
        int contCliente = clientes.tamaño();
        while (i < contCliente) {
            System.out.println(((Cliente) clientes.buscar(i)).toString());
            i++;
        }
    }

    public void consultaPrestamos() {
        int i = 0;
        int contPrestamos = prestamos.tamaño();

        while (i < contPrestamos) {
            System.out.println(((Prestamo) prestamos.buscar(i)).toString());
            i++;
        }
    }

    public int consultaPrestamosPorCliente(String cedula) {
        int i = 0;
        int contPrestamos = prestamos.tamaño();
        int cantPrestamos = 0;
        while (i < contPrestamos) {
            if (((Prestamo) prestamos.buscar(i)).cliente.cedula.equals(cedula)) {
                System.out.println(((Prestamo) prestamos.buscar(i)).toString());
                cantPrestamos++;
            }
            i++;
        }
        if (cantPrestamos == 0) {
            System.out.println("No existen prestamos del cliente ingresado");
        }
        return cantPrestamos;
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

    public int buscarCliente(String cedula) {
        int resp = -1;
        if (clientes.tamaño() == 0) {
            return -1;
        }
        int i = 0;
        int contCliente = clientes.tamaño();
        contCliente = --contCliente;
        if (((Cliente) clientes.buscar(i)).equals(cedula)) {
            return 0;
        }
        while (i < contCliente & !((Cliente) clientes.buscar(i)).equals(cedula)) {
            i++;
        }
        if (((Cliente) clientes.buscar(i)).equals(cedula)) {
            resp = i;
        }
        return resp;
    }
    public int buscarTipoEquipo(String nombre) {
        int resp = -1;
        if (tipos.tamaño() == 0) {
            return -1;
        }
        int i = 0;
        int contTipo = tipos.tamaño();
        contTipo = --contTipo;
        if (((Tipo_Equipo) tipos.buscar(i)).equals(nombre)) {
            return 0;
        }
        while (i < contTipo & !(((Tipo_Equipo) tipos.buscar(i)).equals(nombre))) {
            i++;
        }
        if (((Tipo_Equipo) tipos.buscar(i)).equals(nombre)) {
            resp = i;
        }
        return resp;
    }

    public boolean verificarEquipo(String identificador) {
        if (buscarEquipo(identificador) != -1) {
            return true;
        }
        return false;
    }

    public boolean verificarCliente(String cedula) {
        if (buscarCliente(cedula) != -1) {
            return true;
        }
        return false;
    }
    public boolean verificarTipoEquipo(String nombre) {
        if (buscarTipoEquipo(nombre) != -1) {
            return true;
        }
        return false;
    }

    public void prestarEquipos(ListaDE equipos) {
        int i = 0;
        int tamaño = equipos.tamaño();
        while (i < tamaño) {
            ((Equipo) equipos.buscar(i)).prestar();
            i++;
        }

    }

    public void resumenEquiposPrestadosPorTipo() {
        int pos = 0;
        int cantidadTipos = tipos.tamaño();

        while (pos < cantidadTipos) {
            ((Tipo_Equipo) tipos.buscar(pos)).consultaPorTipoMarca();
            pos++;
        }
    }

    public void resumenPrestamosEntregados() {
        Date fecha_de;
        fecha_de = Menu.devolverFecha(Mensajes.ENTRADA.FECHA_CONSULTA.tx());
        int i = 0;
        int tamaño = prestamos.tamaño();
        int cantidad=0;
        System.out.println("**********                RESTAMOS PENDIENTES DE DEVOLUCION                          **********");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("%-15S%-20S%-20s%-15s%8s%8s%15s\n", "ID.Prest.", "Fecha de Entrega", "Fecha de Devolución", "Cliente", "#Equip.", "DEUDA", "DIAS_MORA");
        System.out.println("-----------------------------------------------------------------------------------------------");
        while (i < tamaño) {
            if (((Prestamo) prestamos.buscar(i)).estado.equals("ENT") & ((Prestamo) prestamos.buscar(i)).fecha_entrega.getTime() < fecha_de.getTime()) {
                ((Prestamo) prestamos.buscar(i)).imprimirDatos(fecha_de);
                cantidad++;
            }
            i++;
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Se encuentran :" + cantidad + " Prestamo(s) registrado(s)");
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    public void resumenPrestamosDevueltos() {
        Date fecha_de;
        fecha_de = Menu.devolverFecha(Mensajes.ENTRADA.FECHA_CONSULTA.tx());
        int i = 0;
        int tamaño = prestamos.tamaño();
        double valor=0;
        int cantidad=0;
        System.out.println("**********                         PRESTAMOS DEVUELTOS                               **********");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15S%-20S%-20s%-15s%8s%8s%15s\n", "ID.Prest.", "Fecha de Entrega", "Fecha de Devolución", "Cliente", "#Equip.", "TOTAL", "ESTADO");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        while (i < tamaño) {
            if (((Prestamo) prestamos.buscar(i)).estado.equals("DEV") & ((Prestamo) prestamos.buscar(i)).fecha_devolucion.getTime() < fecha_de.getTime()) {
                ((Prestamo) prestamos.buscar(i)).imprimirDatos();
                valor=valor+((Prestamo) prestamos.buscar(i)).total;
                cantidad++;
            }
            i++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("Se encuentran :" + cantidad + " Prestamo(s) Facturados(s) por un valor de :"+valor);
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }


    public void imprimirTiposGeneral(){
        int i=0;
        int tamaño=tipos.tamaño();
        System.out.println("**********             LISTA DE TIPOS DE EQUIPOS             **********");
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-10s%-20s%10s%15s%15s\n","Código","Nombre","# Equipos","# Prestados","# Disponibles");
        System.out.println("-----------------------------------------------------------------------");
        while(i<tamaño){
            ((Tipo_Equipo)tipos.buscar(i)).imprimirdatos();
            i++;
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Se encuentran :"+tamaño+" Tipo(s) registrado(s)");
        System.out.println("-----------------------------------------------------------------------");
    }
    public void imprimirEquiposGeneral(){
        int i=0;
        int tamaño=equipos.tamaño();
        System.out.println("**********                 LISTA DE EQUIPOS                  **********");
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-15S%-15S%-15s%-15s%-15s\n","Identificador","Tipo","Marca","Prec.x.Día","Estado");
        System.out.println("-----------------------------------------------------------------------");
        while(i<tamaño){
            ((Equipo)equipos.buscar(i)).imprimirDatos();
            i++;
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Se encuentran :"+tamaño+" Equipo(s) registrado(s)");
        System.out.println("-----------------------------------------------------------------------");
    }
    public void imprimirClientesGeneral(){
        int i=0;
        int tamaño=clientes.tamaño();
        System.out.println("**********                             LISTA DE CLIENTES                             **********");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("%-15S%-20S%-20s%-15s%8s%8s%8s\n","Cedula","Nombre","Direccion","Teléfono","#Prest.","#P.Pdt","#P.Dvl");
        System.out.println("-----------------------------------------------------------------------------------------------");
        while(i<tamaño){
            ((Cliente)clientes.buscar(i)).imprimirDatos();
            i++;
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Se encuentran :"+tamaño+" Cliente(s) registrado(s)");
        System.out.println("-----------------------------------------------------------------------------------------------");
    }
    public void imprimirPrestamosGeneral(){
        int i=0;
        int tamaño=prestamos.tamaño();
        System.out.println("**********                             LISTA DE PRESTAMOS                            **********");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15S%-20S%-20s%-15s%8s%8s%15s\n","ID.Prest.","Fecha de Entrega","Fecha de Devolución","Cliente","#Equip.","TOTAL","ESTADO");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        while(i<tamaño){
            ((Prestamo)prestamos.buscar(i)).imprimirDatos();
            i++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("Se encuentran :"+tamaño+" Prestamo(s) registrado(s)");
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }

    void imprimirPrestamosOrdenValor() {
      int tamaño=prestamos.tamaño();
      int i=0;
      ArbolBin prestamosOrdenados=new ArbolBin();
        while(i<tamaño){
            prestamosOrdenados.insertar(prestamos.buscar(i));
            i++;
        }
        System.out.println("**********         LISTA DE PRESTAMOS ORDENADOS POR VALOR A PAGAR                            **********");
        System.out.printf("%-11S%-12S%-20s%-20s%8s%10s\n","ID.Prest.","CEDULA","Fecha de Entrega","Fecha de Devolución","ESTADO","TOTAL");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        prestamosOrdenados.imprimir();
    }

}
