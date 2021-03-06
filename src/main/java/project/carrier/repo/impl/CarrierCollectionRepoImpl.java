package project.carrier.repo.impl;


import project.carrier.domain.Carrier;
import project.carrier.repo.CarrierRepo;
import project.storage.IdGenerator;
import project.storage.Storage;

import java.util.*;

public class CarrierCollectionRepoImpl implements CarrierRepo {

  @Override
  public void save(Carrier carrier) {
    carrier.setId(IdGenerator.generateId());
    Storage.carrierCollection.add(carrier);
  }

  @Override
  public Optional<Carrier> findById(Long id) {
    for (Carrier carrier : Storage.carrierCollection) {
      if (carrier.getId().equals(id)) {
        return Optional.of(carrier);
      }
    }

    return Optional.empty();
  }

  @Override
  public Optional<Carrier> getByIdFetchingTransportations(long id) {
    return findById(id);
  }

  @Override
  public Carrier[] findByName(String name) {
    List<Carrier> result = new ArrayList<>();

    for (Carrier carrier : Storage.carrierCollection) {
      if (Objects.equals(carrier.getName(), name)) {
        result.add(carrier);
      }
    }

    return result.toArray(new Carrier[0]);
  }

  @Override
  public boolean deleteById(Long id) {
    Iterator<Carrier> iter = Storage.carrierCollection.iterator();

    boolean removed = false;
    while (iter.hasNext()) {
      if (iter.next().getId().equals(id)) {
        iter.remove();
        removed = true;
        break;
      }
    }

    return removed;
  }

  @Override
  public List<Carrier> getAll() {
    return Storage.carrierCollection;
  }

  @Override
  public int countAll() {
    return Storage.carrierCollection.size();
  }

  @Override
  public boolean update(Carrier carrier) {
    return true;
  }

}
