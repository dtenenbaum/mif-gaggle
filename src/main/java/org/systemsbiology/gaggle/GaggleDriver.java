package org.systemsbiology.gaggle;
import org.mule.providers.tcp.protocols.EOFProtocol;
import gov.pnnl.mif.api.pipeline.MifEndpoint;
import gov.pnnl.mif.api.pipeline.MifModule;
import gov.pnnl.mif.api.pipeline.MifException;
import gov.pnnl.mif.api.pipeline.MifJmsConnector;
import gov.pnnl.mif.api.pipeline.MifPipeline;
import gov.pnnl.mif.api.types.EndpointType;

public class GaggleDriver {
  public static void main(String[] args) throws MifException {
    MifPipeline pipeline = new MifPipeline();

    // The following commented out code is example code taken from another project to remind me how to build a MIF pipeline. 
    //
    //MifEndpoint incomingTcp = pipeline.addMifEndpoint("IncomingConnector", EndpointType.TCP, listeningAddress);
    //MifEndpoint handlerOut  = pipeline.addMifEndpoint("handlerOut", EndpointType.VM, "commandInput");
    //MifModule handler = pipeline.addMifModule("ContingencyHandler", ContingencyHandler.class.getName(), incomingTcp, handlerOut, null);
    //MifEndpoint handlerToFileMonitor = pipeline.addMifEndpoint("handlerToFileMonitor", EndpointType.VM, "fileMonitorInput");
    //handler.addOutboundEndpoint(handlerToFileMonitor);
    //
    //MifEndpoint commandIn  = pipeline.addMifEndpoint("commandIn",  EndpointType.VM, "commandInput");
    //MifEndpoint commandOut = pipeline.addMifEndpoint("commandOut", EndpointType.VM, "commandOutput");
    //pipeline.addExternalCommandModule("run_command", commandIn, commandOut);
    //
    //MifEndpoint fileMonitorIn  = pipeline.addMifEndpoint("fileMonitorIn",  EndpointType.VM, "fileMonitorInput");
    //MifEndpoint fileMonitorOut = pipeline.addMifEndpoint("fileMonitorOut", EndpointType.VM, "pushResults");
    //pipeline.addMifModule("ContingencyResultMonitor", ContingencyResultMonitor.class.getName(), fileMonitorIn, fileMonitorOut, null);
    //
    //MifEndpoint resultUploaderIn  = pipeline.addMifEndpoint("resultUploaderIn",   EndpointType.VM, "pushResults");
    //MifEndpoint resultUploaderOut = pipeline.addMifEndpoint("resultUploaderOut",  EndpointType.VM, "resultUpload");
    //pipeline.addExternalCommandModule("upload_command", resultUploaderIn, resultUploaderOut);

    pipeline.start();
  }
}
