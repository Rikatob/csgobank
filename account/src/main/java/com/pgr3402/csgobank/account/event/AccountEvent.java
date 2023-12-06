package com.pgr3402.csgobank.account.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEvent implements Serializable {

    private long accountId;
    private AccountEventEnum eventType;
}
