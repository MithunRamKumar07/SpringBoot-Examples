package com.mithun.springboot.transactional.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BANK_ACCOUNT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    long accountNumber;
    @JsonProperty(value = "employeeIdAcc")
    int employeeId;
    String bankName;
}
