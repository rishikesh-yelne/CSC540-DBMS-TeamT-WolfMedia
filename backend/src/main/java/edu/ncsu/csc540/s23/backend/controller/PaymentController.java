package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.dto.*;
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

    //    gets payment of record label given its id
    @GetMapping("/record-label/{id}/{month}/{year}")
    public Double getPaymentToRecordLabel(@PathVariable Long id, @PathVariable int month, @PathVariable int year) {
        return this.paymentService.getPaymentToRecordLabel(id, month, year);
    }

//    gets payment of artist given its id
    @GetMapping("/artist/{id}/{month}/{year}")
    public Double getPaymentToArtist(@PathVariable Long id, @PathVariable int month, @PathVariable int year) {
        return this.paymentService.getPaymentToArtist(id, month, year);
    }

//    gets payment of podcast host given its id
    @GetMapping("/podcast-host/{id}/{month}/{year}")
    public Double getPaymentToPodcastHost(@PathVariable Long id, @PathVariable int month, @PathVariable int year) {
        return this.paymentService.getPaymentToPodcastHost(id, month, year);
    }

//    fetches revenue of Wolfmedia for a particular month and year
    @GetMapping("/revenue/{month}/{year}")
    public Double getRevenue(@PathVariable int month, @PathVariable int year) {
        return this.paymentService.getRevenue(month, year);
    }

    //    fetches the payments made to all record labels
    @GetMapping("/record-labels")
    public List<RecordLabelPaymentDTO> getPaymentToRecordLabels() { return this.paymentService.getPaymentToRecordLabels(); }

    //    fetches the payments made to all artists
    @GetMapping("/artists")
    public List<ArtistPaymentDTO> getPaymentToArtists() { return this.paymentService.getPaymentToArtists(); }

    //    fetches the payments made to all podcast hosts
    @GetMapping("/podcast-hosts")
    public List<PodcastHostPaymentDTO> getPaymentToPodcastHosts() { return this.paymentService.getPaymentToPodcastHosts(); }

    // fetches total revenue
    @GetMapping("/revenue")
    public List<RevenueDTO> getRevenue() { return this.paymentService.getRevenue(); }

    //    adds payment of record label given its id, month and year
    @PostMapping("/pay-record-label")
    public String payRecordLabel(
            @RequestParam Long recordLabelId,
            @RequestParam(required = false) Optional<Integer> month,
            @RequestParam(required = false) Optional<Integer> year) {
        return this.paymentService.payRecordLabel(recordLabelId, month, year);
    }

    //    adds payment of artist given its id, month and year
    @PostMapping("/pay-artist")
    public String payArtist(
            @RequestParam Long artistId,
            @RequestParam(required = false) Optional<Integer> month,
            @RequestParam(required = false) Optional<Integer> year) {
        return this.paymentService.payArtist(artistId, month, year);
    }

    //    adds payment of podcast host given its id, month and year
    @PostMapping("/pay-podcast-host")
    public String payPodcastHost(
            @RequestParam Long podcastHostId,
            @RequestParam(required = false) Optional<Integer> month,
            @RequestParam(required = false) Optional<Integer> year) {
        return this.paymentService.payPodcastHost(podcastHostId, month, year);
    }

    //    adds payment by user given its id, month and year
    @PostMapping("/pay-subscription")
    public String paySubscription(@RequestBody PaySubscriptionDTO payload) {
        return this.paymentService.paySubscription(payload);
    }
}
