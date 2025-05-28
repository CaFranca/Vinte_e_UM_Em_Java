package br.edu.ifsp.spo.java.cards.itens;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExibirCartas {

    public static void printCartas(List<Carta> cartas) {
        List<String[]> cartasEmLinhas = new ArrayList<>();

        for (Carta carta : cartas) {
            String caminho = String.format("/cards/%s/%s.txt",
                    carta.getNaipe().name(),
                    carta.getValor().name());

            try (InputStream inputStream = ExibirCartas.class.getResourceAsStream(caminho)) {
                if (inputStream == null) {
                    System.out.println("Arquivo não encontrado: " + caminho);
                    return;
                }

                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                String conteudo = new String(buffer);
                String[] linhas = conteudo.split("\n");
                cartasEmLinhas.add(linhas);

            } catch (IOException e) {
                System.out.println("Erro ao ler carta: " + e.getMessage());
                return;
            }
        }

        // Imprime linha por linha de todas as cartas
        int quantidadeLinhas = cartasEmLinhas.get(0).length;

        for (int linha = 0; linha < quantidadeLinhas; linha++) {
            for (String[] carta : cartasEmLinhas) {
                System.out.print(carta[linha] + "  "); // Espaço entre cartas
            }
            System.out.println();
        }
    }
}
