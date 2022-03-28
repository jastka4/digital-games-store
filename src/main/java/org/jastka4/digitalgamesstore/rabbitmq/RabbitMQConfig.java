package org.jastka4.digitalgamesstore.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${config.rabbitmq.queue}")
    String queueName;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }
}
