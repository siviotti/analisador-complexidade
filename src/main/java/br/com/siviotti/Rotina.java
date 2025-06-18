package br.com.siviotti;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma Rotina (método ou função) e seus atributos:
 *
 * @author Douglas Siviotti
 * @since 18/06/2025 15:32
 */
public class Rotina {
    private String nome; // atributo imutável atribuído pelo construtor
    private Linguagem linguagem;
    private List<String> linhasOriginais = new ArrayList<>();
    private List<String> linhasSemComentario = new ArrayList<>();
    private List<String> tokens = new ArrayList<>();
    private int complexidadeCiclomatica;
    private int complexidadeCognitiva;

    public Rotina(String nome, Linguagem linguagem) {
        this.nome = nome;
        this.linguagem=linguagem;
    }

    public String getNome() {
        return nome;
    }

    public Linguagem getLinguagem(){
        return linguagem;
    }

    public List<String> getLinhasOriginais() {
        return linhasOriginais;
    }

    public void setLinhasOriginais(List<String> linhasOriginais) {
        this.linhasOriginais = linhasOriginais;
    }

    public List<String> getLinhasSemComentario() {
        return linhasSemComentario;
    }

    public void setLinhasSemComentario(List<String> linhasSemComentario) {
        this.linhasSemComentario = linhasSemComentario;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public int getComplexidadeCiclomatica() {
        return complexidadeCiclomatica;
    }

    public void setComplexidadeCiclomatica(int complexidadeCiclomatica) {
        this.complexidadeCiclomatica = complexidadeCiclomatica;
    }

    public int getComplexidadeCognitiva() {
        return complexidadeCognitiva;
    }

    public void setComplexidadeCognitiva(int complexidadeCognitiva) {
        this.complexidadeCognitiva = complexidadeCognitiva;
    }
    @Override
    public String toString() {
        return "Rotina{" +
                "nome='" + nome + '\'' +
                ", linhasOriginais=" + linhasOriginais.size() +
                ", linhasSemComentario=" + linhasSemComentario.size() +
                ", tokens=" + tokens.size() +
                ", complexidadeCiclomatica=" + complexidadeCiclomatica +
                ", complexidadeCognitiva=" + complexidadeCognitiva +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rotina rotina = (Rotina) o;

        return nome.equals(rotina.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }


}
