package ru.isshepelev.videocdlibrary.infrastructure.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Video video;

    @Column(name = "is_purchase")
    private boolean isPurchase;  // true для покупки false для аренды
    private LocalDateTime transactionDate;
    private LocalDateTime expirationDate;
}