/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_prestamos;

import Listas.ListaDE;

/**
 *
 * @author COMPUTER
 */
public class Cliente {

    String cedula;
    String nombre;
    String direccion;
    String telefono;
    ListaDE prestamos;

    public Cliente(String cedula, String nombre, String direccion, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.prestamos = new ListaDE();
    }

    public void añadirPrestamo(Prestamo prestamo) {
        this.prestamos.insertar(prestamo);

    }
    public int cantidadPrestamosPendientes() {
        int i = 0;
        int contPrestamo = prestamos.tamaño();
        int cantidad = 0;
        while (i < contPrestamo) {
            if (((Prestamo) prestamos.buscar(i)).estado.equalsIgnoreCase("ENT")) {
                cantidad++;
            }
            i++;
        }
        return cantidad;
    }

    public int cantidadPrestamosCancelados() {
        int i = 0;
        int contPrestamo = prestamos.tamaño();
        int cantidad = 0;
        while (i < contPrestamo) {
            if (((Prestamo) prestamos.buscar(i)).estado.equalsIgnoreCase("DEV")) {
                cantidad++;
            }
            i++;
        }
        return cantidad;
    }

    public int cantidadPrestamosCliente() {
        return prestamos.tamaño();
    }

    public boolean verificarPrestamos() {
        if (cantidadPrestamosCliente() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void listarPrestamos() {
        if (verificarPrestamos()) {
            int i = 0;
            int tamaño = prestamos.tamaño();
            System.out.println("**** Lista de Prestamos a nombre del cliente "+ this.nombre+ "***");
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.printf("%-15S%-20S%-20s%-15s%8s%8s%-15s\n", "ID.Prest.", "Fecha de Entrega", "Fecha de Devolución", "Cliente", "#Equip.", "TOTAL", "ESTADO");
            System.out.println("-----------------------------------------------------------------------------------------------");

            while (i < tamaño) {
                ((Prestamo) this.prestamos.buscar(i)).imprimirDatos();
                i++;
            }
        }
    }

    public boolean equals(String cedula) {
        return cedula.equalsIgnoreCase(this.cedula);
    }

    public void imprimirDatos() {
        System.out.printf("%-15S%-20S%-20s%-15s%8d%8d%8d\n",
                this.cedula,
                this.nombre,
                this.direccion,
                this.telefono,
                cantidadPrestamosCliente(),
                cantidadPrestamosPendientes(),
                cantidadPrestamosCancelados());
    }

}
