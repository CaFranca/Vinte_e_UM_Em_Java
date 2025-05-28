package br.edu.ifsp.spo.java.cards.itens;

import java.io.IOException;
import java.io.InputStream;

public class exibirCarta {

    public void printCarta(Carta carta) {
        var caminhoCompleto = String.format(
                "%s/%s/%s.txt",
                "/cards",
                carta.getNaipe().toString(),
                carta.getValor().toString());
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




