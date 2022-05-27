package main.repository;

import main.entity.Goods;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends CrudRepository<Goods, Integer> {
    @Query("select g from Goods g where g.name = ?1")
    Optional<Goods> findByName(String name);

    @Query("select g from Goods g where g.priority = ?1")
    List<Goods> findByPriorityEqual(Double priority);

    @Query("select g from Goods g where g.priority > ?1")
    List<Goods> findByPriorityGreaterThan(Double priority);

    @Query("select g from Goods g where g.priority < ?1")
    List<Goods> findByPriorityLessThan(Double priority);

    @Query("select (count(g) > 0) from Goods g where g.name = ?1")
    boolean existsByName(String name);

    @Query("select sum(s.goodCount) from Sales s where s.goods.name = ?1")
    Optional<Integer> getCountSoldGoodsByName(String name);

    @Query("select s.createDate from Sales s where s.goods.name = ?1")
    Optional<String> getDateSoldLastTimeByName(String name);
}
