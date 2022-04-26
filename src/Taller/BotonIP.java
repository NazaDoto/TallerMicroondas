package Taller;

public class BotonIP {
    boolean activar = false;

    public void presionarBotonIP(String s) {

        char[] tiempo = new char[s.length()];
        tiempo = s.toCharArray();

        if (s != "00:00") {

            for (int i = 4; i >= 0; i--) {
                while (Character.getNumericValue(tiempo[i]) >= 0) {
                    
                }
            }
        }
    }

}
