/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.conjuntistas;
import lineales.dinamica.Lista;

/**
 *
 * @author MALENA RIVERA FAI-2516
 */
public class ArbolABB {
    private NodoABB raiz;
    
    public ArbolABB(){
       this.raiz=null;
    }
    
    public boolean insertar (Comparable elemento){
        boolean inserccion=true;
        if(this.raiz==null)
            this.raiz = new NodoABB(elemento);
        else
            inserccion= insertarAux(this.raiz, elemento);
        return inserccion;
    }
    private boolean insertarAux(NodoABB n, Comparable elem){
        boolean exito=true;
      if((elem.compareTo(n.getElem())==0))
          exito=false;
      else if (elem.compareTo(n.getElem())<0){
          if(n.getIzquierdo()!=null)
              exito=insertarAux(n.getIzquierdo(),elem);
          else
              n.setIzquierdo(new NodoABB(elem));
          }
      else{
          if(n.getDerecho()!=null)
              exito=insertarAux(n.getDerecho(),elem);
           else
              n.setDerecho(new NodoABB (elem));
      }
      return exito;
    }
    
    
    
    public boolean eliminar (Comparable elem){
        boolean sacar=true;
        if(this.raiz==null)
            sacar=false;
        else 
            sacar=eliminarAux(this.raiz,this.raiz, elem);
        return sacar;    
        }
    private boolean eliminarAux(NodoABB padre, NodoABB n, Comparable elem) {
        boolean exito = false;
        if (n != null) {
            //Si el elemento buscado es el mismo que esta como raiz
            if (elem.compareTo(n.getElem()) == 0) {
                exito = true;
                //Si tiene los dos hijos
                if (n.getDerecho() != null && n.getIzquierdo() != null) 
                    eliminarCasoTres(n);
                else {
                    //si tiene un solo hijo, da igual cual
                    if (n.getDerecho() != null|| n.getIzquierdo() != null) {
                        eliminarCasoDos(padre, n);
                    } //si no tiene ningun hijo
                    else {
                        eliminarCasoUno(padre, elem);
                    }
                }
         }else {
                if (elem.compareTo(n.getElem()) < 0) 
                    exito = eliminarAux(n, n.getIzquierdo(), elem);
                else 
                    exito = eliminarAux(n, n.getDerecho(), elem);
                }
        }
        
        return exito;
    }
    
    private void eliminarCasoUno(NodoABB padre, Comparable elem) {
        //Metodo que elimina un Nodo Hoja
        if (padre == null) 
            this.raiz = null;
       else {
            //si el eliminado es el HI lo saca
            if (elem.compareTo(padre.getIzquierdo().getElem()) == 0) 
                padre.setIzquierdo(null);
             //sino borra el derecho
            else 
                padre.setDerecho(null);
            
        }
    }
    
    
    private void eliminarCasoDos(NodoABB padre, NodoABB n) {
        //Metodo para eliminar un Nodo con un solo hijo
        if (padre == null){
            if (n.getIzquierdo() != null) 
                this.raiz = n.getIzquierdo();
             else 
                this.raiz = n.getDerecho();
           
        } else {
            if (padre.getIzquierdo() != null) {
                //EL HI de padre es el que quiero eliminar
                if (padre.getIzquierdo().getElem().compareTo(n.getElem()) == 0) {
                    //el eliminado tiene HI
                    if (n.getIzquierdo() != null) 
                        padre.setIzquierdo(n.getIzquierdo());
                     //el eliminado tiene HD
                    else 
                        padre.setIzquierdo(n.getDerecho());
                    
                }
                //El HD de padre es el que quiero eliminar
            } else {
                if (n.getDerecho() != null) 
                    padre.setDerecho(n.getDerecho());
                 else 
                    padre.setDerecho(n.getIzquierdo());
                
               }

          }

    
    }
    
    
    private void eliminarCasoTres(NodoABB n){
       //elegí que suba el candidato A (mayor elem del subarbol izq de n)
       
       //creo dos nodos, el que voy a usar de candidato, y el padre de este
       NodoABB candidato, padreCandidato;
       candidato=n.getIzquierdo();
       padreCandidato=n;
       
       //ahora, busco al candidato
       while(candidato.getDerecho()!=null){
           padreCandidato=candidato;
           candidato=candidato.getDerecho();
       }
       
       //ahora, elimino al candidato.
       //como el candidato no puede tener un hijo derecho, me fijo si tiene hijo izq
       if(candidato.getIzquierdo()!=null){
           this.eliminarCasoDos(padreCandidato, candidato);
       }else{
           //si tampoco tenia hijo izq entonces se habia llegado a una hoja
           this.eliminarCasoUno(padreCandidato, candidato.getElem());
       }
       //por ultimo, seteo a n por el elem de candidato
      
       n.setElem(candidato.getElem());
    }
    
    
    public boolean pertenece (Comparable elem){
        boolean pertenecer;
        if(this.raiz==null)
            pertenecer=false;
        else
            pertenecer=perteneceAux(this.raiz, elem);
        return pertenecer;
    }
    private boolean perteneceAux(NodoABB n, Comparable elem) {
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
    private void listarAux (NodoABB n, Lista ls){
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
    private Comparable minimoElemAux(NodoABB n) {
        Comparable min = null;
        if (n != null) {
            if (n.getIzquierdo() == null) 
                min = n.getElem();
             else {
                NodoABB nuevo = n;
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
    private Comparable maximoElemAux(NodoABB n){
        Comparable max = null;
        if (n != null) {
            if (n.getDerecho() == null) 
                max = n.getElem();
           else {
                NodoABB nuevo = n;
                while (nuevo.getDerecho() != null) {
                    nuevo = nuevo.getDerecho();
                }
                max = nuevo.getElem();
            }
        }
        return max;
    }
    
    
    
    public ArbolABB clone(){
    ArbolABB clon= new ArbolABB();
    if(this.raiz==null)
        clon.raiz=null;
    else
        clon.raiz=cloneAux(this.raiz);
    return clon;
    }
private NodoABB cloneAux(NodoABB n){
    NodoABB clonado;
    clonado= new NodoABB (n.getElem(),null,null);
    if(n.getIzquierdo()!=null)
        clonado.setIzquierdo(cloneAux(n.getIzquierdo()));
    if (n.getDerecho()!=null)
        clonado.setDerecho(cloneAux(n.getDerecho()));
return clonado;
}
    



public String toString(){
    return toStringAux(this.raiz,"");
}
    private String toStringAux(NodoABB n, String m) {
        String s = m;
        if (this.raiz == null) {
            s = "Arbol de Busqueda vacio";
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
private void listarRangoAux(NodoABB n, Lista rg, Comparable min, Comparable max){
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
 private boolean eliminarMinimoAux(NodoABB n){
     boolean exito= false;
        if (n != null){
            if (n.getIzquierdo() == null) 
                this.raiz = n.getDerecho();
            else {
                NodoABB nuevo = n.getIzquierdo();
                NodoABB padre= n;
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
 private boolean eliminarMaximoAux(NodoABB n){
     boolean exito= false;
        if (n != null){
            if (n.getDerecho() == null) 
                this.raiz = n.getIzquierdo();
             else {
                NodoABB nuevo = n.getDerecho();
                NodoABB padre= n;
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
}


    


