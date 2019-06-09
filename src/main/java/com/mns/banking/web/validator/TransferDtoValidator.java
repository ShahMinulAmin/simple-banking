package com.mns.banking.web.validator;

import com.mns.banking.web.model.TransferDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TransferDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return TransferDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransferDto transferDto = (TransferDto) target;

        if (transferDto.getFromAccNumber() == null || transferDto.getFromAccNumber().isEmpty()) {
            errors.reject("transferDto.fromAccNumber", ValidationMessages.FROM_ACCOUNT_NUMBER_NOT_NULL);
        }
        if (transferDto.getToAccNumber() == null || transferDto.getToAccNumber().isEmpty()) {
            errors.reject("transferDto.toAccNumber", ValidationMessages.TO_ACCOUNT_NUMBER_NOT_NULL);
        }
        if (transferDto.getAmount() == null || (transferDto.getAmount() != null && transferDto.getAmount() < 0)) {
            errors.reject("transferDto.amount", ValidationMessages.AMOUNT_NOT_NULL_OR_NEGATIVE);
        }
    }
}
