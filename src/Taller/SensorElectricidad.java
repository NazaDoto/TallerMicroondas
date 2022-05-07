package Taller;
import java.awt.itemListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import java.awt.event.ItemEvent;

public class SensorElectricidad extends Thread{
    ItemListener itemListener;
    int estado;
    JTextPane timer;
    JButton[] num;
    JToggleButton botonIniciarParar;
    
    public SensorElectricidad(JTextPane timer, JButton[] num, JToggleButton botonIniciarParar){
        this.timer=timer;
        this.num=num;
        this.botonIniciarParar=botonIniciarParar;
        this.itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent){
                estado = itemEvent.getStateChange();
            }};
    }
    public void run(){

        if(this.estado==ItemEvent.SELECTED){
            encender();
        } else {
            apagar();
        }       

    }
    
    public void encender(){
        this.botonIniciarParar.setEnabled(true);
        this.timer.setText("00:00");
        for(int i=0;i<11;i++){
            this.num[i].setEnabled("true");
        }
    }

    public void apagar(){
        this.botonIniciarParar.setEnabled(false);
        this.timer.setText("");
        for(int i=0;i<11;i++){
            this.num[i].setEnabled("false");
        }
    }

   
}
