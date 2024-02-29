package main.service.impl;

import javax.persistence.EntityNotFoundException;
import main.entity.Sales;
import main.repository.SalesRepository;
import main.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    SalesRepository salesRepository;

    @Override
    public Sales findSaleByID(int id) {
        Optional<Sales> optSales = salesRepository.findById(id);
        if (optSales.isPresent()) {
            return optSales.get();
        }
        else {
            throw new EntityNotFoundException("Sale with that id was not found!");
        }
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

    @Override
    public Sales addSale(Sales sale) {
        return salesRepository.save(sale);
    }

    @Override
    public List<Sales> addListSales(List<Sales> sales) {
        return (List<Sales>) salesRepository.saveAll(sales);
    }
}
