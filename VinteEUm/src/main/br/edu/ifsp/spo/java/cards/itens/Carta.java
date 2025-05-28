package br.edu.ifsp.spo.java.cards.itens;

import java.io.IOException;
import java.io.InputStream;

public class Carta {
    private final Naipe naipe;
    private final Valor valor;

    public Carta(Naipe naipe, Valor valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public Valor getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return getValor() + " de " + getNaipe();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return naipe == carta.naipe && valor == carta.valor;
    }
    public void printCarta(Naipe naipe,Valor valor) {
        var caminhoCompleto = String.format(
                "%s/%s/%s.txt",
                "/cards",
                naipe,
                valor);
        System.out.println(caminhoCompleto);
        try {
            InputStream inputStream = exibirCarta.class.getResourceAsStream(caminhoCompleto);

            if (inputStream == null) {
                System.out.println("Não existe o arquivo");
            } else {
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);

                var cartaComoString = new String(buffer);
                System.out.println(cartaComoString);
            }
        } catch (IOException exception) {
            System.out.println("IOException: " + exception.getMessage());
        }
    }
}

   
