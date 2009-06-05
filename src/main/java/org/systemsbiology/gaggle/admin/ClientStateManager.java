package org.systemsbiology.gaggle.admin;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

public class ClientStateManager {
  private static ClientStateManager instance = null;
  private HashMap<String,GaggleClient> clients;

  private ClientStateManager() {
    clients = new HashMap<String,GaggleClient>();
  }

  public static ClientStateManager getInstance() {
    if(instance == null)
      instance = new ClientStateManager();
    return instance;
  }

  public void addClient(GaggleClient c) {
    clients.put(c.getId(), c);
  }

  public void removeClientById(String id) {
    clients.remove(id);
  }

  public GaggleClient addNewClient(String name, String type, String id) {
    GaggleClient c = new GaggleClient(name, type, id);
    addClient(c);
    return c;
  }

  public List<GaggleClient> getClients(String protocol) {
    List<GaggleClient> selectedClients = new LinkedList<GaggleClient>();
    for(GaggleClient c : clients.values()) {
      if(c.getProtocol().equals(protocol)) {
        selectedClients.add(c);
      }
    }
    return selectedClients;
  }
  public List<GaggleClient> getAllClients() {
    return new LinkedList<GaggleClient>(clients.values());
  }
}
