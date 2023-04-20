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
public class Cola {
  private static final int TAMANIO=10;
  private Object []arr;
  private int frente;
  private int fin;
  
  public Cola(){
      this.arr= new Object [this.TAMANIO];
      this.frente=0;
      this.fin=0;
  }
  //modificadoras
  public boolean poner (Object tipoElm){
    boolean ok=true;
    if((this.fin+1)%TAMANIO==this.frente)
        ok=false;
    else{
          this.arr[this.fin]=tipoElm;
        this.fin=(this.fin+1)%this.TAMANIO;
    }
    return ok;
  }
  
public boolean sacar(){
    boolean sacado=true;
    if(this.esVacia())
        sacado=false;
    else{
        this.arr[this.frente]=null;
        this.frente=(this.frente+1)%(this.TAMANIO);
        
    }
    return sacado;
} 

public Object obtenerFrente(){
    Object tope;
    if(this.esVacia())
        tope=null;
     else
       tope=this.arr[this.frente]; 
    return tope;
    }

public boolean esVacia(){
    boolean si=false;
    if(this.frente%TAMANIO==fin)
      si=true;
    return si;
}
public void vaciar(){
  for (int i=0; i<TAMANIO;i++){
      this.arr[i]=null;
  }
}

public Cola clone(){
    Cola clon;
    clon= new Cola();
    clon.arr=this.arr.clone();
    clon.fin=this.fin;
    clon.frente=this.frente;
    return clon;
}

public String toString(){
    String mostrar;
    if(this.esVacia())
        mostrar="Cola vacia";
    else{
        mostrar="[";
        int i=this.frente;
        while (i!=this.fin){
            mostrar+=" "+arr[i]+" ";
         i=(i+1)%TAMANIO;
        }
        mostrar+="]";
    }
    return mostrar;
    }
}