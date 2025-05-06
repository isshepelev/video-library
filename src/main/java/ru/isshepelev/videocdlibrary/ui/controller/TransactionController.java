package ru.isshepelev.videocdlibrary.ui.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.isshepelev.videocdlibrary.infrastructure.service.TransactionService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactional")
public class TransactionController {
    private final TransactionService transactionService;


    @PostMapping("/buy/{videoId}")
    public void buyVideoToUser(@PathVariable Long videoId, Principal principal){
        transactionService.buy(videoId, principal.getName());
    }

    @PostMapping("/rent/{videoId}")
    public void arendVideoToUser(@PathVariable Long videoId, Principal principal, @RequestParam int days){
        transactionService.rent(videoId, principal.getName(), days);
    }

}
