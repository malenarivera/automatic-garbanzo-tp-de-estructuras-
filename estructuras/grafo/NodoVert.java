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
public class NodoVert {
    private Object tipoVertice;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;
    
     //CONSTRUCTORES
       public NodoVert(Object tipoVertice, NodoVert nodoV){
        this.tipoVertice = tipoVertice;
        this.sigVertice = nodoV;
        this.primerAdy = null;
    }
    
    
    
    public Object getElem(){
        return this.tipoVertice;
    }
    public void setElem(Object tipoVertice){
        this.tipoVertice=tipoVertice;
    }
    
    public NodoVert getSigVertice(){
        return this.sigVertice;
    }
    public void setSigVert (NodoVert sigVertice){
        this.sigVertice=sigVertice;
    }
    
    public NodoAdy getPrimerAdy(){
        return this.primerAdy;
    }
    public void setPrimerAdy (NodoAdy primerAdy){
        this.primerAdy=primerAdy;
    }
    
    
    
    
}
