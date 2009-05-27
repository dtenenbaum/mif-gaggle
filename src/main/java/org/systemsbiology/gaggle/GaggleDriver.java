package org.systemsbiology.gaggle;
import gov.pnnl.mif.api.exception.MifException;
import gov.pnnl.mif.api.MifPipeline;

public class GaggleDriver {
  public static void main(String[] args) throws MifException {
    MifPipeline pipeline = new MifPipeline();

		pipeline.addMifModule(org.systemsbiology.gaggle.admin.JmsIngest.class.getName(),
				"jms://topic/admin", "vm://adminHandler");
		pipeline.addMifModule(org.systemsbiology.gaggle.admin.Handler.class.getName(),
				"vm://adminHandler", "vm://adminJmsOut");
		pipeline.addMifModule(org.systemsbiology.gaggle.admin.JmsEmitter.class.getName(),
				"vm://adminJmsOut", "jms://topic/adminOut");

		pipeline.addMifModule(org.systemsbiology.gaggle.boss.JmsIngester.class.getName(),
				"vm://ingest", "vm://annotate");
		pipeline.addMifModule(org.systemsbiology.gaggle.boss.Handler.class.getName(),
				"vm://ingest", "vm://annotate");
		pipeline.addMifModule(org.systemsbiology.gaggle.boss.Annotator.class.getName(),
				"vm://annotate", "vm://postProcess");
		pipeline.addMifModule(org.systemsbiology.gaggle.boss.PostProcessor.class.getName(),
				"vm://postProcess", "vm://emitJMS");
		pipeline.addMifModule(org.systemsbiology.gaggle.boss.JmsEmitter.class.getName(),
				"vm://emitJMS", "jms://topic/items");

    pipeline.start();
  }
}
