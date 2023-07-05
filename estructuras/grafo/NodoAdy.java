/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.conjuntistas;

/**
 *
 * @author male_
 */
public class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object tipoEtiqueta;
    
    
   public NodoAdy(NodoVert vert, NodoAdy nodo, Object etiq){
      this.vertice = vert;
      this.sigAdyacente = nodo;
      this.tipoEtiqueta = etiq;
    }

    public NodoVert getVertice(){
        return this.vertice;
    }
    
    public void setVertice(NodoVert vertice){
        this.vertice=vertice;
    }
    
    public Object getEtiqueta(){
        return this.tipoEtiqueta;
    }
    
    public void setEtiqueta (Object tipoEtiqueta){
        this.tipoEtiqueta=tipoEtiqueta;
    }
    
    
    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }
    
    public void setSigAdyacente(NodoAdy sigAdyacente){
        this.sigAdyacente=sigAdyacente;
    }

    
}
