package br.wals.hrpayroll.services;

import br.wals.hrpayroll.entities.Payment;
import br.wals.hrpayroll.entities.Worker;
import br.wals.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {

        this.workerFeignClient = workerFeignClient;
    }

    public Payment getPayment(long workerId, int days) {
        var worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
