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
    @Column()
    private long id;


    @OneToMany(mappedBy = "vaultAccount")
    @JsonIgnore
    private List<Vault> vaults;
}
