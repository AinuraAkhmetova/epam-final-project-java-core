package homework_7.common.business.repo;

import homework_7.common.business.domain.BaseEntity;
import homework_7.common.solutions.utils.ArrayUtils;

import static homework_7.storage.Storage.cargos;
import static homework_7.storage.Storage.carriers;

public final class CommonRepoHelper {

  private CommonRepoHelper() {

  }

  public static Integer findEntityIndexInArrayStorageById(BaseEntity[] data, long entityId) {
    for (int i = 0; i < data.length; i++) {
      if (Long.valueOf(entityId).equals(data[i].getId())) {
        return i;
      }
    }

    return null;
  }

}
