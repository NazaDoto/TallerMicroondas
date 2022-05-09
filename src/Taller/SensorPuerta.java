package Taller;

import java.net.URL;
import javax.swing.*;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class SensorPuerta extends Thread {
    int estado;
    boolean hayComida = false;
    JTextPane timer;
    JButton[] num;
    JToggleButton botonIniciarParar;
    JLabel visor;
    ItemListener itemListenerSensorPuerta;
    SensorElectricidad SE;
    BotonIP botonIP;
    tiempoTimer lectorTiempo;

    SensorPuerta(JTextPane timer, JButton[] num, JToggleButton botonIniciarParar, JLabel visor, SensorElectricidad SE,
            BotonIP botonIP, tiempoTimer lectorTiempo) {
        this.timer = timer;
        this.num = num;
        this.botonIniciarParar = botonIniciarParar;
        this.visor = visor;
        this.SE = SE;
        this.botonIP = botonIP;
        this.lectorTiempo = lectorTiempo;
    }

    public void run() {
        itemListenerSensorPuerta = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int estadoPuerta = itemEvent.getStateChange();
                if (estadoPuerta == ItemEvent.SELECTED) {
                    System.out.println("Puerta abierta.");
                    if (SE.estado == ItemEvent.SELECTED) {
                        abrir();
                    }
                }

                if (estadoPuerta == ItemEvent.DESELECTED) {
                    System.out.println("Puerta cerrada.");
                    if (SE.estado == ItemEvent.SELECTED) {
                        cerrar();
                    }
                }

            }
        };
    }

    public void abrir() {

        
        this.botonIniciarParar.setEnabled(false);
        
        if (this.botonIniciarParar.isSelected()==false) {
            System.out.println("Ingresar comida para calentar.");
           
            try {
                URL url1 = getClass().getResource("recursos/negro.png");
                Icon png = new ImageIcon(url1);
                visor.setIcon(png);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
         else {
           System.out.println("Retirar comida.");
            botonIniciarParar.setSelected(false);
            try {
                URL nar = getClass().getResource("recursos/naranja.png");
                Icon png = new ImageIcon(nar);
                visor.setIcon(png);
            } catch (Exception e) {
                e.printStackTrace();
            }}
            for (int i = 0; i < 11; i++) {
                this.num[i].setEnabled(false);
            }

    }

    public void cerrar() {
        if (timer.getText().compareTo("00:00")!=0) { 
            System.out.println("Comida retirada.");
            System.out.println("Ingrese comida para calentar.");
            try {
                URL url1 = getClass().getResource("recursos/negro.png");
                Icon png = new ImageIcon(url1);
                visor.setIcon(png);
            } catch (Exception e) {
                e.printStackTrace();
            }
            hayComida=false;
            lectorTiempo.actualizarTimer(new ActionEvent(num, 10, "Reset"));
            timer.setText("00:00");
        }
        else {
        hayComida = true;
        System.out.println("Comida ingresada. Determine el tiempo de cocción.");
        try {
            URL url2 = getClass().getResource("recursos/apagadoro.png");
            Icon png = new ImageIcon(url2);
            visor.setIcon(png);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        }
        for (int i = 0; i < 11; i++) {
            this.num[i].setEnabled(true);
        }
        this.botonIniciarParar.setEnabled(true);
    }
}
