package main.service;

import main.entity.Goods;

import java.util.List;
import java.util.Optional;

public interface GoodsService {
    Optional<Goods> findGoodByID(int id);
    boolean isExistGood(String name);
    Integer totalCountOfGoodByID(int id);
    Integer totalCountOfGoodByName(String name);
    Integer countOfSoldGoodsByName(String name);
    Optional<String> getWhenGoodSoldLastTimeByName(String name);

    List<Goods> listGoods();
    Optional<Goods> findGoodsByName(String name);
    List<Goods> findGoodsByPriorityEqual(Double priority);
    List<Goods> findGoodsByPriorityMoreThan(Double priority);
    List<Goods> findGoodsByPriorityLessThan(Double priority);
}
