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
public class Lista {
    private Nodo cabecera;
    private int longitud;
    
    public Lista(){
        this.cabecera=null;
        this.longitud=0;
    }
   public boolean insertar(Object elem, int pos){
       boolean exito=true;
       if(pos<1||pos>this.longitud+1)
           exito=false;
       else{
           if(pos==1){
               this.cabecera= new Nodo(elem,this.cabecera);
           }
           else{
               Nodo aux=this.cabecera;
               int i=1;
               while(i<pos-1){
                   aux=aux.getEnl();
                   i++;
               }
               Nodo nuevo= new Nodo(elem,aux.getEnl());
               aux.setEnl(nuevo);
           }
       
       longitud++;
       }return exito;
   }
   
   public Object recuperar(int pos){
       Object tipoElm=null;
       if(pos<1||pos>this.longitud||this.cabecera==null)
           tipoElm=null;
       else{
           Nodo aux=this.cabecera;
           int i=1;
           while(i!=pos&&aux!=null){
               aux=aux.getEnl();
               i++;
           
           }
           if(aux!=null)
           tipoElm=aux.getElm();
           }
       return tipoElm;
       }
   
   public int longitud(){
       int i=this.longitud;
       return i;
   }
public int localizar(Object tipoElem){
    int conta;
    if(this.cabecera==null)
        conta=-1;
    else{
        conta=1;
        Nodo aux=this.cabecera;
        while (aux!=null&&!aux.getElm().equals(tipoElem)){
            aux=aux.getEnl();
            conta++;
        }
        if(conta>this.longitud)
            conta=-1;
    }
    return conta;
   }
   
 
   public boolean eliminar(int pos){
       boolean exito=true;
       if(pos<1||pos>this.longitud||this.cabecera==null)
           exito=false;
       else{
          if(pos==1)
              this.cabecera=cabecera.getEnl();
          else{
              Nodo temp= this.cabecera;
               int i=1;
               while (i!=pos-1&&temp.getEnl()!=null){
                   temp=temp.getEnl();
                   i++;
               }
                  temp.setEnl(temp.getEnl().getEnl());
}
          longitud--;
       }      return exito;
   } 
   
   public boolean esVacia(){
       boolean si=false;
       if(this.cabecera==null){
           si=true;
       }
       return si;
   }
   
   public void vaciar(){
       this.cabecera=null;
       this.longitud=0;
   }
    public Lista clone(){
       Lista clon= new Lista();
       Nodo aux1= this.cabecera;
       if(aux1==null)
           clon.cabecera=null;
       else{
           clon.cabecera= new Nodo( aux1.getElm(), null);
           aux1= aux1.getEnl();
           Nodo aux2=clon.cabecera;
           while(aux1!=null){
               aux2.setEnl(new Nodo (aux1.getElm(),null));
               aux2=aux2.getEnl();
               aux1=aux1.getEnl();
            }
       }
       clon.longitud=this.longitud;
       return clon;
   }
     public String toString(){
        String s="";
        if (this.cabecera==null)
            s="Lista vacia";
        else{
           Nodo aux=this.cabecera;
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
     
     //practica parcial
     public boolean agregarElemento(Object nuevo, int x){
         boolean si=true;
         if(x<1||x>this.longitud+1)
             si=false;
         else{
             this.cabecera= new Nodo (nuevo, this.cabecera);
             Nodo aux1=this.cabecera, aux2;
             int temp=0;
             while (aux1!=null){
                 if(temp==x){
                     aux2= new Nodo (nuevo, aux1.getEnl());
                     aux1.setEnl(aux2);
                     temp=-1;
                 }
                 else{
                     aux1=aux1.getEnl();
                     temp++;
                 }
             }
         }
         return si;
     }
     
     public void eliminarApariciones(Object elem){
         Nodo aux=this.cabecera;
         while(aux!=null){
             if(aux.getElm().equals(elem)){
                 while(aux!=null){
                     aux.setElem(aux.getEnl().getElm());
                     aux.setEnl(aux.getEnl());
                   }          
             }else
                 aux=aux.getEnl();
         }
     }
}