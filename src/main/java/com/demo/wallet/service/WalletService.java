package com.demo.wallet.service;

import java.util.List;

public interface WalletService {

    List<Integer> getNotesForAmount(int amount);
}
