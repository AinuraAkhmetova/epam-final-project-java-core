package homework_7;

import homework_7.cargo.domain.Cargo;
import homework_7.cargo.domain.CargoType;
import homework_7.cargo.repo.CargoArrayRepo;
import homework_7.cargo.repo.impl.CargoArrayRepoImpl;
import homework_7.cargo.service.CargoServiceImpl;
import homework_7.storage.Storage;

public class Application {
    public static void main(String[] args) {
        Cargo cargo = new Cargo() {
            @Override
            public CargoType getCargoType() {
                return CargoType.FOOD;
            }
        };
        cargo.setName("apple");
        cargo.setWeight(5);
        CargoServiceImpl cargoServiceImpl= new CargoServiceImpl();
        cargoServiceImpl.add(cargo);

        Cargo cargo2 = new Cargo() {
            @Override
            public CargoType getCargoType() {
                return CargoType.CLOTHERS;
            }
        };
        cargo2.setName("Dress");
        cargoServiceImpl.add(cargo2);
        Storage.printAllCargos();
        cargoServiceImpl.getById(1L);
        cargoServiceImpl.deleteById(1L);

        Storage.printAllCargos();








    }
}
