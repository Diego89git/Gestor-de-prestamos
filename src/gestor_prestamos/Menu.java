/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_prestamos;

import Interfaz_Consola.Consola;
import Listas.ListaDE;
import Validaciones.Validador;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author COMPUTER
 */
public class Menu {


    private static void crearTipoEquipo(Gestor_Prestamos gestor) {
        System.out.println("\n" + Mensajes.MENU.ENCABEZADO_MENU_REGISTRO_TIPO.tx());
        System.out.println(Mensajes.MENU.SEPARADOR_PRS.tx());
        char resp = 'N';
        do {
            int codigo = gestor.verTamañoTipos() + 1;

            String nombre = Consola.ingresarDato(Mensajes.ENTRADA.NOMBRE_TIPO_EQUIPO.tx(),
                    Mensajes.ERROR.NOMBRE.tx(), Validador.SOLO_LETRAS);
            if (gestor.verificarTipoEquipo(nombre)) {
                System.out.println("Tipo de Equipo ya registrado");
            } else {
                if (gestor.añadirTipo(codigo, nombre)) {
                    System.out.println(Mensajes.NOTIF.NUEVO_TIPO_REGIS.tx());
                }
            }

            resp = Consola.ingresarDato(Mensajes.ENTRADA.RESPUESTA.tx(), Mensajes.ERROR.RESPUESTA.tx(),
                    Validador.RESPUESTA).toUpperCase().charAt(0);
        } while (resp == 'S');
        gestor.consultaTipos();
    }

    private static void crearEquipo(Gestor_Prestamos gestor) {
        System.out.println("\n" + Mensajes.MENU.ENCABEZADO_MENU_REGISTRO_EQUIPO.tx());
        System.out.println(Mensajes.MENU.SEPARADOR_PRS.tx());
        char resp = 'N';
        do {
            Tipo_Equipo tipo = escojerTipo(gestor);
            if (tipo != null) {
                String identificador = Consola.ingresarDato(Mensajes.ENTRADA.IDENTIFICADOR_EQUIPO.tx(), Mensajes.ERROR.IDENTIFICADOR.tx(),
                        Validador.IDENTIFICADOR);
                if (gestor.verificarEquipo(identificador)) {
                    System.out.println("Tipo de Equipo ya registrado");
                } else {

                    String marca = Consola.ingresarDato(Mensajes.ENTRADA.MARCA_EQUIPO.tx(), Mensajes.ERROR.MARCA.tx(), Validador.SOLO_LETRAS);
                    double precio = Double.parseDouble(Consola.ingresarDato(Mensajes.ENTRADA.PRECIO_EQUIPO.tx(), Mensajes.ERROR.VALOR_NO_VALIDO.tx(),
                            Validador.VALOR));
                    if (gestor.añadirEquipo(identificador, tipo, marca, precio)) {
                        System.out.println(Mensajes.NOTIF.NUEVO_EQUIPO.tx());
                    }
                }

                resp = Consola.ingresarDato(Mensajes.ENTRADA.RESPUESTA.tx(), Mensajes.ERROR.RESPUESTA.tx(),
                        Validador.RESPUESTA).toUpperCase().charAt(0);
            }

        } while (resp == 'S');
    }

    public static void crearPrestamo(Gestor_Prestamos gestor) {
        
        if(gestor.equipos.tamaño()>0){
        Cliente cliente;
        Date fecha_en, fecha_de;
        ListaDE equipos = new ListaDE();
        System.out.println("\n" + Mensajes.MENU.ENCABEZADO_MENU_REGISTRO_PRESTAMO.tx());
        System.out.println(Mensajes.MENU.SEPARADOR_PRS.tx());
        int idetificadorPres = gestor.verTamañoPrestamos() + 1;
        String cedula = Consola.ingresarDato(Mensajes.ENTRADA.CEDULA.tx(),
                Mensajes.ERROR.CEDULA.tx(),
                Validador.CEDULA);

        if (gestor.verificarCliente(cedula)) {
            cliente = gestor.obtenerCliente(gestor.buscarCliente(cedula));
        } else {
            System.out.println(Mensajes.NOTIF.NUEVO_CLIENTE.tx());
            System.out.println(Mensajes.MENU.SEPARADOR_PRS.tx());
            crearCliente(gestor, cedula);
            cliente = gestor.obtenerUltimoCliente();
        }
        if (gestor.verTamañoTipos() == 0) {
            System.out.println(Mensajes.ERROR.SIN_DATOS.tx());

        } else {
            equipos = escojerEquipos(gestor);
            fecha_en = devolverFecha(Mensajes.ENTRADA.FECHA_ENTREGA.tx());
            fecha_de = devolverFecha(Mensajes.ENTRADA.FECHA_DEVOLUCION.tx());
            gestor.añadirPrestamo(idetificadorPres, fecha_en, fecha_de, cliente, equipos);
        }
        }else{
            System.out.println("No existen equipos registrados complete la informacion (EQUIPOS)");
        }
    }

    public static void crearCliente(Gestor_Prestamos gestor, String cedula) {

        String nombre = Consola.ingresarDato(Mensajes.ENTRADA.NOMBRE.tx(),
                Mensajes.ERROR.NOMBRE.tx(),
                Validador.SOLO_LETRAS);
        String direccion = Consola.ingresarDato(Mensajes.ENTRADA.DIRECCION.tx());

        String telefono = Consola.ingresarDato(Mensajes.ENTRADA.TELEFONO.tx(),
                Mensajes.ERROR.TELEFONO.tx(),
                Validador.TELEFONO);
        gestor.añadirCliente(cedula, nombre, direccion, telefono);

    }

    private static Tipo_Equipo escojerTipo(Gestor_Prestamos gestor) {
        System.out.println(Mensajes.NOTIF.SELECCION_TIPO_EQUIPO.tx());
        gestor.consultaTipos();
        if (gestor.tipos.tamaño() == 0) {
            System.out.println("Aun no ha registrado ningun tipo, complete la informacion (TIPOS_EQUIPO)");
        } else {
            int j = 0;
            int pos = 0;
            do {
                pos = Integer.parseInt(Consola.ingresarDato(Mensajes.ENTRADA.OPCION.tx(),
                        Mensajes.ERROR.OPCION.tx(),
                        Validador.VALOR));
                if (pos > gestor.verTamañoTipos() || pos < 1) {
                    j = -1;
                } else {
                    j = 0;
                }
            } while (j == -1);
            Tipo_Equipo tipo = null;
            if (pos != 0) {
                tipo = gestor.obtenerTipo(pos - 1);
            }
            return tipo;
        }
        return null;
    }

    public static ListaDE escojerEquipos(Gestor_Prestamos gestor) {
        String identificador;
        Equipo equipo;
        char resp;
        ListaDE equipos = new ListaDE();
        do {
            Tipo_Equipo tipo = escojerTipo(gestor);
            char res = 'n';
            do {
                if (tipo.cantidadEquiposDisponibles() == 0) {
                    System.out.println(Mensajes.ERROR.SIN_DATOS.tx());
                    res = Consola.ingresarDato(Mensajes.ENTRADA.RESPUESTA.tx(),
                            Mensajes.ERROR.RESPUESTA.tx(),
                            Validador.RESPUESTA).toUpperCase().charAt(0);
                    if (res == 'S') {
                        tipo = escojerTipo(gestor);
                    } else {
                        return equipos;
                    }

                } else {
                    res = 'n';
                }
            } while (res == 'S');
            System.out.println("SELECCIONE EL EQUIPO REQUERIDO");
            tipo.consultaEquiposDisponibles();
            do {
                identificador = Consola.ingresarDato(Mensajes.ENTRADA.IDENTIFICADOR_EQUIPO.tx(),
                        Mensajes.ERROR.IDENTIFICADOR.tx(),
                        Validador.IDENTIFICADOR);
                if (gestor.verificarEquipo(identificador)) {

                    equipo = gestor.obtenerEquipo(gestor.buscarEquipo(identificador));
                    equipos.insertar(equipo);
                } else {
                    System.out.println(Mensajes.ERROR.EQUIPO_NO_EXISTE.tx());
                }
            } while (!gestor.verificarEquipo(identificador));
            resp = Consola.ingresarDato(Mensajes.ENTRADA.RESPUESTA.tx(),
                    Mensajes.ERROR.RESPUESTA.tx(),
                    Validador.RESPUESTA).toUpperCase().charAt(0);
        } while (resp == 'S');
        return equipos;
    }

    public static Prestamo escojerPrestamo(Gestor_Prestamos gestor) {
        String cedula = Consola.ingresarDato(Mensajes.ENTRADA.CEDULA.tx(),
                Mensajes.ERROR.CEDULA.tx(),
                Validador.CEDULA);
        if (gestor.verificarCliente(cedula)) {
            Cliente cliente = gestor.obtenerCliente(cedula);
            cliente.listarPrestamos();

            int pos = Integer.parseInt(Consola.ingresarDato(Mensajes.ENTRADA.OPCION.tx(),
                    Mensajes.ERROR.OPCION.tx(),
                    Validador.VALOR));
            return gestor.obtenerPrestamo(pos - 1);
        } else {
            System.out.println(Mensajes.NOTIF.CLIENTE_SIN_PRESTAMOS.tx());
            return null;
        }
    }

    public static boolean editarPrestamo(Gestor_Prestamos gestor) {
        if(gestor.prestamos.tamaño()>0){
        Prestamo prestamo = escojerPrestamo(gestor);
        if (prestamo == null) {
            System.out.println(Mensajes.NOTIF.DEVOLVER_NO.tx());
            return false;
        } else {

           // Menu.submenuEdicion(gestor, prestamo);
            return true;
        }}
        else{
            System.out.println("No existen prestamos registrados");
            return false;
        }

    }

    public static Date devolverFecha(String mensajeEntrada) {
        String fechaCadena = Consola.ingresarDato(mensajeEntrada, Mensajes.ERROR.FECHA.tx(), Validador.FECHA);
        Calendar fecha = Calendar.getInstance();
        int dia = Integer.parseInt(fechaCadena.substring(0, 2));
        int mes = Integer.parseInt(fechaCadena.substring(3, 5));
        int año = Integer.parseInt(fechaCadena.substring(6, 10));
        int hora = Integer.parseInt(fechaCadena.substring(11, 13));
        int minuto = Integer.parseInt(fechaCadena.substring(14, 16));
        fecha.set(año, mes - 1, dia, hora, minuto);
        return fecha.getTime();
    }

    public static void devolverPrestamo(Gestor_Prestamos gestor) {
        if(gestor.prestamos.tamaño()>0){
        Prestamo prestam = escojerPrestamo(gestor);
        if (prestam == null) {
            System.out.println(Mensajes.NOTIF.DEVOLVER_NO.tx());
        } else {
            char resp = Consola.ingresarDato(Mensajes.ENTRADA.DEVOLVER.tx(), Mensajes.ERROR.RESPUESTA.tx(),
                    Validador.RESPUESTA).toUpperCase().charAt(0);
            if (resp == 'S') {
                Date fecha_de;
                fecha_de = devolverFecha(Mensajes.ENTRADA.FECHA_DEVOLUCION.tx());
                prestam.devolver(fecha_de);
                System.out.println(Mensajes.NOTIF.DEVOLVER_SI.tx());
            } else {
                System.out.println(Mensajes.NOTIF.DEVOLVER_NO.tx());
            }
        }}else{
            System.out.println("No se han registrado prestamos");
        }
    }

    private static void editarFechaEntrega(Prestamo prestamo) {
        char resp = Consola.ingresarDato(Mensajes.ENTRADA.EDITAR_FECHA_ENTREGA.tx(), Mensajes.ERROR.RESPUESTA.tx(),
                Validador.RESPUESTA).toUpperCase().charAt(0);
        if (resp == 'S') {
            Date fecha_en;
            fecha_en = devolverFecha(Mensajes.ENTRADA.FECHA_ENTREGA.tx());
            prestamo.actualizarFechaEntrega(fecha_en);
            prestamo.imprimirPrestamo();
            System.out.println(Mensajes.NOTIF.EDITAR_SI.tx());
        } else {
            System.out.println(Mensajes.NOTIF.EDITAR_NO.tx());
        }

    }

    private static void editarFechaDevolucion(Prestamo prestamo) {
        char resp = Consola.ingresarDato(Mensajes.ENTRADA.EDITAR_FECHA_DEVOLUCION.tx(), Mensajes.ERROR.RESPUESTA.tx(),
                Validador.RESPUESTA).toUpperCase().charAt(0);
        if (resp == 'S') {
            Date fecha_de;
            fecha_de = devolverFecha(Mensajes.ENTRADA.FECHA_DEVOLUCION.tx());
            prestamo.actualizarFechaDevolucion(fecha_de);
            prestamo.imprimirPrestamo();
            System.out.println(Mensajes.NOTIF.EDITAR_SI.tx());
        } else {
            System.out.println(Mensajes.NOTIF.EDITAR_NO.tx());
        }

    }

    private static void añadirEquipo(Gestor_Prestamos gestor, Prestamo prestamo) {
        char resp = Consola.ingresarDato(Mensajes.ENTRADA.EDITAR_EQUIPOS.tx(), Mensajes.ERROR.RESPUESTA.tx(),
                Validador.RESPUESTA).toUpperCase().charAt(0);
        if (resp == 'S') {
            escojerEquipo(gestor, prestamo);
        } else {
            System.out.println(Mensajes.NOTIF.EDITAR_NO.tx());
        }
        prestamo.imprimirPrestamo();

    }

    private static void eliminarEquipo(Prestamo prestamo) {
        String identificador;
        char resp = Consola.ingresarDato(Mensajes.ENTRADA.EDITAR_EQUIPOS.tx(), Mensajes.ERROR.RESPUESTA.tx(),
                Validador.RESPUESTA).toUpperCase().charAt(0);
        if (resp == 'S') {
            System.out.println("SELECCIONE EL EQUIPO REQUERIDO");
            prestamo.listarEquipos();

            identificador = Consola.ingresarDato(Mensajes.ENTRADA.IDENTIFICADOR_EQUIPO.tx(),
                    Mensajes.ERROR.IDENTIFICADOR.tx(),
                    Validador.IDENTIFICADOR);
            if (prestamo.verificarEquipo(identificador)) {
                System.out.println(Mensajes.NOTIF.EDITAR_SI.tx());
                int posi = prestamo.buscarEquipo(identificador);
                Equipo equipo = prestamo.obtenerEquipo(posi);
                prestamo.eliminarEquipo(posi);
                equipo.devolver();

            } else {
                System.out.println(Mensajes.ERROR.EQUIPO_NO_EXISTE.tx());
                System.out.println(Mensajes.NOTIF.EDITAR_NO.tx());
            }
        } else {
            System.out.println(Mensajes.NOTIF.EDITAR_NO.tx());
        }
        prestamo.imprimirPrestamo();

    }

    public static void escojerEquipo(Gestor_Prestamos gestor, Prestamo prestamo) {
        String identificador;
        Equipo equipo;
        char resp;
        do {
            Tipo_Equipo tipo = escojerTipo(gestor);
            char res = 'n';
            do {
                if (tipo.cantidadEquiposDisponibles() == 0) {
                    System.out.println(Mensajes.ERROR.SIN_DATOS.tx());
                    res = Consola.ingresarDato(Mensajes.ENTRADA.RESPUESTA.tx(),
                            Mensajes.ERROR.RESPUESTA.tx(),
                            Validador.RESPUESTA).toUpperCase().charAt(0);
                    if (res == 'S') {
                        tipo = escojerTipo(gestor);
                    } else {
                        res = 'n';
                    }

                } else {
                    res = 'n';
                }
            } while (res == 'S');
            System.out.println("SELECCIONE EL EQUIPO REQUERIDO");
            tipo.consultaEquiposDisponibles();
            do {
                identificador = Consola.ingresarDato(Mensajes.ENTRADA.IDENTIFICADOR_EQUIPO.tx(),
                        Mensajes.ERROR.IDENTIFICADOR.tx(),
                        Validador.IDENTIFICADOR);
                if (gestor.verificarEquipo(identificador)) {
                    System.out.println(Mensajes.NOTIF.EDITAR_SI.tx());
                    equipo = gestor.obtenerEquipo(gestor.buscarEquipo(identificador));
                    prestamo.añadirEquipo(equipo);
                    equipo.prestar();
                } else {
                    System.out.println(Mensajes.ERROR.EQUIPO_NO_EXISTE.tx());
                }
            } while (!gestor.verificarEquipo(identificador));
            resp = Consola.ingresarDato(Mensajes.ENTRADA.RESPUESTA.tx(),
                    Mensajes.ERROR.RESPUESTA.tx(),
                    Validador.RESPUESTA).toUpperCase().charAt(0);
        } while (resp == 'S');

    }

}
