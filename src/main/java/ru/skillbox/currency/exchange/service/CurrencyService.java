package ru.skillbox.currency.exchange.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.CurrencyNameAndValue;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.mapper.CurrencyMapper;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyMapper mapper;
    private final CurrencyRepository repository;

    public CurrencyDto getById(Long id) {
        log.info("CurrencyService method getById executed");
        Currency currency = repository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found with id: " + id));
        return mapper.convertToDto(currency);
    }

    public List<CurrencyNameAndValue> getCurrencies() {
        log.info("CurrencyService method getCurrencies executed");
        List<Currency> currency = repository.findAll();
        return mapper.convertToListCurrencyNameAndValue(currency);
    }

    public Double convertValue(Long value, Long numCode) {
        log.info("CurrencyService method convertValue executed");
        Currency currency = repository.findByIsoNumCode(numCode);
        return value * currency.getValue();
    }

    public CurrencyDto create(CurrencyDto dto) {
        log.info("CurrencyService method create executed");
        return mapper.convertToDto(repository.save(mapper.convertToEntity(dto)));
    }

    public void save(CurrencyDto dto) {
        log.info("CurrencyService method save executed");
        Currency currency = repository.findByIsoNumCode(dto.getIsoNumCode());
        if (currency == null) {
            currency = mapper.convertToEntity(dto);
        } else {
            mapper.updateCurrencyFromDto(dto, currency);
        }
        repository.save(currency);
    }
}
