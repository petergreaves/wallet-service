package com.demo.wallet.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private List<Integer> notesRequired = null;

    @Override
    public List<Integer> getNotesForAmount(List<Integer> wallet, List<Integer> _notesRequired, int value) {

        Integer sum = wallet.stream()
                .reduce(0, Integer::sum);

        if (value > sum) {
            System.out.println("Not enough money");
            return new ArrayList<Integer>();
        } else {
            return populate(wallet, _notesRequired, value);
        }

//        List<Integer> notesRequired = _notesRequired;
//        if (value>0){
//            Integer thisNote=wallet.get(0);
//            if (thisNote>value){
//                wallet.remove(0);
//            }
//            else{
//                value -= thisNote;
//                wallet.remove(0);
//                notesRequired.add(thisNote);
//            }
//            getNotesForAmount(wallet, _notesRequired, value);
//        }
//        return notesRequired;
    }

    private List<Integer> populate(List<Integer> wallet, List<Integer> _notesRequired, int value) {

        notesRequired = _notesRequired;
        if (value > 0) {
            Integer thisNote = wallet.get(0);
            if (thisNote > value) {
                wallet.remove(0);
            } else {
                value -= thisNote;
                wallet.remove(0);
                notesRequired.add(thisNote);
            }
            populate(wallet, _notesRequired, value);
        }
        return notesRequired;
    }

}

