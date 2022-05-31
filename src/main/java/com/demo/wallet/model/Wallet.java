package com.demo.wallet.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    private String label;

    @Singular("note")
    public  List<Integer> notes;

}
