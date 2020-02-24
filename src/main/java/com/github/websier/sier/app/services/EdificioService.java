package com.github.websier.sier.app.services;

import static com.github.websier.sier.app.domain.specifications.EdificioSpecification.where;
import static com.github.websier.sier.app.utils.FormUtils.addNotificacao;
import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.util.Optional;

import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.models.Edificio;
import com.github.websier.sier.app.domain.repositories.EdificioRepository;
import com.github.websier.sier.app.utils.TipoAlerta;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    public void salvar(
        Edificio edificio,
        RedirectAttributes redirect
    ) {
        if (isNull(edificio.getId())) {
            addNotificacao(redirect, TipoAlerta.NOVO, persistir(edificio));
        } else {
            addNotificacao(redirect, TipoAlerta.ATUALIZADO, atualizar(edificio));
        }
    }

    public Edificio persistir(Edificio edificio) {
        return repository.save(edificio);
    }

    public Edificio atualizar(Edificio edificio) {
        var persistir = obterPorId(edificio.getId());
        var createdBy = persistir.getColeta().getCreatedBy();
        BeanUtils.copyProperties(edificio, persistir, "id");
        persistir.getColeta().setCreatedBy(createdBy);
        return repository.save(persistir);
    }

    public void deletar(Edificio edificio) {
        repository.delete(edificio);
    }
}