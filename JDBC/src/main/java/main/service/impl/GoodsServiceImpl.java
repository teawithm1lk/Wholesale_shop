package main.service.impl;

import main.entity.Goods;
import main.entity.Sales;
import main.entity.Warehouse1;
import main.entity.Warehouse2;
import main.repository.GoodsRepository;
import main.repository.SalesRepository;
import main.repository.Warehouse1Repository;
import main.repository.Warehouse2Repository;
import main.service.GoodsService;

import java.util.List;
import java.util.Optional;

public class GoodsServiceImpl implements GoodsService {
    GoodsRepository goodsRepository;
    SalesRepository salesRepository;
    Warehouse1Repository warehouse1Repository;
    Warehouse2Repository warehouse2Repository;

    @Override
    public Optional<Goods> findGoodByID(int id) {
        return goodsRepository.findById(id);
    }

    @Override
    public boolean isExistGood(String name) {
        return goodsRepository.existsByName(name);
    }

    @Override
    public Integer totalCountOfGoodByID(int id) {
        return warehouse1Repository.findByGoodID(id)
                .stream()
                .mapToInt(Warehouse1::getGoodCount)
                .sum()
                +
                warehouse2Repository.findByGoodID(id)
                .stream()
                .mapToInt(Warehouse2::getGoodCount)
                .sum();
    }

    @Override
    public Integer totalCountOfGoodByName(String name) {
        return warehouse1Repository.findByName(name).getGoodCount()
                +
                warehouse2Repository.findByName(name).getGoodCount();
    }

    @Override
    public Integer countOfSoldGoodsByName(String name) {
        return salesRepository.findSalesByName(name).stream()
                .mapToInt(Sales::getGoodCount)
                .sum();
    }

    @Override
    public Optional<String> getWhenGoodSoldLastTimeByName(String name) {
        return goodsRepository.getDateSoldLastTimeByName(name);
    }

    @Override
    public List<Goods> listGoods() {
        return (List<Goods>)goodsRepository.findAll();
    }

    @Override
    public Optional<Goods> findGoodsByName(String name) {
        return goodsRepository.findByName(name);
    }

    @Override
    public List<Goods> findGoodsByPriorityEqual(Double priority) {
        return goodsRepository.findByPriorityEqual(priority);
    }

    @Override
    public List<Goods> findGoodsByPriorityMoreThan(Double priority) {
        return goodsRepository.findByPriorityGreaterThan(priority);
    }

    @Override
    public List<Goods> findGoodsByPriorityLessThan(Double priority) {
        return goodsRepository.findByPriorityLessThan(priority);
    }
}
