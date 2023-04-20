/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.arbolGen;

import universidad.estructuras.arbolGen.NodoGen;
import lineales.dinamica.Cola;
import lineales.dinamica.Lista;

/**
 *
 * @author MALENA RIVERA FAI-2516
 */
public class ArbolGen {
    private NodoGen raiz;
    
    public ArbolGen(){
        this.raiz=null;
    }
public boolean insertar (Object nuevo, Object padre){
    boolean exito=true;
    if (this.raiz==null)
        this.raiz = new NodoGen (nuevo, null, null);
    else{
        NodoGen nodoPadre= obtenerNodo (this.raiz, padre);
        if (nodoPadre!=null){
            if(nodoPadre.getHijoIzquierdo()==null)
                nodoPadre.setHijoIzquierdo(new NodoGen (nuevo,null,null));
            else{
                NodoGen aux=nodoPadre.getHijoIzquierdo(),her= null;
                while (aux!=null){
                    her=aux;
                    aux=aux.getHermanoDerecho();
                }
                her.setHermanoDerecho(new NodoGen (nuevo,null,null));
            }
            
        }else
            exito=false;
}
    return exito;
}
    private NodoGen obtenerNodo(NodoGen n, Object buscado) {
        //Metodo PRIVADO que busca un elemento y devuelve el nodo que
        // lo contiene. Si no se encuentra buscado devuelve null
        NodoGen resultado = null, hijito;
        if (n != null) {
            if (n.getElem().equals(buscado))//si el buscado es n, lo devuleve
                resultado = n;
             else {
                if (n.getHijoIzquierdo() != null) { 
                  //No es el buscado, busca primero en el hijo izquierdo
                    resultado = obtenerNodo(n.getHijoIzquierdo(), buscado);
                    //Si no lo encuentra en el hijo izq busca en los hermanos
                    if (resultado == null) {
                        hijito = n.getHijoIzquierdo().getHermanoDerecho();
                        while (hijito != null&resultado==null) {
                            resultado = obtenerNodo(hijito, buscado);
                            hijito = hijito.getHermanoDerecho();

                        }
                    }
                }
            }
        }    return resultado;
    }   
    
    //LISTADOS
    
    public Lista listarPreorden() {
        Lista salida = new Lista();
        listarPreOrdenAux(this.raiz, salida);
        return salida;
    }
    private void listarPreOrdenAux(NodoGen n, Lista ls) {
        if (n != null) {
            //inserta la raiz
            ls.insertar(n.getElem(), ls.longitud() + 1);
            //"Voy primero al hijo izquierdo"
            if (n.getHijoIzquierdo() != null) {
                listarPreOrdenAux(n.getHijoIzquierdo(), ls);
            
            NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
            while (hijo != null) {
                listarPreOrdenAux(hijo, ls);
                hijo = hijo.getHermanoDerecho();
            }
        }
        }
    }
    
    public Lista listarInorden() {
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }
    private void listarInordenAux(NodoGen n, Lista lista) {
        if (n != null) {
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), lista);
            }
            lista.insertar(n.getElem(), lista.longitud() + 1);
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, lista);
                    hijo = hijo.getHermanoDerecho();
                }

            }
        }
    }
    
    public Lista listarPosorden() {
        Lista salida = new Lista();
        listarPosOrdenAux(this.raiz, salida);
        return salida;
    }
    private void listarPosOrdenAux(NodoGen n, Lista ls) {
        if (n != null) {
            if (n.getHijoIzquierdo() != null) {
                listarPosOrdenAux(n.getHijoIzquierdo(), ls);
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarPosOrdenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
                
            }ls.insertar(n.getElem(), ls.longitud() + 1);
        }
    }
   
  
    public Lista listarPorNiveles() {
        Lista niv = new Lista();
        porNivelesAux(this.raiz, niv);
        return niv;
    }
    private Cola porNivelesAux(NodoGen n, Lista ls) {
       Cola q= new Cola();
       q.poner(this.raiz);
       while (q.esVacia()==false){
           NodoGen actual;
           actual= (NodoGen)q.obtenerFrente();
           q.sacar();
              System.out.println(actual.getElem());
           if(actual.getHijoIzquierdo()!=null){
               q.poner(actual.getHijoIzquierdo());
               NodoGen her=n.getHijoIzquierdo().getHermanoDerecho();
               while (her!=null){
                 q.poner(actual.getHermanoDerecho());
                 her=her.getHermanoDerecho();
               }
               
        }
       }
       return q;
   }   
    
    
    public Object padre(Object elem) {
        Object padre;
        padre = padreAux(this.raiz, elem, null);
        return padre;
    }
    private Object padreAux(NodoGen n, Object buscado, Object padrastro) {
        //Metodo que devuelve el padre de un nodo
        //en caso de arbol vacio, de buscar el padre de raiz, o que no se encuentre devuelve null
        Object resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                resultado = padrastro;
            } else {
                //va al hijo izquierdo
                if (n.getHijoIzquierdo() != null) {
                    resultado = padreAux(n.getHijoIzquierdo(), buscado, n.getElem());
                    //Si no era sus hijo izquierdo, se fija en los hermanos de este
                    if (resultado == null) {
                        NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                        while (hijo != null&&resultado==null) {
                            resultado = padreAux(hijo, buscado, n.getElem());
                            hijo = hijo.getHermanoDerecho();
                        }
                    }
                }
            }
        }

            return resultado;
        }
    

   public boolean esVacio(){
       boolean si=true;
       if(this.raiz==null)
           si=false;
       return si;
   }
   
   public void vaciar(){
       this.raiz=null;
   }
   
   
public ArbolGen clone(){
    ArbolGen clon= new ArbolGen();
    if(this.raiz==null)
        clon.raiz=null;
    else
        clon.raiz=cloneAux(this.raiz);
    return clon;
    }
private NodoGen cloneAux(NodoGen n){
    NodoGen clonado;
    clonado= new NodoGen (n.getElem(),null,null);
    if(n.getHijoIzquierdo()!=null)
        clonado.setHijoIzquierdo(cloneAux(n.getHijoIzquierdo()));
    if (n.getHermanoDerecho()!=null)
        clonado.setHermanoDerecho(cloneAux(n.getHermanoDerecho()));
return clonado;
}


    public int nivel(Object elem) {
        int nivel = nivelAux(elem, this.raiz, 0);
        return nivel;
    }
    private int nivelAux(Object elem, NodoGen n, int nivel) {
        int aux = -1;
        if (n != null) {
            if (n.getElem().equals(elem))
                aux = nivel;
             else {
                aux = nivelAux(elem, n.getHijoIzquierdo(), nivel + 1);
                if (aux == -1) {
                    NodoGen hijo = n.getHermanoDerecho();
                    while (hijo != null) {
                        aux = nivelAux(elem, n.getHermanoDerecho(), nivel);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
             }   
        }
            return aux;
    
    }
    
    public boolean pertenece (Object tipoElem){
        boolean si;
        si=perteneceAux(this.raiz,tipoElem);
        return si;
    }
    private boolean perteneceAux(NodoGen n, Object tipoElem) {
        //Metodo booleano que busca en preOrden si un elemento pertenece al arbol
        //En caso de arbol vacio o de no pertenecer devuelve false
        boolean si = false;
        if (n != null) {
            //se fija si el elemento que lo llamo es igual al que busca
            if(n.getElem().equals(tipoElem))
                si=true;
            if (n.getHijoIzquierdo() != null) {
                //si no era ese, busca es su hijo izquierdo
                si=perteneceAux(n.getHijoIzquierdo(),tipoElem);
                //Si tampoco era su hijo izquierdo, busca en los hermanos de este
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null&&!si) {
                    si=perteneceAux(hijo, tipoElem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return si;
    }
    
    public int altura (){
        int altura;
        altura=alturaAux(this.raiz);
        return altura;
    }
    private int alturaAux(NodoGen n){
        int altura=0;
        if(this.raiz==null)
            altura=-1;
        else{
            if(n!=null){
                if(n.getHijoIzquierdo()==null)
                    altura=0;
                else
                altura=Math.max(alturaAux(n.getHijoIzquierdo()), alturaAux(n.getHermanoDerecho()))+1;
               
                }
            
        }
        return altura;
    }
    
    public Lista ancentros(Object tipoElem){
        Lista listorti= new Lista();
            ancestrosAux(this.raiz ,tipoElem, listorti);
            return listorti;
    }
    private boolean ancestrosAux(NodoGen n, Object elem, Lista ls){
        boolean si=false;
        if (n!= null) {
            if (n.getElem().equals(elem)) {
                si=true;
            } else {
                   NodoGen aux=n.getHijoIzquierdo();
                   while (aux!=null&!si){
                       si=ancestrosAux(aux,elem,ls);
                       aux=aux.getHermanoDerecho();
                   }
                       
                if (si) 
                    ls.insertar(n.getElem(),ls.longitud()+1);
                
            }
 
        }
        return si;
    }
  
    
   public String toString(){
       return toStringAux(this.raiz);
   }
   private String toStringAux(NodoGen n){
       String cadena="";
       if(n!=null){
           cadena+=n.getElem().toString()+ "-->";
           NodoGen hijo= n.getHijoIzquierdo();
           while (hijo!=null){
               cadena+=hijo.getElem().toString()+",";
               hijo=hijo.getHermanoDerecho();
           }
           hijo=n.getHijoIzquierdo();
           while(hijo!=null){
               cadena+="\n"+toStringAux(hijo);
               hijo=hijo.getHermanoDerecho();
           }
       }
       return cadena;
   }
   
}
