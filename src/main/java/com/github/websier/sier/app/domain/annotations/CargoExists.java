/*
 * Copyright (C) 2019 Renato
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.websier.sier.app.domain.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.github.websier.sier.app.domain.repositories.CargoRepository;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * CargoExists
 */
@Documented
@Constraint(validatedBy = CargoValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CargoExists {

    String message() default "O cargo informado não existe no sistema.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class CargoValidator implements ConstraintValidator<CargoExists, String> {

    @Autowired
    private CargoRepository repository;

    @Override
    public boolean isValid(String nome, ConstraintValidatorContext context) {
        return repository.existsByNome(nome);
    }

}