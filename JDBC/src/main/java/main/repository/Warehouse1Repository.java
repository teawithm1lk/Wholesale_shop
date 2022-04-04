package main.repository;

import main.entity.Warehouse1;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Warehouse1Repository extends CrudRepository<Warehouse1, Integer> {
    @Query("select sum(w.goodCount) from Warehouse1 w where w.goods.id = ?1")
    Integer getCountByGoodID(Integer goodID);

    @Query("select w from Warehouse1 w where w.goodCount = ?1")
    List<Warehouse1> findByGoodCount(Integer goodCount);

    @Query("select w from Warehouse1 w where w.goods.id = ?1")
    List<Warehouse1> findByGoodID(Integer goodID);

    @Query("select w from Warehouse1 w where w.goods.name = ?1")
    Warehouse1 findByName(String name);

    @Query("select w from Warehouse1 w where w.goodCount = ?1")
    List<Warehouse1> findByGoodCountEquals(Integer count);

    @Query("select w from Warehouse1 w where w.goodCount > ?1")
    List<Warehouse1> findByGoodCountGreaterThan(Integer count);

    @Query("select w from Warehouse1 w where w.goodCount < ?1")
    List<Warehouse1> findByGoodCountLessThan(Integer count);
}
