package org.jastka4.digitalgamesstore.rabbitmq;

import org.jastka4.digitalgamesstore.data.ProductData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RabbitMQSender {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQSender.class);

    @Resource
    private AmqpTemplate rabbitTemplate;

    @Resource
    private Queue queue;

    public void send(final ProductData productData) {
        this.rabbitTemplate.convertAndSend(queue.getName(), productData);
        LOG.info("Sent a message to RabbitMQ: '{}'", productData);
    }
}
