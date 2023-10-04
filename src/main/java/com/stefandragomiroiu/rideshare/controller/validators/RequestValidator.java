package com.stefandragomiroiu.rideshare.controller.validators;

import am.ik.yavi.core.Validator;
import com.stefandragomiroiu.rideshare.controller.exception.BadRequestException;

public interface RequestValidator<T> {

    Validator<T> getValidatorInstance();

    default void validate(T object) {
        getValidatorInstance()
                .validate(object)
                .throwIfInvalid(BadRequestException::new);
    }

}
