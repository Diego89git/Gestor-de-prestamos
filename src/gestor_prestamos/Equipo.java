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
public class Equipo {

    String identificador;
    Tipo_Equipo tipo;
    String marca;
    double precio;
    boolean disponible;

    public Equipo(String identificador, Tipo_Equipo tipo, String marca, double precio) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.marca = marca;
        this.precio = precio;
        this.disponible=true;
    }
        
    public boolean equals(String identificador){
     return  identificador.equalsIgnoreCase(this.identificador);
    }
    public void prestar(){
        this.disponible=false;
    }
    public void devolver(){
        this.disponible=true;
    }
    public boolean estaDispobile(){
        return disponible;
    }
    public String estado(){
       String estado;
        if(this.disponible){
            estado="DISPONIBLE";
        }else{
            estado="PRESTADO";
        }
        return estado;
    }
    public void imprimirDatos(){
        
     System.out.printf("%-15S%-15S%-15s%-15.2f%-15s\n"
             ,this.identificador
             ,this.tipo.nombre
             ,this.marca
             ,this.precio,estado());
    }
    public void imprimirDetallePrestamo(){
        
     System.out.printf("%-15S%-15S%-15s%-15.2f\n"
             ,this.identificador
             ,this.tipo.nombre
             ,this.marca
             ,this.precio);
    }
    
    public String toString(){
        return identificador+"\t\t"+tipo.nombre+"\t\t"+estado() ;
    }
}
