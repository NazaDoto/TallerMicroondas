package Taller;

import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import java.awt.event.ItemEvent;

public class SensorElectricidad extends Thread {
   
    int estado;
    JTextPane timer;
    JButton[] num;
    JToggleButton botonIniciarParar;
    ItemListener itemListener;
    JLabel visor;
    BotonIP botonIP;
    

    public SensorElectricidad(JTextPane timer, JButton[] num, JToggleButton botonIniciarParar, JLabel visor, BotonIP botonIP) {
        this.timer = timer;
        this.num = num;
        this.botonIniciarParar = botonIniciarParar;
        this.visor=visor;
        this.botonIP=botonIP;
        this.start();
    }

    public void run() {
        
        itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                estado = itemEvent.getStateChange();
                if (estado == ItemEvent.SELECTED) {
                    encender();
                    
                } else {
                    apagar();
                    System.out.println("Microondas desenchufado.");
                }
            }
        };
        
    }

    public void encender() {
        System.out.println("Microondas enchufado. Iniciando...");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Microondas encendido.");
        this.botonIniciarParar.setEnabled(true);
        this.timer.setText("00:00");
        for (int i = 0; i < 11; i++) {
            this.num[i].setEnabled(true);
        }
        

    }

    public void apagar() {
        System.out.println("Microondas desenchufado.");
        
        this.botonIniciarParar.setEnabled(false);
        this.botonIniciarParar.setSelected(false);
        botonIP.pararBotonIP(false);
        this.timer.setText("");
        for (int i = 0; i < 11; i++) {
            this.num[i].setEnabled(false);
        }
        try {
            URL url1 = getClass().getResource("recursos/negro.png");
            Icon png = new ImageIcon(url1);
            visor.setIcon(png);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
