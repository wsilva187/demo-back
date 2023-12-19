package br.soc.avaliacao.services.builder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.commons.exception.DominioException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CsvFileBuilder {

	private static final String GENERIC_FILE_NAME_TEMPLATE = "%s_%s.csv";

	private String[] header;
	private Stream<String[]> body;
	private String fileNameTemplate;

	public Path build() throws ApiException {
		log.debug("Iniciando construção do arquivo fisico csv ({})", fileNameTemplate);
		Path filePath = buildFileName();
		try (PrintWriter pw = new PrintWriter(filePath.toFile(), "UTF-8")) {
			pw.println(String.join(";", this.header));
			body.map(b -> String.join(";", b)).forEach(pw::println);
			log.debug("Arquivo fisico csv ({}) gerado con sucesso em {}", fileNameTemplate, filePath.toAbsolutePath());
			return filePath;
		} catch (FileNotFoundException e) {
			log.error("Não foi possivel criar arquivo csv {}", fileNameTemplate, e);
			throw new DominioException("Não foi possivel criar arquivo csv {}" + fileNameTemplate, e);
		} catch (UnsupportedEncodingException e) {
			log.error("Erro ao codificar arquivo csv " + fileNameTemplate, e);
			throw new DominioException("Não foi possivel criar o arquivo csv " + fileNameTemplate, e);
		}
	}

	private Path buildFileName() throws ApiException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
			Path tempDir = Files.createTempDirectory("relatorio-glosas-");
			String fileName = String.format(GENERIC_FILE_NAME_TEMPLATE, fileNameTemplate, LocalDate.now().format(formatter));
			return Paths.get(tempDir.toString(), fileName);
		} catch (IOException e) {
			throw new DominioException("Não foi possivel criar o nome do arquivo para o tempalte " + fileNameTemplate, e);
		}
	}

	public CsvFileBuilder header(String[] header) {
		this.header = header;
		return this;
	}

	public CsvFileBuilder body(Stream<String[]> body) {
		this.body = body;
		return this;
	}

	public CsvFileBuilder fileNameTemplate(String fileNameTemplate) {
		this.fileNameTemplate = fileNameTemplate;
		return this;
	}

	public static CsvFileBuilder builder() {
		return new CsvFileBuilder();
	}

}
