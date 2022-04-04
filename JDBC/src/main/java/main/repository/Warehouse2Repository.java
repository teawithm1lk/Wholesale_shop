package main.repository;

import main.entity.Warehouse2;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Warehouse2Repository extends CrudRepository<Warehouse2, Integer> {
    @Query("select sum(w.goodCount) from Warehouse2 w where w.goods.id = ?1")
    Integer getCountByGoodID(Integer goodID);

    @Query("select w from Warehouse2 w where w.goodCount = ?1")
    List<Warehouse2> findByGoodCount(Integer goodCount);

    @Query("select w from Warehouse2 w where w.goods.id = ?1")
    List<Warehouse2> findByGoodID(Integer goodID);

    @Query("select w from Warehouse2 w where w.goods.name = ?1")
    Warehouse2 findByName(String name);

    @Query("select w from Warehouse2 w where w.goodCount = ?1")
    List<Warehouse2> findByGoodCountEquals(Integer count);

    @Query("select w from Warehouse2 w where w.goodCount > ?1")
    List<Warehouse2> findByGoodCountGreaterThan(Integer count);

    @Query("select w from Warehouse2 w where w.goodCount < ?1")
    List<Warehouse2> findByGoodCountLessThan(Integer count);
}
