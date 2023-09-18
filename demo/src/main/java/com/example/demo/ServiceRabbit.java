package com.example.demo;


import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class  ServiceRabbit
{
    private String filePath;

    private RabbitTemplate template;

    public ServiceRabbit(String filePath){
        this.filePath = filePath;
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory("sparrow.rmq.cloudamqp.com");
        connectionFactory.setUsername("izihdkap");
        connectionFactory.setPassword("PzzrQlXVjzlIwQUwJDkOJr5dJYlwf0zA");
        connectionFactory.setVirtualHost("izihdkap");

        //Recommended settings
        connectionFactory.setRequestedHeartBeat(30);
        connectionFactory.setConnectionTimeout(30000);

        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        Queue queue = new Queue("rabbit");
        admin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange("myExchange");
        admin.declareExchange(exchange);
        admin.declareBinding(
                BindingBuilder.bind(queue).to(exchange).with("foo.*"));


        template = new RabbitTemplate(connectionFactory);
    }

    public void read() throws Exception{
        Scanner sc = new Scanner(new File(filePath));

        while(sc.hasNext()){
            LocalDateTime time = LocalDateTime.now();
            Coada a = new Coada(Timestamp.valueOf(time),
                    0,
                    Float.parseFloat(sc.next())
            );
            System.out.println("[x] Sending: " + a);
            template.convertAndSend("myExchange", "foo.bar", a.toString());
            Thread.sleep(20000);
        }


    }
}
