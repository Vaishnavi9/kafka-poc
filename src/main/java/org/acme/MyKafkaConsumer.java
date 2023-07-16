package org.acme;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class MyKafkaConsumer {

    @Inject
    MyKafkaProducer kafkaProducer;

    public MyKafkaConsumer (MyKafkaProducer myKafkaProducer) {
        this.kafkaProducer = myKafkaProducer;
    }

    @Incoming("my-consumer")
    public void consume (String message) {
        System.out.println("before "+message);
        if (null != message) kafkaProducer.sendMessage(message.toUpperCase());
        System.out.println("after "+message);
    }
}