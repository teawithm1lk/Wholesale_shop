package main.service.impl;

import javax.persistence.EntityNotFoundException;
import main.entity.Goods;
import main.entity.Sales;
import main.entity.Warehouse1;
import main.entity.Warehouse2;
import main.repository.GoodsRepository;
import main.repository.SalesRepository;
import main.repository.Warehouse1Repository;
import main.repository.Warehouse2Repository;
import main.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    Warehouse1Repository warehouse1Repository;

    @Autowired
    Warehouse2Repository warehouse2Repository;

    @Override
    public Goods findGoodByID(int id) {
        Optional<Goods> optGood = goodsRepository.findById(id);
        if (optGood.isPresent()) {
            return optGood.get();
        }
        else {
            throw new EntityNotFoundException("Good with that id was not found!");
        }
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
    public String getWhenGoodSoldLastTimeByName(String name) {
        Optional<String> optTime = goodsRepository.getDateSoldLastTimeByName(name);
        if (optTime.isPresent()) {
            return optTime.get();
        }
        else {
            throw new EntityNotFoundException("Sale of that good was not found!");
        }
    }

    @Override
    public Goods findGoodsByName(String name) {
        Optional<Goods> optGoods = goodsRepository.findByName(name);
        if (optGoods.isPresent()) {
            return optGoods.get();
        }
        else {
            throw new EntityNotFoundException("Good with that name was not found!");
        }
    }

    @Override
    public List<Goods> listGoods() {
        return (List<Goods>)goodsRepository.findAll();
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

    @Override
    public Goods addGood(Goods good) {
        return goodsRepository.save(good);
    }

    @Override
    public List<Goods> addListGoods(List<Goods> goods) {
        return (List<Goods>) goodsRepository.saveAll(goods);
    }
}
