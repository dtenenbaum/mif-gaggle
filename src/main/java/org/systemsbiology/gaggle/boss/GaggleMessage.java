package org.systemsbiology.gaggle.boss;

import java.io.Serializable; 
import java.util.List; 
import java.util.LinkedList; 

public class GaggleMessage {
  private Serializable payload;
  private List<String> recipients;

  public List<String> getRecipients() {
    return new LinkedList(recipients);
  }
  public void addRecipients(List<String> recipients) {
    this.recipients.addAll(recipients);
  }
  public void clearRecipients() {
    recipients = new LinkedList<String>();
  }
  public void addRecipient(String recipient) {
    recipients.add(recipient);
  }
  public void setRecipients(List<String> recipients) {
    this.recipients = recipients;
  }

  public Serializable getPayload() {
    return payload;
  }
  public void setPayload(Serializable p) {
    payload = p;
  }

  public GaggleMessage(Serializable payload) {
    this(payload, new LinkedList<String>());
  }

  public GaggleMessage(LinkedList<String> recipients) {
    this(null, recipients);
  }

  public GaggleMessage(Serializable payload, LinkedList<String> recipients) {
    this.payload = payload;
    this.recipients = recipients;
  }
}
