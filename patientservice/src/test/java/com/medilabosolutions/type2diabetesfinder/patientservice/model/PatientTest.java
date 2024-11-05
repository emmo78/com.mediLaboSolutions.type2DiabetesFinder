package com.medilabosolutions.type2diabetesfinder.patientservice.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientTest {

    private ValidatorFactory factory;

    private Validator validator;

    private Patient patient;

    @BeforeAll
    public void setUpForAllTests() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public void undefForAllTests() {
        validator = null;
        factory = null;
    }

    @BeforeEach
    public void setUpPerTest() {
        patient = new Patient();
    }

    @AfterEach
    public void undefPerTest() {
        patient = null;
    }

    @ParameterizedTest(name = "{0} should throw a ConstraintViolationException")
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyz"})//26*2=52
    @Tag("PatientTU")
    @DisplayName("@Valid test with a incorrect patient firstName should throw a ConstraintViolationException")
    public void saveTestIncorrectUsernameShouldThrowAConstraintViolationException(String firstName) {

        //GIVEN
        patient = Patient.builder()
                .id(1)
                .firstName(firstName)
                .lastName("lastName")
                .birthDate(LocalDate.of(1950,12,31))
                .genre("M")
                .build();

        String msg = null;
        if (firstName!=null) {
            if (!firstName.isBlank()) {
                msg = "Firstname must be maximum of 35 characters";//26*2=52
            } else {
                msg = "Firstname is mandatory"; //empty or blank
            }
        } else {
            msg = "Firstname is mandatory"; //null
        }

        //WHEN
        Set<ConstraintViolation<Patient>> constraintViolations = validator.validate(patient);

        //THEN
        assertThat(constraintViolations).isNotEmpty();
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo(msg);
    }
}
