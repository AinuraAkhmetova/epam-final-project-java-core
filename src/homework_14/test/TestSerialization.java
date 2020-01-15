package homework_14.test;

import homework_14.cargo.domain.Cargo;
import homework_14.cargo.domain.ClothersCargo;
import homework_14.cargo.domain.FoodCargo;
import homework_14.carrier.domain.Carrier;
import homework_14.carrier.domain.CarrierType;
import homework_14.transportation.domain.Transportation;

import org.junit.Before;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestSerialization {
    private static final String FILE = "C:\\Users\\ainura\\AppData\\Local\\Temp\\lesson148333933479582851954.txt";

    private static final Long ID_FOODCARGO = 1L;
    private static final String NAME_MILK = "Milk";
    private static final int WEIGHT_100 = 100;
    private static final int TEMPERATURE = -50;
    private static FoodCargo foodCargo;

    private static final Long ID_CLOTHESCARGO = 2L;
    private static final String NAME_JEANS = "Jeans";
    private static final int WEIGHT_200 = 200;
    private static final String SIZE = "XS";
    private static ClothersCargo clothesCargo;

    private static final Long ID_CARRIER = 3L;
    private static final String ADDRESS = "ADDRESS";
    private static final CarrierType CARRIER_TYPE_CAR = CarrierType.SHIP;
    private static final String NAME_FASTEST = "Fastest";
    private static Carrier carrier;

    private static final Long ID_TRANSPORTATION = 4L;
    private static final String DESCRIPTION = ".....";
    private static final String BILL_TO = "IVAN";
    private static Transportation transportation = null;
    private static List<Transportation> transportations = null;


    @Before
    public void setUp() throws Exception {

        foodCargo = createFoodCargo(NAME_MILK, WEIGHT_100, TEMPERATURE, ID_FOODCARGO);
        clothesCargo = createClothersCargo(NAME_JEANS, WEIGHT_200, SIZE, ID_CLOTHESCARGO);
        carrier = createCarrier(ID_CARRIER, ADDRESS, CARRIER_TYPE_CAR, NAME_FASTEST);
        transportation = createTransportation(ID_TRANSPORTATION, BILL_TO, DESCRIPTION, foodCargo, carrier);

        transportations = new ArrayList<Transportation>();
        transportations.add(transportation);

        foodCargo.setTransportations(transportations);
        //writerToFile();
    }


    @org.junit.Test
    public void testSerialization() throws IOException, ClassNotFoundException {

        try (ObjectInputStream stream = new ObjectInputStream(
                new FileInputStream(new File(FILE)))) {

            FoodCargo foodCargo = (FoodCargo) stream.readObject();
            ClothersCargo clothersCargo = (ClothersCargo) stream.readObject();
            Carrier carrier = (Carrier) stream.readObject();
            Transportation transportation = (Transportation) stream.readObject();

            assertEquals(foodCargo.getStoreTemperature(), TEMPERATURE);
            assertEquals(foodCargo.getWeight(), WEIGHT_100);
            assertEquals(foodCargo.getId(), ID_FOODCARGO);
            assertEquals(foodCargo.getName(), NAME_MILK);
            assertEquals(foodCargo.getTransportations().get(0).getId(), ID_TRANSPORTATION);
            assertEquals(foodCargo.getTransportations().get(0).getDescription(), DESCRIPTION);

            assertEquals(carrier.getName(), NAME_FASTEST);
            assertEquals(carrier.getAddress(), ADDRESS);
            assertEquals(carrier.getCarrierType(), CARRIER_TYPE_CAR);
            assertEquals(carrier.getId(), ID_CARRIER);
            assertEquals(carrier.getTransportations().get(0).getId(), ID_TRANSPORTATION);

            assertEquals(transportation.getCargo().getName(), NAME_MILK);
            assertEquals(transportation.getCarrier().getName(), NAME_FASTEST);
            assertEquals(transportation.getDescription(), DESCRIPTION);
            assertEquals(transportation.getBillTo(), BILL_TO);
            assertEquals(transportation.getId(), ID_TRANSPORTATION);


        }
    }

    private static FoodCargo createFoodCargo(String name, int weight, int tempereture, Long id) {
        FoodCargo foodCargo = new FoodCargo();
        foodCargo.setId(id);
        foodCargo.setStoreTemperature(tempereture);
        foodCargo.setWeight(weight);
        foodCargo.setName(name);
        return foodCargo;
    }

    private static ClothersCargo createClothersCargo(String name, int weight, String size, Long id) {
        ClothersCargo clothersCargo = new ClothersCargo();
        clothersCargo.setId(id);
        clothersCargo.setSize(size);
        clothersCargo.setWeight(weight);
        clothersCargo.setName(name);
        return clothersCargo;
    }

    private static Carrier createCarrier(Long id, String address, CarrierType carrierType, String name) {
        Carrier carrier = new Carrier();
        carrier.setName(name);
        carrier.setCarrierType(carrierType);
        carrier.setAddress(address);
        carrier.setId(id);
        return carrier;
    }

    private static Transportation createTransportation(Long id, String billTo, String description,
                                                       Cargo cargo, Carrier carrier) {

        Transportation transportation = new Transportation();
        transportation.setDescription(description);
        transportation.setBillTo(billTo);
        transportation.setId(id);
        transportation.setCargo(cargo);
        transportation.setCarrier(carrier);
        return transportation;
    }

    private static void writerToFile() {
        Path file = null;
        try {
            file = Files.createTempFile("lesson14", ".txt");

            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {

                objectOutput.writeObject(foodCargo);
                objectOutput.writeObject(clothesCargo);
                objectOutput.writeObject(carrier);
                objectOutput.writeObject(transportation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(file);
    }
}