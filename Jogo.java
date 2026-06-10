package jogoCartas;

public abstract class Jogo {

    protected String nome;
    protected int quantidadeJogadores;
    protected String[] jogadores;

    public Jogo(String nome, String[] jogadores) {
        this.nome = nome;
        this.jogadores = jogadores;
        this.quantidadeJogadores = jogadores.length;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeJogadores() {
        return quantidadeJogadores;
    }

    public String[] getJogadores() {
        return jogadores;
    }

    public abstract void iniciar();

    public abstract void exibirRegras();

    protected void exibirSeparador() {
        System.out.println("========================================");
    }
}
