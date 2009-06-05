package org.systemsbiology.gaggle.client;
import java.util.UUID;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.Serializable;
import org.systemsbiology.gaggle.types.DataMatrix;

public class ClientDriver {
  private Producer adminProducer;
  private Consumer adminConsumer;
  private Producer gaggleProducer;
  private Consumer gaggleConsumer;
  private String id;

  public ClientDriver() {
    id = UUID.randomUUID().toString();
    adminProducer = new Producer("adminSend");
    adminProducer.connect();
    adminConsumer = new Consumer("admin");

    gaggleProducer = new Producer("send");
    gaggleProducer.connect();
    gaggleConsumer = new Consumer("gaggle");

    createConsumerConsoleLoggingThread();
  }

  public void run() {
    sendGaggleConnect();
    // Pause for a moment to catch our breath...
    try { Thread.sleep(1500); } catch (Exception e) { /* who cares? */ }
    sendGaggleTestMessage();
  }

  private void createConsumerConsoleLoggingThread() {
    // Don't you  just love this thread-starting syntax? Ugh...
    new Thread(new Runnable() {
      public void run() { gaggleConsumer.run(); }
    }).start();
    new Thread(new Runnable() {
      public void run() { adminConsumer.run(); }
    }).start();
    return;
  }

  private void sendGaggleConnect() {
    System.err.println("Sending connect message...");
    HashMap<String,String> msg = new HashMap<String,String>();
    msg.put("type", "join");
    msg.put("name", "Sample Gaggle Client");
    msg.put("id", id);
    try { adminProducer.send(msg); }
    catch (Exception e) {
      System.err.println("Error sending connect: ");
      e.printStackTrace();
    }
  }

  private void sendGaggleLeave() {
    HashMap<String,String> msg = new HashMap<String,String>();
    msg.put("type", "leave");
    msg.put("id", UUID.randomUUID().toString());
    try { adminProducer.send(msg); }
    catch (Exception e) {
      System.err.println("Error sending connect: ");
      e.printStackTrace();
    }
  }

  private void sendGaggleTestMessage() {
    HashMap<String,Serializable> msg = new HashMap<String,Serializable>();
    LinkedList<String> recipients = new LinkedList<String>();
    msg.put("to", recipients);
    msg.put("body", new DataMatrix());
    try {
    gaggleProducer.send(msg);
    }
    catch (Exception e) {
      System.err.println("Error sending test gaggle message...");
      e.printStackTrace();
    }
    return;
  }

  public static void main(String[] args) {
    new ClientDriver().run();
  }
}
