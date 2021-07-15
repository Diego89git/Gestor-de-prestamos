/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

/**
 *
 * @author COMPUTER
 */
public class Validador {

    public static final int MENU = 0;
    public static final int SOLO_LETRAS = 1;
    public static final int CEDULA = 2;
    public static final int VALOR = 3;
    public static final int TELEFONO = 4;
    public static final int IDENTIFICADOR = 5;
    public static final int RESPUESTA = 6;
    public static final int FECHA = 7;

    public static boolean esValor(String calif) {
        String regex = "[+-]?([0-9]*[.])?[0-9]+";
        if (calif.matches(regex)) {
            double valorCalif = Double.parseDouble(calif);
            return (valorCalif > 0 && valorCalif <= 1000000);
        }
        return false;
    }

    public static boolean esMenu(String opcion) {
        String regex = "-?[1-9]+";
        if (opcion.matches(regex)) {
            int valorOpcion = Integer.parseInt(opcion);
            return (valorOpcion == -1 || (valorOpcion > 0 && valorOpcion < 10));
        }
        return false;
    }

    public static boolean esSoloLetras(String cadena) {
        String regex = "[ñÑáéíóúÁÉÍÓÚA-Za-z].+";

        return (cadena.matches(regex));
    }

    public static boolean esRespuesta(String cadena) {
        String regex = "[sSnN]";

        return (cadena.matches(regex));
    }

    public static boolean esTelefono(String cadena) {
        if (cadena.length() < 7 || cadena.length() > 10) {
            return false;
        }
        String regex = "[0-9]+";
        return (cadena.matches(regex));
    }

    public static boolean esIdentificador(String cadena) {
        if (cadena.length() != 6) {
            return false;
        }
        String regex = "[0-9]+";
        String carUno = cadena.substring(0, 1).concat("K");
        String carDos = cadena.substring(1, 2).concat("K");
        String carTres = cadena.substring(2, 3).concat("K");
        String carCuatro = cadena.substring(3, 4);
        String carCinco = cadena.substring(4, 5);
        String carSeis = cadena.substring(5, 6);
        if (esSoloLetras(carUno) && esSoloLetras(carDos) && esSoloLetras(carTres)
                && carCuatro.matches(regex) && carCinco.matches(regex) && carSeis.matches(regex)) {
            return true;
        }
        return false;
    }
    public static boolean esFecha(String cadena) {//15/11/2020 15:30
        if (cadena.length() != 16) {
            return false;
        }
        String año = cadena.substring(6, 10);
        String mes = cadena.substring(3, 5);
        String dia = cadena.substring(0, 2);
        String hora = cadena.substring(11, 13);
        String minuto = cadena.substring(14, 16);
        if (esAño(año) && esMes(mes) && esDia(dia, mes) && esHora(hora) && esMinuto(minuto)) {
            return true;
        }
        return false;
    }
    public static boolean esAño(String año){
       String regex = "[0-9]+"; 
       if (año.matches(regex)) {
            int valor = Integer.parseInt(año);
            return (valor > 2019 && valor <= 2100);
        }
        return false;
    }
    public static boolean esMes(String mes){
       String regex = "[0-9]+"; 
       if (mes.matches(regex)) {
            int valorCalif = Integer.parseInt(mes);
            return (valorCalif > 0 && valorCalif <= 12);
        }
        return false;
    }
    public static boolean esDia(String dia, String mes){
       String regex = "[0-9]+"; 
       int mesE = Integer.parseInt(mes);
       if (dia.matches(regex)) {
            int diaE = Integer.parseInt(dia);
            switch (mesE){
                 case 1: return (diaE > 0 && diaE <= 31);              
                 case 2: return (diaE > 0 && diaE <= 28);              
                 case 3: return (diaE > 0 && diaE <= 31);              
                 case 4: return (diaE > 0 && diaE <= 30);              
                 case 5: return (diaE > 0 && diaE <= 31);              
                 case 6: return (diaE > 0 && diaE <= 30);              
                 case 7: return (diaE > 0 && diaE <= 31);              
                 case 8: return (diaE > 0 && diaE <= 31);              
                 case 9: return (diaE > 0 && diaE <= 30);              
                 case 10: return (diaE > 0 && diaE <= 31);              
                 case 11: return (diaE > 0 && diaE <= 30);              
                 case 12: return (diaE > 0 && diaE <= 31);              
                 default: return false ; 
            }
            
        }
        return false;
    }
    public static boolean esHora(String horas){
       String regex = "[0-9]+"; 
       if (horas.matches(regex)) {
            int valor = Integer.parseInt(horas);
            return (valor >= 0 && valor <= 23);
        }
        return false;
    }
    public static boolean esMinuto(String minuto){
       String regex = "[0-9]+"; 
       if (minuto.matches(regex)) {
            int valor = Integer.parseInt(minuto);
            return (valor >=0  && valor <= 59);
        }
        return false;
    }

    public static boolean esCedula(String cedula) {
        boolean cedulaCorrecta;
        try {
            if (cedula.length() == 10) {
                // Coeficientes de validación cédula
                // El decimo digito se lo considera dígito verificador
                int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                int verificador = Integer.parseInt(cedula.substring(9, 10));
                int suma = 0;
                int digito;
                for (int i = 0; i < (cedula.length() - 1); i++) {
                    digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                    suma += ((digito % 10) + (digito / 10));
                }

                cedulaCorrecta = ((suma % 10 == 0 && verificador == 0) || (10 - suma % 10 == verificador));
            } else {
                cedulaCorrecta = false;
            }
        } catch (Exception e) {
            cedulaCorrecta = false;
        }
        return cedulaCorrecta;
    }

    public static boolean esEdadEstudiar(String edad) {
        String regex = "[0-9]+";
        if (edad.matches(regex)) {
            int valorEdad = Integer.parseInt(edad);
            return (valorEdad > 17 && valorEdad < 65);
        }
        return false;
    }

    public static boolean validar(String dato, int tipoValidador) {
        boolean esValido;
        switch (tipoValidador) {
            case MENU:
                esValido = esMenu(dato);
                break;
            case SOLO_LETRAS:
                esValido = esSoloLetras(dato);
                break;
            case CEDULA:
                esValido = esCedula(dato);
                break;
            case VALOR:
                esValido = esValor(dato);
                break;

            case TELEFONO:
                esValido = esTelefono(dato);
                break;
            case IDENTIFICADOR:
                esValido = esIdentificador(dato);
                break;
            case RESPUESTA:
                esValido = esRespuesta(dato);
                break;
            case FECHA:
                esValido= esFecha(dato);
                break;
            default:
                throw new AssertionError();
        }
        return esValido;
    }
}
