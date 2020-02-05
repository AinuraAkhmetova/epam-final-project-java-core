package homework_8.application;

import homework_8.application.serviceholder.ServiceHolder;
import homework_8.application.serviceholder.StorageType;
import homework_8.cargo.service.CargoService;
import homework_8.carrier.service.CarrierService;
import homework_8.common.solutions.utils.CollectionUtils;
import homework_8.storage.initor.InMemoryStorageInitor;
import homework_8.storage.initor.StorageInitor;
import homework_8.transportation.service.TransportationService;

public class Application {

    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        ServiceHolder.initServiceHolder(StorageType.ARRAY);
        cargoService = ServiceHolder.getInstance().getCargoService();
        carrierService = ServiceHolder.getInstance().getCarrierService();
        transportationService = ServiceHolder.getInstance().getTransportationService();

        StorageInitor storageInitor = new InMemoryStorageInitor();
        storageInitor.initStorage();

        printStorageData();
        doSearchOperations();
        doUpdateOperations();
        doSortOperations();
    }

    private static void printStorageData() {
        System.out.println("ALL CARGOS");
        cargoService.printAll();
        printSeparator();

        System.out.println("ALL CARRIERS");
        carrierService.printAll();
        printSeparator();

        System.out.println("ALL TRANSPOORTATIONS");
        transportationService.printAll();
        printSeparator();
    }

    private static void doSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        System.out.println(cargoService.getById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 8");
        System.out.println(carrierService.getById(8L));
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'Clothers_Name_1'");
        CollectionUtils.printCollection(cargoService.getByName("Clothers_Name_1"));
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name'");
        CollectionUtils.printCollection(carrierService.getByName("Carrier_Name"));
    }

    private static void doSortOperations() {
        System.out.println("SORT CARGO BY NAME ");
        CollectionUtils.printCollection(cargoService.sortByName());
        printSeparator();

        System.out.println("SORT CARGO BY WEIGHT");
        CollectionUtils.printCollection(cargoService.sortByWeight());
        printSeparator();

        System.out.println("SORT CARGO BY NAME AND WEIGHT");
        CollectionUtils.printCollection(cargoService.sortByNameAndWeight());
        printSeparator();
    }

    private static void doUpdateOperations() {
        System.out.println("UPDATE CARGO NAME BY ID = 3 ");
        cargoService.updateName(3L, "CLO");

        System.out.println("UPDATE CARGO WEIGHT BY ID = 2 ");
        cargoService.updateWeight(2L, 20);
        cargoService.printAll();

        printSeparator();

    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

}

