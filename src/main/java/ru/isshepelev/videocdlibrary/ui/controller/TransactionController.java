package ru.isshepelev.videocdlibrary.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isshepelev.videocdlibrary.infrastructure.service.TransactionService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactional")
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping("/buy/{videoId}")
    public ResponseEntity<?> buyVideoToUser(@PathVariable Long videoId, Principal principal) {
        try {
            transactionService.buy(videoId, principal.getName());
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Произошла ошибка при покупке видео");
        }
    }

    @PostMapping("/rent/{videoId}")
    public ResponseEntity<?> arendVideoToUser(@PathVariable Long videoId,
                                              Principal principal,
                                              @RequestParam int days) {
        try {
            transactionService.rent(videoId, principal.getName(), days);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Произошла ошибка при аренде видео");
        }
    }

}
