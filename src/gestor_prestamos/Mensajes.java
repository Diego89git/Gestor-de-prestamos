/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_prestamos;

/**
 *
 * @author COMPUTER
 */
public class Mensajes {

    public enum ENTRADA {
        OPCION("Opción: "),
        CEDULA("Cédula del Cliente: "),
        NOMBRE("Nombre del Cliente: "),
        DIRECCION("Dirección del Cliente: "),
        TELEFONO("Teléfono del Cliente: "),
        CODIGO_TIPO_EQUIPO("Código del tipo de Equipo: "),
        NOMBRE_TIPO_EQUIPO("Nombre del tipo de Equipo: "),
        IDENTIFICADOR_EQUIPO("ID del Equipo(AAA000-ZZZ999): "),
        MARCA_EQUIPO("Marca del Equipo: "),
        PRECIO_EQUIPO("Precio del Equipo: "),
        FECHA_ENTREGA("Fecha de Entrega (DD/MM/YYYY HH:MM): "),
        FECHA_DEVOLUCION("Fecha de Devolución (DD/MM/YYYY HH:MM): "),
        FECHA_CONSULTA("Fecha de Devolución (DD/MM/YYYY HH:MM): "),
        ESTADO("Estado del Préstamo(E/M): "),
        RESPUESTA("Seguir Ingresando(S/N): "),
        EDITAR_FECHA_ENTREGA("Realmente desea editar la fecha de entrega (S/N): "),
        EDITAR_FECHA_DEVOLUCION("Realmente desea editar la fecha de devolución (S/N): "),
        EDITAR_EQUIPOS("Realmente desea editar los equipos registrados (S/N): "),
        DEVOLVER("Desea registrar prestamo como devuelto(S/N): ");
        private String valor;

        private ENTRADA(String v) {
            valor = v;
        }

        public String tx() {
            return valor;
        }
    }

    public enum ERROR {
        OPCION("Número de opción no válida."),
        CEDULA("El número de cédula especificado no es válido."),
        NOMBRE("Solo se aceptan caracteres alfabéticos como parte del nombre."),
        VALOR_NO_VALIDO("Valor no válido"),
        EQUIPO_NO_EXISTE("No existe equipo registrado con el número de identificación ingresado."),
        RESPUESTA("Respuesta no Válida"),
        MARCA("Solo se aceptan caracteres alfabéticos como parte de la Marca"),
        IDENTIFICADOR("El Dato Ingresado no cumple con la sintaxis (AAA000-ZZZ999)"),
        SIN_DATOS("No existen datos registrados para uso"),
        FECHA("El Dato Ingresado no cumple con la sintaxis DD/MM/YYYY HH:MM"),
        TELEFONO("Ingresar teléfono válido(000000 o 0999999999)");
        private String valor;

        private ERROR(String v) {
            valor = v;
        }

        public String tx() {
            return valor;
        }
    }

    public enum NOTIF {
        DESPEDIDA("HASTA PRONTO!!!"),
        NUEVO_TIPO_REGIS("Datos de Tipo de Equipo ingresados satisfactoriamente."),
        NUEVO_EQUIPO("Datos de Equipo ingresados satisfactoriamente."),
        CEDULA("Cedula : "),
        NOMBRE("Nombre : "),
        APELLIDO("Apellido:"),
        SELECCION_TIPO_EQUIPO("\n***SELECCIONES EL TIPO DE EQUIPO"),
        SELECCION_EQUIPO("Seleccione el EQUIPO a entregar"),
        NUEVO_CLIENTE("CLIENTE NO REGISTRADO PORFAVOR COMPLETE LOS DATOS"),
        CLIENTE_SIN_PRESTAMOS("El cliente ingresado no registra prestamos"),
        DEVOLVER_SI("Se ha registrado con exito la devolucion de los equipos"),
        DEVOLVER_NO("No se ha registrado ninguna devolucion"),
        EDITAR_SI("Se ha editado el registro Satisfactoriamente"),
        EDITAR_NO("No se ha realizado cambios al registro");

        private String valor;

        private NOTIF(String v) {
            valor = v;
        }

        public String tx() {
            return valor;
        }
    }

    public enum MENU {
        ENCABEZADO_MENU_GENERAL             ("     ***** GESTOR  DE PRESTAMOS DE EQUIPOS DE COMPUTACIÓN   ****     "),
        ENCABEZADO_MENU_REGISTRO_TIPO       ("     **********    REGISTRO DE TIPOS DE EQUIPO              *****    "),
        ENCABEZADO_MENU_REGISTRO_EQUIPO     ("     *****             REGISTRO DE EQUIPOS                  *****    "),
        ENCABEZADO_MENU_REGISTRO_PRESTAMO   ("     *****            REGISTRO DE PRESTAMOS                 *****    "),
        ENCABEZADO_MENU_REPORTES            ("     *****                   REPORTES                       *****    "),
        ENCABEZADO_MENU_EDICION             ("     *****             EDICION DE PRESTAMO                  *****    "),
        SEPARADOR_PRS  ("-----------------------------------------------------------------------------"),
        OPCION1("1  TIPO DE EQUIPO "),
        OPCION2("2. REGISTRO DE EQUIPO "),
        OPCION3("3. REGISTRO DE PRESTAMO DE EQUIPO "),
        OPCION4("4. DEVOLVER PRESTAMO"),
        OPCION5("5. REPORTES"),
        OPCION6("6. EDITAR PRESTAMO "),
        OPCION7("7. SALIR "),
        OPCION1_RP("1  EQUIPOS PRESTADOS TIPO/CATEGORIA "),
        OPCION2_RP("2. PRESTAMOS ENTREGADOS "),
        OPCION3_RP("3. PRESTAMOS DEVUELTOS "),
        OPCION4_RP("4. LISTA DE TIPOS"),
        OPCION5_RP("5. LISTA DE EQUIPOS"),
        OPCION6_RP("6. LISTA DE CLIENTES "),
        OPCION7_RP("7. LISTA DE PRESTAMOS "),
        OPCION8_RP("8. LISTA DE PRESTAMOS ORDENADOS POR VALOR GENERADO "),
        OPCION9_RP("9. REGRESAR "),
        OPCION1_ED("1  EDITAR FECHA DE ENTREGA"),
        OPCION2_ED("2. EDITAR FECHA DE DEVOLUCION"),
        OPCION3_ED("3. AÑADIR EQUIPOS"),
        OPCION4_ED("4. ELIMINAR EQUIPOS"),
        OPCION5_ED("5. REGRESAR ");
        private String valor;

        private MENU(String v) {
            valor = v;
        }

        public String tx() {
            return valor;
        }
    }
}
