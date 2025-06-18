package br.com.siviotti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe de teste para {@link LeitorArquivo}.
 *
 * @author Douglas Siviotti com Gemini
 * @since 18/06/2025 - 14:08
 */

class LeitorArquivoTest {

    @TempDir
    Path tempDir; // Diretório temporário gerenciado pelo JUnit

    @Test
    void deveLerArquivoComConteudoCorretamente() throws IOException {
        // Arrange
        LeitorArquivo leitor = new LeitorArquivo();
        Path arquivoTeste = tempDir.resolve("testeComConteudo.txt");
        String conteudoEsperado = "Linha 1\nLinha 2\nOutra linha aqui\n";

        // Escreve conteúdo no arquivo temporário
        try (BufferedWriter writer = Files.newBufferedWriter(arquivoTeste)) {
            writer.write("Linha 1");
            writer.newLine();
            writer.write("Linha 2");
            writer.newLine();
            writer.write("Outra linha aqui");
        }

        // Act
        String conteudoLido = leitor.lerArquivo(arquivoTeste.toString());

        // Assert
        assertEquals(conteudoEsperado, conteudoLido);
    }

    @Test
    void deveLerArquivoVazioCorretamente() throws IOException {
        // Arrange
        LeitorArquivo leitor = new LeitorArquivo();
        Path arquivoVazio = tempDir.resolve("arquivoVazio.txt");
        Files.createFile(arquivoVazio); // Cria um arquivo vazio
        String conteudoEsperado = "";

        // Act
        String conteudoLido = leitor.lerArquivo(arquivoVazio.toString());

        // Assert
        assertEquals(conteudoEsperado, conteudoLido);
    }

    @Test
    void deveRetornarStringVaziaQuandoArquivoNaoExistir() {
        // Arrange
        LeitorArquivo leitor = new LeitorArquivo();
        String caminhoArquivoInexistente = tempDir.resolve("arquivoQueNaoExiste.txt").toString();
        String conteudoEsperado = "";

        // Act
        String conteudoLido = leitor.lerArquivo(caminhoArquivoInexistente);

        // Assert
        // A implementação atual imprime o stack trace e retorna uma string vazia.
        // Idealmente, poderia lançar uma exceção específica que seria capturada aqui.
        assertEquals(conteudoEsperado, conteudoLido);
    }

    @Test
    void deveAdicionarQuebraDeLinhaAoFinalDeCadaLinhaLida() throws IOException {
        // Arrange
        LeitorArquivo leitor = new LeitorArquivo();
        Path arquivoTeste = tempDir.resolve("testeQuebraLinha.txt");
        String linha1 = "Primeira linha";
        String linha2 = "Segunda linha";

        try (BufferedWriter writer = Files.newBufferedWriter(arquivoTeste)) {
            writer.write(linha1);
            writer.newLine();
            writer.write(linha2);
        }

        String conteudoEsperado = linha1 + "\n" + linha2 + "\n";

        // Act
        String conteudoLido = leitor.lerArquivo(arquivoTeste.toString());

        // Assert
        assertEquals(conteudoEsperado, conteudoLido);
    }
}
