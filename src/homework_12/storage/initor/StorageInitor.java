package homework_12.storage.initor;

import homework_12.common.business.exception.checked.InitStorageException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface StorageInitor {
  void initStorage() throws InitStorageException, ParserConfigurationException, IOException, SAXException;
}
