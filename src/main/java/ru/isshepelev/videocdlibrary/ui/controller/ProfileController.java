package ru.isshepelev.videocdlibrary.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Transaction;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.User;
import ru.isshepelev.videocdlibrary.infrastructure.service.TransactionService;
import ru.isshepelev.videocdlibrary.infrastructure.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final TransactionService transactionService;


    @GetMapping("")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);

        List<Transaction> transactions = transactionService.getUserTransactions(username);

        List<Transaction> purchases = transactions.stream()
                .filter(Transaction::isPurchase)
                .collect(Collectors.toList());

        List<Transaction> activeRentals = transactions.stream()
                .filter(t -> !t.isPurchase() &&
                        (t.getExpirationDate() == null || t.getExpirationDate().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());

        model.addAttribute("user", user);
        model.addAttribute("purchases", purchases);
        model.addAttribute("rentals", activeRentals);
        return "profile";
    }
}
