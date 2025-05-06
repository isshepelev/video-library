package ru.isshepelev.videocdlibrary.infrastructure.service;

import java.security.Principal;

public interface TransactionService {

    void buy(Long videoId, String username);

    void rent(Long videoId, String username, int days);

}
