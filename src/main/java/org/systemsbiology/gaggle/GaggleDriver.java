package org.systemsbiology.gaggle;
import gov.pnnl.mif.api.exception.MifException;
import gov.pnnl.mif.api.MifPipeline;
import gov.pnnl.mif.api.MifJmsConnector.JmsProvider;

public class GaggleDriver {
  public static void main(String[] args) throws MifException {
    MifPipeline pipeline = new MifPipeline();
		pipeline.addMifJmsConnector("tcp://localhost:61616", JmsProvider.ACTIVEMQ);

		// Admin pipeline
		pipeline.addMifModule(org.systemsbiology.gaggle.admin.JmsIngest.class.getName(), "jms://topic:adminSend", "vm://adminHandler");
		pipeline.addMifModule(org.systemsbiology.gaggle.admin.Handler.class.getName(), "vm://adminHandler", "vm://adminJmsOut");
		pipeline.addMifModule(org.systemsbiology.gaggle.admin.JmsEmitter.class.getName(), "vm://adminJmsOut", "jms://topic:admin");

		// Gaggle pipeline
		pipeline.addMifModule(org.systemsbiology.gaggle.boss.JmsIngester.class.getName(), "jms://topic:send", "vm://ingest");
		pipeline.addMifModule(org.systemsbiology.gaggle.boss.Handler.class.getName(), "vm://ingest", "vm://annotate");
		pipeline.addMifModule(org.systemsbiology.gaggle.boss.Annotator.class.getName(), "vm://annotate", "vm://postProcess");
		pipeline.addMifModule(org.systemsbiology.gaggle.boss.PostProcessor.class.getName(), "vm://postProcess", "vm://emitJMS");
		pipeline.addMifModule(org.systemsbiology.gaggle.boss.JmsEmitter.class.getName(), "vm://emitJMS", "jms://topic:items");

    pipeline.start();
  }
}
