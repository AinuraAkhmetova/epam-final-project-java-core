package homework_13.storage.initor.fileinitor;

import homework_13.cargo.domain.Cargo;
import homework_13.cargo.domain.CargoType;
import homework_13.cargo.domain.ClothersCargo;
import homework_13.cargo.domain.FoodCargo;
import homework_13.carrier.domain.Carrier;
import homework_13.carrier.domain.CarrierType;
import homework_13.common.business.exception.checked.InitStorageException;
import homework_13.common.solutions.utils.FileUtils;
import homework_13.common.solutions.utils.JavaUtilDateUtils;
import homework_13.transportation.domain.Transportation;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class XmlSaxFileDataInitor extends BaseFileInitor {

    private static final String FILE = "/homework_13/xmldata.xml";
    CargoHandler cargoHandler = new CargoHandler();
    CarrierHandler carrierHandler = new CarrierHandler();
    TransportationHandler transportationHandler = new TransportationHandler();

    public void initStorage() throws InitStorageException, ParserConfigurationException, SAXException, IOException {

        File file = null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            file = getFileWithInitData();
            saxParser.parse(file, cargoHandler);
            saxParser.parse(file, carrierHandler);
            saxParser.parse(file, transportationHandler);
            Map<String, Cargo> cargoMap = cargoHandler.getCargos();
            Map<String, Carrier> carrierMap = carrierHandler.getCarriers();
            List<BaseFileInitor.ParsedTransportation> transportations = transportationHandler.getTransportations();

            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        } finally {
            if (file != null) {
                file.delete();
            }
        }

    }
    private void Handler(){
        CargoHandler cargoHandler = new CargoHandler();
        CarrierHandler carrierHandler = new CarrierHandler();
        TransportationHandler transportationHandler = new TransportationHandler();
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("init-data", "homework_13", FILE);
    }


    private StringBuilder stringBuilder = new StringBuilder();

    private class CargoHandler extends DefaultHandler {

        private Cargo cargo;
        private FoodCargo foodCargo;
        private ClothersCargo clothersCargo;
        private Map<String, Cargo> cargos = new LinkedHashMap<>();
        private Map.Entry<String, Cargo> entry;

        @Override
        public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes)
                throws org.xml.sax.SAXException {

            stringBuilder.setLength(0);

            switch (qName) {
                case "cargo": {
                    cargo = new Cargo() {
                        @Override
                        public CargoType getCargoType() {
                            String cargoType = attributes.getValue("type");
                            if (cargoType.equals("FOOD")) {
                                foodCargo = new FoodCargo();
                                return CargoType.FOOD;
                            } else if (cargoType.equals("CLOTHERS")) {
                                clothersCargo = new ClothersCargo();
                                return CargoType.CLOTHERS;
                            }
                            return null;
                        }
                    };
                    String id = attributes.getValue("id");
                    entry = new AbstractMap.SimpleEntry<>(id, cargo);
                    break;
                }
            }
        }

        @Override
        public void endElement(String s, String s1, String qName) {
            String data = stringBuilder.toString();
            switch (qName) {
                case "name": {
                    cargo.setName(data);
                    break;
                }
                case "weight": {
                    cargo.setWeight(Integer.parseInt(data));
                    break;
                }
                case "expirationDate": {
                    try {
                        foodCargo.setExpirationDate(JavaUtilDateUtils.valueOf(data));
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                case "size": {
                    clothersCargo.setSize(data);
                    break;
                }
                case "storeTemperature": {
                    foodCargo.setStoreTemperature(Integer.parseInt(data));
                    cargo = foodCargo;
                    break;
                }
                case "material": {
                    clothersCargo.setMaterial(data);
                    cargo = clothersCargo;
                    break;
                }
                case "cargo": {
                    cargos.put(entry.getKey(), entry.getValue());
                }
            }
        }

        @Override
        public void characters(char[] chars, int start, int length) {
            String data = new String(chars, start, length);
            stringBuilder.append(data);
        }

        public Map<String, Cargo> getCargos() {
            return cargos;
        }
    }

    private class CarrierHandler extends DefaultHandler {
        private Carrier carrier;
        private Map<String, Carrier> carriers = new LinkedHashMap<>();
        private Map.Entry<String, Carrier> entry;
        boolean isEntered = false;

        @Override
        public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes)
                throws org.xml.sax.SAXException {

            stringBuilder.setLength(0);

            switch (qName) {
                case "carrier": {
                    isEntered = true;
                    carrier = new Carrier();
                    String id = attributes.getValue("id");
                    entry = new AbstractMap.SimpleEntry<>(id, carrier);
                    break;
                }
            }
        }

        @Override
        public void endElement(String s, String s1, String qName) {
            String data = stringBuilder.toString();
            switch (qName) {
                case "name": {
                    if (isEntered == true) {
                        carrier.setName(data);
                    }
                    break;
                }
                case "address": {
                    carrier.setAddress(data);
                    break;
                }
                case "type": {
                    if (isEntered == true) {
                        carrier.setCarrierType(CarrierType.valueOf(data));
                    }
                    break;
                }
                case "carrier": {
                    carriers.put(entry.getKey(), entry.getValue());
                    isEntered = false;
                    break;
                }
            }
        }

        @Override
        public void characters(char[] chars, int start, int length) {
            String data = new String(chars, start, length);
            stringBuilder.append(data);
        }

        public Map<String, Carrier> getCarriers() {
            return carriers;
        }
    }


    private class TransportationHandler extends DefaultHandler {

        private ParsedTransportation result;
        private Transportation transportation;
        private List<ParsedTransportation> transportations = new ArrayList<>();

        @Override
        public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes)
                throws org.xml.sax.SAXException {
            stringBuilder.setLength(0);

            switch (qName) {
                case "transportation": {
                    result = new ParsedTransportation();
                    result.setCargoRef(attributes.getValue("cargoref"));
                    result.setCarrierRef(attributes.getValue("carrierref"));
                    transportation = new Transportation();
                    break;
                }
            }
        }

        @Override
        public void endElement(String s, String s1, String qName) {
            String data = stringBuilder.toString();
            switch (qName) {
                case "billto": {
                    transportation.setBillTo(data);
                    break;
                }
                case "transportationBeginDate": {
                    try {
                        transportation.setTransportationBeginDate(JavaUtilDateUtils.valueOf(data));
                        break;
                    } catch (ParseException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                case "description": {
                    transportation.setDescription(data);
                    break;
                }
                case "transportation": {
                    result.setTransportation(transportation);
                    result.setTransportation(transportation);
                    transportations.add(result);
                    break;
                }
            }
        }

        @Override
        public void characters(char[] chars, int start, int length) {
            String data = new String(chars, start, length);
            stringBuilder.append(data);
        }

        public List<ParsedTransportation> getTransportations() {
            return transportations;
        }
    }
}

