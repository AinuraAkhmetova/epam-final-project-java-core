package project.common.business.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CommonRepo<TYPE, ID> {

  Optional<TYPE> findById(ID id);

  void save(TYPE entity);

  boolean update(TYPE entity);

  boolean deleteById(ID id) throws SQLException;

  List<TYPE> getAll();

  int countAll();
}
