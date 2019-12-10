package com.github.websier.sier.app.services;

import static com.github.websier.sier.app.domain.specifications.AlunoSpecification.where;
import static com.github.websier.sier.app.utils.Cargos.ALUNO;

import java.util.Optional;

import com.github.websier.sier.app.domain.models.Aluno;
import com.github.websier.sier.app.domain.repositories.AlunoRepository;
import com.github.websier.sier.app.domain.repositories.CargoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

/**
 * AlunoService
 */
@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private CargoRepository cargos;

    public Page<Aluno> obterTodos(
        Optional<String> nome,
        Optional<String> turma,
        Model model,
        Pageable pageable
    ) {
        var specification = where(nome, turma, model);
        return repository.findAll(specification, pageable);
    }

    public Page<Aluno> obterTodos(
        Specification<Aluno> specification,
        Pageable pageable
    ) {
        return repository.findAll(specification, pageable);
    }

    public Aluno obterAlunoPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Aluno persistir(Aluno aluno) {
        return repository.save(atribuirCargoDeAluno(aluno));
    }

    public Aluno atualizar(Aluno aluno) {
        var entidade = obterAlunoPorId(aluno.getId());
        return repository.save(atualizarCamposDoAluno(aluno, entidade));
    }

    private Aluno atribuirCargoDeAluno(Aluno aluno) {
        var cargoDeAluno = cargos.findOptionalByNome(ALUNO)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        aluno.setCargo(cargoDeAluno);
        return aluno;
    }

    private Aluno atualizarCamposDoAluno(Aluno fonte, Aluno alvo) {
        alvo.setNome(fonte.getNome());
        alvo.setApelido(fonte.getApelido());
        alvo.setTurma(fonte.getTurma());
        return alvo;
    }
    
}