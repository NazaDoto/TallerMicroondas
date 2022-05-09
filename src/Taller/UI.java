package Taller;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Timer;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import javax.swing.JLabel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class UI {
    public void interfaz() {

        JFrame ventana = new JFrame("Microondas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 500);
        ventana.setResizable(false);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);
        URL iconURL = getClass().getResource("asd.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        ventana.setIconImage(icon.getImage());

        // VISOR
        JLabel visor = new JLabel();

        visor.setBounds(20, 20, 550, 330);
        visor.setBackground(Color.black);
        try {
            URL url = getClass().getResource("recursos/negro.png");
            Icon gif = new ImageIcon(url);
            visor.setIcon(gif);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TIMER
        JTextPane timer = new JTextPane();
        StyledDocument centrado = timer.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        centrado.setParagraphAttributes(0, centrado.getLength(), center, false);
        timer.setFont(new Font("Consolas", Font.BOLD, 30));
        timer.setForeground(Color.green);
        timer.setBackground(Color.black);
        timer.setText("");
        timer.setEditable(false);
        timer.setBounds(585, 20, 180, 40);

        // TECLADO NUMERICO
        JButton[] num = new JButton[11];
        num[0] = new JButton("0");
        num[0].setBounds(645, 170, 60, 30);
        num[1] = new JButton("1");
        num[1].setBounds(585, 140, 60, 30);
        num[2] = new JButton("2");
        num[2].setBounds(645, 140, 60, 30);
        num[3] = new JButton("3");
        num[3].setBounds(705, 140, 60, 30);
        num[4] = new JButton("4");
        num[4].setBounds(585, 110, 60, 30);
        num[5] = new JButton("5");
        num[5].setBounds(645, 110, 60, 30);
        num[6] = new JButton("6");
        num[6].setBounds(705, 110, 60, 30);
        num[7] = new JButton("7");
        num[7].setBounds(585, 80, 60, 30);
        num[8] = new JButton("8");
        num[8].setBounds(645, 80, 60, 30);
        num[9] = new JButton("9");
        num[9].setBounds(705, 80, 60, 30);
        num[10] = new JButton("Reset");
        num[10].setFont(new Font("Consolas", Font.BOLD, 9));
        num[10].setBounds(705, 170, 60, 30);
        JToggleButton botonIniciarParar = new JToggleButton("Iniciar / Parar");
        botonIniciarParar.setBounds(620, 220, 120, 30);
        botonIniciarParar.setEnabled(false);
        botonIniciarParar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tiempoTimer lectorTiempo = new tiempoTimer();
        Timer temporizador = new Timer();
        
        
        for (int i = 0; i < 11; i++) {
            num[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            num[i].setEnabled(false);
            num[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand() != "0") {
                        botonIniciarParar.setEnabled(true);
                    }
                    timer.setText(lectorTiempo.actualizarTimer(e));
                }
            });
        }

        // BOTON INICIAR
      
        BotonIP botonIP = new BotonIP(timer, temporizador, lectorTiempo, num, visor, botonIniciarParar);
        

        // ELECTRICIDAD
        JToggleButton sensorElectricidad = new JToggleButton("🔌");
        sensorElectricidad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sensorElectricidad.setBounds(20, 430, 50, 20);
        SensorElectricidad SE = new SensorElectricidad(timer, num, botonIniciarParar, visor, botonIP);

       // SE.start();
        ItemListener itemListenerElectricidad = SE.itemListener;
        while (SE.isAlive()) {
            itemListenerElectricidad = SE.itemListener;
        }

        // BOTON ABRIR/CERRAR
        JToggleButton sensorPuerta = new JToggleButton();
        sensorPuerta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sensorPuerta.setBounds(585, 365, 180, 80);
        SensorPuerta SP = new SensorPuerta(timer, num, botonIniciarParar, visor, SE, botonIP, lectorTiempo);
        SP.start();
        ItemListener itemListenerSensorPuerta = SP.itemListenerSensorPuerta;
        while (SP.isAlive()){
            itemListenerSensorPuerta = SP.itemListenerSensorPuerta;
          
        }
        ItemListener itemIP = new ItemListener() {

            public void itemStateChanged(ItemEvent itemEvent) {
                int estadoPuerta = itemEvent.getStateChange();
                if (estadoPuerta == ItemEvent.SELECTED) {                    
                    botonIP.presionarBotonIP(SP.hayComida);
                } else {
                    habilitarNum(num);
                    botonIP.pararBotonIP(SP.hayComida);
                }
            }
        };

        botonIniciarParar.addItemListener(itemIP);
        sensorPuerta.addItemListener(itemListenerSensorPuerta);
        sensorElectricidad.addItemListener(itemListenerElectricidad);
        ventana.add(sensorElectricidad);
        ventana.add(sensorPuerta);
        ventana.add(timer);
        ventana.add(visor);

        // ventana.getContentPane().add(visor);
        for (int i = 0; i < 11; i++) {
            ventana.add(num[i]);
        }

        ventana.add(botonIniciarParar);

        ventana.setVisible(true);
    }

    public void deshabilitarNum(JButton[] num) {
        for (int i = 0; i < 11; i++) {
            num[i].setEnabled(false);
        }
    }

    public void habilitarNum(JButton[] num) {
        for (int i = 0; i < 11; i++) {
            num[i].setEnabled(true);
        }
    }
}
