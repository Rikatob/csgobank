package com.pg3402.csgobank.vaultAccount.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AccountEvent implements Serializable {

    private long accountId;
    private AccountEventEnum eventType;

}
