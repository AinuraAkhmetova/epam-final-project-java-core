package homework_10.transportation.repo.impl;

import static homework_10.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static homework_10.storage.Storage.transportationArray;
import static homework_10.storage.Storage.transportationIndex;

import homework_10.common.solutions.utils.ArrayUtils;
import homework_10.storage.IdGenerator;
import homework_10.transportation.domain.Transportation;
import homework_10.transportation.repo.TransportationRepo;

import java.util.Arrays;
import java.util.List;

public class TransportationArrayRepoImpl implements TransportationRepo {

  @Override
  public void add(Transportation transportation) {
    if (transportationIndex == transportationArray.length) {
      Transportation[] newTransportations =
          new Transportation[transportationArray.length * 2];
      ArrayUtils.copyArray(transportationArray, newTransportations);
      transportationArray = newTransportations;
    }

    transportation.setId(IdGenerator.generateId());
    transportationArray[transportationIndex] = transportation;
    transportationIndex++;
  }

  @Override
  public Transportation getById(long id) {
    for (Transportation transportation : transportationArray) {
      if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
        return transportation;
      }
    }

    return null;
  }

  @Override
  public List<Transportation> getAll() {
    return Arrays.asList(transportationArray);
  }

  @Override
  public void update(Transportation transportation) {

  }

  @Override
  public boolean deleteById(long id) {
    Integer indexToDelete = findEntityIndexInArrayStorageById(transportationArray, id);

    if (indexToDelete == null) {
      return false;
    } else {
      ArrayUtils.removeElement(transportationArray, indexToDelete);
      return true;
    }
  }
}
