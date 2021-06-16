package br.com.cyber.conquista.frameworkConquista.enums;

public enum ExceptionsMessages {

	NO_SUCH_ELEMENT {

		@Override
		public String getException(String description) {
			return String.format("O elemento %s não foi encontrado.", description);
		}
	},

	TIMEOUT {

		@Override
		public String getException(String description) {
			return String.format("Tempo excedido para encontrar o elemento %s.", description);
		}
	},

	NOT_VISIBLE {

		@Override
		public String getException(String description) {
			return String.format("A visão do elemento %s está obstruída ou ele está oculto.", description);
		}
	},

	EXCEPTION {

		@Override
		public String getException(String description) {
			return String.format(
					"Uma exceção inesperada ocorreu no elemento %s." + System.lineSeparator()
							+ "Por favor trate esta exceção no enumerador ExceptionsMessages e na classe Interactions",
					description);
		}
	},

	NOT_INTERACTABLE {

		@Override
		public String getException(String description) {
			return String.format("O elemento %s não está interagível.", description);
		}
	},

	STALE_REFERENCE {

		@Override
		public String getException(String description) {
			return String.format("O elemento %s não está mais visível na DOM.", description);
		}
	},

	INTERRUPTED {
		@Override
		public String getException(String description) {
			return String.format("A thread aguardava implicitamente quando foi interrompida!");
		}
	},
	
	WEBDRIVER {

		@Override
		public String getException(String description) {
			return String.format("Falha ao se conectar na plataforma através da url %s.", description);
		}
	},
	
	NO_SUCH_FRAME {

		@Override
		public String getException(String description) {
			return String.format("O frame %s não existe.", description);
		}
	},
	
	IO_EXCEPTION {
		@Override
		public String getException(String description) {
			return String.format("Falha ao realizar e armazenar a captura de tela.");
		}
	};

	public abstract String getException(String description);
}
