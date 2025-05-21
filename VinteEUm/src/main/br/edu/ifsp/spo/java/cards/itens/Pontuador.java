package br.edu.ifsp.spo.java.cards.itens;

import java.util.List;

public class Pontuador {
    public int verificarPontuacao(List<Carta> cartas, int modo) {
        int total = 0;

        for (Carta carta : cartas) {
            switch (carta.getValor()) {
                case AS -> {
                    if (modo == 1) total += 10;
                    else total += 11;
                }
                case DOIS -> total += 2;
                case TRES -> total += 3;
                case QUATRO -> total += 4;
                case CINCO -> total += 5;
                case SEIS -> total += 6;
                case SETE -> total += 7;
                case OITO -> total += 8;
                case NOVE -> total += 9;
                case DEZ -> total += 10;
                case VALETE, DAMA, REI -> {
                    if (modo == 1) total += 10;
                    else total += 1;
                }
            }
        }

        return total;
    }
}
