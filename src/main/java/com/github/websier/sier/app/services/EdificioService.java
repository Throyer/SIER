package com.github.websier.sier.app.services;

import static com.github.websier.sier.app.domain.specifications.EdificioSpecification.where;

import java.time.LocalDate;
import java.util.Optional;

import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.models.Edificio;
import com.github.websier.sier.app.domain.repositories.EdificioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

/**
 * EdificioService
 */
@Service
public class EdificioService {

    @Autowired
    private EdificioRepository repository;

    public Page<Edificio> obterTodos(
        Optional<TipoColeta> fonteColeta,
        Optional<String> nome,
        Optional<String> autor,
        Optional<LocalDate> dataColeta,
        Model model,
        Pageable pageable
    ) {
        var specification = where(fonteColeta, nome, autor, dataColeta, model);
        return repository.findAll(specification, pageable);
    }

    public Page<Edificio> obterTodos(
        Specification<Edificio> specification,
        Pageable pageable
    ) {
        return repository.findAll(specification, pageable);
    }

    public Edificio obterPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Edificio persistir(Edificio edificio) {
        return repository.save(edificio);
    }

    public Edificio atualizar(Edificio edificio) {
        var entidade = obterPorId(edificio.getId());
        return repository.save(atualizarCamposDoEdificio(entidade, edificio));
    }

    public void deletar(Edificio edificio) {
        repository.delete(edificio);
    }

    private Edificio atualizarCamposDoEdificio(Edificio destino, Edificio fonte) {
        destino.setNome(fonte.getNome());
        destino.setNomeConhecido(fonte.getNomeConhecido());
        destino.setDataConstrucao(fonte.getDataConstrucao());
        destino.setNumeroAndares(fonte.getNumeroAndares());
        destino.setEndereco(fonte.getEndereco());
        destino.getColeta().setUpdatedBy(fonte.getColeta().getUpdatedBy());
        return destino;
    }
}