package br.com.cyber.conquista.frameworkConquista.enums;

public enum SistemaOperacional {

	LINUX("Linux",""),
	WINDOWS("Windows",".exe"),
	MACOS("MacOS", "");
	
	private String descricao;
	private String extensao;
	 
	private SistemaOperacional(String descricao, String extensao) {
        this.descricao = descricao;
		this.extensao = extensao;
    }
 
    public String getDescricao() {
        return descricao;
    }
    
    public String getExtensao() {
		return extensao;
	}
   
    
}
