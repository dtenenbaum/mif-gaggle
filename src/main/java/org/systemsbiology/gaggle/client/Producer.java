package org.systemsbiology.gaggle.client;

import gov.pnnl.mif.MifException;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Demonstrate sending jms messages using an ActiveMQ provider
 * Adapted from ActiveMQ example ProducerTool.java
 */
public class Producer {

  // url, user, password needed to connect to the ActiveMQ server
  private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
  private String user = ActiveMQConnection.DEFAULT_USER;
  private String password = ActiveMQConnection.DEFAULT_PASSWORD;
  
  //Name of the topic or queue (defined below)
  private String subject = "DEFAULT.SUBJECT";
  private boolean topic = true; // use a topic by default
  
  private Session session = null;
  
  private MessageProducer producer = null;
  
  // Whether the session is transacted (defined below)
  private boolean transacted = false;
  
  private  Connection connection = null;
  
  private boolean connected = false;
  
  public Producer() {
  }

  public Producer(String subject) {
    this();
    setSubject(subject);
  }
  
  // Create a producer which sends a number of messages to a destination
  public static void main(String[] args) throws Exception {
    Producer producer = new Producer();
    producer.connect();
    
    for (int i=0; i < 10; i++) {
      System.out.println("sending: " + i);
      producer.send("message-" + i);
    }
    producer.disconnect();
  }

  public void connect() {
    System.out.println("Connecting to URL: " + url);
    System.out.println("Publishing to " + (topic ? "topic" : "queue") + ": " + subject);
    try {
      // Create and start the connection to the JMS server, then create session
      connection = new ActiveMQConnectionFactory(user, password, url).createConnection();
      connection.start();
      session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);

      // Create a topic or a queue (generically referred to as destinations) to send messages to.
      // Topics use publish/subscribe model while queues use a point-to-point model 
      Destination destination = null;
      if (topic) {
        destination = session.createTopic(subject);
      }
      else {
        destination = session.createQueue(subject);
      }

      // Create the producer (ie, message sender)
      producer = session.createProducer(destination);
      producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
      connected = true;
    }
    catch (Exception e) {
      System.out.println("Caught: " + e);
      e.printStackTrace();
    }
  }
  
  public void disconnect() throws Exception {
    connection.close();
    connected = false;
  }
  
  private void send(Message message) throws Exception {
    producer.send(message);
  }
  
  public void send(String text) throws Exception {
    assertConnected();
    TextMessage message = session.createTextMessage(text);
    send(message);
  }
  
  public void send(Serializable object) throws Exception {
    assertConnected();
    ObjectMessage message = session.createObjectMessage();
    message.setObject(object);
    send(message);
  }
  
  private void assertConnected() throws Exception {
    if (!connected) {
      throw new MifException("Not connected to JMS. Must connect before sending with: Producer.connect()");
    }
  }
  
  public void commit() throws Exception {
    if (transacted) {
      session.commit();
    }
  }

  // ------------------------
  // Setters and getters
  // ------------------------

  public void setPassword(String pwd) {
    this.password = pwd;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setTopic(boolean topic) {
    this.topic = topic;
  }

  public void setQueue(boolean queue) {
    this.topic = !queue;
  }

  public void setTransacted(boolean transacted) {
    this.transacted = transacted;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setUser(String user) {
    this.user = user;
  }
}
