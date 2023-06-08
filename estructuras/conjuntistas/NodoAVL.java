/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.conjuntistas;

/**
 *
 * @author male_
 */
public class NodoAVL {
    
    //ATRIBUTOS
    private Comparable elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;
    
    //CONSTRUCTORES
    public NodoAVL (Comparable elemento, NodoAVL izq, NodoAVL der, int altura){
        this.elem=elemento;
        this.izquierdo=izq;
        this.derecho=der;
        this.altura=altura;
    }
    
    public NodoAVL (Comparable elemento){
        this.elem=elemento;
        this.izquierdo=null;
        this.derecho=null;
        this.altura=0;
    }
    
    //OBSERVADORES
    public Comparable getElem(){
        return this.elem;
    }
    
    public NodoAVL getIzquierdo(){
        return this.izquierdo;
    }
    
    public NodoAVL getDerecho(){
        return this.derecho;
    }
    
    //MODIFICADORES
    
    public void setDerecho(NodoAVL der){
        this.derecho=der;
    }
    
    public void setIzquierdo(NodoAVL izq){
        this.izquierdo=izq;
    }
    
    public void setElem(Comparable elem){
        this.elem=elem;
    }
    
    public int getAltura(){
    return altura;
}

    public void recalcularAltura(){
        //altura por defecto, si alguno tenia se actualiza
      int alturaIzq= -1, alturaDer=-1;
       if (this.izquierdo!=null){
           alturaIzq=this.izquierdo.getAltura();
       }
       if (this.derecho!=null){
           alturaDer=this.derecho.getAltura();
       }
       this.altura=(Math.max(alturaIzq,alturaDer))+1;
    }
    
}
