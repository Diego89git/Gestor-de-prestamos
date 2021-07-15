/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author felix
 */
public class NodoDE {
    Object dato;
    NodoDE siguiente;
    NodoDE anterior;
    
    NodoDE ( Object dato ){
        this.dato = dato;
        this.siguiente = this.anterior = null;
    }
}
