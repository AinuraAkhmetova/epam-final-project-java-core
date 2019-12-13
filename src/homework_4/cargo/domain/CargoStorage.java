package homework_4.cargo.domain;

import homework_4.IdGenerator;

public class CargoStorage {
    private static final int ARRAY_CAPACITY = 2;

    private static Cargo[] cargos = new Cargo[ARRAY_CAPACITY];
    private static int cargoIndex = 0;


    public static Cargo[] getAll() {
        return cargos;
    }

    public static void findById(Long id) {

        for (int i = 0; i < cargos.length; i++) {
            if (cargos[i].getId().equals(id)) {
                System.out.println("ID = " + id);
            }
        }
    }

    /*public static void findByName(String name) {
        for (int i = 0; i < cargos.length; i++) {
            if (name.equals(cargos[i].getName())) {
                System.out.println("Name = " + name);
            } else {
                System.out.println("Name was not found");
            }
        }
    }*/


    public static void addCargo(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargos[cargoIndex] = cargo;
        cargoIndex++;

        if (cargoIndex % (ARRAY_CAPACITY - 1) == 0) {
            Cargo[] newCargos = new Cargo[cargoIndex + ARRAY_CAPACITY];
            copyCargoArray(cargos, newCargos);
            cargos = newCargos;
        }
    }

    private static void copyCargoArray(Cargo[] src, Cargo[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    public static void printAllCargos() {
        for (Cargo cargo : cargos) {
            if (cargo != null) {
                System.out.println(cargo);
            }
        }
    }
}
