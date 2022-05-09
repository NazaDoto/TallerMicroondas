package Taller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JTextPane;
import javax.swing.JToggleButton;

public class BotonIP extends Thread {
    JTextPane timer;
    Timer temporizador;
    tiempoTimer lectorTiempo;
    JButton[] num;
    JLabel visor;
    JToggleButton botonIniciarParar;
    SensorPuerta SP;
   

    public BotonIP(JTextPane timer, Timer temporizador, tiempoTimer lectorTiempo, JButton[] num, JLabel visor,
            JToggleButton botonIniciarParar) {
        this.timer = timer;
        this.temporizador = temporizador;
        this.lectorTiempo = lectorTiempo;
        this.num = num;
        this.visor = visor;
        this.botonIniciarParar = botonIniciarParar;
        
        this.start();
    }

    public void run() {

        while (true) {
            try {
                if (timer.getText().compareTo("00:00") == 0 || timer.getText().compareTo("0:0") == 0
                        || timer.getText().compareTo("") == 0 || timer.getText() == null) {
                    botonIniciarParar.setEnabled(false);

                    
                } else {
                    botonIniciarParar.setEnabled(true);
                   
                }
            } catch (NullPointerException e) {

            }
        }
    }

    // FUNCIONAMIENTO DEL TIMER
    public void presionarBotonIP(Boolean hayComida) {
        System.out.println("Boton de inicio presionado.");
        for(int i=0;i<11;i++){
            num[i].setEnabled(false);
        }
        if(hayComida){
            System.out.println("Calentando comida...");
            try {
                URL url = getClass().getResource("dorito3.gif");
                Icon gif = new ImageIcon(url);
                visor.setIcon(gif);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se detectó comida en el microondas. Calentando de todas formas...");
            try {
                URL url = getClass().getResource("recursos/naranja.png");
                Icon gif = new ImageIcon(url);
                visor.setIcon(gif);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

        String[] cortada = timer.getText().split(":");
        String cortada1 = cortada[0];
        String cortada2 = cortada[1];

        temporizador = new Timer();
        temporizador.scheduleAtFixedRate(new TimerTask() {
            int j = Integer.parseInt(cortada1);
            int k = Integer.parseInt(cortada2);

            public void run() {
                visor.setBackground(Color.orange);

                timer.setText(j + ":" + k);
                if (j > 0) {
                    if (k > 0 && k < 60) {
                        k--;
                    } else {
                        k = 59;
                        j--;
                    }

                } else {
                    if (k > 0 && k < 60) {
                        k--;
                    } else {
                        if (k == 0) {
                            temporizador.cancel();
                            visor.setBackground(Color.black);
                            timer.setText("00:00");
                            botonIniciarParar.setSelected(false);
                            System.out.println("Cocción finalizada. Retire la comida.");                            
                            lectorTiempo.actualizarTimer(new ActionEvent(num[10], 10, "Reset"));
                        }
                    }
                }

            }
        }, 0, 1000);

    }

    public void pararBotonIP(Boolean hayComida) {
        System.out.println("Microondas pausado.");
        if (hayComida){
            
        try {
            URL url2 = getClass().getResource("doritoStatic.png");
            Icon png = new ImageIcon(url2);
            visor.setIcon(png);
        } catch (Exception e) {
            e.printStackTrace();
        }} else{
            try {
                URL url2 = getClass().getResource("recursos/naranja.png");
                Icon png = new ImageIcon(url2);
                visor.setIcon(png);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        temporizador.cancel();
    }

}
