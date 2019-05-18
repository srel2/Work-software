package annotations;

import annotations.validations.ChoiceBoxNoEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChoiceBoxNoEmptyValidator.class)
public @interface ChoiceBoxNoEmpty {
    String message();
    Class[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
