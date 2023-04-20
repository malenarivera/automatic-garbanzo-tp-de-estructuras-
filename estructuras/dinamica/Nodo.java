/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamica;

/**
 *
 * @author MALENA RIVERA FAI-2516
 */
 class Nodo {
    private Object elem;
    private Nodo enlace;
    
    //Constructor
    public Nodo (Object elem, Nodo enlace){
        this.elem=elem;
        this.enlace=enlace;
    }
    
    //Modificadoras
    public void setElem (Object elem){
        this.elem=elem;
    }
    
    public void setEnl (Nodo enl){
        this.enlace=enl;
    }
    
    //Observadoras
    public Object getElm(){
        return elem;
    }
    
    public Nodo getEnl(){
        return enlace;
    }
}
    