/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import gestor_prestamos.Prestamo;

/**
 *
 * @author Mauricio
 */
public class NodoABin {

    Object dato;
    public NodoABin enlIzq;
    public NodoABin enlDer;

    NodoABin() {
        this.dato = null;
        this.enlIzq = this.enlDer = null;
    }

    NodoABin(Object dato) {
        this.dato = dato;
        this.enlIzq = this.enlDer = null;
    }

    boolean insertar(Object dato) {
        if (dato.equals(this.dato)) {
            return false;
        } else if (((Prestamo) dato).devolverTotal() < ((Prestamo) this.dato).devolverTotal()) {
            if (this.enlIzq == null) {
                this.enlIzq = new NodoABin(dato);
            } else {
                return this.enlIzq.insertar(dato);
            }
        } else if (this.enlDer == null) {
            this.enlDer = new NodoABin(dato);
        } else {
            return this.enlDer.insertar(dato);
        }
        return true;
    }

    int sumarHasta(int umbral, int suma) {
        if ((int) this.dato < umbral) {
            if (this.enlIzq != null) {
                suma = this.enlIzq.sumarHasta(umbral, suma);
            }
            suma = suma + (int) this.dato;
            System.out.println(suma);
            if (this.enlDer != null) {
                suma = this.enlDer.sumarHasta(umbral, suma);
            }
        }else{
           if (this.enlIzq != null) {
                suma = this.enlIzq.sumarHasta(umbral, suma);
            }
            
        }
        return suma;
    }

    void imprimir() {

        if (this.enlIzq != null) {
            this.enlIzq.imprimir();
        }
        System.out.println(dato.toString());
        if (this.enlDer != null) {
            this.enlDer.imprimir();
        }
        //System.out.println(dato.toString());

    }

    int imprimirAbuelos() {
        if(this.enlDer==null && this.enlIzq==null){
            return 0;
        }
        int desIz=0;
        if(this.enlIzq!=null){
            desIz= 1+this.enlIzq.imprimirAbuelos();
        }
        int desDer=0;
        if(this.enlDer!=null){
            desDer= 1+this.enlDer.imprimirAbuelos();
        }
        int desTot=0;
        desTot=desIz+desDer;
        if(desTot>=2){
            System.out.println(this.dato);
        }
        return Math.max(desIz, desIz);
    }

}
