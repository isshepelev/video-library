package ru.isshepelev.videocdlibrary.infrastructure.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Transaction;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.User;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Video;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.TransactionRepository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.UserRepository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.VideoRepository;
import ru.isshepelev.videocdlibrary.infrastructure.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    @Override
    public void buy(Long videoId, String username) {
        User user = findUserOrThrow(username);
        Video video = findVideoOrThrow(videoId);

        checkExistingPurchase(user, video);

        Transaction transaction = createTransaction(user, video, true, null);
        transactionRepository.save(transaction);

        log.info("Пользователь {} купил видео с id: {}", username, videoId);
    }

    @Override
    public void rent(Long videoId, String username, int days) {
        User user = findUserOrThrow(username);
        Video video = findVideoOrThrow(videoId);

        checkExistingRental(user, video);

        LocalDateTime expirationDate = LocalDateTime.now().plusDays(days);
        Transaction transaction = createTransaction(user, video, false, expirationDate);
        transactionRepository.save(transaction);

        log.info("Пользователь {} арендовал видео с id: {} на {} дней", username, videoId, days);
    }

    @Override
    public List<Transaction> getUserTransactions(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return transactionRepository.findByUser(user);
    }

    private User findUserOrThrow(String username) {
        if (userRepository.existsByUsername(username)) {
            return userRepository.findByUsername(username);
        }else {
            log.error("Пользователь с именем {} не найден", username);
            throw new UsernameNotFoundException("User not found by username " + username);
        }
    }

    private Video findVideoOrThrow(Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> {
                    log.error("Видео с id {} не найдено", videoId);
                    return new NullPointerException("Video not found by id: " + videoId);
                });
    }

    private void checkExistingPurchase(User user, Video video) {
        boolean alreadyPurchased = transactionRepository.existsByUserAndVideoAndIsPurchase(user, video, true);
        if (alreadyPurchased) {
            log.error("Пользователь {} уже купил видео с id {}", user.getUsername(), video.getId());
            throw new IllegalStateException("Видео уже куплено");
        }
    }

    private void checkExistingRental(User user, Video video) {
        boolean activeRentalExists = transactionRepository.existsByUserAndVideoAndIsPurchaseAndExpirationDateAfter(
                user, video, false, LocalDateTime.now());
        if (activeRentalExists) {
            log.error("Пользователь {} уже арендовал видео с id {} и аренда еще активна",
                    user.getUsername(), video.getId());
            throw new IllegalStateException("Видео уже арендовано и аренда еще активна");
        }
    }

    private Transaction createTransaction(User user, Video video, boolean isPurchase, LocalDateTime expirationDate) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setVideo(video);
        transaction.setPurchase(isPurchase);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setExpirationDate(expirationDate);
        return transaction;
    }



    @Override
    public boolean hasAccessToVideo(User user, Video video) {
        boolean hasPurchase = transactionRepository.existsByUserAndVideoAndIsPurchase(user, video, true);
        boolean hasActiveRental = transactionRepository.existsByUserAndVideoAndIsPurchaseAndExpirationDateAfter(
                user, video, false, LocalDateTime.now());

        return hasPurchase || hasActiveRental;
    }
}
