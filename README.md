#  Jogo 21 em Java — CaFranca

Este projeto implementa um jogo de cartas baseado nas regras simplificadas do "21" (Blackjack), desenvolvido em Java, com interação via terminal. O objetivo do jogo é alcançar 21 pontos somando os valores das cartas, sem ultrapassar esse limite.

---

##  Funcionalidades

- Baralho completo de 52 cartas (embaralhado aleatoriamente).
- Jogadores humanos ou computador (modo automático).
- Sistema de pontuação baseado nas regras clássicas do "21":
  - A → 1 ponto  
  - 2 a 10 → valor da carta  
  - J, Q, K → 10 pontos
- Ciclo de jogo para dois jogadores:
  - Comprar cartas ou parar.
  - Verificação automática de vitória, derrota ou empate.
- Suporte a **múltiplas rodadas** (até 10), com pontuação acumulada.
- Interface simples via terminal, utilizando a classe `JogoUI`.

---

##  Organização do Código

- `Baralho`, `Carta`, `Valor`, `Naipe` → Modelagem das cartas e do baralho.
- `Jogador` → Armazena nome, pontuação e cartas na mão.
- `Pontuador` → Classe utilitária que calcula a pontuação de uma lista de cartas.
- `Jogo` → Controla as regras do jogo e fluxo das rodadas.
- `JogoUI` → Responsável pela interação com o usuário via terminal.
- `App` → Classe principal (`main`) que inicia a aplicação e lida com replays.

---

##  Regras resumidas

- **21 pontos exatos**: Vitória direta.
- **Ambos com 21**: Empate.
- **Ultrapassou 21**: Derrota.
- **Ambos estouraram**: Empate.
- **Mais próximo de 21** vence, em caso de ninguém estourar ou fazer 21.
- Empates resultam na reinicialização da rodada com a mesma configuração.

---

##  Sistema de Pontuação (Múltiplas Rodadas)

- Empate: +10 pontos para ambos.
- Um estourou e o outro não:  
  - Vencedor: soma dos seus pontos.  
  - Perdedor: -5 pontos.
- Ambos estouraram:  
  - Cada um perde a diferença que ultrapassou de 21.
- Ambos com 21: +21 pontos cada.
- Um com 21 e outro não: vencedor ganha +30 pontos.
- Ambos com menos de 21: vencedor recebe a diferença entre as pontuações.

---

##  Como executar

1. Clone o repositório:
   ```bash
   https://github.com/CaFranca/Vinte_e_UM_Em_Java.git
