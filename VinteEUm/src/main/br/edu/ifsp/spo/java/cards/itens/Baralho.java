package br.edu.ifsp.spo.java.cards.itens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private final List<Carta> cartas;

    /**
     * Construtor que cria um baralho padrão com todas as combinações
     * de naipes e valores, e embaralha as cartas.
     */
    public Baralho() {
        this.cartas = new ArrayList<>();

        // Cria todas as cartas possíveis no baralho
        for (var suit : Naipe.values()) {
            for (var rank : Valor.values()) {
                this.cartas.add(new Carta(suit, rank));
            }
        }

        // Embaralha o baralho
        Collections.shuffle(this.cartas);
    }

    /**
     * Remove e retorna a carta do topo do baralho.
     *
     * @return Carta retirada do baralho.
     * @throws IndexOutOfBoundsException se o baralho estiver vazio.
     */
    public Carta tirarCarta() {
        return this.cartas.remove(0);
    }

    /**
     * Retorna a quantidade de cartas restantes no baralho.
     *
     * @return número de cartas disponíveis.
     */
    public int cartasRestantes() {
        return this.cartas.size();
    }

    /**
     * Representação textual do baralho mostrando quantas cartas restam.
     *
     * @return String representando o baralho.
     */
    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cartasRestantes() +
                '}';
    }

    /**
     * Retorna a lista interna de cartas do baralho.
     *
     * @return Lista de cartas.
     */
    public List<Carta> getCards() {
        return cartas;
    }

    /**
     * Reseta o baralho para o estado inicial, com todas as cartas e embaralhado.
     */
    public void resetarBaralho() {
        this.cartas.clear();

        // Recria todas as cartas
        for (var suit : Naipe.values()) {
            for (var rank : Valor.values()) {
                this.cartas.add(new Carta(suit, rank));
            }
        }

        // Embaralha o baralho novamente
        Collections.shuffle(this.cartas);
    }
}
