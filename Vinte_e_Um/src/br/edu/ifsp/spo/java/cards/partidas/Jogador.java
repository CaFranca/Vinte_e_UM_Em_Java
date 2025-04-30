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

        // Se a pontuação for muito baixa, a IA deve pegar uma carta
        if (pontuacaoAtual < 15 && pontuacaoAtual > 1) {
            exibirFraseDePegarCarta();
            TimeUnit.SECONDS.sleep(1);
            return 1; // Pega carta se tiver menos de 17
        }
        // Se a pontuação estiver entre 15 e 18, a IA pode hesitar
        else if (pontuacaoAtual > 15 && pontuacaoAtual < 18) {
            exibirFraseDeHesitar();
            TimeUnit.SECONDS.sleep(2);
            return (int) ((Math.random() * 2) + 1); // Pode pegar carta ou parar
        }
        // Se a pontuação for muito alta, a IA decide não pegar mais cartas
        else {
            exibirFraseDeParar();
            TimeUnit.SECONDS.sleep(1);
            return 2; // Decide parar
        }
    }

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

