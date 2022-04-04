package main.service;

import main.entity.Sales;

import java.util.List;
import java.util.Optional;

public interface SalesService {
    Optional<Sales> findSaleByID(int id);
    Integer countSoldGoodsByGoodID(int goodID);
    List<String> namesOfGoodsWhichSoldGreaterThan(int count);

    List<Sales> listSales();
    List<Sales> findSalesByGoodID(int goodID);
    List<Sales> findSalesByCountEqual(int count);
    List<Sales> findSalesByCountMoreThan(int count);
    List<Sales> findSalesByCountLessThan(int count);
    List<Sales> findSalesByCreateDate(String createDate);
}
