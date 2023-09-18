package ps2.PS2.model;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ps2.PS2.repository.MeasurementRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Data implements CommandLineRunner {


        @Autowired
        protected final MeasurementRepository measurementRepository;

        private int i_d = 0;



    public Data(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }


    @Override
        public void run(String... args) throws Exception {
            CachingConnectionFactory connectionFactory=new CachingConnectionFactory("sparrow.rmq.cloudamqp.com");
            connectionFactory.setUsername("prrmbqpm");
            connectionFactory.setPassword("owRDY0mb45_WXFwT3gBwsUyZz7UH_Zw4");
            connectionFactory.setVirtualHost("prrmbqpm");

            //Recommended settings
            connectionFactory.setRequestedHeartBeat(500);
            connectionFactory.setConnectionTimeout(30000);

            SimpleMessageListenerContainer container =
                    new SimpleMessageListenerContainer(connectionFactory);

            List<Measurement> measurements = new ArrayList<>();

           UUID measureid = UUID.randomUUID();
            Object listener = new Object() {

                public void handleMessage(String foo) {

                    System.out.println("[x] Got: " + foo);
                    String[] params = foo.split(", ");
                    StringBuilder timestamp = new StringBuilder();
                    for(int c = 16 ; c < params[0].length() ; c++){
                        timestamp.append(params[0].charAt(c));
                    }
                    System.out.println(timestamp.toString());
                    StringBuilder id = new StringBuilder();
                    for(int c = 10 ; c < params[1].length() ; c++){
                        id.append(params[1].charAt(c));
                    }
                    System.out.println(id.toString());
                    StringBuilder value = new StringBuilder();
                    for(int c = 6 ; c < params[2].length()-1 ; c++){
                        value.append(params[2].charAt(c));
                    }

                    System.out.println(value);
                    measurementRepository.save(new Measurement(Float.parseFloat(value.toString()), Timestamp.valueOf(timestamp.toString()),false));

                }
            };

            MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
            container.setMessageListener(adapter);
            container.setQueueNames("GligorM2");
            container.start();
        }
}
