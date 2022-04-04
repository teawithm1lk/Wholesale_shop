package main.repository;

import main.entity.Sales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalesRepository extends CrudRepository<Sales, Integer> {
    @Query("select g.name from Goods g left join Sales s where s.goodCount > ?1")
    List<String> findNamesByCountGreaterThan(Integer goodCount);

    @Query("select s from Sales s where s.goods.name = ?1")
    List<Sales> findSalesByName(String name);

    @Query("select sum(s.goodCount) from Sales s where s.goods.id = ?1")
    Integer getCountSoldGoodsByGoodID(Integer goodID);

    @Query("select s from Sales s where s.goods.id = ?1")
    List<Sales> findByGoodID(Integer goodID);

    @Query("select s from Sales s where s.goodCount = ?1")
    List<Sales> findByGoodCountEquals(Integer count);

    @Query("select s from Sales s where s.goodCount > ?1")
    List<Sales> findByGoodCountGreaterThan(Integer count);

    @Query("select s from Sales s where s.goodCount < ?1")
    List<Sales> findByGoodCountLessThan(Integer count);

    @Query("select s from Sales s where s.goodCount = ?1")
    List<Sales> findByGoodCount(Integer goodCount);

    @Query("select s from Sales s where s.createDate = ?1")
    List<Sales> findByCreateDate(String createDate);
}
