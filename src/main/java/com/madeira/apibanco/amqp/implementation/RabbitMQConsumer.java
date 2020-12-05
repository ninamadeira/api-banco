package com.madeira.apibanco.amqp.implementation;


import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.madeira.apibanco.amqp.AmqpConsumer;
import com.madeira.apibanco.dto.CorrentistaNewDTO;
import com.madeira.apibanco.services.ConsumerService;

@Component
public class RabbitMQConsumer implements AmqpConsumer<CorrentistaNewDTO> {

    @Autowired
    private ConsumerService consumerService;

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(CorrentistaNewDTO correntistaNewDTO) {
        try {
            consumerService.action(correntistaNewDTO);
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
