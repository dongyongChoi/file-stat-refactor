package com.toyproject.filestatrefactor.file.infrastructure.messaging;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileChangeCdcConsumer {

    @KafkaListener(topics = {
            "${app.debezium.topics.files}",
            "${app.debezium.topics.file-hist}",
            "${app.debezium.topics.folder}"
    })
    public void logFileChange(ConsumerRecord<String, String> record) {
        log.info(
                "CDC event received. topic={}, partition={}, offset={}, key={}, payload={}",
                record.topic(),
                record.partition(),
                record.offset(),
                record.key(),
                record.value()
        );
    }
}
