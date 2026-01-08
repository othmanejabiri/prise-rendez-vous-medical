package com.medical.notificationservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic appointmentBookedTopic() {
        return new NewTopic("appointment-booked", 1, (short) 1);
    }

    @Bean
    public NewTopic consultationCompletedTopic() {
        return new NewTopic("consultation-completed", 1, (short) 1);
    }
}
