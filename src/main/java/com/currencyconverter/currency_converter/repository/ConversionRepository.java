package com.currencyconverter.currency_converter.repository;

import com.currencyconverter.currency_converter.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRepository extends JpaRepository<Conversion, Long> {
}
