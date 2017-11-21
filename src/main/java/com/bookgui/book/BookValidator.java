/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookgui.book;

import com.bookgui.book.Book;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author denis
 */
public class BookValidator {

    private ValidatorFactory validateFactory;
    private Validator validator;

    @PostConstruct
    private void init() {
        validateFactory = Validation.buildDefaultValidatorFactory();
        validator = validateFactory.getValidator();
    }

    @PreDestroy
    private void close() {
        validateFactory.close();
    }

    public void validateBook(Book book) {
        Set<ConstraintViolation<Book>> constraintViolations
            = validator.validate(book);

        if (constraintViolations.size() > 0) {
            Set<String> violationMessages = new HashSet<>();

            constraintViolations.forEach((constraintViolation) -> {
                violationMessages.add(constraintViolation.getPropertyPath()
                    + ": " + constraintViolation.getMessage());
            });

            throw new RuntimeException("Book is not valid:\n"
                + StringUtils.join(violationMessages, "\n"));
        }
    }
}
