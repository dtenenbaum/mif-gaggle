package org.systemsbiology.gaggle.admin;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import gov.pnnl.mif.user.MifProcessor;

public class JmsIngest implements MifProcessor {
  public Serializable listen(Serializable input) {
    Map<String,String> message = (Map<String,String>) input;
    String type = message.get("type");
    if(type.equals("join")) {
      ClientStateManager state = ClientStateManager.getInstance();
      state.addNewClient(message.get("name"), "jms", message.get("id"));
    }
    else if(type.equals("leave")) {
      ClientStateManager state = ClientStateManager.getInstance();
      state.removeClientById(message.get("id"));
    }
    return input;
  }

  public String getInputType() {
    return Serializable.class.getName();
  }
  public String getOutputType() {
    return Serializable.class.getName();
  }
}
