package br.edu.ifsp.spo.java.cards;

import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.itens.Naipe;
import br.edu.ifsp.spo.java.cards.itens.Valor;
import br.edu.ifsp.spo.java.cards.partidas.Jogo;
import br.edu.ifsp.spo.java.cards.partidas.JogoUI;

import java.io.IOException;
import java.io.InputStream;

public class App2 {

    public static void main(String[] args) throws InterruptedException {

        for (var naipe : Naipe.values()){
            for (var valor : Valor.values()){
                var carta = new Carta(naipe, valor);
                var caminhoCompleto = String.format(
                        "%s/%s/%s.txt",
                        "/cards",
                        carta.getNaipe().toString(),
                        carta.getValor().toString());
                System.out.println(caminhoCompleto);
                try{
                    InputStream inputStream = App2.class.getResourceAsStream(caminhoCompleto);

                    if (inputStream == null){
                        System.out.println("Não existe o arquivo");
                    }else {
                        byte[] buffer = new byte[inputStream.available()];
                        inputStream.read(buffer);

                        var cartaComoString = new String(buffer);
                        System.out.println(cartaComoString);
                    }
                } catch (IOException exception){
                    System.out.println("IOException: " + exception.getMessage());
                }
            }
        }


    }
}

