package org.jastka4.digitalgamesstore.rabbitmq;

import org.jastka4.digitalgamesstore.data.ProductData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${config.rabbitmq.queue}")
    public void receiveMessage(final ProductData product) {
        LOG.info("Received a message from RabbitMQ: {}", product);
    }
}
