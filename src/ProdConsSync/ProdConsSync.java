/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodconssync;

/**
 *
 * @author bfreu
 */
public class ProdConsSync {

    /**
     * @param args the command line arguments
     */
  public static void main(String[] args) {
    Buffer b=new Buffer();
    Productor p=new Productor(b);
    Consumidor c=new Consumidor(b);
    p.start();
    c.start();
  }
    
}
