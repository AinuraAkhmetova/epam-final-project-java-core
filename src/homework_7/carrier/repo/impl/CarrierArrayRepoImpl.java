package homework_7.carrier.repo.impl;

import static homework_7.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static homework_7.storage.Storage.carrierIndex;
import static homework_7.storage.Storage.carriers;

import homework_7.carrier.domain.Carrier;
import homework_7.carrier.repo.CarrierArrayRepo;
import homework_7.carrier.repo.CarrierCommonRepo;
import homework_7.storage.IdGenerator;
import homework_7.common.solutions.utils.ArrayUtils;

import java.util.Objects;

public class CarrierArrayRepoImpl implements CarrierArrayRepo {

  private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

  @Override
  public void add(Carrier carrier) {
    if (carrierIndex == carriers.length) {
      Carrier[] newCarriers = new Carrier[carriers.length * 2];
      ArrayUtils.copyArray(carriers, newCarriers);
      carriers = newCarriers;
    }

    carrier.setId(IdGenerator.generateId());
    carriers[carrierIndex] = carrier;
    carrierIndex++;
  }

  @Override
  public Carrier getById(long id) {
    for (Carrier carrier : carriers) {
      if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
        return carrier;
      }
    }

    return null;
  }

  @Override
  public Carrier[] getByName(String name) {
    Carrier[] searchResultWithNullableElems = getByNameIncludingNullElements(name);
    if (searchResultWithNullableElems == null || searchResultWithNullableElems.length == 0) {
      return EMPTY_CARRIER_ARRAY;
    } else {
      return excludeNullableElementsFromArray(searchResultWithNullableElems);
    }
  }

  private Carrier[] getByNameIncludingNullElements(String name) {
    Carrier[] result = new Carrier[carriers.length];

    int curIndex = 0;
    for (Carrier carrier : carriers) {
      if (carrier != null && Objects.equals(carrier.getName(), name)) {
        result[curIndex++] = carrier;
      }
    }

    return result;
  }


  private Carrier[] excludeNullableElementsFromArray(Carrier[] carriers) {
    int sizeOfArrWithNotNullElems = 0;

    for (Carrier carrier : carriers) {
      if (carrier != null) {
        sizeOfArrWithNotNullElems++;
      }
    }

    if (sizeOfArrWithNotNullElems == 0) {
      return EMPTY_CARRIER_ARRAY;
    } else {
      Carrier[] result = new Carrier[sizeOfArrWithNotNullElems];
      int index = 0;
      for (Carrier carrier : carriers) {
        if (carrier != null) {
          result[index++] = carrier;
        }
      }

      return result;
    }
  }

  @Override
  public boolean deleteById(long id) {
    Integer indexToDelete = findEntityIndexInArrayStorageById(carriers, id);

    if (indexToDelete == null) {
      return false;
    } else {
      ArrayUtils.removeElement(carriers, indexToDelete);
      return true;
    }
  }
}
