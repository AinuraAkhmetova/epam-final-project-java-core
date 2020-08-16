package project.carrier.repo.impl;

import project.carrier.domain.Carrier;
import project.carrier.repo.CarrierRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarrierDataBaseRepoImpl implements CarrierRepo {
    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return Optional.empty();
    }

    @Override
    public Carrier[] findByName(String name) {
        return new Carrier[0];
    }

    @Override
    public Optional<Carrier> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void save(Carrier entity) {

    }

    @Override
    public boolean update(Carrier entity) {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public List<Carrier> getAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
