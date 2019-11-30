package com.github.websier.sier.app.domain.dtos;

import com.github.websier.sier.app.domain.enuns.TipoColeta;

/**
 * TipoColetaDTO
 */
public class TipoColetaDTO {

    private String nome;
    private String chave;

    public TipoColetaDTO(TipoColeta tipoColeta) {
        this.nome = formatarNome(tipoColeta.name());
        this.chave = tipoColeta.name();
    }

    public String getNome() {
        return nome;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String formatarNome(String chave) {
        if (chave == null || chave.isEmpty()) {
            return chave;
        }
        
        StringBuilder resultado = new StringBuilder();
     
        boolean converterProximo = true;
        for (char letra : chave.toCharArray()) {
            if (Character.isSpaceChar(letra)) {
                converterProximo = true;
            } else if (converterProximo) {
                letra = Character.toTitleCase(letra);
                converterProximo = false;
            } else {
                letra = Character.toLowerCase(letra);
            }
            resultado.append(letra);
        }
     
        return resultado.toString().replaceAll("_", " ");
    }
}