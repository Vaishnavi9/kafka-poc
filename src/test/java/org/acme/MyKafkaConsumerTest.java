package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.spi.Connector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(KafkaTestResourceLifecycleManager.class)
class MyKafkaConsumerTest {

    @Inject
    @Connector("smallrye-in-memory")
    InMemoryConnector connector;

    @Test
    void test() throws InterruptedException {
        InMemorySource<String> consumer = connector.source("my-consumer");
        InMemorySink<String> producer = connector.sink("my-producer");
        String message = "test-generated-message";
        consumer.send(message);
        Thread.sleep(5000);
        String receivedMessage = producer.received().get(0).getPayload();
        Assertions.assertEquals(message.toUpperCase(), receivedMessage);
    }
}
