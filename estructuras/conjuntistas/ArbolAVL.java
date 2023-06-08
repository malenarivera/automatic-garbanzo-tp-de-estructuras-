/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.conjuntistas;
import lineales.dinamica.Lista;

/**
 *
 * @author male_
 */
public class ArbolAVL {
       private NodoAVL raiz;
    
    public ArbolAVL(){
       this.raiz=null;
    }
    
    
     public boolean insertar (Comparable elemento){
        boolean inserccion=true;
        if(this.raiz==null){
            this.raiz = new NodoAVL(elemento);
            raiz.recalcularAltura();
        }else{
            inserccion= insertarAux(this.raiz, this.raiz, elemento);
        }
        return inserccion;
    }
    private boolean insertarAux(NodoAVL padre, NodoAVL n, Comparable elem){
        boolean exito=true;
      if((elem.compareTo(n.getElem())==0))
          exito=false;
      else if (elem.compareTo(n.getElem())<0){
          if(n.getIzquierdo()!=null)
              exito=insertarAux(n, n.getIzquierdo(),elem);
          else
              n.setIzquierdo(new NodoAVL(elem));
          }
      else{
          if(n.getDerecho()!=null)
              exito=insertarAux(n, n.getDerecho(),elem);
           else
              n.setDerecho(new NodoAVL (elem));
      }
       n.recalcularAltura();
       padre.recalcularAltura();
      //el nodo recalcula su altura jiji
      if(exito)
          this.balancearArbol(padre,n);
      return exito;
    }
    
    
    public boolean eliminar (Comparable elem){
        boolean sacar=true;
        if(this.raiz==null)
            sacar=false;
        else 
            sacar=eliminarAux(null,this.raiz, elem);
        return sacar;    
        }
    private boolean eliminarAux(NodoAVL padre, NodoAVL n, Comparable elem) {
        boolean exito = false;
        if (n != null) {
            //Si el elemento buscado es el mismo que esta como raiz
            if (elem.compareTo(n.getElem()) == 0) {
                exito = true;
                //Si tiene los dos hijos
                if (n.getDerecho() != null && n.getIzquierdo() != null) 
                    eliminarCasoTres(padre, n);
                else {
                    //si no tiene ningun hijo
                    if (n.getDerecho() == null && n.getIzquierdo() == null) {
                        eliminarCasoUno(padre, elem);
                    }
                    else {
                        eliminarCasoDos(padre, n);
                    }
                }
         }else {
                if (elem.compareTo(n.getElem()) < 0) 
                    exito = eliminarAux(n, n.getIzquierdo(), elem);
                else 
                    exito = eliminarAux(n, n.getDerecho(), elem);
             }
        
         if(exito)
             balancearArbol(padre,n);
        }
        return exito;
    }
    
    private void eliminarCasoUno(NodoAVL padre, Comparable elem) {
        //Metodo que elimina un Nodo Hoja
        if (padre == null){
            this.raiz = null;
        }else {
            //si el eliminado es el HI lo saca
            if (elem.compareTo(padre.getIzquierdo().getElem()) == 0) 
                padre.setIzquierdo(null);
             //sino borra el derecho
            else 
                padre.setDerecho(null);
            
         padre.recalcularAltura();
        }
    }
    
    
    private void eliminarCasoDos(NodoAVL padre, NodoAVL n) {
        //Metodo para eliminar un Nodo con un solo hijo
        if (padre == null){
            if (n.getIzquierdo() != null) 
                this.raiz = n.getIzquierdo();
             else 
                this.raiz = n.getDerecho();
           
        } else {
                //EL HI de padre es el que quiero eliminar
                if (padre.getIzquierdo() != null&&padre.getIzquierdo().getElem().compareTo(n.getElem()) == 0) {
                    //el eliminado tiene HI
                    if (n.getIzquierdo() != null) 
                        padre.setIzquierdo(n.getIzquierdo());
                     //el eliminado tiene HD
                    else 
                        padre.setIzquierdo(n.getDerecho());
                    
                    padre.getIzquierdo().recalcularAltura();
                    
                
                //El HD de padre es el que quiero eliminar
            } else {
                if (n.getDerecho() != null) 
                    padre.setDerecho(n.getDerecho());
                 else 
                    padre.setDerecho(n.getIzquierdo());
                
                
                padre.getDerecho().recalcularAltura();
               }
            padre.recalcularAltura();
          }
    }
    
    
    private void eliminarCasoTres(NodoAVL padreN, NodoAVL n){
       //elegí que suba el candidato A (mayor elem del subarbol izq de n)
       //creo dos nodos, el que voy a usar de candidato, y el padre de este
       NodoAVL candidato, padreCandidato;
       candidato=n.getIzquierdo();
       padreCandidato=n;
       
       //ahora, busco al candidato
       while(candidato.getDerecho()!=null){
           padreCandidato=candidato;
           candidato=candidato.getDerecho();
       }
       
       //ahora, elimino al candidato.
       //como el candidato no puede tener un hijo derecho, me fijo si tiene hijo izq
       if(candidato!=null){
            n.setElem(candidato.getElem());
         if(candidato.getIzquierdo()!=null){
           this.eliminarCasoDos(padreCandidato, candidato);
         }else{
           //si tampoco tenia hijo izq entonces se habia llegado a una hoja
           this.eliminarCasoUno(padreCandidato, candidato.getElem());
         }
         
         if(padreN==null){
             this.raiz=candidato;
         }
       padreN.recalcularAltura();
       n.recalcularAltura();
    }
    }
    
    
    public boolean pertenece (Comparable elem){
        boolean pertenecer;
        if(this.raiz==null)
            pertenecer=false;
        else
            pertenecer=perteneceAux(this.raiz, elem);
        return pertenecer;
    }
    private boolean perteneceAux(NodoAVL n, Comparable elem) {
        boolean si = false;
        if (n != null) {
            if (elem.compareTo(n.getElem()) == 0) 
                si = true;
             else {
                if (elem.compareTo(n.getIzquierdo()) < 0) 
                    si = perteneceAux(n.getIzquierdo(), elem);
                 else 
                    si = perteneceAux(n.getDerecho(), elem);
                
            }
        }
        return si;
    }
    
    
    public Lista listar(){
        Lista ls= new Lista();
        listarAux(this.raiz,ls);
        return ls;
    }
    private void listarAux (NodoAVL n, Lista ls){
         if(n!=null){
             listarAux(n.getIzquierdo(),ls);
             ls.insertar(n.getElem(), ls.longitud()+1);
             listarAux(n.getDerecho(),ls);
         }
    }
    
    public boolean esVacio(){
        return this.raiz==null;
    }
    
    public void vaciar(){
        this.raiz=null;
    }
    
    
    public Comparable minimoElem(){
        Comparable min;
        min=minimoElemAux(this.raiz);
        return min;
    }
    private Comparable minimoElemAux(NodoAVL n) {
        Comparable min = null;
        if (n != null) {
            if (n.getIzquierdo() == null) 
                min = n.getElem();
             else {
                NodoAVL nuevo = n;
                while (nuevo.getIzquierdo() != null) {
                    nuevo = nuevo.getIzquierdo();
                }
                min = nuevo.getElem();
            }
        }
        return min;
    }
    
    
     public Comparable maximoElem(){
        Comparable max;
        max=maximoElemAux(this.raiz);
        return max;
    }
    private Comparable maximoElemAux(NodoAVL n){
        Comparable max = null;
        if (n != null) {
            if (n.getDerecho() == null) 
                max = n.getElem();
           else {
                NodoAVL nuevo = n;
                while (nuevo.getDerecho() != null) {
                    nuevo = nuevo.getDerecho();
                }
                max = nuevo.getElem();
            }
        }
        return max;
    }
    
    
    
    public ArbolAVL clone(){
    ArbolAVL clon= new ArbolAVL();
    if(this.raiz==null)
        clon.raiz=null;
    else
        clon.raiz=cloneAux(this.raiz);
    return clon;
    }
private NodoAVL cloneAux(NodoAVL n){
    NodoAVL clonado;
    clonado = new NodoAVL (n.getElem(),null,null, 0);
    if(n.getIzquierdo()!=null)
        clonado.setIzquierdo(cloneAux(n.getIzquierdo()));
    if (n.getDerecho()!=null)
        clonado.setDerecho(cloneAux(n.getDerecho()));
return clonado;
}
    



public String toString(){
    System.out.println("Mandandolo al toString con raiz: "+raiz.getElem());
    return toStringAux(this.raiz,"");
}
    private String toStringAux(NodoAVL n, String m) {
        String s = m;
        if (this.raiz == null) {
            s = "Arbol AVL vacio";
        } else {
            if (n != null) {
                s += "\n";
                s += n.getElem().toString();
                if (n.getIzquierdo() != null) 
                    s += " HI: " + n.getIzquierdo().getElem();
                else 
                    s += " HI: - ";
                
                if (n.getDerecho() != null) 
                    s += " HD: " + n.getDerecho().getElem();
                else 
                    s += " HD: - ";
                

                s = toStringAux(n.getIzquierdo(), s);
                s = toStringAux(n.getDerecho(), s);

            }
        }
        return s;

    }

public Lista listarRango (Comparable min, Comparable max){
    Lista rg = new Lista();
    if(min.compareTo(max)<=0)
        listarRangoAux(this.raiz,rg,min,max);
    return rg;
}
private void listarRangoAux(NodoAVL n, Lista rg, Comparable min, Comparable max){
    if(n!=null){
        
        //mientras min sea menor al elemento en el nodo, sigue buscando.
      if(min.compareTo(n.getElem())<0)
          listarRangoAux(n.getIzquierdo(),rg,min,max);
      
      //cuando min deja de ser menor que el elemento en el nodo
      //o max deja de ser mayor que el elemento en el nodo, los empieza a insertar.
      if(min.compareTo(n.getElem())<=0&&max.compareTo(n.getElem())>=0)
          rg.insertar(n.getElem(), rg.longitud()+1);
      
      //mientras max sea mayor al elemento en el nodo n, sigue buscando 
      if(max.compareTo(n.getElem())>0)
          listarRangoAux(n.getDerecho(),rg,min,max);
          
     
        }
        
     }




 public boolean eliminarMinimo (){
   boolean exito;
   exito=eliminarMinimoAux(this.raiz);
   return exito;
 }
 private boolean eliminarMinimoAux(NodoAVL n){
     boolean exito= false;
        if (n != null){
            if (n.getIzquierdo() == null) 
                this.raiz = n.getDerecho();
            else {
                NodoAVL nuevo = n.getIzquierdo();
                NodoAVL padre= n;
                while (nuevo.getIzquierdo() != null) {
                    nuevo = nuevo.getIzquierdo();
                    padre=n.getIzquierdo();
                }
                // en caso de que el minimo tenga un HD
                if(nuevo.getDerecho()==null)
                    padre.setIzquierdo(null);
                else
                    padre.setIzquierdo(nuevo.getDerecho());
            }
          exito=true;
        }
        return exito;
    }
 
 
 
 public boolean eliminarMaximo (){
   boolean exito;
   exito=eliminarMaximoAux(this.raiz);
   return exito;
 }
 private boolean eliminarMaximoAux(NodoAVL n){
     boolean exito= false;
        if (n != null){
            if (n.getDerecho() == null) 
                this.raiz = n.getIzquierdo();
             else {
                NodoAVL nuevo = n.getDerecho();
                NodoAVL padre= n;
                while (nuevo.getDerecho() != null) {
                    nuevo = nuevo.getDerecho();
                    padre=n.getDerecho();
                }
                //en caso de que el elem maximo tenga un HD
                if(nuevo.getIzquierdo()==null)
                    padre.setDerecho(null);
                else
                    padre.setDerecho(nuevo.getIzquierdo());
            }
          exito=true;
        }
        return exito;
    }
 
 private void balancearArbol(NodoAVL padre, NodoAVL n){
 int balanceN= getBalance(n),
     balanceHijoDer= getBalance(n.getDerecho()),
     balanceHijoIzq= getBalance(n.getIzquierdo());
 
     if(n==raiz){ //si estoy trabajando con la raiz
         if(Math.abs(balanceN)>1){//si estoy desbalanceado
             if(balanceN>0){//si es para el lado izq
                 if(balanceHijoIzq>=0) // y mi hijo izquierdo esta caido para el mismo lado
                     raiz=(this.rotarDer(n));
                 else{
                     n.setIzquierdo(rotarIzq(n.getIzquierdo()));
                     raiz= this.rotarDer(n);
                  }
             }else{ //si estoy para el lado derecho
                  if(balanceHijoDer<=0) //y mi hijo derecho esta caido para el mismo lado
                      raiz= this.rotarIzq(n);
                  else{
                      n.setDerecho(rotarDer(n.getDerecho()));
                      raiz=this.rotarIzq(n);
                      }
             }
             n.recalcularAltura();
             raiz.recalcularAltura();
                         
         }
            
    }else{ //si no estoy trabajando con la raiz
         if(Math.abs(balanceN)>1){ //si el padre esta desbalanceado
                if(balanceN>0){   //si estoy caido para la izq
                    if(balanceHijoIzq>=0){ // y mi hijo izq tmb
                        if(padre.getDerecho().getElem().compareTo(n.getElem())==0)
                            padre.setDerecho(rotarDer(n));
                        else
                            padre.setIzquierdo(rotarDer(n));
                    
                    }else{ //sino pues,, rotacion doble
                           n.setIzquierdo(rotarIzq(n.getIzquierdo()));
                           if(padre.getDerecho().getElem().compareTo(n.getElem())==0)
                               padre.setDerecho(rotarDer(n));
                           else
                               padre.setIzquierdo(rotarDer(n));
                    }
                }else{ //si estoy caido para el lado contrario
                    if(balanceHijoDer<=0){
                        if(padre.getDerecho().getElem().compareTo(n.getElem())==0)
                            padre.setDerecho(rotarIzq(n));
                        else
                            padre.setIzquierdo(rotarIzq(n));
                    }else{
                         n.setDerecho(rotarDer(n.getDerecho()));
                        if(padre.getDerecho().getElem().compareTo(n.getElem())==0)
                            padre.setDerecho(rotarIzq(n));
                        else
                            padre.setIzquierdo(rotarIzq(n));
                    }
                }
             n.recalcularAltura();
             padre.recalcularAltura();
         }
         
     }
    
     
}
 
    
 private int getBalance(NodoAVL n){
   int alturaHijoIzq=-1,alturaHijoDer=-1,balance;
   
   if(n!=null){
       n.recalcularAltura();
      if(n.getIzquierdo()!=null)
            alturaHijoIzq=n.getIzquierdo().getAltura();
   
     if(n.getDerecho()!=null)
             alturaHijoDer=n.getDerecho().getAltura();
   }
   //calculo el balance
    balance= alturaHijoIzq-(alturaHijoDer);
  return balance;
 }
     
 private NodoAVL rotarIzq(NodoAVL n){
     NodoAVL h, temp;
     h= n.getDerecho();
     temp= h.getIzquierdo();
     h.setIzquierdo(n);
     n.setDerecho(temp);
     return h;  
 }
 
 private NodoAVL rotarDer(NodoAVL n){
     NodoAVL h,temp;
     h= n.getIzquierdo();
     temp= h.getDerecho();
     h.setDerecho(n);
     n.setIzquierdo(temp);
     return h;           
 }
 
 
}
