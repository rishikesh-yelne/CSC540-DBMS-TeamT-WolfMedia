package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.dto.ArtistPaymentDTO;
import edu.ncsu.csc540.s23.backend.model.dto.PayRecordDTO;
import edu.ncsu.csc540.s23.backend.model.dto.PodcastHostPaymentDTO;
import edu.ncsu.csc540.s23.backend.model.dto.RecordLabelPaymentDTO;
import edu.ncsu.csc540.s23.backend.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/record-label/{id}/{month}/{year}")
    public Double getPaymentToRecordLabel(@PathVariable Long id, @PathVariable int month, @PathVariable int year) {
        return this.paymentService.getPaymentToRecordLabel(id, month, year);
    }


    @GetMapping("/artist/{id}/{month}/{year}")
    public Double getPaymentToArtist(@PathVariable Long id, @PathVariable int month, @PathVariable int year) {
        return this.paymentService.getPaymentToArtist(id, month, year);
    }

    @GetMapping("/podcast-host/{id}/{month}/{year}")
    public Double getPaymentToPodcastHost(@PathVariable Long id, @PathVariable int month, @PathVariable int year) {
        return this.paymentService.getPaymentToPodcastHost(id, month, year);
    }

    @GetMapping("/record-labels")
    public List<RecordLabelPaymentDTO> getPaymentToRecordLabels() { return this.paymentService.getPaymentToRecordLabels(); }


    @GetMapping("/artists")
    public List<ArtistPaymentDTO> getPaymentToArtists() { return this.paymentService.getPaymentToArtists(); }


    @GetMapping("/podcast-hosts")
    public List<PodcastHostPaymentDTO> getPaymentToPodcastHosts() { return this.paymentService.getPaymentToPodcastHosts(); }

    @PostMapping("/pay-record-label")
    public String payRecordLabel(
            @RequestBody PayRecordDTO payment,
            @RequestParam(required = false) Optional<Integer> month,
            @RequestParam(required = false) Optional<Integer> year) {
        return this.paymentService.payRecordLabel(payment, month, year);
    }
}
