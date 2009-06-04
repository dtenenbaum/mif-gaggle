package org.systemsbiology.gaggle.admin;
import java.util.UUID;

public class GaggleClient {
  // Effectively immutable "friendly" name
  private String name;
  public String getName() {
    return name;
  } 

  // Effectively immutable identifier -- string representation of a UUID. 
  private String id;
  public String getId() {
    return id;
  }

  // Effectively immutable protocol string. 
  // Exampels: 'jms', 'json', 'stomp'
  private String protocol;
  public String getProtocol() {
    return protocol;
  }

  public GaggleClient(String name, String protocol) {
    this(name, protocol, UUID.randomUUID().toString());
  }
  public GaggleClient(String name, String protocol, String id) {
    this.name = name;
    this.protocol = protocol;
    this.id = id;
  }
}
