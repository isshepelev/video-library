package ru.isshepelev.videocdlibrary.infrastructure.service;

import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Transaction;

import java.security.Principal;
import java.util.List;

public interface TransactionService {

    void buy(Long videoId, String username);

    void rent(Long videoId, String username, int days);

    List<Transaction> getUserTransactions(String username);

}
