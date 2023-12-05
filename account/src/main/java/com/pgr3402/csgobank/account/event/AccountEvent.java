package com.pgr3402.csgobank.account.event;

import lombok.Value;

import java.io.Serializable;

@Value
public class AccountEvent implements Serializable {

    Long accountId;
}
