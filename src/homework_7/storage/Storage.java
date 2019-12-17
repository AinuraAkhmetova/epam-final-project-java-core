package homework_7.storage;


import homework_7.cargo.domain.Cargo;
import homework_7.carrier.domain.Carrier;
import homework_7.common.solutions.utils.ArrayUtils;
import homework_7.transportation.domain.Transportation;

public class Storage {

  private static final int ARRAY_CAPACITY = 10;

  public static Cargo[] cargos = new Cargo[ARRAY_CAPACITY];
  public static int cargoIndex = 0;

  public static Carrier[] carriers = new Carrier[ARRAY_CAPACITY];
  public static int carrierIndex = 0;

  public static Transportation[] transportations = new Transportation[ARRAY_CAPACITY];
  public static int transportationIndex = 0;



  public static void printAllCargos() {
    ArrayUtils.printArray(cargos);
  }

  public static void printAllCarriers() {
    ArrayUtils.printArray(carriers);
  }

  public static void printAllTransportations() {
    ArrayUtils.printArray(transportations);
  }

}
