package org.systemsbiology.gaggle.boss;

import java.io.Serializable;
import java.util.HashMap;
import gov.pnnl.mif.api.types.MifProcessor;

public class JmsEmitter implements MifProcessor {
	public Serializable listen(Serializable input) {
		return null;
	}

	public String getInputType() {
		return Serializable.class.getName();
	}
	public String getOutputType() {
		return Serializable.class.getName();
	}
}