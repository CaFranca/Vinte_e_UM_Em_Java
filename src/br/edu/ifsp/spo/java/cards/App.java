package br.edu.ifsp.spo.java.cards;

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
            jogar = ui.perguntarSeAceitaRevanche();
            ui.exibirMensagem("Jogo encerrado!!");
        }

        ui.fecharScanner();
    }
}
