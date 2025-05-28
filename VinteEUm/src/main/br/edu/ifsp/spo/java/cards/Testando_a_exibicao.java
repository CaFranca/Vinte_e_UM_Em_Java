package br.edu.ifsp.spo.java.cards;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.itens.Naipe;
import br.edu.ifsp.spo.java.cards.itens.Valor;

import java.io.IOException;
import java.io.InputStream;

public class Testando_a_exibicao {

    /**
     * Método principal que percorre todos os naipes e valores para tentar
     * carregar e exibir o conteúdo ASCII dos arquivos de cartas correspondentes.
     * Exibe o caminho do arquivo e o conteúdo, ou mensagem caso não exista.
     */
    public static void main(String[] args) throws InterruptedException {

        // Percorre todos os naipes disponíveis no enum Naipe
        for (var naipe : Naipe.values()) {
            // Para cada naipe, percorre todos os valores disponíveis no enum Valor
            for (var valor : Valor.values()) {
                // Cria uma carta com o naipe e valor atuais
                var carta = new Carta(naipe, valor);

                // Monta o caminho completo do arquivo ASCII da carta, ex: "/cards/COPAS/AS.txt"
                var caminhoCompleto = String.format(
                        "%s/%s/%s.txt",
                        "/cards",
                        carta.getNaipe().toString(),
                        carta.getValor().toString());

                // Imprime o caminho do arquivo sendo acessado
                System.out.println(caminhoCompleto);

                try {
                    // Tenta abrir o arquivo usando getResourceAsStream
                    InputStream inputStream = Testando_a_exibicao.class.getResourceAsStream(caminhoCompleto);

                    // Se o arquivo não existir, avisa o usuário
                    if (inputStream == null) {
                        System.out.println("Não existe o arquivo");
                    } else {
                        // Lê todo o conteúdo do arquivo para um array de bytes
                        byte[] buffer = new byte[inputStream.available()];
                        inputStream.read(buffer);

                        // Converte o conteúdo para string e imprime
                        var cartaComoString = new String(buffer);
                        System.out.println(cartaComoString);
                    }
                } catch (IOException exception) {
                    // Caso ocorra algum erro de leitura, imprime a mensagem do erro
                    System.out.println("IOException: " + exception.getMessage());
                }
            }
        }

    }
}
