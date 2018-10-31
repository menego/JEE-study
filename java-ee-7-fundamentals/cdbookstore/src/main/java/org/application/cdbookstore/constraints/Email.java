package org.application.cdbookstore.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
@Documented
public @interface Email {

    // ======================================
    // =             Attributes             =
    // ======================================

    String message() default "{Email.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // ======================================
    // =          Inner Annotation          =
    // ======================================

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
    @interface List {
        Email[] value();
    }
}