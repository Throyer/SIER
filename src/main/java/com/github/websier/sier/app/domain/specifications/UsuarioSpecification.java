package com.github.websier.sier.app.domain.specifications;

import static com.github.websier.sier.app.utils.FormUtils.isPresent;
import static com.github.websier.sier.app.utils.FormUtils.toArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import com.github.websier.sier.app.domain.models.Usuario;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;

/**
 * UsuarioSpecification
 */
public class UsuarioSpecification {

    public static Specification<Usuario> where(
        Optional<String> cargo,
        Optional<String> turma,
        Optional<String> nome,
        Optional<String> apelido,
        Optional<String> email,
        Optional<Boolean> situacao,
        Model model
    ) {
        return (usuario, query, builder) -> {
            
            List<Predicate> predicates = new ArrayList<>();

            if (isPresent(turma)) {
                predicates.add(builder
                    .like(usuario
                        .get("turma"),"%" + turma.get() + "%"));
                model.addAttribute("turma", turma.get());
            }

            if (isPresent(cargo)) {
                predicates.add(builder
                    .like(usuario
                        .get("cargo")
                            .get("nome"),"%" + cargo.get() + "%"));
                model.addAttribute("cargo", cargo.get());
            }

            if (isPresent(nome)) {
                predicates.add(builder
                    .like(usuario.get("nome"), "%" + nome.get() + "%"));
                model.addAttribute("nome", nome.get());
            }

            if (isPresent(apelido)) {
                predicates.add(builder
                    .like(usuario
                        .get("apelido"), "%" + apelido.get() + "%"));
                model.addAttribute("apelido", apelido.get());
            }
            
            if (isPresent(email)) {
                predicates.add(builder
                    .like(usuario
                        .get("email"), "%" + email.get() + "%"));
                model.addAttribute("email", email.get());
            }
            
            if (situacao.isPresent()) {
                predicates.add(builder
                    .equal(usuario
                        .get("ativo"), situacao.get()));
                model.addAttribute("situacao", situacao.get());
            }

            return builder.and(toArray(predicates));
        };
    }
}