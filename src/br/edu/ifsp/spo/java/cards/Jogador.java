package br.edu.ifsp.spo.java.cards;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private final String nome;
    private final List<Carta> cartas = new ArrayList<>();

    public Jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarCarta(Carta carta) {
        cartas.add(carta);
    }

    public List<Carta> getCartas() {
        return cartas;
    }
}
