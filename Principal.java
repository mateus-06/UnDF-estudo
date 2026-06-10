package jogoCartas;

import java.util.Scanner;

public class Principal {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("       BEM-VINDO À CENTRAL DE JOGOS     ");
        System.out.println("========================================");

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nEscolha o jogo:");
            System.out.println("1 - Jogo de Cartas");
            System.out.println("2 - Jogo de Tabuleiro");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            int opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    executarJogoCartas();
                    break;
                case 2:
                    executarJogoTabuleiro();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("\nAté mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void executarJogoCartas() {
        System.out.println("\n--- JOGO DE CARTAS ---");
        System.out.println("(mínimo 2 jogadores, máximo 6)");
        System.out.print("Quantos jogadores? ");
        int qtd = lerInteiro();

        String[] jogadores = lerNomesJogadores(qtd);
        JogoCartas jogo = new JogoCartas(jogadores);

        System.out.println("\nDeseja ver as regras antes de começar? (s/n): ");
        String resp = scanner.nextLine().trim().toLowerCase();
        if (resp.equals("s")) {
            jogo.exibirRegras();
        }

        jogo.iniciar();
    }

    private static void executarJogoTabuleiro() {
        System.out.println("\n--- JOGO DE TABULEIRO ---");
        System.out.print("Quantos jogadores? ");
        int qtd = lerInteiro();

        String[] jogadores = lerNomesJogadores(qtd);
        JogoTabuleiro jogo = new JogoTabuleiro(jogadores);

        System.out.print("Deseja ver as regras antes de começar? (s/n): ");
        String resp = scanner.nextLine().trim().toLowerCase();
        if (resp.equals("s")) {
            jogo.exibirRegras();
        }

        jogo.iniciar();
    }

    private static String[] lerNomesJogadores(int qtd) {
        String[] nomes = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            System.out.print("Nome do jogador " + (i + 1) + ": ");
            nomes[i] = scanner.nextLine().trim();
            if (nomes[i].isEmpty()) {
                nomes[i] = "Jogador " + (i + 1);
            }
        }
        return nomes;
    }

    private static int lerInteiro() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número: ");
            }
        }
    }
}