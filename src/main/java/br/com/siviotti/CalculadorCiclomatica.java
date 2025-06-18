package br.com.siviotti;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que calcula complexidade ciclomática a partir de uma Rotina.
 *
 * @author Douglas Siviotti
 * @since 18/06/2025 15:43
 */
public class CalculadorCiclomatica {

    private static final Map<Linguagem, List<String>> KEYWORDS = new HashMap<>();
    private static final List<String> JAVA_KEYWORDS = List.of(
            "if",
            "else",
            "while",
            "do",
            "for",
            "switch",
            "case",
            "default",
            "catch",
            "finally",
            "&&", // Operador AND lógico (condicional)
            "||"  // Operador OR lógico (condicional)
            // "throw" e "throws" podem ser considerados dependendo da interpretação,
            // mas os listados acima são os mais comuns para contagem de pontos de decisão.
            // "return" não é contado diretamente, mas um método com múltiplos returns
            // pode ser reescrito com if/else, aumentando a complexidade.
    );

    private static final List<String> PYTHON_KEYWORDS = List.of(
            "if",
            "elif",
            "else", // Similar ao Java, 'else' faz parte da estrutura 'if'
            "for",  // Laço
            "while", // Laço
            "try",
            "except", // Cada 'except' é um caminho alternativo
            "finally", // 'finally' afeta o fluxo
            "and",     // Operador lógico
            "or",      // Operador lógico
            "assert"   // Cria um ponto de decisão condicional
            // List comprehensions com 'if' (ex: [x for x in lista if x > 0]) também adicionam complexidade,
            // mas a detecção seria mais complexa do que simples tokens.
            // 'with' também pode ser considerado, pois gerencia um contexto (similar a try/finally).
    );
    private static final List<String> JAVASCRIPT_KEYWORDS = List.of(
            "if",
            "else", // 'else if' é comum, mas 'else' faz parte da estrutura
            "for",  // Inclui for, for...in, for...of
            "while",
            "do",   // do...while
            "switch",
            "case",
            "default",
            "try",
            "catch",
            "finally",
            "&&",   // Operador AND lógico
            "||",   // Operador OR lógico
            "??"    // Operador de coalescência nula (pode ser visto como um ponto de decisão)
            // Funções de seta com condicionais ternários (ex: cond ? val1 : val2) também adicionam,
            // mas a detecção do ternário '?' como token separado seria necessária.
    );
    private static final List<String> GO_KEYWORDS = List.of(
            "if",
            "else", // 'else if' é comum, mas 'else' faz parte da estrutura
            "for",  // Única estrutura de laço em Go, mas pode ter condições
            "switch",
            "case",
            "default",
            "select", // Usado com canais, cada 'case' em um select é um ponto de decisão
            // "defer" e "panic"/"recover" afetam o fluxo, mas não são contados tradicionalmente como pontos de decisão diretos para CC.
            // Operadores lógicos && e || são implícitos nas condições e não tokens separados como em Java/JS para este propósito.
            // A contagem em Go geralmente foca nas estruturas de controle.
            "&&", // Adicionando para consistência, se o tokenizador os separar
            "||"  // Adicionando para consistência, se o tokenizador os separar
    );

    private static final List<String> C_KEYWORDS = List.of(
            "if",
            "else",
            "while",
            "do",
            "for",
            "switch",
            "case",
            "default",
            // "goto" é uma palavra-chave em C que cria saltos, aumentando a complexidade,
            // mas sua contagem pode ser tratada de forma especial ou simplesmente adicionada aqui.
            "goto",
            "&&", // Operador AND lógico
            "||"  // Operador OR lógico
            // Operador ternário '?' também adiciona complexidade
    );

    static {
        KEYWORDS.put(Linguagem.JAVA, JAVA_KEYWORDS);
        KEYWORDS.put(Linguagem.PYTHON, PYTHON_KEYWORDS);
        KEYWORDS.put(Linguagem.JAVASCRIPT, JAVASCRIPT_KEYWORDS);
        KEYWORDS.put(Linguagem.GO, GO_KEYWORDS);
        KEYWORDS.put(Linguagem.C, C_KEYWORDS);
    }

    /**
     * Calcula a complexidade ciclomática de uma rotina.
     *
     * @param rotina A rotina a ser analisada.
     * @return A complexidade ciclomática da rotina.
     */
    public int calcular(Rotina rotina) {
        int complexidade = 1; // Inicia com 1 para o ponto de entrada (nó de entrada)
        List<String> incKeyword = getKeywordsPorLinguagem(rotina.getLinguagem()); // keywords que geram incremento
        if (rotina.getTokens()== null) return complexidade;
        for (String token:  rotina.getTokens()){ // usando for para didática - funcional seria menor
            if (incKeyword.contains(token)) {
                complexidade++;
            }
        }
        return complexidade;
    }

    // Método que só existe se ainda não existe implementação para uma linguagem da lista definida
    private List<String> getKeywordsPorLinguagem(Linguagem linguagem) {
        List<String> keywords = KEYWORDS.get(linguagem);
        if (keywords == null) {
            throw new IllegalArgumentException("Linguagem não suportada para cálculo de complexidade ciclomática: " + linguagem);
        }
        return keywords;
    }
}
