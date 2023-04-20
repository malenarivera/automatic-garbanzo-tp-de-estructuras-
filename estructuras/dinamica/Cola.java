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
public class Cola {
   private Nodo frente;
   private Nodo fin;
   
   public Cola(){
    this.frente=null;
    this.fin=null;
   }

    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {
            exito = false;
        } else {
            this.frente = this.frente.getEnl();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }
   public boolean poner(Object tipoElm){
       Nodo nuevo= new Nodo (tipoElm,null);
       if(this.frente==null){
           this.frente=nuevo;
           this.fin=nuevo;
       }
       else{
           this.fin.setEnl(nuevo);
           this.fin=nuevo;
       }
       return true;
   }
   
   public Object obtenerFrente(){
       Object tope;
       if(this.frente==null)
           tope=null;
       else
           tope=this.frente.getElm();
       return tope;
   }
   
   public boolean esVacia(){
       boolean si=false;
       if(this.frente==null)
           si=true;
       return si;
   }
   
   public void vaciar(){
       this.frente=null;
       this.fin=null;
   }
   
   public Cola clone(){
       Cola clon= new Cola();
       Nodo aux1= this.frente;
       if(aux1==null)
           clon.frente=null;
       else{
           clon.frente= new Nodo( aux1.getElm(), null);
           aux1= aux1.getEnl();
           Nodo aux2=clon.frente;
           while(aux1!=null){
               aux2.setEnl(new Nodo (aux1.getElm(),null));
               aux2=aux2.getEnl();
               aux1=aux1.getEnl();
            }
       clon.fin=this.fin;
       }
       return clon;
   }
   
    public String toString(){
        String s="";
        if (this.frente==null)
            s="Cola vacia";
        else{
           Nodo aux=this.frente;
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
}

