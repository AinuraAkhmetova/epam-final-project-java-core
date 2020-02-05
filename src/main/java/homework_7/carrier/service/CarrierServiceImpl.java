package homework_7.carrier.service;

import homework_7.carrier.domain.Carrier;
import homework_7.carrier.domain.CarrierType;
import homework_7.carrier.repo.impl.CarrierArrayRepoImpl;
import homework_7.carrier.repo.impl.CarrierCollectionRepoImpl;


public class CarrierServiceImpl implements CarrierService {
    CarrierArrayRepoImpl CarrierArrayRepoImpl = new CarrierArrayRepoImpl();
    CarrierCollectionRepoImpl CarrierCollectionRepoImpl = new CarrierCollectionRepoImpl();

    @Override
    public  void add(Carrier Carrier) {

        System.out.println("Begin tpo add Carrier");
        if(Carrier.getCarrierType()== CarrierType.SHIP){
            CarrierArrayRepoImpl.add(Carrier);
            System.out.println("Carrier was added to CarrierArray");
        }
        else {
            CarrierCollectionRepoImpl.add(Carrier);
            System.out.println("Carrier was added to CarrierCollection");
        }
    }

    @Override
    public Carrier getById(Long id) {
        if (CarrierArrayRepoImpl.getById(id)!=null){
            System.out.println("Carrier with ID " +id  +" was found in CarrierArray");
            return CarrierArrayRepoImpl.getById(id);
        }
        else if (CarrierCollectionRepoImpl.getById(id)!=null){
            System.out.println("Carrier with ID " +id  +" was found in CarrierCollection");
            return CarrierCollectionRepoImpl.getById(id);
        }
        else {
            System.out.println("ID was not found");
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (CarrierArrayRepoImpl.getById(id)!=null){
            CarrierArrayRepoImpl.deleteById(id);
            System.out.println("Carrier with ID " +id  +" was deleted from CarrierArray");
            return true;
        }
        else if (CarrierCollectionRepoImpl.getById(id)!=null){
            CarrierCollectionRepoImpl.deleteById(id);
            System.out.println("Carrier with ID " +id  +" was deleted from CarrierCollection");
            return true;
        }
        else {
            System.out.println("ID was not found");
            return false;
        }
    }


}
