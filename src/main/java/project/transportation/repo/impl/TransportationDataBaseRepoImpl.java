package project.transportation.repo.impl;

import project.common.solutions.utils.DbUtils;
import project.transportation.domain.Transportation;
import project.transportation.repo.TransportationRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TransportationDataBaseRepoImpl implements TransportationRepo {
    @Override
    public Optional<Transportation> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void save(Transportation entity) {

    }

    @Override
    public boolean update(Transportation entity) {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public List<Transportation> getAll() {
        return DbUtils.selectAll("SELECT * FROM TRANSPORTATION",
                TransportationMapper::mapTransportation);
    }

    @Override
    public int countAll() {
        return 0;
    }
}
