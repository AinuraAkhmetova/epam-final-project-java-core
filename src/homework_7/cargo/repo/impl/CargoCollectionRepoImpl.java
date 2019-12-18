package homework_7.cargo.repo.impl;

import homework_7.cargo.domain.Cargo;
import homework_7.cargo.repo.CargoCollectionRepo;
import homework_7.storage.IdGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static homework_7.storage.Storage.cargosList;

public class CargoCollectionRepoImpl implements CargoCollectionRepo {


    @Override
    public void add(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargosList.add(cargo);

    }

    @Override
    public Cargo getById(long id) {
        for (Cargo cargo : cargosList) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }

        return null;
    }

    @Override
    public List<Cargo> getByName(String name) {
        List<Cargo>result = new ArrayList<>();
        for (Cargo cargo:cargosList) {
            if(cargo.getName().equals(name))  {
                result.add(cargo);
            }
        }
        return result;

    }

    @Override
    public boolean deleteById(long id) {
        Iterator<Cargo> cargoIterator = cargosList.iterator();
        while(cargoIterator.hasNext()) {
            Cargo nextCargo = cargoIterator.next();
            if (nextCargo.getId().equals(id)) {
                cargoIterator.remove();
                return true;
            }
        }
        return false;
    }
}
