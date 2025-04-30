package br.edu.ifsp.spo.java.cards;
import br.edu.ifsp.spo.java.cards.partidas.JogoUI;
import br.edu.ifsp.spo.java.cards.partidas.Jogo;

public class App {
    public static void main(String[] args) {
        JogoUI ui = new JogoUI();
        String nome1, nome2;

        nome1 = ui.pedirNomeJogador(1);
        nome2 = ui.pedirNomeJogador(2);

        int modo = ui.escolherModoPontuacao();

        int jogar = 1;
        while (jogar == 1) {
            Jogo jogo = new Jogo(nome1, nome2, modo, ui);
            jogo.iniciar();

            if (jogo.jogoEmpatado) {

                ui.exibirMensagem("O jogo terminou empatado. Reiniciando o jogo...");
            } else {

                jogar = ui.perguntarSeAceitaRevanche();
                if (jogar == 1) {
                    ui.exibirMensagem("Iniciando uma nova partida...");
                }
                ui.exibirMensagem("Jogo encerrado!!");
            }
        }

        ui.fecharScanner();
    }
}
