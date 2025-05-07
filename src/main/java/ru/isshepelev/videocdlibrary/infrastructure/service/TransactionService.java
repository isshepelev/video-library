package ru.isshepelev.videocdlibrary.infrastructure.service;

import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Transaction;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.User;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Video;

import java.util.List;

public interface TransactionService {

    void buy(Long videoId, String username);

    void rent(Long videoId, String username, int days);

    List<Transaction> getUserTransactions(String username);

    boolean hasAccessToVideo(User user, Video video);
}
