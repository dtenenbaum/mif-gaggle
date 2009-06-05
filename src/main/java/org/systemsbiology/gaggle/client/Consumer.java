package org.systemsbiology.gaggle.client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * Demonstrate consuming JMS messages using an ActiveMQ provider.
 * (Adapted from ConsumerTool.java in the ActiveMQ examples.)
 * 
 * To become a consumer, we start by implementing MessageListener 
 * to receive JMS messages and ExceptionListener to get notified 
 * of JMS exceptions
 */
public class Consumer implements MessageListener {

  private ConsumerConfig config = new ConsumerConfig();
  
  public Consumer() {
    config.setTopic(true);
    config.setMessageListener(this);
  }

  public Consumer(String subject) {
    this();
    config.setSubject(subject);
  }
  
  // Starts up a JMS consumer, which blocks forever waiting for messages
  public static void main(String[] args) {
    Consumer consumer = new Consumer();
    consumer.run();
  }
  
  public void run() {
    config.connect();
  }

  // Satisfies the MessageListener interface, called every time we receive a message
  public void onMessage(Message message) {
    try {
      if (message instanceof TextMessage) {
        TextMessage txtMsg = (TextMessage) message;
        System.out.println("Received text: " + txtMsg.getText());
      }
      else if (message instanceof ObjectMessage) {
        ObjectMessage objMsg = (ObjectMessage) message;
        System.out.println("Received object: " + objMsg.getObject());
      }
      // Could also be of type BytesMessage which provides varios read() 
      // methods to get the data
      else { 
        System.out.println("Received: " + message);
      }
      
      //--------------------------
      // Do application stuff here!
      //--------------------------
    }
    catch (JMSException e) {
      System.out.println("Caught: " + e);
      e.printStackTrace();
    }
  }
}
