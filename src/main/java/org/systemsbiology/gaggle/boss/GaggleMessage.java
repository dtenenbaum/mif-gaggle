package org.systemsbiology.gaggle.boss;

import org.systemsbiology.gaggle.types.GaggleData;
import java.util.List; 
import java.util.LinkedList; 

public class GaggleMessage {
  private GaggleData payload;
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

  public GaggleData getPayload() {
    return payload;
  }
  public void setPayload(GaggleData p) {
    payload = p;
  }

  public GaggleMessage(GaggleData payload) {
    this(payload, new LinkedList<String>());
  }

  public GaggleMessage(LinkedList<String> recipients) {
    this(null, recipients);
  }

  public GaggleMessage(GaggleData payload, LinkedList<String> recipients) {
    this.payload = payload;
    this.recipients = recipients;
  }
}
