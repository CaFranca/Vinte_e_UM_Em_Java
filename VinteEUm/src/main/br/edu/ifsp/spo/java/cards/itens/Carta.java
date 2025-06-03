package br.edu.ifsp.spo.java.cards.itens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Carta {
    private final Naipe naipe;
    private final Valor valor;

    public Carta(Naipe naipe, Valor valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    /**
     * Exibe no terminal uma lista de cartas lado a lado usando seus desenhos ASCII.
     * Caso a lista esteja vazia, informa que não há cartas para mostrar.
     *
     * @param cartas Lista de cartas a serem exibidas lado a lado.
     */
// Método estático que exibe uma lista de cartas desenhadas em ASCII lado a lado
    public static void exibirCartasLadoALado(List<Carta> cartas) {

        // Verifica se a lista de cartas está vazia
        if (cartas.isEmpty()) {
            System.out.println("Nenhuma carta para exibir.");
            return; // Encerra o método se não houver cartas
        }

        // Obtém o número de linhas que compõem o desenho ASCII de uma carta
        // (Pressupõe que todas as cartas têm o mesmo número de linhas no desenho)
        int linhasPorCarta = cartas.get(0).carregarAscii().size();

        // Percorre cada linha do desenho das cartas (linha 0 até a última)
        for (int i = 0; i < linhasPorCarta; i++) {

            // Cria um StringBuilder para montar a linha atual composta por todas as cartas
            StringBuilder linhaComposta = new StringBuilder();

            // Percorre cada carta da lista
            for (Carta c : cartas) {
                // Carrega o desenho ASCII da carta atual
                List<String> desenho = c.carregarAscii();

                // Adiciona a linha atual (i) da carta no StringBuilder
                linhaComposta.append(desenho.get(i)).append("  "); // Adiciona dois espaços entre cartas
            }

            // Exibe no console a linha composta com essa parte de todas as cartas
            System.out.println(linhaComposta);
        }
    }


    /**
     * Retorna o naipe da carta.
     */
    public Naipe getNaipe() {
        return naipe;
    }

    /**
     * Retorna o valor da carta.
     */
    public Valor getValor() {
        return valor;
    }

    /**
     * Representação textual da carta no formato "VALOR de NAIPE".
     */
    @Override
    public String toString() {
        return getValor() + " de " + getNaipe();
    }

    /**
     * Compara se esta carta é igual a outro objeto (considera naipe e valor).
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return naipe == carta.naipe && valor == carta.valor;
    }

    /**
     * Carrega o desenho ASCII da carta a partir do arquivo texto correspondente.
     * Caso o arquivo não exista ou ocorra erro, retorna um desenho "vazio" padrão.
     *
     * @return Lista de strings representando as linhas do desenho ASCII da carta.
     */
    public List<String> carregarAscii() {
        List<String> linhas = new ArrayList<>();
        String caminhoCompleto = String.format(
                "/cards/%s/%s.txt",
                naipe.toString(),
                valor.toString()
        );

        try (InputStream inputStream = getClass().getResourceAsStream(caminhoCompleto);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                System.out.println("Arquivo não encontrado: " + caminhoCompleto);
                // Retorna desenho padrão para carta "vazia"
                linhas.add("+-----------+");
                for (int i = 0; i < 5; i++) linhas.add("|           |");
                linhas.add("+-----------+");
                return linhas;
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo ASCII: " + e.getMessage());
            linhas.clear();
            linhas.add("+-----------+");
            for (int i = 0; i < 5; i++) linhas.add("|           |");
            linhas.add("+-----------+");
        }

        return linhas;
    }

    /**
      Método legado para imprimir o desenho ASCII de uma carta específica no console.
     * Pode ser usado para testes ou visualização individual.
     *
     * @param naipe Naipe da carta a ser exibida.
     * @param valor Valor da carta a ser exibida.
     */
    public void printCarta(Naipe naipe, Valor valor) {
        String caminhoCompleto = String.format(
                "/cards/%s/%s.txt",
                naipe.toString().toLowerCase(),
                valor.toString()
        );

        System.out.println(caminhoCompleto);

        try (InputStream inputStream = Carta.class.getResourceAsStream(caminhoCompleto)) {
            if (inputStream == null) {
                System.out.println("Não existe o arquivo");
                return;
            }

            byte[] buffer = inputStream.readAllBytes();
            String cartaComoString = new String(buffer);
            System.out.println(cartaComoString);

        } catch (IOException exception) {
            System.out.println("IOException: " + exception.getMessage());
        }
    }
}
