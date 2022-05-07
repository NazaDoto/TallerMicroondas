package Taller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class BotonIP {
    JTextPane timer;
    Timer temporizador;
    tiempoTimer lectorTiempo;
    JButton num;
    JTextArea visor;

    public BotonIP(JTextPane timer, Timer temporizador, tiempoTimer lectorTiempo, JButton num, JTextArea visor) {
        this.timer = timer;
        this.temporizador = temporizador;
        this.lectorTiempo = lectorTiempo;
        this.num = num;
        this.visor = visor;
    }

    // FUNCIONAMIENTO DEL TIMER
    public void presionarBotonIP() {
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
                            lectorTiempo.actualizarTimer(new ActionEvent(num, 10, "Reset"));
                        }
                    }
                }

            }
        }, 0, 1000);

    }

    public void pararBotonIP() {
        temporizador.cancel();
    }

}
