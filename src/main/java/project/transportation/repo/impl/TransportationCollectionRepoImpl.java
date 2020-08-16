package project.transportation.repo.impl;


import project.storage.IdGenerator;
import project.transportation.domain.Transportation;
import project.transportation.repo.TransportationRepo;
import project.storage.Storage;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class TransportationCollectionRepoImpl implements TransportationRepo {

  @Override
  public void save(Transportation transportation) {
    transportation.setId(IdGenerator.generateId());
    Storage.transportationCollection.add(transportation);
  }

  @Override
  public Optional<Transportation> findById(Long id) {
    for (Transportation transportation : Storage.transportationCollection) {
      if (transportation.getId().equals(id)) {
        return Optional.of(transportation);
      }
    }

    return Optional.empty();
  }

  @Override
  public List<Transportation> getAll() {
    return Storage.transportationCollection;
  }

  @Override
  public boolean update(Transportation transportation) {
    return true;
  }

  @Override
  public boolean deleteById(Long id) {
    boolean deleted = false;

    Iterator<Transportation> iter = Storage.transportationCollection.iterator();
    while (iter.hasNext()) {
      if (iter.next().getId().equals(id)) {
        iter.remove();
        deleted = true;
        break;
      }
    }
    return deleted;
  }

  @Override
  public int countAll() {
    return Storage.transportationCollection.size();
  }
}
