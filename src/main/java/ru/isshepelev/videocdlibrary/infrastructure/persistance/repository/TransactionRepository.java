package ru.isshepelev.videocdlibrary.infrastructure.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Transaction;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.User;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Video;

import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsByUserAndVideoAndIsPurchase(User user, Video video, boolean isPurchase);

    boolean existsByUserAndVideoAndIsPurchaseAndExpirationDateAfter(
            User user, Video video, boolean isPurchase, LocalDateTime date);
}
