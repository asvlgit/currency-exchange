package ru.skillbox.currency.exchange.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.entity.CurrencyXml;
import ru.skillbox.currency.exchange.mapper.CurrencyMapper;
import ru.skillbox.currency.exchange.tools.CurrencyXmlParser;

@Slf4j
@RequiredArgsConstructor
@Service
@EnableScheduling
public class CurrencyLoader {

    private final CurrencyXmlParser currencyXmlParser;
    private final CurrencyService service;
    private final CurrencyMapper mapper;

    @Value("${api.central_bank_currency_url}")
    private final String xmlUrl;

    @Scheduled(fixedDelay = 3_600_000)
    public void load() {
        CurrencyXml currencyXml = getCurrencyXml();
        currencyXml.getValutes().forEach(c->{
            CurrencyDto currency = mapper.convertCurrencyXmlToDto(c);
            service.save(currency);
        });
    }

    public CurrencyXml getCurrencyXml() {
        RestTemplate restTemplate = new RestTemplate();
        String xmlData = restTemplate.getForObject(xmlUrl, String.class);
        return currencyXmlParser.toObject(xmlData);
    }
}
