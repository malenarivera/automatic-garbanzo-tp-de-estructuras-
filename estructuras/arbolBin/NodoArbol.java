/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.arbolBin;

/**
 *
 * @author MALENA RIVERA FAI-2516
 */
public class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
   //CONSTRUCTOR
    public NodoArbol(Object elemento, NodoArbol izq, NodoArbol der){
        this.elem=elemento;
        this.izquierdo=izq;
        this.derecho=der;
    }
    
    //OBSERVADORAS
    public Object getElem(){
        return elem;
    }
    public NodoArbol getIzquierdo(){
        return izquierdo;
    }
    public NodoArbol getDerecho(){
        return derecho;
    }
    
    //MODIFICADORAS
    public void setElem(Object elemento){
        this.elem=elemento;
    }
    public void setIzquiedo (NodoArbol izq){
        this.izquierdo=izq;
    }
    public void setDerecho (NodoArbol der){
        this.derecho=der;
    }
}
