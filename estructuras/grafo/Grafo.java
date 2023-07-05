/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.conjuntistas;
import universidad.estructuras.dinamica.Lista;

/**
 *
 * @author male_
 */
public class Grafo {
    private NodoVert inicio;
    
    
    
    
    public boolean insertarVertice (Object nuevoVertice){
        boolean exito=false;
        NodoVert aux= this.ubicarVertice(nuevoVertice);
        
        if(aux==null){
            this.inicio=new NodoVert(nuevoVertice,this.inicio);
            exito=true;
        }
        return exito;
    }
    private NodoVert ubicarVertice (Object buscado){
        NodoVert aux= this.inicio;
        while(aux!=null && !aux.getElem().equals(buscado)){
            aux=aux.getSigVertice();
        }
        return aux;
    }
    
    
    
    public Lista listarEnProfundidad(){
        Lista visitados= new Lista();
        
        //define un vertice donde comenzar a recorrer
        NodoVert aux= this.inicio;
        while(aux!=null){
            if(visitados.localizar(aux.getElem())<0){
                //si el vertice no fue visitado aun, avanza en profundidad
                listarEnProfundidadAux(aux, visitados);
            }
            aux=aux.getSigVertice();
        }
        return visitados;
    }
    private void listarEnProfundidadAux(NodoVert n, Lista vis){
        if(n!=null ){
            //marca al vertice n como visitado
            vis.insertar(n.getElem(), vis.longitud()+1);
            NodoAdy ady= n.getPrimerAdy();
            while(ady!=null){
                //visita en profundidad los adyacentes de n aun no visitados
                if(vis.localizar(ady.getVertice().getElem())<0){
                    listarEnProfundidadAux(ady.getVertice(),vis);
                }
                ady=ady.getSigAdyacente();
            }
        }
    }
    
    
    public boolean eliminarVertice(Object verticeAEliminar){
        //metodo que elimina el verticeAEliminar
        //devuelve false unicamente en caso de que el vertice no exista
        
        boolean exito=false;
        if(this.existeVerticeAux(verticeAEliminar)){
            //si existe el vertice que quiero eliminar
            NodoVert verticeQueElimino= this.ubicarVertice(verticeAEliminar);
            exito= eliminarVerticeAux(verticeQueElimino);
         }
        return exito;
    }
    private boolean eliminarVerticeAux(NodoVert verticeAEliminar){
        
         NodoVert verticeQueElimino= this.ubicarVertice(verticeAEliminar);
        
            //primero, elimino todos los arcos
            NodoAdy primerAdy= verticeQueElimino.getPrimerAdy();
            
            while(primerAdy!=null){
                //por cada nodoAdyacente del vertice que voy a eliminar, lo desconecto
                //para eliminar el arco elimino como
                //origen= el vertice apuntado en el primer adyacente
                //destino= el vertice que voy a eliminar
                
                eliminarArcoAux(primerAdy.getVertice(), verticeQueElimino);
                
                //luego solo cambio el primer nodo por el segundo y asi hasta que no hayan mas adyacentes.
                verticeQueElimino.setPrimerAdy(primerAdy.getSigAdyacente());
                primerAdy=verticeQueElimino.getPrimerAdy();
            }
            
            //y ahora elimino el vertice
            
            //si el vertice que elimino era el primero entonces
            if(this.inicio==verticeQueElimino)
                this.inicio=verticeQueElimino.getSigVertice();
            else{
                //si no era el primero busco a su padre
                NodoVert padreVerticeQueElimino=this.inicio;
                while(padreVerticeQueElimino.getSigVertice()!=verticeQueElimino){
                    padreVerticeQueElimino=padreVerticeQueElimino.getSigVertice();
                }
                //una vez que lo encuentra entonces seteo ese enlace
                padreVerticeQueElimino.setSigVert(verticeQueElimino.getSigVertice());
            }
       return true;
    }
    
    
    public boolean eliminarArco(Object origen, Object destino){
        //Metodo que elimina un arco entre dos nodos
        //Primero verifica la existencia de ambos nodos
       boolean exito=false;
       
       NodoVert nodoOrigen=this.ubicarVertice(origen);
       NodoVert nodoDestino=this.ubicarVertice(destino);
       
       if(nodoOrigen!=null && nodoDestino!=null){
           if(this.existeArcoAux(nodoOrigen, nodoDestino)){
                 this.eliminarArcoAux(nodoOrigen, destino);
                 this.eliminarArcoAux(nodoDestino, origen);
                 exito=true;
           }
       }
     return exito;
    }
    
    private void eliminarArcoAux(NodoVert origen, Object destino){
        //metodo privado que uso para desconectar un arco desde el nodoOrigen al nodoDestino
        //previamente se debe haber verificado la existencia del arco
        
        NodoAdy primerAdyOrigen=origen.getPrimerAdy(); //
        NodoAdy padrePrimerAdyOrigen=null;
        
        while(!(primerAdyOrigen.getVertice().getElem().equals(destino))){
            padrePrimerAdyOrigen=primerAdyOrigen;
            primerAdyOrigen = primerAdyOrigen.getSigAdyacente();
        }
        
        //una vez que encuentra la conexion de origen y destino, la elimina
        //si el padre es nulo entonces era el primer adyacente
        if(padrePrimerAdyOrigen==null)
            //si el primer adyacente no tenia otro adyacente entonces el enlace me quedaria en nulo
            origen.setPrimerAdy(primerAdyOrigen.getSigAdyacente());
        else
            //lo mismo en este caso
            padrePrimerAdyOrigen.setSigAdyacente(primerAdyOrigen.getSigAdyacente());
   }
   
    
  public boolean insertarArco(Object origen, Object destino, Object etiqueta){
      //Metodo que inserta un arco entre dos nodos.
      //Devuelve false unicamente en los casos de que ya exista un arco que ya una esos nodos
      //o que alguno de los nodos no exista
      boolean exito=false;
      
      NodoVert nodoOrigen, nodoDestino;
      //si ambos nodos existen entonces
      if(this.existeVertice(origen) && this.existeVertice(destino)){
            nodoOrigen=this.ubicarVertice(origen);
            nodoDestino=this.ubicarVertice(destino);
            
            exito=this.insertarArcoAux(nodoOrigen, nodoDestino, etiqueta);
        }
      
  return exito;
  }
  private boolean insertarArcoAux(NodoVert origen, NodoVert destino,Object etiqueta ){
      //Metodo privado que inseta un arco dos nodos
      //Devuelve true unicamente si no existe previamente un arco entre ambos
      boolean exito=false;
      
      //verifico que no haya un arco entre ambos
      if(!existeArcoAux(origen,destino)){
          //primero lo inserto al final del nodo origen, junto con la etiqueta
          NodoAdy temp= origen.getPrimerAdy();
          while(temp.getSigAdyacente()!=null){
              temp=temp.getSigAdyacente();
          }
          temp.setSigAdyacente(new NodoAdy(destino, null, etiqueta));
          
          
          //y ahora hago lo mismo con el destino
          temp= destino.getPrimerAdy();
          while(temp.getSigAdyacente()!=null){
              temp=temp.getSigAdyacente();
          }
          temp.setSigAdyacente(new NodoAdy(origen, null, etiqueta));
          
          exito=true;
       }
    return exito;
  }
  
  
  public boolean existeArco(Object origen, Object destino){
      //metodo que verifica la existencia de un arco entre ambos nodos pasados por parametro
      //devuelve true unicamente si existe un arco
      //esto lo hace verificando si alguno de los vertices de los nodos adyacentes de origen son igual al destino
      
      
      boolean encontrado=false;
      
      //primero verifico que ambos sean vertices en mi grafo
      NodoVert nodoOrigen= this.ubicarVertice(origen);
      NodoVert nodoDestino=this.ubicarVertice(destino);
      
      if(nodoOrigen!=null && nodoDestino!=null){
          encontrado=existeArcoAux(nodoOrigen, nodoDestino);
      }
      return encontrado;
  }
private boolean existeArcoAux(NodoVert origen, NodoVert destino){
        //Metodo privado que busca la existencia de un arco entre dos nodos
        //esto lo hace fijandose en la lista de adyacencia del nodoOrigen
        //y se fija si alguno de esos nodos adyacentes apunta al nodoDestino
        //sino, devuelve falso
        boolean encontrado=false;
        
        NodoAdy adyOrigen= origen.getPrimerAdy();
      
        while (adyOrigen != null&&!encontrado) {
             //si lo encuentra entonces
                if (adyOrigen.getVertice() == destino) 
                     encontrado=true;
             adyOrigen = adyOrigen.getSigAdyacente();
          }
      
    return encontrado;
}

 
public boolean existeVertice(Object verticeABuscar){
    return existeVerticeAux(verticeABuscar);
}
private boolean existeVerticeAux(Object verticeABuscar){
      //Metodo  que verifica si existe un vertice
      //Solo devolvera true si existe el vertice
      //Esto lo hace usando el metodo ubicarVertice, si el vertice devuelvo es nulo, entoces no existia
    boolean exito=false;
    
    NodoVert vertice= this.ubicarVertice(verticeABuscar);
    if(vertice!=null)
        exito=true;
    
    return exito;
}
        
      

public boolean existeCamino(Object origen, Object destino){
    boolean exito=false;
    //verifica si ambos vertices existen
    
    NodoVert auxOrigen=null;
    NodoVert auxDestino=null;
    NodoVert auxInicio=this.inicio;
    
    while((auxOrigen==null || auxDestino==null) && auxInicio!=null){
        if(auxInicio.getElem().equals(origen)) auxOrigen=auxInicio;
        if(auxInicio.getElem().equals(destino)) auxDestino=auxInicio;
        auxInicio=auxInicio.getSigVertice();
    }
    
    if(auxOrigen!=null && auxDestino!=null){
       //si ambos vertices existen busca si existe camino entre ambos
       Lista visitados=new Lista();
       exito=existeCaminoAux(auxOrigen,destino,visitados);
    }
  return exito;  
}
private boolean existeCaminoAux(NodoVert n, Object dest, Lista vis){
    boolean exito=false;
    if(n!=null){
        //si vertice n es el destino entonces hay camino
        if(n.getElem().equals(dest))
            exito=true;
        else{
            //sino es el destino verifica si hay un camino entre n y destino
            vis.insertar(n.getElem(), vis.longitud()+1);
            NodoAdy ady= n.getPrimerAdy();
            while(!exito && ady !=null){
                if(vis.localizar(ady.getVertice().getElem())<0){
                    exito=existeCaminoAux(ady.getVertice(),dest,vis);
                }
              ady=ady.getSigAdyacente();
            }
             }
    }
     return exito;        
}
 

public boolean esVacio(){
    boolean exito=false;
    if(this.inicio==null){
        exito=true;
    }
    return exito;
}
}

        
    
    
    
    
    
