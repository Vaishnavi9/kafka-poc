package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class MyKafkaConsumerTest {

    @Inject
    MyKafkaConsumer kafkaConsumer;
    @Inject
    MyKafkaProducer kafkaProducer;

    @Test
    void test() {
        kafkaConsumer.consume("Test message");
    }
}
