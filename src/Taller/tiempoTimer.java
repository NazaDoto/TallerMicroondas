package Taller;

import java.awt.event.ActionEvent;

public class tiempoTimer {
    String[] reloj = { "0", "0", ":", "0", "0" };
    int posicion = 0;

    public String actualizarTimer(ActionEvent e) {

        if (e.getActionCommand() != "Reset") {
            switch (posicion) {
                case 0:
                    if (e.getActionCommand() != "0") {
                        reloj[4] = e.getActionCommand();
                        posicion++;
                    }
                    break;
                case 1:
                    reloj[3] = reloj[4];
                    reloj[4] = e.getActionCommand();
                    posicion = posicion + 2;
                    break;
                case 2:
                    reloj[2] = ":";
                    break;
                case 3:
                    reloj[1] = reloj[3];
                    reloj[3] = reloj[4];
                    reloj[4] = e.getActionCommand();

                    posicion++;
                    break;
                case 4:
                    if (Integer.parseInt(reloj[4]) > 5) {

                        if (Integer.parseInt(reloj[3]) == 9) {
                            if (Integer.parseInt(reloj[1]) == 9) {
                                reloj[0] = reloj[1];
                            } else {
                               reloj[0] = String.valueOf((Integer.parseInt(reloj[1]) + 1));
                                reloj[1] = "0";
                            }

                        } else {
                            reloj[0]=reloj[1];
                            reloj[1] = String.valueOf((Integer.parseInt(reloj[1]) + 1));
                        }

                        reloj[3] = "5";
                        reloj[4] = "9";
                    } else {
                        reloj[0] = reloj[1];
                        reloj[1] = reloj[3];
                        reloj[3] = reloj[4];
                        reloj[4] = e.getActionCommand();
                    }
                    posicion++;
                    break;
            }
        } else {
            reloj[4] = "0";

            reloj[3] = "0";

            reloj[2] = ":";

            reloj[1] = "0";

            reloj[0] = "0";
            posicion = 0;
        }

        String tiempo = (reloj[0] + reloj[1] + reloj[2] + reloj[3] + reloj[4]);
        return tiempo;
    }

}
