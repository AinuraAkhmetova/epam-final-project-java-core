package homework_22.storage.initor.multithread;

import homework_22.common.solutions.utils.FileUtils;
import homework_22.common.solutions.utils.xml.sax.XmlSaxUtils;
import homework_22.storage.initor.fileinitor.BaseFileInitor.ParsedTransportation;
import homework_22.storage.initor.fileinitor.xml.sax.SaxHandler;
import homework_22.storage.initor.fileinitor.xml.sax.XmlSaxFileDataInitor;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransportationParser implements Runnable {

  private static final String FILE = "resources/homework_18/xmldata.xml";

  private volatile boolean hasError = false;

  private List<ParsedTransportation> transportations;

  @Override
  public void run() {
    File file = null;
    try {
      file = getFileWithInitData();
      List<ParsedTransportation> transportations = parseCargosFromFile(file);
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

  private List<ParsedTransportation> parseCargosFromFile(File file) throws Exception {
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

  private synchronized void setTransportations(List<ParsedTransportation> transportations) {
    this.transportations = transportations;
  }

  public synchronized List<ParsedTransportation> getTransportations() {
    return this.transportations;
  }
}
