package project.cargo.repo.impl;

import project.cargo.domain.Cargo;
import project.cargo.domain.FoodCargo;
import project.cargo.search.CargoSearchCondition;
import project.common.solutions.utils.DbUtils;
import project.common.solutions.utils.CollectionUtils;
import project.transportation.domain.Transportation;
import project.transportation.repo.impl.TransportationDataBaseRepoImpl;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CargoDataBaseRepoImpl extends CommonCargoRepo {


    public CargoDataBaseRepoImpl() {
    }

    public static void main(String[] args) {
        CargoDataBaseRepoImpl cargoDataBaseRepo = new CargoDataBaseRepoImpl();

        TransportationDataBaseRepoImpl transportationDataBaseRepo = new TransportationDataBaseRepoImpl();

        List<Cargo> cargos2 = cargoDataBaseRepo.getAll();
        cargos2.forEach(cargo33 -> System.out.println(cargo33.toString()));
        System.out.println("---------------");


        FoodCargo cargo = new FoodCargo();
        cargo.setId(20L);
        cargo.setName("APPLE");
        cargo.setWeight(230);
        cargo.setStoreTemperature(2);


        cargoDataBaseRepo.save(cargo);
        //System.out.println(cargoDataBaseRepo.findById(2L).toString());
        //System.out.println(cargoDataBaseRepo.findByName("JEANS").length);


       // List<Transportation> transportations = transportationDataBaseRepo.getAll();
        //transportations.forEach(transportation -> System.out.println(transportation.toString()));

        try {
            cargoDataBaseRepo.deleteById(20L);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //System.out.println(cargo.toString());


        List<Cargo> cargos = cargoDataBaseRepo.getAll();
        List<Transportation> transportations = transportationDataBaseRepo.getAll();
        cargos.forEach(cargo33 -> System.out.println(cargo33.toString()));
       transportations.forEach(transportation -> System.out.println(transportation.toString()));
    }

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Cargo[] findByName(String name) {
        return DbUtils.selectByName("SELECT * FROM CARGO WHERE NAME =?", name, CargoMapper::mapCargo).toArray(new Cargo[0]);

    }

    @Override
    public List<Cargo> search(CargoSearchCondition searchCondition) {

        List<Cargo> cargos = getAll();

        if (CollectionUtils.isNotEmpty(cargos)) {
            if (searchCondition.needSorting()) {
                Comparator<Cargo> cargoComparator = createCargoComparator(searchCondition);
                cargos.sort(searchCondition.isAscOrdering() ? cargoComparator : cargoComparator.reversed());
            }
        }
        return cargos;
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        return Optional.ofNullable(DbUtils.selectById(
                "SELECT * FROM CARGO WHERE ID=?", id, CargoMapper::mapCargo).get(0));

    }


    @Override
    public void save(Cargo cargo) {
        DbUtils.executeUpdate(
                "INSERT INTO CARGO (ID, NAME, WEIGHT, ENTITY_TYPE) VALUES (?,?,?,?)",
                ps -> {
                    int i = 0;
                    ps.setLong(++i, cargo.getId());
                    ps.setString(++i, cargo.getName());
                    ps.setInt(++i, cargo.getWeight());
                    ps.setString(++i, String.valueOf(cargo.getCargoType()));
                }
        );

    }

    @Override
    public boolean update(Cargo cargo) {
        int affectedRows = DbUtils.executeUpdate(
                "UPDATE CARGO SET NAME=?, WEIGHT=?,ENTITY_TYPE=? WHERE ID =?",
                ps -> {
                    int i = 0;
                    ps.setString(++i, cargo.getName());
                    ps.setInt(++i, cargo.getWeight());
                    ps.setString(++i, String.valueOf(cargo.getCargoType()));
                    ps.setLong(++i, cargo.getId());
                }
        );

        return affectedRows == 1;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {

        return DbUtils.transaction("DELETE FROM TRANSPORTATION WHERE CARGO_ID =?",
                ps -> {
                    int i = 0;
                    ps.setLong(++i, id);
                }, "DELETE FROM CARGO WHERE ID =?",
                ps2 -> {int i = 0;
                    ps2.setLong(++i, id);
                });

    }

    @Override
    public List<Cargo> getAll() {
        return DbUtils.selectAll("SELECT * FROM CARGO",
                CargoMapper::mapCargo);
    }

    @Override
    public int countAll() {
        return getAll().size();
    }
}


