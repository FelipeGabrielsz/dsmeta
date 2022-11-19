package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {

        //LocalDate.ofInstant pegando a data do instante que estamos
        //Instant.now() pegando a data de extamente agora
        //ZoneId.systemDefault() pegando o fusohorario de a gora
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        //Transformando "parse" string em um LocalDate
        //minusDays subtrai as datas, nesse caso estamos diminuindo 1 ano (365 dias)
        LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);

        //Usando expressao condiconal ternaria
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        return repository.findSales(min, max, pageable);
    }
}