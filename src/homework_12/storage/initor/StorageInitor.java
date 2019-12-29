package homework_12.storage.initor;

import homework_12.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
  void initStorage() throws InitStorageException;
}
