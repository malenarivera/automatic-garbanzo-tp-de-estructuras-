/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.arbolGen;

/**
 *
 * @author MALENA RIVERA FAI-2516
 */
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;
    
     //CONSTRUCTOR
    public NodoGen (Object elemento, NodoGen izq, NodoGen der){
        this.elem=elemento;
        this.hijoIzquierdo=izq;
        this.hermanoDerecho=der;
    }
    
    //OBSERVADORAS
    public Object getElem(){
        return elem;
    }
    public NodoGen getHijoIzquierdo(){
        return hijoIzquierdo;
    }
    public NodoGen getHermanoDerecho(){
        return hermanoDerecho;
    }
    
    //MODIFICADORAS
    public void setElem(Object elemento){
        this.elem=elemento;
    }
    public void setHijoIzquierdo (NodoGen izq){
        this.hijoIzquierdo=izq;
    }
    public void setHermanoDerecho (NodoGen der){
        this.hermanoDerecho=der;
    }
    
}

