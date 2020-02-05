package homework_6.cargo.domain.CargoRepo;

import homework_6.cargo.domain.Cargo;

public interface CargoInterface {

    Cargo getCargoById(long id);
    Cargo[] getCargoByName(String name);
    void addCargo(Cargo cargo);
    void deleteCargo(long id);
    void printAllCargos();


}
