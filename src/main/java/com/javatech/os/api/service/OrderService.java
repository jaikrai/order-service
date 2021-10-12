package com.javatech.os.api.service;

import com.javatech.os.api.common.Payment;
import com.javatech.os.api.common.TransactionRequest;
import com.javatech.os.api.common.TransactionResponse;
import com.javatech.os.api.entity.Order;
import com.javatech.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    // injecting rest template
    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getAmount());
        // rest api call -- to do the call first we need to inject the rest template
       Payment paymentResponse =  template.postForObject("http://localhost:9091/payment/doPayment", payment, Payment.class);
       response = paymentResponse.getPaymentStatus().equals("success")?"Payment processing successful and placed":"there is a failure in payment api, order added to cart";
      repository.save(order);
      return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),response);

    }

}
