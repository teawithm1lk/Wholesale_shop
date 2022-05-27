package main.service;

import main.entity.Sales;

import java.util.List;

public interface SalesService {
    Sales findSaleByID(int id);
    Integer countSoldGoodsByGoodID(int goodID);
    List<String> namesOfGoodsWhichSoldGreaterThan(int count);

    List<Sales> listSales();
    List<Sales> findSalesByGoodID(int goodID);
    List<Sales> findSalesByCountEqual(int count);
    List<Sales> findSalesByCountMoreThan(int count);
    List<Sales> findSalesByCountLessThan(int count);
    List<Sales> findSalesByCreateDate(String createDate);

    Sales addSale(Sales sale);
    List<Sales> addListSales(List<Sales> sales);
}
