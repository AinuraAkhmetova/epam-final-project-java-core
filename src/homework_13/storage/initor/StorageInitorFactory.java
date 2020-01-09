package homework_13.storage.initor;

import homework_13.storage.initor.fileinitor.TextFileDataInitor;
import homework_13.storage.initor.fileinitor.XmlDomFileDataInitor;
import homework_13.storage.initor.fileinitor.XmlSaxFileDataInitor;

public final class StorageInitorFactory {

  private StorageInitorFactory() {

  }

  public static StorageInitor getStorageInitor(InitStorageType initStorageType) {
    switch (initStorageType) {

      case MEMORY: {
        return new InMemoryStorageInitor();
      }
      case TEXT_FILE: {
        return new TextFileDataInitor();
      }
      case XML_DOM_FILE:{
        return new XmlDomFileDataInitor();
      }
      case XML_SAX_FILE:{
        return new XmlSaxFileDataInitor();

      }      default: {
        throw new RuntimeException("Unknown storage init type " + initStorageType);
      }
    }
  }

}
