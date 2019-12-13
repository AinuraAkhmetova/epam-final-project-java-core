package homework_4;


import homework_4.cargo.domain.Cargo;
import homework_4.cargo.domain.CargoStorage;

public class Demo {
    public static void main(String[] args) {
        Cargo cargo = new Cargo();
        cargo.setName("A1");
        CargoStorage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A2");
        CargoStorage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A3");
        CargoStorage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A4");
        CargoStorage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A5");
        CargoStorage.addCargo(cargo);
        CargoStorage.findById(3L);

        CargoStorage.printAllCargos();

    }
}
