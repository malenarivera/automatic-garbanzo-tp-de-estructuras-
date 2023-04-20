/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.arbolBin;

import lineales.dinamica.Cola;
import lineales.dinamica.Lista;
/**
 *
 * @author MALENA RIVERA FAI-2516
 */
public class ArbolBin {
    private NodoArbol raiz;
    
    public ArbolBin(){
        this.raiz=null;
    }
    
    public boolean insertar(Object elemNuevo,Object elemPadre, char lugar){
        //inserta elemNuevo  como hijo del primer nodo encontrado en preorden
        //igual a elemPadre, como hijo izquierdo(I) o derecho (D), segun lo
        //indique el parametro 
        boolean exito=true;
        
        if (this.raiz==null){
            //si el arbol está vacio entonces lo ponemos en la raiz
        this.raiz= new NodoArbol(elemNuevo, null, null);
        }
        else{
            //si no está vacio, se busca al padre
            NodoArbol nodoPadre= obtenerNodo(this.raiz, elemPadre);
            if(nodoPadre!=null)
                if (lugar=='I'&&nodoPadre.getIzquierdo()==null)
                    //si el padre existe y no tiene HI se lo agrega
                    nodoPadre.setIzquiedo(new NodoArbol (elemNuevo,null,null));
                else{
                    if (lugar=='D'&& nodoPadre.getDerecho()==null)
                        //si el existe y no tiene HD se lo agrega
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo,null,null));
                    else
                        //si el padre no exite o ya tiene ese hijo, da error
                        exito=false;
                }
            else
                exito=false;
        }
        return exito;
    }
    private NodoArbol obtenerNodo (NodoArbol n, Object buscado){
        //Metodo PRIVADO que busca un elemento y devuelve el nodo que
        // lo contiene. Si no se encuentra buscado devuelve null
        
        NodoArbol resultado=null;
        if (n!=null)
           if (n.getElem().equals(buscado))
            //si el buscado es n, lo devuleve
            resultado=n;
           else{
               //No es el buscado, busca primero en el hijo izquierdo
               resultado= obtenerNodo (n.getIzquierdo(),buscado);
               //Si no lo encuentra en el hijo izq busca en el hijo der
               if (resultado==null)
                   resultado=obtenerNodo(n.getDerecho(),buscado);
           }
        return resultado;
    }
    
    
    public boolean esVacio(){
        boolean si=false;
        if(this.raiz==null)
            si=true;
        return si;
    }
    
    
    public void vaciar(){
        this.raiz=null;
    }
    
    
    public Lista listarPreorden(){
        Lista pre= new Lista();
        preOrdenAux(pre, this.raiz);
       return pre;
    }
    private void preOrdenAux(Lista pre, NodoArbol n){
       int pos=pre.longitud()+1;
        if(n!=null){
            pre.insertar(n.getElem(),pos);
            preOrdenAux(pre, n.getIzquierdo());
            preOrdenAux(pre,n.getDerecho());
            
        }
    }
    
    
    public Lista listarInorden(){
        Lista in= new Lista();
        inOrdenAux(in,this.raiz);
        return in;
    }
    private void inOrdenAux(Lista in, NodoArbol n){
         if(n!=null){
             inOrdenAux(in,n.getIzquierdo());
             in.insertar(n.getElem(),in.longitud()+1);
             inOrdenAux(in,n.getDerecho());
                
                
      }}
  
    
  public Lista listarPosorden(){
      Lista pos= new Lista();
      posOrdenAux(pos,this.raiz);
      return pos;
  }
  private void posOrdenAux(Lista pos,NodoArbol n){
      if(n!=null){
          posOrdenAux(pos, n.getIzquierdo());
          posOrdenAux(pos,n.getDerecho());
          pos.insertar(n.getElem(),pos.longitud()+1);
      }
  }
  

  
  public Lista listarPorNiveles(){
      Lista niv= new Lista();
     porNivelesAux(this.raiz,niv);
     return niv;
  }
   private Cola porNivelesAux(NodoArbol n, Lista ls){
       Cola q= new Cola();
       q.poner(this.raiz);
       int i=1;
       while (!q.esVacia()){
           NodoArbol actual;
           actual= (NodoArbol)q.obtenerFrente();
           q.sacar();
           ls.insertar(actual.getElem(), i);
           i++;
           if(actual.getIzquierdo()!=null)
               q.poner(actual.getIzquierdo());
           if(actual.getDerecho()!=null)
               q.poner(actual.getDerecho());
               
        }
       return q;
   }   
      

public ArbolBin clone(){
    ArbolBin clon= new ArbolBin();
    if(this.raiz==null)
        clon.raiz=null;
    else
        clon.raiz=cloneAux(this.raiz);
    return clon;
    }
private NodoArbol cloneAux(NodoArbol n){
    NodoArbol clonado;
    clonado= new NodoArbol (n.getElem(),null,null);
    if(n.getIzquierdo()!=null)
        clonado.setIzquiedo(cloneAux(n.getIzquierdo()));
    if (n.getDerecho()!=null)
        clonado.setDerecho(cloneAux(n.getDerecho()));
return clonado;
}


public Object padre(Object elem){
    Object padre;
    padre=padreAux(this.raiz,elem, null);
    return padre;
    
}    private Object padreAux (NodoArbol n, Object buscado, Object padrastro){
        Object resultado=null;
        if (n!=null)
           if (n.getElem().equals(buscado))
            resultado=padrastro;
           else{
               resultado= padreAux(n.getIzquierdo(),buscado,n.getElem());
               if (resultado==null)
                   resultado=padreAux(n.getDerecho(),buscado,n.getElem());
           }
        return resultado;
    }
    

public int nivel (Object elem){
int nivel= nivelAux(elem, this.raiz,0);
return nivel;
} 
private int nivelAux(Object elem, NodoArbol n, int nivel){
    int aux=-1;
    if (n!=null){
        if(n.getElem().equals(elem))
            aux=nivel;
        else{
            aux=nivelAux(elem,n.getIzquierdo(),nivel+1);
            if (aux==-1)
            aux=nivelAux(elem,n.getDerecho(),nivel+1);
        }
    }
    return aux;
}


public int altura(){
    int altura= alturaAux(this.raiz);
    return altura;
}
private int alturaAux (NodoArbol n){
  int alt=0;
  if(this.raiz==null)
      alt=-1;
  else{
      if(n!=null){
      if(n.getDerecho()==null&&n.getIzquierdo()==null)
          alt=0;
      else
       alt=Math.max(alturaAux(n.getIzquierdo()), alturaAux(n.getDerecho()))+1;
      }
  }
      
  return alt;
}





public String toString(){
    return toStringAux(this.raiz,"");
}
private String toStringAux(NodoArbol n, String m){
     String s=m;
    if(this.raiz==null)
     s="Arbol Binario vacio";
    else{
        if(n!=null){
         s+= "\n";
         s+= n.getElem().toString();
         if(n.getIzquierdo()!=null)
          s+= " HI: "+n.getIzquierdo().getElem();
         else
             s+=" HI: - ";
         if (n.getDerecho()!=null)
            s+= " HD: "+n.getDerecho().getElem();
         else
             s+=" HD: - ";
        
         s= toStringAux(n.getIzquierdo(),s);
         s= toStringAux(n.getDerecho(),s);

       }
    }
    return s;

}

  public Lista ObtenerAncentros(Object tipoElem){
        Lista listorti= new Lista();
            ancestrosAux(this.raiz ,tipoElem, listorti);
            return listorti;
    }
    private boolean ancestrosAux(NodoArbol n, Object elem, Lista ls){
        boolean si=false;
        if (n!= null) {
            if (n.getElem().equals(elem)) 
                //si es el elemento que busco, cambio si a true
                si=true;
            else{
                //caso contrario lo busco en sus hijos
                
                //voy a su hijo izquierdo
                  if(n.getIzquierdo()!=null)
                  si=ancestrosAux(n.getIzquierdo(),elem,ls);
                  
                  //Si no lo encontro, voy al hijo derecho
                  if(!si&&n.getDerecho()!=null)
                    si=ancestrosAux(n.getDerecho(),elem,ls);
                  
                  //si lo encontro, empiezo a insertar
                  if(si)
                     ls.insertar(n.getElem(), 1);
                 }
        }
        return si;
    }
}