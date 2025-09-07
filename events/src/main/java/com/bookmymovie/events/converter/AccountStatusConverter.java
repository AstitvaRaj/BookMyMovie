package com.bookmymovie.events.converter;

import com.bookmymovie.events.entities.enums.AccountStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, String> {

    @Override
    public String convertToDatabaseColumn(AccountStatus accountStatus) {
        return accountStatus.name();
    }

    @Override
    public AccountStatus convertToEntityAttribute(String s) {
        try{
            return AccountStatus.valueOf(s);
        } catch (Exception e) {
            return AccountStatus.UNKNOWN;
        }
    }

}
