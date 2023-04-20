/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estatica;

/**
 *
 * @author MALENA RIVERA FAI-2516
 */
public class Pila {
    private Object[] arr;
    private int tope;
    private static final int TAMANIO=10;
    
    public Pila(){
        this.arr= new Object [TAMANIO];
        this.tope=-1;
    }
    public boolean apilar (Object nuevoElm){
        boolean exito;
        if ((this.tope)+1>=this.TAMANIO)
            exito=false;
        else{
            this.tope++;
           this.arr[tope]=nuevoElm;
          exito=true;
    }
        return exito;
    }
    public boolean desapilar (){
        boolean ok;
        if (this.tope<=-1)
            ok=false;
        else{
           this.arr[this.tope]=null;
           this.tope--;
          ok=true;
    }
        return ok;
    }
    
    public Object obtenerTope (){
     Object tipoEl;
     if(this.tope<=-1)
      tipoEl=null;
     else
      tipoEl=arr[tope];
     return tipoEl;
    }
    
    public boolean esVacia(){
        boolean si;
        if (this.tope<=-1)
            si=true;
        else
            si=false;
        return si;
    }
    
    public void vaciar(){
        for (int i=tope;i>-1;i--){
            arr[i]=null;
            this.tope--;
    }}
    
   public Pila clone(){
       Pila clon;
       clon= new Pila();
       clon.arr=this.arr.clone();
       clon.tope=this.tope;
       return clon;
   }
   public String toString(){
       String mostrar="";
       if(this.tope<=-1)
           mostrar="Pila Vacia";
       else{
           mostrar="[";
            int i=this.tope;
            while (i>-1){
           mostrar+=" "+this.arr[i].toString();
           i--;
       }
       mostrar+="]";
       }
       return mostrar;
   }
           
}
