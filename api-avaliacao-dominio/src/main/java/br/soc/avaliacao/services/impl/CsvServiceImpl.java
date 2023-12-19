package br.soc.avaliacao.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import br.soc.avaliacao.commons.exception.ApiException;
import br.soc.avaliacao.commons.exception.DominioException;
import br.soc.avaliacao.commons.util.DateUtils;
import br.soc.avaliacao.domain.ExameRealizado;
import br.soc.avaliacao.services.CsvService;
import br.soc.avaliacao.services.builder.CsvFileBuilder;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CsvServiceImpl implements CsvService {

	private static final String ERRO_AO_GERAR_ARQUIVO_CSV = "Erro ao gerar arquivo CSV";

	@Override
	public byte[] exportaCsv(List<ExameRealizado> exames) throws ApiException {
		Path filepath = CsvFileBuilder.builder()
				.header(csvHeader())
				.body(exames.stream().map(csvBody()))
				.fileNameTemplate("ExamesRealizados")
				.build();

		try {			
			return Files.readAllBytes(filepath);
		} catch (IOException e) {
			log.error(ERRO_AO_GERAR_ARQUIVO_CSV, e);
			throw new DominioException(ERRO_AO_GERAR_ARQUIVO_CSV, e);
		}
	} 

	private String[] csvHeader() {
		String[] header = new String[] { "Cod. Func", "Nome Func", "Cod. Exame", "Nome Exame", "Data realização" };
		return ArrayUtils.addAll(header);
	}

	private Function<ExameRealizado, String[]> csvBody() {
		return exame -> {
			String[] list = new String[] { 
					exame.getIdFuncionario().toString(),
					exame.getNomeFuncionario(),
					exame.getCodigoExame().toString(),
					exame.getNomeExame(),
					DateUtils.toDateTimeBr(exame.getDataRealizacao())
				};

				return ArrayUtils.addAll(list);
		};
	}

}
