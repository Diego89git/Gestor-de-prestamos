/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_prestamos;

import Listas.ListaDE;
import java.util.ArrayList;

/**
 *
 * @author COMPUTER
 */
public class Tipo_Equipo {

    int codigo;
    String nombre;
    ListaDE equipos;

    public Tipo_Equipo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.equipos = new ListaDE();

    }

    public void añadirEquipo(Equipo equipo) {
        equipos.insertar(equipo);
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

    public void consultaEquiposDisponibles() {
        int i = 0;
        int contEquipo = equipos.tamaño();
        System.out.println("\n----     Equipo Disponibles     ----");
        System.out.println("------------------------------------");
        System.out.println("IDENTIFICADOR  \tTIPO DE EQUIPO");
        System.out.println("------------------------------------");
        while (i < contEquipo) {
            if (((Equipo) equipos.buscar(i)).disponible) {
                System.out.println(((Equipo) equipos.buscar(i)).toString());
            }
            i++;
        }
    }

    public void consultaEquiposPrestados() {
        int i = 0;
        int contEquipo = equipos.tamaño();
        System.out.println("\n----     Equipo Prestados     ----");
        System.out.println("TIPO .:" + "\t" + this.nombre);
        System.out.println("------------------------------------");
        System.out.println("IDENTIFICADOR  \tTIPO DE EQUIPO");
        System.out.println("------------------------------------");
        while (i < contEquipo) {
            if (!((Equipo) equipos.buscar(i)).disponible) {
                System.out.println(((Equipo) equipos.buscar(i)).toString());
            }
            i++;
        }
    }

    public void consultaPorTipoMarca() {
        ArrayList<String> marcas = new ArrayList();
        int i = 0;
        int contEquipo = equipos.tamaño();
        while (i < contEquipo) {
            if (marcas.isEmpty()) {
                marcas.add(((Equipo) equipos.buscar(i)).marca);
            } else {
                int j = 0;
                int tamaño = marcas.size();
                int coincidencias = 0;
                while (j < tamaño) {
                    if (((Equipo) equipos.buscar(i)).marca.equals(marcas.get(j))) {
                        coincidencias++;

                    }
                    j++;
                }
                if (coincidencias == 0) {
                    marcas.add(((Equipo) equipos.buscar(i)).marca);
                }
            }
            i++;
        }

        int k = 0;
        int catidadMarcas = marcas.size();
        while (k < catidadMarcas) {
            int l = 0;
            int contadorEquipoConsulta = cantidadEquiposPrestados();
            if (contadorEquipoConsulta > 0) {
                System.out.println("\n----     Equipo Prestados     ----");
                System.out.println("TIPO/MARCA.: " + nombre + "/" + marcas.get(k));
                System.out.println("----------------------------------------");
                System.out.println("IDENTIFICADOR  \tTIPO DE EQUIPO");
                System.out.println("----------------------------------------");
                int total = 0;
                while (l < contadorEquipoConsulta) {
                    if (((Equipo) equipos.buscar(l)).marca.equals(marcas.get(k)) && !((Equipo) equipos.buscar(l)).disponible) {
                        System.out.println(((Equipo) equipos.buscar(l)).toString());
                        total++;
                    }
                    l++;
                }
                System.out.println("En total se entregó "+total+" Equipos");
                System.out.println("----------------------------------------");
            }
            k++;
        }

    }

    public int cantidadEquiposEnTipo() {
        return equipos.tamaño();
    }

    public int cantidadEquiposDisponibles() {
        int i = 0;
        int contEquipo = equipos.tamaño();
        int cantidad = 0;
        while (i < contEquipo) {
            if (((Equipo) equipos.buscar(i)).disponible) {
                cantidad++;
            }
            i++;
        }
        return cantidad;
    }

    public int cantidadEquiposPrestados() {
        int i = 0;
        int contEquipo = equipos.tamaño();
        int cantidad = 0;
        while (i < contEquipo) {
            if (!((Equipo) equipos.buscar(i)).disponible) {
                cantidad++;
            }
            i++;
        }
        return cantidad;
    }
    public void imprimirdatos(){
        System.out.printf("%-10d%-20S%10d%15d%15d\n",codigo
                ,nombre,cantidadEquiposEnTipo()
                ,cantidadEquiposPrestados()
                ,cantidadEquiposDisponibles());
    }

    @Override
    public String toString() {
        return codigo + "\t\t  " + nombre;
    }
    public boolean equals(String nombre){
        return this.nombre.equalsIgnoreCase(nombre);
    }
}
