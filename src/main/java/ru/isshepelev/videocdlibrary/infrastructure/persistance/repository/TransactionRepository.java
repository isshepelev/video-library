package ru.isshepelev.videocdlibrary.infrastructure.persistance.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Transaction;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.User;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Video;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsByUserAndVideoAndIsPurchase(User user, Video video, boolean isPurchase);

    boolean existsByUserAndVideoAndIsPurchaseAndExpirationDateAfter(
            User user, Video video, boolean isPurchase, LocalDateTime date);

    List<Transaction> findByUser(User user);

    @Query("SELECT t FROM Transaction t WHERE t.user = :user AND t.isPurchase = true ORDER BY t.transactionDate DESC")
    List<Transaction> findPurchaseTransactionsByUser(@Param("user") User user);
}
