package ps2.PS2.service;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import ps2.PS2.config.Coada;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;


public class ServiceRabbit
{
    private final String filePath;

    private final RabbitTemplate template;

    public ServiceRabbit(String filePath){
        this.filePath = filePath;
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory("sparrow.rmq.cloudamqp.com");
        connectionFactory.setUsername("prrmbqpm");
        connectionFactory.setPassword("owRDY0mb45_WXFwT3gBwsUyZz7UH_Zw4");
        connectionFactory.setVirtualHost("prrmbqpm");

        //Recommended settings
        connectionFactory.setRequestedHeartBeat(500
        );
        connectionFactory.setConnectionTimeout(30000);

        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        Queue queue = new Queue("GligorM2");
        admin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange("exchange");
        admin.declareExchange(exchange);
        admin.declareBinding(
                BindingBuilder.bind(queue).to(exchange).with("foo.*"));


        template = new RabbitTemplate(connectionFactory);
    }

    public void read() throws Exception{
        Scanner sc = new Scanner(new File(filePath));

        //sc.useDelimiter(",");
        UUID id = UUID.randomUUID();
        while(sc.hasNext()){
            LocalDateTime time = LocalDateTime.now();
            Coada a = new Coada(Timestamp.valueOf(time),
                    id,
                    Float.parseFloat(sc.next())
            );
            System.out.println("[x] Sending: " + a);
            template.convertAndSend("myExchange", "foo.bar", a.toString());
            Thread.sleep(10000);
        }


    }
}
