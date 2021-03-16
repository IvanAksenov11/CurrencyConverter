package com.example.currencyconverter.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="valute")
@Data
public class Valute {
    public Valute(String id, String numCode, String charCode, Integer nominal, String name, Double value) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    @Id
    private String id;
    private String numCode;
    private String charCode;
    private Integer nominal;
    private String name;
    private Double value;
}
