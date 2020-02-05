package homework_14.storage.initor;

import homework_14.common.business.exception.checked.InitStorageException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface StorageInitor {
  void initStorage() throws InitStorageException, ParserConfigurationException, SAXException, IOException;
}
