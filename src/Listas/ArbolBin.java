/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author Mauricio
 */
public class ArbolBin {

    //NODO RAIZ: Nivel 0
    //NODOS HOJAS: Sin subniveles
    //NODO PADRE: Precedente
    //NODO HIJO: Nodo siguiente
    //NODO HERMANO: Mismo precedente
    //Insertar arbol lexicografico: 
    NodoABin raiz;

    public ArbolBin() {
        this.raiz = null;
    }

   public boolean insertar(Object dato) {
        if (this.raiz == null) {
            try {
                this.raiz = new NodoABin(dato);
            } catch (Exception e) {
                return true;
            }
        } else {
            return this.raiz.insertar(dato);
        }
        return true;
    }

    int sumarHasta(int umbral) {
        int suma=0;
        if (this.raiz == null) {
            System.out.println("No existen datos");
        } else {
            suma = this.raiz.sumarHasta(umbral, suma);
        }
        return suma;
    }

    public void imprimir() {
        String dato = "";
        if (this.raiz == null) {
            System.out.println("No existen datos");
        } else {
            this.raiz.imprimir();
        }
        //return dato;
    }
    
    public void imprimirAbuelos(){
        if(this.raiz==null || (this.raiz.enlDer==null || this.raiz.enlIzq==null))
        return;
         this.raiz.imprimirAbuelos();
    }

}
