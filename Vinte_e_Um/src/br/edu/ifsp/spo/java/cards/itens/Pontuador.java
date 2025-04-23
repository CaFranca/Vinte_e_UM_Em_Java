package br.edu.ifsp.spo.java.cards.itens;

import java.util.List;

public class Pontuador {
    public int verificarPontuacao(List<Carta> cartas, int modo) {
        int total = 0;

        for (Carta carta : cartas) {
            switch (carta.getValor()) {
                case AS -> total += 20;
                case DOIS -> total += 20;
                case TRES -> total += 20;
                case QUATRO -> total += 20;
                case CINCO -> total += 20;
                case SEIS -> total += 20;
                case SETE -> total += 20;
                case OITO -> total += 20;
                case NOVE -> total += 20;
                case DEZ, VALETE, DAMA, REI -> {
                    if (modo == 1) total += 20;
                    else total += 1;
                }
            }
        }

        return total;
    }
}
