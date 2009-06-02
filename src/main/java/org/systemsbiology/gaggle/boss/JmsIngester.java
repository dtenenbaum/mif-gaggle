package org.systemsbiology.gaggle.boss;

import java.io.Serializable;
import java.util.HashMap;
import gov.pnnl.mif.api.types.MifProcessor;

public class JmsIngester implements MifProcessor {
	public Serializable listen(Serializable input) {
		return input;
	}

	public String getInputType() {
		return Serializable.class.getName();
	}
	public String getOutputType() {
		return Serializable.class.getName();
	}
}
