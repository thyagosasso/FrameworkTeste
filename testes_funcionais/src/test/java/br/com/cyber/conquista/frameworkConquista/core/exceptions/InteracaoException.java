package br.com.cyber.conquista.frameworkConquista.core.exceptions;

import static br.com.cyber.conquista.frameworkConquista.core.utils.Utilitarios.formatter;

/**
 * <p>
 * <strong>Excessões Personalizadas</strong> para a classe
 * {@link br.com.cyber.conquista.frameworkConquista.core.interacao.Interactions
 * Interactions}.
 * 
 * @author Felipe Gadelha
 * @author Jonathan Daflon
 * @author Thyago Sasso
 */
public class InteracaoException extends RuntimeException implements IException {

	private static final long serialVersionUID = 1L;
	private String message;

	public InteracaoException(Exception e, String description) {
		message = handler(e, description);
		message = String.format("%s - %s", formatter(), message);
		logger(e, message);
	}

	public String toString() {
		return description(message);
	}
}