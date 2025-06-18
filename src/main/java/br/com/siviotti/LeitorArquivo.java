package br.com.siviotti;

/**
 * Classe responsável por ler o conteúdo de um arquivo.
 *
 * @author Douglas Siviotti com Gemini
 * @since 18/06/2025 - 14:08
 */
public class LeitorArquivo {

	public String lerArquivo(String caminhoArquivo) {
		StringBuilder conteudo = new StringBuilder();
		try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(caminhoArquivo))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				conteudo.append(linha).append("\n");
			}
		} catch (java.io.IOException e) {
			throw new RuntimeException("Erro ao ler o arquivo: " + caminhoArquivo, e);
		}
		return conteudo.toString();
	}

}
