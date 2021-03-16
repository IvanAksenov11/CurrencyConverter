package com.example.currencyconverter.repos;

import com.example.currencyconverter.domain.Valute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuteRepo extends JpaRepository<Valute, String> {
}
