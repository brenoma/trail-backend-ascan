package br.com.atlantico.brenoararipe.subsapi.connection;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.Queue;

import static constants.RabbitMQConstants.QUEUE_SUBSCRIPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


public class RabbitMQConnectionTest extends RabbitMQConnection {

    @InjectMocks
    private RabbitMQConnection rabbitMQConnection;

    @Mock
    private Queue queue;

    @Test
    public void test1() {
//        // Arrange
//        doReturn(queue).when(rabbitMQConnection.queue(QUEUE_SUBSCRIPTION));
        when(rabbitMQConnection.queue(QUEUE_SUBSCRIPTION)).thenReturn(queue);
//
//        // Act
        Queue queue2 = rabbitMQConnection.queue(QUEUE_SUBSCRIPTION);
//
//        // Assert
        assertEquals(Queue.class, queue2.getClass());
    }
}
