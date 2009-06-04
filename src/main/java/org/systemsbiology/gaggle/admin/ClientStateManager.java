package org.systemsbiology.gaggle.admin;

import java.util.List;
import java.util.LinkedList;

public class ClientStateManager {
  private List<GaggleClient> clients;

  public List<GaggleClient> getClients(String protocol) {
    List<GaggleClient> selectedClients = new LinkedList<GaggleClient>();
    for(GaggleClient c : clients) {
      if(c.getProtocol().equals(protocol)) {
        selectedClients.add(c);
      }
    }
    return selectedClients;
  }
}
