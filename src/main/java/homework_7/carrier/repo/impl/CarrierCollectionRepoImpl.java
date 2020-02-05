package homework_7.carrier.repo.impl;

import homework_7.carrier.domain.Carrier;
import homework_7.carrier.repo.CarrierCollectionRepo;
import homework_7.storage.IdGenerator;

import static homework_7.storage.Storage.carriersList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarrierCollectionRepoImpl implements CarrierCollectionRepo {

    @Override
    public List<Carrier> getByName(String name) {
        List<Carrier>result = new ArrayList<>();
        for (Carrier carrier:carriersList) {
            if(carrier.getName().equals(name))  {
                result.add(carrier);
            }
        }
        return result;
    }

    @Override
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carriersList.add(carrier);

    }

    @Override
    public Carrier getById(long id) {
        for (Carrier carrier : carriersList) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }

        return null;
    }

    @Override
    public boolean deleteById(long id) {
        Iterator<Carrier> carrierIterator = carriersList.iterator();
        while(carrierIterator.hasNext()) {
            Carrier nextCarrier = carrierIterator.next();
            if (nextCarrier.getId().equals(id)) {
                carrierIterator.remove();
                return true;
            }
        }
        return false;
    }
}



