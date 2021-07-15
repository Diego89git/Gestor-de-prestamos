/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author Diego Toapanta
 */
public class ListaDE {

    NodoDE primero;
    NodoDE ultimo;
    int tamaño;

    public ListaDE() {
        this.primero = this.ultimo = null;
        this.tamaño=0;
    }

    public boolean insertar(Object dato) {
        NodoDE nuevo;
        try {
            nuevo = new NodoDE(dato);
        } catch (Exception e) {
            return false;
        }
        if (this.primero == null) //lista vacía
        {
            this.primero = this.ultimo = nuevo;
        } else {
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        this.tamaño++;
        return true;
    }

    public boolean insertarPorUltimo(Object dato) {
        NodoDE nuevo;
        try {
            nuevo = new NodoDE(dato);
        } catch (Exception e) {
            return false;
        }
        if (this.tamaño == 0) //lista vacía
        {
            this.primero = this.ultimo = nuevo;
        } else {
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        this.tamaño++;
        return true;
    }

   public  boolean insertarPorPrimero(Object dato) {
        NodoDE nuevo;
        try {
            nuevo = new NodoDE(dato);
        } catch (Exception e) {
            return false;
        }
        if (this.primero == null) //lista vacía
        {
            this.primero = this.ultimo = nuevo;
        } else {
            nuevo.siguiente = this.primero;
            this.primero.anterior = nuevo;
            this.primero = nuevo;
        }
        this.tamaño++;
        return true;
    }
public int tamaño(){
    return this.tamaño;
}
    public boolean insertarPos(Object dato, int pos) {
        if (pos < 0 || pos > this.tamaño) {
            return false; //posición inválida
        }
        if (pos == 0) {
            return insertarPorPrimero(dato);
        }
        if (pos == (this.tamaño - 1)) {
            return insertarPorUltimo(dato);
        }
        int posActual = 0;
        NodoDE aux = this.primero;
        pos = --pos;
        while (posActual < pos) {
            aux = aux.siguiente;
            posActual++;
        }

        NodoDE nuevo;
        try {
            nuevo = new NodoDE(dato);
        } catch (Exception e) {
            return false;  //no hay memoria suficiente
        }
        nuevo.siguiente = aux.siguiente;
        aux.siguiente = nuevo;
        nuevo.anterior = aux;
        nuevo.siguiente.anterior = nuevo;
        this.tamaño++;
        return true;
    }

    public Object buscar(int pos) {
        if (pos < 0 || pos >= this.tamaño) {
            return null;
        }
        int posActual = 0;
        NodoDE aux = this.primero;
        while (posActual < pos) {
            aux = aux.siguiente;
            posActual++;
        }
        return aux.dato;
    }

    public int buscaPrim(Object dato) {
        NodoDE aux = this.primero;
        int i = 0;
        while (!aux.dato.equals(dato) && i < this.tamaño) {
            aux = aux.siguiente;
            i++;
            //System.out.println("sige en "+i);
        }
        if (aux.dato.equals(dato)) {
            return i;
        } else {
            return -1;
        }
    }

    public boolean borrarPorObjeto(Object dato) {

        //int i = buscaPrim(dato);
        boolean borrado = false;
        
        while (buscaPrim(dato) != -1) {
            borrado = borrarPos(buscaPrim(dato));
        }
        return borrado;

    }

    public boolean borrarPos(int pos) {
        if (pos < 0 || pos >= this.tamaño) {
            return false;
        }
        if (pos == 0) {
            this.primero = this.primero.siguiente;
            if (this.primero != null) {
                this.primero.anterior = null;
            } else {
                this.ultimo = null;
            }
            this.tamaño--;
            return true;

        }

        int posAct = 0;
        NodoDE aux = this.primero;
        pos = --pos;
        while (posAct < pos) {
            aux = aux.siguiente;
            posAct += 1;
        }

        aux.siguiente = aux.siguiente.siguiente;
        if (aux.siguiente != null) {
            aux.siguiente.anterior = aux;
        } else {
            this.ultimo = aux;
        }
        this.tamaño--;
        return true;

    }

    public void imprimir() {
        NodoDE aux = this.primero;
        while (aux != null) {
            System.out.println(aux.dato.toString());
            aux = aux.siguiente;
        }
    }

    /*
    void imprimir( ComandoImpresion comando) {
        NodoDE aux = this.primero;
        while (aux != null) {
            System.out.println(comando.textoAImprimir( aux.dato ));
            aux = aux.siguiente;
        }
    }
     */
}
