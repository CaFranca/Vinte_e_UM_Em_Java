package br.edu.ifsp.spo.java.cards.partidas;

import br.edu.ifsp.spo.java.cards.itens.Carta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

// -------- Subclasse IA --------
class IA extends Jogador {
    public IA(String nome) {
        super(nome);
    }

    public int decidirJogada(int pontuacaoAtual) throws InterruptedException {
        System.out.println("Deixe-me pensar....");
        TimeUnit.SECONDS.sleep(2);
        if (pontuacaoAtual < 15 && pontuacaoAtual > 1) return 1;// Pega carta se tiver menos de 17
        else if (pontuacaoAtual>15 && pontuacaoAtual<18) {
            System.out.println("Isso pode ser arriscado!");
            TimeUnit.SECONDS.sleep(1);
            return (int) ((Math.random() * 2) + 1);
        }
        else System.out.println("Cé Loko, num Compensa!");return 2;
    }
}
