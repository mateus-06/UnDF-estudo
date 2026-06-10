package jogoCartas;

import java.util.Random;
import java.util.Scanner;

public class JogoTabuleiro extends Jogo {

    private static final int FACES_DADO = 6;

    private int[] posicoes;
    private int totalRodadas;
    private int tamanhTabuleiro;
    private Random dado;
    private Scanner scanner;

    public JogoTabuleiro(String[] jogadores) {
        super("Jogo de Tabuleiro", jogadores);
        this.posicoes = new int[jogadores.length];
        this.totalRodadas = 0;
        this.dado = new Random();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void iniciar() {
        exibirSeparador();
        System.out.println("Iniciando: " + getNome());
        exibirSeparador();

        // Usuário define o tamanho do tabuleiro
        System.out.print("Quantas casas tera o tabuleiro? (recomendado: 20 a 50): ");
        tamanhTabuleiro = lerInteiro();

        System.out.println("\nJogadores: " + getQuantidadeJogadores());
        System.out.println("Meta: alcançar ou ultrapassar a casa " + tamanhTabuleiro);
        System.out.println();

        String[] jogadores = getJogadores();
        String vencedor = null;

        while (vencedor == null) {
            totalRodadas++;
            System.out.println("--- Rodada " + totalRodadas + " ---");

            for (int i = 0; i < jogadores.length; i++) {
                // Usuário pressiona Enter para rolar o dado de cada jogador
                System.out.print(jogadores[i] + ", pressione Enter para rolar o dado...");
                scanner.nextLine();

                int rolagem = rolarDado();
                posicoes[i] += rolagem;

                System.out.printf("%s rolou %d -> posicao %d%n", jogadores[i], rolagem, posicoes[i]);

                if (posicoes[i] >= tamanhTabuleiro) {
                    vencedor = jogadores[i];
                    break;
                }
            }
            System.out.println();
        }

        System.out.println("VENCEDOR: " + vencedor
                + " (alcancou a casa " + posicoes[indexOf(jogadores, vencedor)] + ")");
        System.out.println("Total de rodadas: " + totalRodadas);
        exibirPlacar(jogadores);
        exibirSeparador();
    }

    private int rolarDado() {
        return dado.nextInt(FACES_DADO) + 1;
    }

    private int indexOf(String[] arr, String valor) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(valor)) return i;
        }
        return -1;
    }

    private void exibirPlacar(String[] jogadores) {
        System.out.println("\nPlacar final:");
        for (int i = 0; i < jogadores.length; i++) {
            int pos = Math.min(posicoes[i], tamanhTabuleiro);
            System.out.printf("  %-12s -> casa %d/%d%n", jogadores[i], pos, tamanhTabuleiro);
        }
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
        System.out.println("- O usuario define o tamanho do tabuleiro ao iniciar.");
        System.out.println("- Dado de " + FACES_DADO + " faces (valores de 1 a " + FACES_DADO + ").");
        System.out.println("- Cada jogador pressiona Enter para rolar o dado na sua vez.");
        System.out.println("- O jogador avanca o numero de casas igual ao valor do dado.");
        System.out.println("- Vence quem alcançar ou ultrapassar a ultima casa primeiro.");
        exibirSeparador();
    }
}
