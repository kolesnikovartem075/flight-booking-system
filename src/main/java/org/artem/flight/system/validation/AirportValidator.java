package org.artem.flight.system.validation;

import org.artem.flight.system.dto.ScheduleCreateEditDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AirportValidator implements ConstraintValidator<AirportValidation, ScheduleCreateEditDto> {


    @Override
    public boolean isValid(ScheduleCreateEditDto scheduleCreateEditDto, ConstraintValidatorContext constraintValidatorContext) {
        var endAirportId = scheduleCreateEditDto.getEndAirportId();

        var startAirportId = scheduleCreateEditDto.getStartAirportId();
        if (endAirportId == null || startAirportId == null) {
            return false;
        }
        return !endAirportId.equals(startAirportId);
    }
}