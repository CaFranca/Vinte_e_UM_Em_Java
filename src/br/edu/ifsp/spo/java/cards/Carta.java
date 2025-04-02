package br.edu.ifsp.spo.java.cards;

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
}
