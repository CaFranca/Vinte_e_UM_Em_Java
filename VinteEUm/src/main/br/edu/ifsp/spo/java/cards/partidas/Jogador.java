package br.edu.ifsp.spo.java.cards.partidas;

import br.edu.ifsp.spo.java.cards.itens.Carta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Jogador {
    // Nome do jogador
    private final String nome;

    // Lista de cartas na mão do jogador
    private final List<Carta> cartas = new ArrayList<>();

    // Pontuação atual do jogador
    private int pontuacao;

    // Construtor que inicializa o nome e pontuação do jogador
    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    // Retorna o nome do jogador
    public String getNome() {
        return nome;
    }

    // Adiciona uma carta à mão do jogador
    public void adicionarCarta(Carta carta) {
        cartas.add(carta);
    }

    // Retorna a lista de cartas do jogador
    public List<Carta> getCartas() {
        return cartas;
    }

    // Retorna a pontuação atual do jogador
    public int getPontuacao() {
        return pontuacao;
    }

    // Adiciona pontos à pontuação atual do jogador
    public void addPontuacao(int pontos) {
        this.pontuacao += pontos;
    }

    // Limpa todas as cartas da mão do jogador
    public void limparCartas() {
        cartas.clear();
    }
}

// Subclasse que representa a Inteligência Artificial do jogo
class IA extends Jogador {

    public IA(String nome) {
        super(nome);
    }

    /**
     * Decide a jogada da IA com base na pontuação atual.
     * Pode escolher pegar uma carta (1) ou parar (2).
     *
     * @param pontuacaoAtual Pontuação atual da IA
     * @return 1 para pegar carta, 2 para parar
     * @throws InterruptedException para simular tempo de reflexão
     */
    public int decidirJogada(int pontuacaoAtual) throws InterruptedException {
        System.out.println("Deixe-me pensar....");
        TimeUnit.SECONDS.sleep(2);

        if (pontuacaoAtual < 15 && pontuacaoAtual > 1) {
            // Pontuação baixa: a IA decide pegar mais uma carta
            exibirFraseDePegarCarta();
            TimeUnit.SECONDS.sleep(1);
            return 1;
        } else if (pontuacaoAtual > 15 && pontuacaoAtual < 18) {
            // Pontuação intermediária: a IA hesita entre pegar ou parar
            exibirFraseDeHesitar();
            TimeUnit.SECONDS.sleep(2);
            return (int) ((Math.random() * 2) + 1); // 1 ou 2 aleatório
        } else {
            // Pontuação alta: a IA decide parar para não estourar
            exibirFraseDeParar();
            TimeUnit.SECONDS.sleep(1);
            return 2;
        }
    }

    // Frases aleatórias exibidas quando a IA decide pegar carta
    private void exibirFraseDePegarCarta() {
        String[] frases = {
                "Vou pegar mais uma carta!",
                "Preciso de mais uma para melhorar!",
                "Vou tentar a sorte com outra carta.",
                "Quem sabe a próxima é a que eu preciso!",
                "Ainda não é hora de parar, mais uma carta.",
                "Eu tenho fé que essa próxima carta vai ser boa.",
                "Vamos lá, mais uma chance!",
                "Se eu pegar mais uma carta, quem sabe o que acontece!",
                "Só mais uma carta e eu fico mais forte!"
        };
        System.out.println(frases[(int) (Math.random() * frases.length)]);
    }

    // Frases aleatórias exibidas quando a IA está indecisa
    private void exibirFraseDeHesitar() {
        String[] frases = {
                "Isso pode ser arriscado, mas vou tentar.",
                "Hmm, estou com um pouco de medo de estourar...",
                "Estou quase lá, mas será que vale a pena arriscar?",
                "Está perigoso, mas talvez eu deva arriscar!",
                "Estou pensando se devo pegar mais uma ou parar.",
                "Será que eu vou conseguir? Estou quase lá!",
                "É, está complicado... uma decisão difícil!",
                "Será que vou arriscar e tentar mais uma carta?",
                "Eu sei que pode ser perigoso, mas estou tentado!"
        };
        System.out.println(frases[(int) (Math.random() * frases.length)]);
    }

    // Frases aleatórias exibidas quando a IA decide parar
    private void exibirFraseDeParar() {
        String[] frases = {
                "Cé Loko, num Compensa!",
                "Acho que já deu, não vale a pena arriscar.",
                "Melhor não arriscar mais, vou parar por aqui.",
                "Não quero estourar, então vou parar.",
                "Já fiz o meu jogo, agora é só aguardar.",
                "Cheguei no meu limite, vou ficar por aqui.",
                "Não vale a pena continuar, já deu o que tinha que dar.",
                "Acho que é hora de parar antes que eu me arrependa!",
                "Minha sorte chegou ao fim, agora vou parar."
        };
        System.out.println(frases[(int) (Math.random() * frases.length)]);
    }
}
