package br.edu.ifsp.spo.java.cards.regras;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ifsp.spo.java.cards.itens.Valor;
import br.edu.ifsp.spo.java.cards.itens.Naipe;
import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.itens.Pontuador;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PontuadorTest {

    private Carta criarCarta(Valor valor, Naipe naipe) {
        return new Carta(naipe, valor);
    }

    @Test
    public void testPontuacaoSimples() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartas = Arrays.asList(
                criarCarta(Valor.AS, Naipe.OUROS), criarCarta(Valor.DOIS, Naipe.COPAS),
                criarCarta(Valor.TRES, Naipe.ESPADAS), criarCarta(Valor.QUATRO, Naipe.PAUS),
                criarCarta(Valor.CINCO, Naipe.OUROS)
        );

        int pontuacao = pontuador.verificarPontuacao(cartas, 1);
        assertEquals(11 + 2 + 3 + 4 + 5, pontuacao);

        pontuacao = pontuador.verificarPontuacao(cartas, 2);
        assertEquals(1 + 2 + 3 + 4 + 5, pontuacao);
    }

    @Test
    public void testPontuacaoFiguras() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartasModo1 = Arrays.asList(
                criarCarta(Valor.VALETE, Naipe.OUROS),
                criarCarta(Valor.DAMA, Naipe.COPAS),
                criarCarta(Valor.REI, Naipe.ESPADAS)
        );
        int pontuacaoModo1 = pontuador.verificarPontuacao(cartasModo1, 1);
        assertEquals(10 + 10 + 10, pontuacaoModo1);

        List<Carta> cartasModo2 = Arrays.asList(
                criarCarta(Valor.VALETE, Naipe.PAUS),
                criarCarta(Valor.DAMA, Naipe.OUROS),
                criarCarta(Valor.REI, Naipe.COPAS)
        );
        int pontuacaoModo2 = pontuador.verificarPontuacao(cartasModo2, 2);
        assertEquals(1 + 1 + 1, pontuacaoModo2);
    }

    @Test
    public void testPontuacaoMisturada() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartas = Arrays.asList(
                criarCarta(Valor.AS, Naipe.OUROS), criarCarta(Valor.DOIS, Naipe.COPAS),
                criarCarta(Valor.VALETE, Naipe.ESPADAS),
                criarCarta(Valor.DAMA, Naipe.PAUS), criarCarta(Valor.REI, Naipe.OUROS)
        );

        int pontuacaoModo1 = pontuador.verificarPontuacao(cartas, 1);
        assertEquals(11 + 2 + 10 + 10 + 10, pontuacaoModo1);

        int pontuacaoModo2 = pontuador.verificarPontuacao(cartas, 2);
        assertEquals(1 + 2 + 1 + 1 + 1, pontuacaoModo2);
    }

    @Test
    public void testPontuacaoListaVazia() {
        Pontuador pontuador = new Pontuador();
        List<Carta> cartas = Arrays.asList();
        int pontuacao = pontuador.verificarPontuacao(cartas, 1);
        assertEquals(0, pontuacao);
    }

    @Test
    public void testPontuacaoCartasRepetidas() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartas = Arrays.asList(
                criarCarta(Valor.AS, Naipe.OUROS),
                criarCarta(Valor.AS, Naipe.COPAS),
                criarCarta(Valor.AS, Naipe.ESPADAS)
        );
        int pontuacao = pontuador.verificarPontuacao(cartas, 1);
        assertEquals(11 + 11 + 11, pontuacao);
    }

    @Test
    public void testPontuacaoAsValendo11() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartas = Arrays.asList(
                criarCarta(Valor.AS, Naipe.OUROS),
                criarCarta(Valor.DOIS, Naipe.COPAS)
        );

        int pontuacao = pontuador.verificarPontuacao(cartas, 1);
        assertEquals(11 + 2, pontuacao);
    }

    @Test
    public void testPontuacaoAsValendo1() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartas = Arrays.asList(
                criarCarta(Valor.AS, Naipe.OUROS),
                criarCarta(Valor.DOIS, Naipe.COPAS)
        );

        int pontuacao = pontuador.verificarPontuacao(cartas, 2);
        assertEquals(1 + 2, pontuacao);
    }

    @Test
    public void testPontuacaoMaxima() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartas = Arrays.asList(
                criarCarta(Valor.AS, Naipe.OUROS),
                criarCarta(Valor.DEZ, Naipe.COPAS)
        );

        int pontuacao = pontuador.verificarPontuacao(cartas, 1);
        assertEquals(1 + 10 + 10, pontuacao);
    }

    @Test
    public void testRodadaEmpate() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartasJogador1 = Arrays.asList(
                criarCarta(Valor.DEZ, Naipe.OUROS),
                criarCarta(Valor.OITO, Naipe.COPAS)
        );
        List<Carta> cartasJogador2 = Arrays.asList(
                criarCarta(Valor.NOVE, Naipe.ESPADAS),
                criarCarta(Valor.NOVE, Naipe.PAUS)
        );

        int pontosJogador1 = pontuador.verificarPontuacao(cartasJogador1, 1);
        int pontosJogador2 = pontuador.verificarPontuacao(cartasJogador2, 1);

        assertEquals(18, pontosJogador1);
        assertEquals(18, pontosJogador2);
    }

    @Test
    public void testRodadaEstouroDeUmJogador() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartasJogador1 = Arrays.asList(
                criarCarta(Valor.DEZ, Naipe.OUROS),
                criarCarta(Valor.NOVE, Naipe.COPAS),
                criarCarta(Valor.QUATRO, Naipe.ESPADAS)
        ); // 23

        List<Carta> cartasJogador2 = Arrays.asList(
                criarCarta(Valor.DEZ, Naipe.PAUS),
                criarCarta(Valor.OITO, Naipe.OUROS)
        ); // 18

        int pontosJogador1 = pontuador.verificarPontuacao(cartasJogador1, 1);
        int pontosJogador2 = pontuador.verificarPontuacao(cartasJogador2, 1);

        assertTrue(pontosJogador1 > 21);
        assertTrue(pontosJogador2 <= 21);
        assertEquals(23, pontosJogador1);
        assertEquals(18, pontosJogador2);
    }

    @Test
    public void testRodadaAmbosEstouram() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartasJogador1 = Arrays.asList(
                criarCarta(Valor.DEZ, Naipe.OUROS),
                criarCarta(Valor.NOVE, Naipe.COPAS),
                criarCarta(Valor.SETE, Naipe.ESPADAS) // 26
        );
        List<Carta> cartasJogador2 = Arrays.asList(
                criarCarta(Valor.DEZ, Naipe.PAUS),
                criarCarta(Valor.OITO, Naipe.OUROS),
                criarCarta(Valor.SEIS, Naipe.COPAS) // 24
        );

        int pontosJogador1 = pontuador.verificarPontuacao(cartasJogador1, 1);
        int pontosJogador2 = pontuador.verificarPontuacao(cartasJogador2, 1);

        assertTrue(pontosJogador1 > 21);
        assertTrue(pontosJogador2 > 21);
        assertEquals(26, pontosJogador1);
        assertEquals(24, pontosJogador2);
    }

    @Test
    public void testRodadaAmbos21() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartasJogador1 = Arrays.asList(
                criarCarta(Valor.AS, Naipe.OUROS),
                criarCarta(Valor.DEZ, Naipe.COPAS)
        ); // 11 + 10 = 21

        List<Carta> cartasJogador2 = Arrays.asList(
                criarCarta(Valor.NOVE, Naipe.PAUS),
                criarCarta(Valor.DOIS, Naipe.OUROS),
                criarCarta(Valor.DEZ, Naipe.COPAS) // 9 + 2 + 10 = 21
        );

        int pontosJogador1 = pontuador.verificarPontuacao(cartasJogador1, 1);
        int pontosJogador2 = pontuador.verificarPontuacao(cartasJogador2, 1);

        assertEquals(21, pontosJogador1);
        assertEquals(21, pontosJogador2);
    }

    @Test
    public void testRodadaUmFaz21OutroNao() {
        Pontuador pontuador = new Pontuador();

        List<Carta> cartasJogador1 = Arrays.asList(
                criarCarta(Valor.AS, Naipe.OUROS),
                criarCarta(Valor.DEZ, Naipe.COPAS) // 11 + 10 = 21
        );

        List<Carta> cartasJogador2 = Arrays.asList(
                criarCarta(Valor.OITO, Naipe.ESPADAS),
                criarCarta(Valor.DEZ, Naipe.PAUS) // 8 + 10 = 18
        );

        int pontosJogador1 = pontuador.verificarPontuacao(cartasJogador1, 1);
        int pontosJogador2 = pontuador.verificarPontuacao(cartasJogador2, 1);

        assertEquals(21, pontosJogador1);
        assertEquals(18, pontosJogador2);
        assertTrue(pontosJogador1 > pontosJogador2);
    }

}
