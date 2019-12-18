package homework_7.cargo.repo;

import homework_7.cargo.domain.Cargo;

public interface CargoCommonRepo {

    void add(Cargo cargo);

    Cargo getById(long id);
}
