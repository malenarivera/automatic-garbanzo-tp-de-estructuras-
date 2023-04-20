/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.conjuntistas;

/**
 *
 * @author diana
 */
class NodoABB {

    //ATRIBUTOS
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;
    
    //CONSTRUCTORES
    public NodoABB (Comparable elemento, NodoABB izq, NodoABB der){
        this.elem=elemento;
        this.izquierdo=izq;
        this.derecho=der;
    }
    
    public NodoABB (Comparable elemento){
        this.elem=elemento;
        this.izquierdo=null;
        this.derecho=null;
    }
    
    //OBSERVADORES
    public Comparable getElem(){
        return this.elem;
    }
    
    public NodoABB getIzquierdo(){
        return this.izquierdo;
    }
    
    public NodoABB getDerecho(){
        return this.derecho;
    }
    
    //MODIFICADORES
    
    public void setDerecho(NodoABB der){
        this.derecho=der;
    }
    
    public void setIzquierdo(NodoABB izq){
        this.izquierdo=izq;
    }
    
    public void setElem(Comparable elem){
        this.elem=elem;
    }
    
    
    
    
}
