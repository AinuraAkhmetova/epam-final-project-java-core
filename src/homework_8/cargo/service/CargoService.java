package homework_8.cargo.service;

import homework_8.cargo.domain.Cargo;
import homework_8.cargo.domain.CargoType;
import homework_8.common.business.service.CommonService;
import homework_8.transportation.domain.Transportation;

import java.util.List;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getAll();

    List<Cargo> getByName(String name);

    List<Cargo> sortByName();

    List<Cargo> sortByWeight();

    List<Cargo> sortByNameAndWeight();

    void updateWeight(Long id, int weight);

    void updateTransportation(Long id, Transportation transportation[]);

    void updateName(Long id, String name);

}
