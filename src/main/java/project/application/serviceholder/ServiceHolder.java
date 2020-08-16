package project.application.serviceholder;

import project.cargo.repo.impl.CargoArrayRepoImpl;
import project.cargo.repo.impl.CargoCollectionRepoImpl;
import project.cargo.repo.impl.CargoDataBaseRepoImpl;
import project.cargo.service.CargoService;
import project.cargo.service.CargoServiceImpl;
import project.carrier.repo.impl.CarrierArrayRepoImpl;
import project.carrier.repo.impl.CarrierCollectionRepoImpl;
import project.carrier.repo.impl.CarrierDataBaseRepoImpl;
import project.carrier.service.CarrierService;
import project.carrier.service.CarrierServiceImpl;
import project.transportation.repo.impl.TransportationArrayRepoImpl;
import project.transportation.repo.impl.TransportationCollectionRepoImpl;
import project.transportation.repo.impl.TransportationDataBaseRepoImpl;
import project.transportation.service.TransportationService;
import project.transportation.service.TransportationServiceImpl;

public final class ServiceHolder {

  private static ServiceHolder instance = null;

  private final CarrierService carrierService;
  private final CargoService cargoService;
  private final TransportationService transportationService;

  private ServiceHolder(StorageType storageType) {
    SimpleServiceHolder initedServiceHolder = getInitedServiceHolder(storageType);
    cargoService = initedServiceHolder.cargoService;
    carrierService = initedServiceHolder.carrierService;
    transportationService = initedServiceHolder.transportationService;
  }

  public static void initServiceHolder(StorageType storageType) {
    ServiceHolder.instance = new ServiceHolder(storageType);
  }

  public static ServiceHolder getInstance() {
    return instance;
  }

  private static class SimpleServiceHolder {

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    public SimpleServiceHolder(
        CarrierService carrierService,
        CargoService cargoService,
        TransportationService transportationService) {
      this.carrierService = carrierService;
      this.cargoService = cargoService;
      this.transportationService = transportationService;
    }
  }

  private SimpleServiceHolder getInitedServiceHolder(StorageType storageType) {
    switch (storageType) {

      case ARRAY: {
        return new SimpleServiceHolder(
            new CarrierServiceImpl(new CarrierArrayRepoImpl()),
            new CargoServiceImpl(new CargoArrayRepoImpl()),
            new TransportationServiceImpl(new TransportationArrayRepoImpl()));
      }
      case COLLECTION: {
        return new SimpleServiceHolder(
            new CarrierServiceImpl(new CarrierCollectionRepoImpl()),
            new CargoServiceImpl(new CargoCollectionRepoImpl()),
            new TransportationServiceImpl(new TransportationCollectionRepoImpl()));
      }
      default:{
        return new SimpleServiceHolder(
                new CarrierServiceImpl(new CarrierDataBaseRepoImpl()),
                new CargoServiceImpl(new CargoDataBaseRepoImpl()),
                new TransportationServiceImpl(new TransportationDataBaseRepoImpl()));
      }

         }
  }

  public CarrierService getCarrierService() {
    return carrierService;
  }

  public CargoService getCargoService() {
    return cargoService;
  }

  public TransportationService getTransportationService() {
    return transportationService;
  }
}
