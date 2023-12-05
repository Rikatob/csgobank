package com.pg3402.csgobank.vaultAccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pg3402.csgobank.vault.Vault;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vault_account")
public class VaultAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long id;


    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Vault> vaults;
}
