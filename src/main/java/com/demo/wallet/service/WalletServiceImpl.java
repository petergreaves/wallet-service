package com.demo.wallet.service;

import com.demo.wallet.model.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    private Wallet wallet;

    public WalletServiceImpl(@Autowired Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public List<Integer> getNotesForAmount(int value) {

        if (value < 1){
            log.info("Value is less than 1!");
            return new ArrayList<Integer>();
        }

        Integer sum = wallet
                .getNotes()
                .stream()
                .reduce(0, Integer::sum);

        if (value > sum) {
           log.info("Not enough money in the wallet!");
           return new ArrayList<Integer>();
        } else {
            // clone the notes from the wallet
            List<Integer> notes=new ArrayList<>();
            wallet.getNotes().stream().forEach(n -> notes.add(n));

            List<Integer> notesRequired = new ArrayList<>();

            return populate(notes, notesRequired, value).stream()
                    .sorted(Comparator.comparing(v -> v.intValue(), Comparator.reverseOrder()))
                    .map(v -> v.intValue())
                    .collect(Collectors.toList());
        }
    }

    private List<Integer> populate(List<Integer> notesFromWallet, List<Integer> notesRequired, int value) {

        if (value > 0 && !notesFromWallet.isEmpty()) {
            Integer thisNote = notesFromWallet.get(0);
            if (thisNote > value) {
                notesFromWallet.remove(0);
            } else {
                value -= thisNote;
                notesFromWallet.remove(0);
                notesRequired.add(thisNote);
            }
            populate(notesFromWallet, notesRequired, value);
        }
        return notesRequired;
    }

}

