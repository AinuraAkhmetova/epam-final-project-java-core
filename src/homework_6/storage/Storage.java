package homework_6.storage;


import homework_6.cargo.domain.Cargo;
import homework_6.cargo.domain.CargoRepo.CargoInterface;
import homework_6.carrier.domain.CarrierRepo.CarrierInterface;
import homework_6.carrier.domain.Carrier;
import homework_6.common.utils.ArrayUtils;
import homework_6.transportation.domain.Transportation;
import homework_6.transportation.domain.TransportationRepo.TransportationInterface;

import java.util.Objects;

public class Storage implements CargoInterface, CarrierInterface, TransportationInterface {

  private static final int ARRAY_CAPACITY = 10;

  private static Cargo[] cargos = new Cargo[ARRAY_CAPACITY];
  private static int cargoIndex = 0;

  private static Carrier[] carriers = new Carrier[ARRAY_CAPACITY];
  private static int carrierIndex = 0;

  private static Transportation[] transportations = new Transportation[ARRAY_CAPACITY];
  private static int transportationIndex = 0;



  @Override
  public Cargo getCargoById(long id) {
    for (Cargo cargo : cargos) {
      if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
        return cargo;
      }
    }

    return null;
  }

  @Override
  public Cargo[] getCargoByName(String name) {
    Cargo[] result = new Cargo[cargos.length];

    int curIndex = 0;
    for (Cargo cargo : cargos) {
      if (cargo != null && Objects.equals(cargo.getName(), name)) {
        result[curIndex++] = cargo;
      }
    }

    return result;
  }



  @Override
  public void addCargo(Cargo cargo) {
    cargo.setId(IdGenerator.generateId());
    cargos[cargoIndex] = cargo;
    cargoIndex++;

    if (cargoIndex == cargos.length) {
      Cargo[] newCargos = new Cargo[cargos.length * 2];
      ArrayUtils.copyArray(cargos, newCargos);
      cargos = newCargos;
    }

  }

  @Override
  public void deleteCargo(long id) {
    int temp=0;
    for (int i=0;i<cargos.length;i++){
      if (cargos[i].getId().equals(id)){
        temp=i;
        break;
      }
    }
    for(int k=temp;k<cargos.length-1;k++){
      cargos[k]=cargos[k+1];
    }
    Cargo newCargos[]=new Cargo[cargos.length-1];
    ArrayUtils.copyArray(cargos,newCargos);
    cargos=newCargos;
  }

  @Override
  public void printAllCargos() {
    ArrayUtils.printArray(cargos);
  }

  public void addCarrier(Carrier carrier) {
    carrier.setId(IdGenerator.generateId());
    carriers[carrierIndex] = carrier;
    carrierIndex++;

    if (carrierIndex == carriers.length) {
      Carrier[] newCarriers = new Carrier[carriers.length * 2];
      ArrayUtils.copyArray(carriers, newCarriers);
      carriers = newCarriers;
    }
  }

  @Override
  public void deleteCarrier(long id) {

  }

  public Carrier getCarrierById(long id) {
    for (Carrier carrier : carriers) {
      if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
        return carrier;
      }
    }

    return null;
  }

  @Override
  public Carrier[] getCarrierByName(String name) {
    return new Carrier[0];
  }

  public static Carrier[] getCarriersByName(String name) {
    Carrier[] result = new Carrier[carriers.length];

    int curIndex = 0;
    for (Carrier carrier : carriers) {
      if (carrier != null && Objects.equals(carrier.getName(), name)) {
        result[curIndex++] = carrier;
      }
    }

    return result;
  }

  public  void printAllCarriers() {
    ArrayUtils.printArray(carriers);
  }

  public void addTransportation(Transportation transportation) {
    transportation.setId(IdGenerator.generateId());
    transportations[transportationIndex] = transportation;
    transportationIndex++;

    if (transportationIndex == transportations.length) {
      Transportation[] newTransportations = new Transportation[transportations.length * 2];
      ArrayUtils.copyArray(transportations, newTransportations);
      transportations = newTransportations;
    }
  }

  public Transportation getTransportationById(long id) {
    for (Transportation transportation : transportations) {
      if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
        return transportation;
      }
    }

    return null;
  }

  public void printAllTransportations() {
    ArrayUtils.printArray(transportations);
  }



}
