package ru.skillbox.currency.exchange.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.CurrencyNameAndValue;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.entity.CurrencyDetailsXml;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto convertToDto(Currency currency);

    Currency convertToEntity(CurrencyDto currencyDto);

    @Mapping(target = "id", ignore = true)
    void updateCurrencyFromDto(CurrencyDto dto, @MappingTarget Currency entity);

    @Mapping(target = "id", ignore = true)
    CurrencyDto convertCurrencyXmlToDto(CurrencyDetailsXml currencyDetailsXml);

    List<CurrencyNameAndValue> convertToListCurrencyNameAndValue(List<Currency> currency);
}
