package homework_7.cargo.service;

import homework_7.cargo.domain.Cargo;
import homework_7.cargo.domain.CargoType;
import homework_7.cargo.repo.impl.CargoArrayRepoImpl;
import homework_7.cargo.repo.impl.CargoCollectionRepoImpl;

public class CargoServiceImpl implements CargoService {
    CargoArrayRepoImpl cargoArrayRepoImpl = new CargoArrayRepoImpl();
    CargoCollectionRepoImpl cargoCollectionRepoImpl = new CargoCollectionRepoImpl();

    @Override
    public  void add(Cargo cargo) {

        System.out.println("Begin tpo add cargo");
        if(cargo.getCargoType()== CargoType.FOOD){
            cargoArrayRepoImpl.add(cargo);
            System.out.println("Cargo was added to CargoArray");
        }
        else {
            cargoCollectionRepoImpl.add(cargo);
            System.out.println("Cargo was added to CargoCollection");
        }
    }

    @Override
    public Cargo getById(Long id) {
        if (cargoArrayRepoImpl.getById(id)!=null){
            System.out.println("Cargo with ID " +id  +" was found in CargoArray");
            return cargoArrayRepoImpl.getById(id);
        }
        else if (cargoCollectionRepoImpl.getById(id)!=null){
            System.out.println("Cargo with ID " +id  +" was found in CargoCollection");
            return cargoCollectionRepoImpl.getById(id);
        }
        else {
            System.out.println("ID was not found");
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (cargoArrayRepoImpl.getById(id)!=null){
            cargoArrayRepoImpl.deleteById(id);
            System.out.println("Cargo with ID " +id  +" was deleted from CargoArray");
            return true;
        }
        else if (cargoCollectionRepoImpl.getById(id)!=null){
            cargoCollectionRepoImpl.deleteById(id);
            System.out.println("Cargo with ID " +id  +" was deleted from CargoCollection");
            return true;
        }
        else {
            System.out.println("ID was not found");
            return false;
        }
    }
}
