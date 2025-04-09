package br.edu.ifsp.spo.java.cards;

import java.util.List;

public class Pontuador {
    public int verificarPontuacao(List<Carta> cartas) {
        int total = 0;
        for (Carta carta : cartas) {
            switch (carta.getValor()) {
                case AS -> total += 1;
                case DOIS -> total += 2;
                case TRES -> total += 3;
                case QUATRO -> total += 4;
                case CINCO -> total += 5;
                case SEIS -> total += 6;
                case SETE -> total += 7;
                case OITO -> total += 8;
                case NOVE -> total += 9;
                case DEZ, VALETE, DAMA, REI -> total += 10;
            }
        }
        return total;
    }
}
