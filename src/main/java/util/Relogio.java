
package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;
import javax.swing.Timer;

public class Relogio {
    
    private int horas;
    private int minutos;
    private int segundos;
    
    public Relogio(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormatada = time.format(formatter);
        String[] vectHoras = horaFormatada.split(":");
        this.horas = Integer.parseInt(vectHoras[0]);
        this.minutos = Integer.parseInt(vectHoras[1]);
        this.segundos = 0;
    }
public void iniciar() {
        Thread threadRelogio = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    atualizarTempo();
                    
                    try {
                        Thread.sleep(1000); // Aguarda 1 segundo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadRelogio.start();
    }

    private void atualizarTempo() {
        segundos++;
        if (segundos == 60) {
            segundos = 0;
            minutos++;
            if (minutos == 60) {
                minutos = 0;
                horas++;
                if (horas == 24) {
                    horas = 0;
                }
            }
        }
    }

    private String obterHoraAtual() {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public static void main(String[] args) {
        Relogio relogio = new Relogio();
        relogio.iniciar();
    }
}