package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class MyKafkaProducer {

    @Inject
    @Channel("my-producer")
    Emitter<String> emitter;

    public void sendMessage(String message){
        emitter.send(message);
    }
}
