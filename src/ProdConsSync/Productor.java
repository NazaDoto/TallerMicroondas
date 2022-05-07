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

public class Productor extends Thread {
  private Buffer buffer;
  private final String letras="abcdefghijklmnopqrstuvxyz";
  public Productor(Buffer buffer) {
    this.buffer=buffer;
  }
    public void run() {
        for(int i=0; i<10; i++){
            char c=letras.charAt((int)(Math.random()*letras.length()));
            buffer.poner(c);
            try {
                sleep(400);
            } catch (InterruptedException e) { }
        }
    }
}
