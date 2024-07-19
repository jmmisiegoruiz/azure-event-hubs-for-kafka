package org.example.springkafkaehproducer;

import org.example.springkafkaehproducer.service.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringKafkaEhProducerApplication {

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private final Producer producer;

    @Autowired
    SpringKafkaEhProducerApplication(Producer producer) {
        this.producer = producer;
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringKafkaEhProducerApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            for (String arg : args) {
                switch (arg) {
                    case "--producer":
                        this.producer.sendMessage("awalther", "t-shirts");
                        this.producer.sendMessage("htanaka", "t-shirts");
                        this.producer.sendMessage("htanaka", "batteries");
                        this.producer.sendMessage("eabara", "t-shirts");
                        this.producer.sendMessage("htanaka", "t-shirts");
                        this.producer.sendMessage("jsmith", "book");
                        this.producer.sendMessage("awalther", "t-shirts");
                        this.producer.sendMessage("jsmith", "batteries");
                        this.producer.sendMessage("jsmith", "gift card");
                        this.producer.sendMessage("eabara", "t-shirts");
                        break;
                    case "--consumer":
                        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("test");
                        listenerContainer.start();
                        break;
                    default:
                        break;
                }
            }
        };
    }
}
