package main.service.impl;

import main.entity.Sales;
import main.repository.SalesRepository;
import main.service.SalesService;

import java.util.List;
import java.util.Optional;

public class SalesServiceImpl implements SalesService {
    SalesRepository salesRepository;

    @Override
    public Optional<Sales> findSaleByID(int id) {
        return salesRepository.findById(id);
    }

    @Override
    public Integer countSoldGoodsByGoodID(int goodID) {
        return salesRepository.findByGoodID(goodID).stream()
                .mapToInt(Sales::getGoodCount)
                .sum();
    }

    @Override
    public List<String> namesOfGoodsWhichSoldGreaterThan(int count) {
        return salesRepository.findNamesByCountGreaterThan(count);
    }

    @Override
    public List<Sales> listSales() {
        return (List<Sales>) salesRepository.findAll();
    }

    @Override
    public List<Sales> findSalesByGoodID(int goodID) {
        return salesRepository.findByGoodID(goodID);
    }

    @Override
    public List<Sales> findSalesByCountEqual(int count) {
        return salesRepository.findByGoodCountEquals(count);
    }

    @Override
    public List<Sales> findSalesByCountMoreThan(int count) {
        return salesRepository.findByGoodCountGreaterThan(count);
    }

    @Override
    public List<Sales> findSalesByCountLessThan(int count) {
        return salesRepository.findByGoodCountLessThan(count);
    }

    @Override
    public List<Sales> findSalesByCreateDate(String createDate) {
        return salesRepository.findByCreateDate(createDate);
    }
}
