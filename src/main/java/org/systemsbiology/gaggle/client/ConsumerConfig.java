package org.systemsbiology.gaggle.client;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Demonstrate consuming JMS messages using an ActiveMQ provider.
 * (Adapted from ConsumerTool.java in the ActiveMQ examples.)
 * 
 * To become a consumer, we start by implementing MessageListener 
 * to receive JMS messages and ExceptionListener to get notified 
 * of JMS exceptions
 */
public class ConsumerConfig implements MessageListener, ExceptionListener {

  // url, user, password needed to connect to the ActiveMQ server
  private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
  private String user = ActiveMQConnection.DEFAULT_USER;
  private String password = ActiveMQConnection.DEFAULT_PASSWORD;
  
  // Name of the topic or queue (defined below)
  private String subject = "DEFAULT.SUBJECT";
  private boolean topic = true; // use a topic by default
  
  // A durable consumer is one which notifies the server to hold a message
  // if the consumer is temporarily unavailable.  If true, we need to 
  // provide a consumer name when the session is created.
  private boolean durable = false;
  private String consumerName = "GaggleClient";
  
  private MessageListener messageListener = this;
  
  private ExceptionListener exceptionListener = this;
  
  public ConsumerConfig() {
  }
  
  // Starts up a JMS consumer, which blocks forever waiting for messages
  public static void main(String[] args) {
    ConsumerConfig consumerTool = new ConsumerConfig();
    consumerTool.connect();
  }
  

  public void connect() {
    System.out.println("Connecting to URL: " + url);
    System.out.println("Consuming " + (topic ? "topic" : "queue") + ": " + subject);
    System.out.println("Using a " + (durable ? "durable" : "non-durable") + " subscription");

    try {
      // Create and start connection to the JMS server, then create a session
      Connection connection = new ActiveMQConnectionFactory(user, password, url).createConnection();
      connection.setExceptionListener(exceptionListener); // We want to be notified of JMS exceptions
      connection.start();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      
      // Create a topic or a queue (generically referred to as destinations) to listen on.
      // Topics use publish/subscribe model while queues use a point-to-point model 
      Destination destination = null;
      if (topic) {
        destination = session.createTopic(subject);
      }
      else {
        destination = session.createQueue(subject);
      }

      // Create the consumer (ie, message listener)
      MessageConsumer consumer = null;
      if (durable && topic) {
        consumer = session.createDurableSubscriber((Topic) destination, consumerName);
      }
      else {
        consumer = session.createConsumer(destination);
      }
      
      // Once we set the message listener its onMessage() method can be called
      consumer.setMessageListener(messageListener);
    }
    catch (Exception e) {
      System.out.println("Caught: " + e);
      e.printStackTrace();
    }
  }

  // Satisfies the MessageListener interface, called every time we receive a message
  public void onMessage(Message message) {
    System.out.println("Default consumer received: " + message);
  }


  // Satisfies ExceptionListener interface
  public synchronized void onException(JMSException ex) {
    System.out.println("JMS Exception occured.");
    ex.printStackTrace();
  }
  
  //--------------------------
  // setters/getters
  //--------------------------

  public void setConsumerName(String consumerName) {
    this.consumerName = consumerName;
  }

  public void setDurable(boolean durable) {
    this.durable = durable;
  }

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

  public void setUrl(String url) {
    this.url = url;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public MessageListener getMessageListener() {
    return messageListener;
  }

  public void setMessageListener(MessageListener messageListener) {
    this.messageListener = messageListener;
  }

  public ExceptionListener getExceptionListener() {
    return exceptionListener;
  }

  public void setExceptionListener(ExceptionListener exceptionListener) {
    this.exceptionListener = exceptionListener;
  }

  public String getUrl() {
    return url;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public String getSubject() {
    return subject;
  }

  public boolean isTopic() {
    return topic;
  }

  public boolean isDurable() {
    return durable;
  }

  public String getConsumerName() {
    return consumerName;
  }

}
