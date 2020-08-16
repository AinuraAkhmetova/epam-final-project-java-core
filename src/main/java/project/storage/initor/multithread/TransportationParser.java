package project.storage.initor.multithread;

import project.common.solutions.utils.FileUtils;
import project.common.solutions.utils.xml.sax.XmlSaxUtils;
import project.storage.initor.fileinitor.xml.sax.SaxHandler;
import project.storage.initor.fileinitor.xml.sax.XmlSaxFileDataInitor;
import project.storage.initor.fileinitor.BaseFileInitor;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransportationParser implements Runnable {

  private static final String FILE = "/homework_18/xmldata.xml";

  private volatile boolean hasError = false;

  private List<BaseFileInitor.ParsedTransportation> transportations;

  @Override
  public void run() {
    File file = null;
    try {
      file = getFileWithInitData();
      List<BaseFileInitor.ParsedTransportation> transportations = parseCargosFromFile(file);
      setTransportations(transportations);
    } catch (Exception e) {
      e.printStackTrace();
      hasError = true;
    } finally {
      if (file != null) {
        file.delete();
      }
    }
  }

  private List<BaseFileInitor.ParsedTransportation> parseCargosFromFile(File file) throws Exception {
    SAXParser parser = XmlSaxUtils.getParser();
    SaxHandler saxHandler = new SaxHandler();
    parser.parse(file, saxHandler);
    return saxHandler.getTransportations();
  }

  private File getFileWithInitData() throws IOException {
    return FileUtils
        .createFileFromResource(
            XmlSaxFileDataInitor.class, "init-data", "lesson", FILE);
  }

  public boolean isHasError() {
    return hasError;
  }

  private synchronized void setTransportations(List<BaseFileInitor.ParsedTransportation> transportations) {
    this.transportations = transportations;
  }

  public synchronized List<BaseFileInitor.ParsedTransportation> getTransportations() {
    return this.transportations;
  }
}
