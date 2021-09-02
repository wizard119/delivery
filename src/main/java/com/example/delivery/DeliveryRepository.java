package com.example.delivery;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    List<Delivery> findByPaymentId(Long paymentId);

}