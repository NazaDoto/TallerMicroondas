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
public class Consumidor extends Thread {
  private Buffer buffer;
  public Consumidor(Buffer buffer) {
    this.buffer=buffer;
  }
  public void run(){
    char valor;
    for(int i=0; i<10; i++){
        valor=buffer.recoger();
        try{
            sleep(400);
        }catch (InterruptedException e) { }
    }
  }
}

