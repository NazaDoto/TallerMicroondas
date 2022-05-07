package prodconssync;

/**
 *
 * @author bfreu
 */
public class Buffer {
  private char contenido;
  private boolean disponible=false;
  public Buffer() {
  }

  public synchronized char recoger(){
    while(!disponible){
        try{
            wait();
        }catch(InterruptedException ex){}
    }
    System.out.println("Consumidor: "+contenido);
    disponible=false;
    notify();
    return contenido;
  }

  public synchronized void poner(char valor){
     while(disponible){
        try{
            wait();
        }catch(InterruptedException ex){}
    }
    contenido=valor;
    System.out.println("Productor: " +valor);
    disponible=true;
    notify();
  }
}