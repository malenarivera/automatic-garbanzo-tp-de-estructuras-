/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamica;

import lineales.dinamica.Nodo;

/**
 *
 * @author MALENA RIVERA FAI-2516
 */
public class Pila{
    private Nodo tope;
    
    public Pila(){
        this.tope=null;
    }
    
    public boolean apilar (Object nuevoEl){
        Nodo nuevo= new Nodo (nuevoEl,this.tope);
        this.tope=nuevo;
        return true;
    }
    
    public boolean desapilar (){
        boolean ok;
        if (this.tope==null)
            ok=false;
        else{
           tope=this.tope.getEnl();
            ok=true;
        }
        return ok;
    }
    public Object obtenerTope(){
        Object top;
        if (this.tope==null)        
            top=null;
        else        
            top=this.tope.getElm();
        return top;
    }
     public boolean esVacia(){
         boolean si;
         if (this.tope==null)
             si=true;
         else
             si=false;
         return si;
     }
     
     public void vaciar(){
      this.tope=null;
     }
   
    public String toString(){
        String s="";
        if (this.tope==null)
            s="Pila vacia";
        else{
           Nodo aux=this.tope;
           s="[";
           while(aux!=null){
               s+=aux.getElm().toString();
               aux=aux.getEnl();
               if(aux!=null)
                   s+=",";
           }
           s+="]";
    }
        return s;
    }
   public Pila clone(){
       Pila clon= new Pila();
   Nodo aux1= this.tope;
       if(aux1==null)
           clon.tope=null;
       else{
           clon.tope= new Nodo( aux1.getElm(), null);
           aux1= aux1.getEnl();
           Nodo aux2=clon.tope;
           while(aux1!=null){
               aux2.setEnl(new Nodo (aux1.getElm(),null));
               aux2=aux2.getEnl();
               aux1=aux1.getEnl();
            }
       
       }
       return clon;
  } 
}
     
    
