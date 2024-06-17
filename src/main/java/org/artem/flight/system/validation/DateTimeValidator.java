package org.artem.flight.system.validation;

import org.artem.flight.system.dto.ScheduleCreateEditDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateTimeValidator implements ConstraintValidator<DateValidation, ScheduleCreateEditDto> {


    @Override
    public boolean isValid(ScheduleCreateEditDto scheduleCreateEditDto, ConstraintValidatorContext constraintValidatorContext) {
        if (scheduleCreateEditDto.getStartTime() == null) {
            return false;
        }

        if (scheduleCreateEditDto.getEndTime() == null) {
            return false;
        }

        if (scheduleCreateEditDto.getStartTime().isBefore(LocalDateTime.now())) {
            return false;
        }

        return scheduleCreateEditDto.getStartTime().isBefore(scheduleCreateEditDto.getEndTime());
    }
}
