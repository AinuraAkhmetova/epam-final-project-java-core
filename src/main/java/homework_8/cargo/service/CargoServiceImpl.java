package homework_8.cargo.service;

import homework_8.cargo.domain.Cargo;
import homework_8.cargo.domain.CargoType;
import homework_8.cargo.repo.CargoRepo;
import homework_8.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CargoServiceImpl implements CargoService {

    private CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void add(Cargo cargo) {
        cargoRepo.add(cargo);
    }

    @Override
    public Cargo getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }
        return null;
    }

    @Override
    public List<Cargo> getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public List<Cargo> getByName(String name) {
        Cargo[] found = cargoRepo.getByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);
    }

    @Override
    public boolean deleteById(Long id) {
        return cargoRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        List<Cargo> allCargos = cargoRepo.getAll();

        for (Cargo cargo : allCargos) {
            System.out.println(cargo);
        }
    }

    @Override
    public List<Cargo> sortByName() {
        List<Cargo> allCargos = cargoRepo.getAll();

        Collections.sort(allCargos, new Comparator<Cargo>() {
            @Override
            public int compare(Cargo o1, Cargo o2) {
                return o1.getName().compareTo(o2.getName());
            }

        });
        return allCargos;
    }

    @Override
    public List<Cargo> sortByWeight() {
        List<Cargo> allCargos = cargoRepo.getAll();
        Collections.sort(allCargos, new Comparator<Cargo>() {
            @Override
            public int compare(Cargo o1, Cargo o2) {

                return ((Integer) (o1.getWeight())).compareTo(o2.getWeight());
            }
        });
        return allCargos;
    }

    @Override
    public List<Cargo> sortByNameAndWeight() {
        List<Cargo> allCargos = cargoRepo.getAll();
        Collections.sort(allCargos, new Comparator<Cargo>() {
            @Override
            public int compare(Cargo o1, Cargo o2) {
                int k = o1.getName().compareTo(o2.getName());
                if (k == 0) {
                    return ((Integer) o1.getWeight()).compareTo(o2.getWeight());
                } else return k;
            }
        });
        return allCargos;
    }

    @Override
    public void updateWeight(Long id, int weight) {
        if (cargoRepo.getById(id) != null) {
            cargoRepo.getById(id).setWeight(weight);
        }
    }

    @Override
    public void updateTransportation(Long id, Transportation[] transportation) {
        if (cargoRepo.getById(id) != null) {
            cargoRepo.getById(id).setTransportations(transportation);
        }
    }

    @Override
    public void updateName(Long id, String name) {
        if (cargoRepo.getById(id) != null) {
            cargoRepo.getById(id).setName(name);
        }
    }

}