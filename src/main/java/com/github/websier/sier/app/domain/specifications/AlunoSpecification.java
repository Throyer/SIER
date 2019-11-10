package com.github.websier.sier.app.domain.specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import com.github.websier.sier.app.domain.models.Aluno;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;

public class AlunoSpecification {

    public static Specification<Aluno> where(
        Optional<String> nome,
        Optional<String> turma,
        Model model
    ) {
        return (aluno, query, builder) -> {
            
            List<Predicate> predicates = new ArrayList<>();

            if (nome.isPresent()) {
                predicates.add(builder
                    .like(aluno
                        .get("usuario")
                            .get("nome"), "%" + nome.get() + "%"));
                model.addAttribute("nome", nome.get());
            }

            if (turma.isPresent()) {
                predicates.add(builder
                    .like(aluno
                        .get("turma"), "%" + turma.get() + "%"));
                model.addAttribute("turma", turma.get());
            }

            return builder.and(getPredicates(predicates));
        };
    }

    private static Predicate [] getPredicates(List<Predicate> predicates) {
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}