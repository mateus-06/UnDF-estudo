package jogoCartas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JogoCartas extends Jogo {

    private static final int MIN_JOGADORES = 2;
    private static final int MAX_JOGADORES = 6;
    private static final int TOTAL_CARTAS_BARALHO = 52;

    private List<String> baralho;
    private int cartasPorJogador;
    private Scanner scanner;

    public JogoCartas(String[] jogadores) {
        super("Jogo de Cartas", jogadores);
        this.baralho = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        inicializarBaralho();
    }

    private void inicializarBaralho() {
        String[] naipes = {"Espadas", "Copas", "Ouros", "Paus"};
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String naipe : naipes) {
            for (String valor : valores) {
                baralho.add(valor + " de " + naipe);
            }
        }
        Collections.shuffle(baralho);
    }

    @Override
    public void iniciar() {
        exibirSeparador();
        System.out.println("Iniciando: " + getNome());
        exibirSeparador();

        if (!validarJogadores()) {
            return;
        }

        // Usuário escolhe quantas rodadas jogar
        System.out.print("Quantas rodadas deseja jogar? ");
        int totalRodadas = lerInteiro();

        for (int r = 1; r <= totalRodadas; r++) {
            System.out.println("\n=== RODADA " + r + " ===");

            // Reinicia o baralho a cada rodada
            baralho.clear();
            inicializarBaralho();

            cartasPorJogador = TOTAL_CARTAS_BARALHO / getQuantidadeJogadores();
            System.out.println("Cartas por jogador: " + cartasPorJogador);

            List<List<String>> maos = distribuirCartas();
            String[] jogadores = getJogadores();

            System.out.print("Deseja ver as mãos de todos os jogadores? (s/n): ");
            String ver = scanner.nextLine().trim().toLowerCase();
            if (ver.equals("s")) {
                for (int i = 0; i < jogadores.length; i++) {
                    System.out.println("Mao de " + jogadores[i] + ": " + maos.get(i));
                }
            }

            Random rand = new Random();
            String vencedor = jogadores[rand.nextInt(jogadores.length)];
            System.out.println("Vencedor da rodada " + r + ": " + vencedor);
            System.out.println("Cartas restantes no baralho: " + baralho.size());
        }

        exibirSeparador();
        System.out.println("Fim do jogo!");
        exibirSeparador();
    }

    private boolean validarJogadores() {
        int qtd = getQuantidadeJogadores();
        if (qtd < MIN_JOGADORES) {
            System.out.println("Erro: minimo de " + MIN_JOGADORES + " jogadores. Informado: " + qtd);
            return false;
        }
        if (qtd > MAX_JOGADORES) {
            System.out.println("Erro: maximo de " + MAX_JOGADORES + " jogadores. Informado: " + qtd);
            return false;
        }
        return true;
    }

    private List<List<String>> distribuirCartas() {
        int numJogadores = getQuantidadeJogadores();
        List<List<String>> maos = new ArrayList<>();
        for (int i = 0; i < numJogadores; i++) {
            maos.add(new ArrayList<>());
        }
        for (int rodada = 0; rodada < cartasPorJogador; rodada++) {
            for (int j = 0; j < numJogadores; j++) {
                if (!baralho.isEmpty()) {
                    maos.get(j).add(baralho.remove(0));
                }
            }
        }
        return maos;
    }

    private int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite um numero: ");
            }
        }
    }

    @Override
    public void exibirRegras() {
        exibirSeparador();
        System.out.println("Regras - " + getNome());
        exibirSeparador();
        System.out.println("- Minimo de jogadores : " + MIN_JOGADORES);
        System.out.println("- Maximo de jogadores : " + MAX_JOGADORES);
        System.out.println("- Total de cartas     : " + TOTAL_CARTAS_BARALHO + " (baralho padrao)");
        System.out.println("- As cartas sao distribuidas igualmente entre os jogadores.");
        System.out.println("- O vencedor de cada rodada e determinado aleatoriamente.");
        System.out.println("- As cartas restantes ficam no baralho apos a distribuicao.");
        exibirSeparador();
    }
}
