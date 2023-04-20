/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.estructuras.conjuntistas;

/**
 *
 * @author MALENA RIVERA FAI-2516
 */
public class Heap {
    private final int TAMANIO=20;
    private Comparable [] heap;
    private int ultimo;
    
    public Heap(){
        this.heap= new Comparable[TAMANIO];
        this.ultimo=0;
        
    }
     public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0 ) {
            exito = false;
        } else {
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }
      private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;
        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                // temp tiene al menos un hijo (izq) y lo considera menor
                if (posH < this.ultimo) {
                    // hijo menor tiene hermano derecho
                    // el hijo derecho es menor que los dos
                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0)
                        posH++;
                }
                // compara al hijo menor con el padre
                if (this.heap[posH].compareTo(temp) < 0) {
                    // el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    // el padre es menor que sus hijos, está bien ubicado
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }
    
    
    
    public Comparable recuperarCima(){
      return this.heap[1];
     }
     
     public boolean esVacio(){
         return this.ultimo==0;
     }
     
    
     public void vaciar(){
         for (int i=1; i<=this.ultimo;i++){
             this.heap[i]=null;
         }
     }
     
     public boolean insertar(Comparable elem){
        boolean exito = true;
        if (this.esVacio()) {
            //si el arbol estaba vacio inserto el elemento como raiz
            this.heap[this.ultimo + 1] = elem;
            this.ultimo++;
        } else {
            //Si hay espacio en el arreglo lo inserto en el ultimo nivel y lo acomodo con el hacerSubir
            if (this.ultimo < TAMANIO) {
                this.heap[this.ultimo + 1] = elem;
                this.ultimo++;
                //calculo a su padre haciendo la division entera de 2
                int padre = this.ultimo / 2;
                hacerSubir(padre);
            } else 
                exito = false;
            
        }
        return exito;
    }
     private void hacerSubir (int padre){
        int posH = this.ultimo;
        Comparable temp=this.heap[padre];
        boolean listo=false;
        while(!listo){
            //si el hijo es menor que su padre los intercambia
            if(padre >=1 && this.heap[posH].compareTo(this.heap[padre])<0){
                this.heap[padre]= this.heap[posH];
                this.heap[posH]=temp;
                posH=padre;
                padre=padre/2;
                temp=this.heap[padre];
            }else{
                //cuando llega a la raiz ó el elemento ya esta bien ordenado corta la ejecucion
                listo = true;
            }
        }
     }
    
     
    public Heap clone(){
         Heap clon = new Heap();
         if(this.ultimo==0)
             clon.ultimo=0;
         else{
             for(int i=1;i<=this.ultimo;i++){
                 clon.heap[i]=this.heap[i];
                 clon.ultimo++;
             }
         }
         return clon;
    }
     
     
     public String toString(){
       String s="";
       if(this.ultimo==0)
             s="Arbol vacio";
       else{
           for(int i=1; i<=this.ultimo;i++){
               s+=this.heap[i]+":";
               if(this.heap[i*2]!=null&& (i*2)<=this.ultimo)
                 s+="  HI--> "+this.heap[(i*2)];
               else
                   s+="  HI--> -";
               if(this.heap[(i*2)+1]!=null&&((i*2)+1)<=this.ultimo)   
                 s+="  HD--> "+this.heap[(i*2)+1];
               else
                   s+= "  HD--> -";
               s+="\n";
            }   
         
        }
     return s;
     }
}


    
    

