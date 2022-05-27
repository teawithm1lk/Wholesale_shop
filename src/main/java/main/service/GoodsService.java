package main.service;

import main.entity.Goods;

import java.util.List;

public interface GoodsService {
    Goods findGoodByID(int id);
    boolean isExistGood(String name);
    Integer totalCountOfGoodByID(int id);
    Integer totalCountOfGoodByName(String name);
    Integer countOfSoldGoodsByName(String name);
    String getWhenGoodSoldLastTimeByName(String name);
    Goods findGoodsByName(String name);

    List<Goods> listGoods();
    List<Goods> findGoodsByPriorityEqual(Double priority);
    List<Goods> findGoodsByPriorityMoreThan(Double priority);
    List<Goods> findGoodsByPriorityLessThan(Double priority);

    Goods addGood(Goods good);
    List<Goods> addListGoods(List<Goods> goods);
}
