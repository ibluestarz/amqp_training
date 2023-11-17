package fr.lernejo.chat;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Launcher {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Launcher.class)) {
            RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your messages. Press Enter to send (type 'exit' to quit):");

            while (true) {
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                rabbitTemplate.convertAndSend("", "chat_messages", message);
            }
        }
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Queue queue) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(queue.getName());
        rabbitTemplate.setDefaultReceiveQueue(queue.getName());
        return rabbitTemplate;
    }
}
