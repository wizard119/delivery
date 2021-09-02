package com.example.delivery;

import com.example.delivery.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyHandler {
    @Autowired DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_StartDelivery(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener StartDelivery : " + paymentApproved.toJson() + "\n\n");

        // Sample Logic //
        Delivery delivery = new Delivery();        
        delivery.setPaymentId(paymentApproved.getId());
        delivery.setProductId(paymentApproved.getProductId());
        //delivery.setStatus("DeliveryStarted");
        delivery.setAmt(paymentApproved.getAmt());
        deliveryRepository.save(delivery);            
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_CancelDelivery(@Payload PaymentCanceled paymentCanceled){

        if(!paymentCanceled.validate()) return;

        System.out.println("\n\n##### listener CancelDelivery : " + paymentCanceled.toJson() + "\n\n");

        // Sample Logic //
        List<Delivery> deliveryList = deliveryRepository.findByPaymentId(paymentCanceled.getId());
        if ((deliveryList != null) && !deliveryList.isEmpty()){
            deliveryRepository.deleteAll(deliveryList);
        }         
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}
}
