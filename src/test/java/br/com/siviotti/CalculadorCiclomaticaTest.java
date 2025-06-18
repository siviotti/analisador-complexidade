package br.com.siviotti;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculadorCiclomaticaTest {

    private CalculadorCiclomatica calculador;

    @BeforeEach
    void setUp() {
        calculador = new CalculadorCiclomatica();
    }

    @Test
    @DisplayName("Deve retornar complexidade 1 para rotina com tokens vazios")
    void calcular_ComTokensVazios_DeveRetornarUm() {
        Rotina rotina = new Rotina("teste", Linguagem.JAVA);
        rotina.setTokens(List.of());
        assertEquals(1, calculador.calcular(rotina));
    }

    @Test
    @DisplayName("Deve retornar complexidade 1 para rotina com tokens nulos")
    void calcular_ComTokensNulos_DeveRetornarUm() {
        // A classe Rotina de teste trata tokens nulos como lista vazia
        Rotina rotina = new Rotina("teste", Linguagem.JAVA);
        rotina.setTokens(null);
        assertEquals(1, calculador.calcular(rotina));
    }

    @Test
    @DisplayName("Deve retornar complexidade 1 para rotina sem palavras-chave de incremento")
    void calcular_SemPalavrasChaveIncremento_DeveRetornarUm() {
        Rotina rotina = new Rotina("teste", Linguagem.JAVA);
        rotina.setTokens(List.of("public", "void", "main", "String", "args"));
        assertEquals(1, calculador.calcular(rotina));
    }

    @Test
    @DisplayName("Deve calcular complexidade corretamente para Java")
    void calcular_ParaJava_DeveRetornarComplexidadeCorreta() {
        Rotina rotina = new Rotina("teste", Linguagem.JAVA);
        rotina.setTokens(List.of("if", "a", ">", "b", "&&", "c", "else", "while", "d"));
        // 1 (base) + 1 (if) + 1 (&&) + 1 (else) + 1 (while) = 5
        assertEquals(5, calculador.calcular(rotina));
    }

    @Test
    @DisplayName("Deve calcular complexidade corretamente para Python")
    void calcular_ParaPython_DeveRetornarComplexidadeCorreta() {
        Rotina rotina = new Rotina("teste", Linguagem.PYTHON);
        rotina.setTokens(List.of("if", "x", "in", "range"));
        // 1 (base) + 1 (if) + 1 (and) + 1 (for) + 1 (assert) = 5
        assertEquals(2, calculador.calcular(rotina));
    }

    @Test
    @DisplayName("Deve calcular complexidade corretamente para JavaScript")
    void calcular_ParaJavaScript_DeveRetornarComplexidadeCorreta() {
        Rotina rotina = new Rotina("teste", Linguagem.JAVASCRIPT);
        rotina.setTokens(List.of("if", "condition1", "||", "condition2", "switch", "value", "case", "1"));
        // 1 (base) + 1 (if) + 1 (||) + 1 (switch) + 1 (case) = 5
        assertEquals(5, calculador.calcular(rotina));
    }

    @Test
    @DisplayName("Deve calcular complexidade corretamente para Go")
    void calcular_ParaGo_DeveRetornarComplexidadeCorreta() {
        Rotina rotina = new Rotina("teste", Linguagem.GO);
        rotina.setTokens(List.of("if", "err", "!=", "nil", "for", "i", ":=", "0", "select", "case", "ch"));
        // 1 (base) + 1 (if) + 1 (for) + 1 (select) + 1 (case) = 5
        assertEquals(5, calculador.calcular(rotina));
    }

    @Test
    @DisplayName("Deve calcular complexidade corretamente para C")
    void calcular_ParaC_DeveRetornarComplexidadeCorreta() {
        Rotina rotina = new Rotina("teste", Linguagem.C);
        rotina.setTokens(List.of("if", "x", "&&", "y", "while", "1", "goto", "label"));
        // 1 (base) + 1 (if) + 1 (&&) + 1 (while) + 1 (goto) = 5
        assertEquals(5, calculador.calcular(rotina));
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para linguagem não suportada")
    void calcular_ComLinguagemNaoSuportada_DeveLancarExcecao() {
        Rotina rotina = new Rotina("teste", Linguagem.PASCAL);
        rotina.setTokens(List.of("begin", "end"));
        assertThrows(IllegalArgumentException.class, () -> calculador.calcular(rotina));
    }
}

