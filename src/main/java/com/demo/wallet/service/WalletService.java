package com.demo.wallet.service;

import java.util.List;

public interface WalletService {

    List<Integer> getNotesForAmount(List<Integer> wallet, List<Integer> notesRequired, int amount );
}
