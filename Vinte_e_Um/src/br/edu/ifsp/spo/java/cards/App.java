package br.edu.ifsp.spo.java.cards;
import br.edu.ifsp.spo.java.cards.partidas.JogoUI;
import br.edu.ifsp.spo.java.cards.partidas.Jogo;
import br.edu.ifsp.spo.java.cards.itens.Baralho;

public class App {
    public static void main(String[] args) throws InterruptedException {
        JogoUI ui = new JogoUI();
        int tipoJogo = ui.escolherJogadores();
        int modo = ui.escolherModoPontuacao();
        String[] nomes = ui.obterNomesDosJogadores(tipoJogo);

        Baralho baralho = new Baralho();

        int jogar = 1;
        while (jogar == 1) {
            Jogo jogo = new Jogo(nomes[0], nomes[1], modo, ui, tipoJogo, baralho);
            jogo.iniciar();

            if (jogo.jogoEmpatado) {
                ui.exibirMensagem("O jogo terminou empatado. Reiniciando o jogo...");
            } else {
                jogar = ui.perguntarSeAceitaRevanche();
                if (jogar == 1) {
                    ui.exibirMensagem("Iniciando uma nova partida...");
                } else {
                    ui.exibirMensagem("Jogo encerrado!!");
                }
            }
        }

        ui.fecharScanner();
    }
}
