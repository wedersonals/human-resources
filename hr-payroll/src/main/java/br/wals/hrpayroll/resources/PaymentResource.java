package br.wals.hrpayroll.resources;

import br.wals.hrpayroll.entities.Payment;
import br.wals.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    private final PaymentService service;

    public PaymentResource(PaymentService service) {
        this.service = service;
    }

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping("/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable("workerId") Long workerId,
                                              @PathVariable("days") int days) {
        return ResponseEntity.ok(service.getPayment(workerId, days));
    }

    public ResponseEntity<Payment> getPaymentAlternative(Long workerId, int days) {
        Payment payment = new Payment("Bran", 400.0, days);
        return ResponseEntity.ok(payment);
    }
}
