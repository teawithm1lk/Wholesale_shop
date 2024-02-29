package main.controller.api;

import javax.persistence.EntityNotFoundException;
import main.entity.Sales;
import main.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/wc")
public class ControllerBySales {
    @Autowired
    SalesService salesService;

    @GetMapping("/sales/find/id/id={id}")
    ResponseEntity<Sales> getByID(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(salesService.findSaleByID(id), HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale with this id was not found!");
        }
    }

    @GetMapping("/sales/find/sold_count_by_id/id={id}")
    ResponseEntity<Integer> getSoldCountGoodsByGoodID(@PathVariable Integer id) {
        return new ResponseEntity<>(salesService.countSoldGoodsByGoodID(id), HttpStatus.OK);
    }

    @GetMapping("/sales/find/names_by_sold_count_more/count={count}")
    ResponseEntity<List<String>> getNamesWhichSoldMoreTimesThan(@PathVariable Integer count) {
        return new ResponseEntity<>(salesService.namesOfGoodsWhichSoldGreaterThan(count), HttpStatus.OK);
    }

    @GetMapping("/sales")
    ResponseEntity<List<Sales>> getAllSales() {
        return new ResponseEntity<>(salesService.listSales(), HttpStatus.OK);
    }

    @GetMapping("/sales/find/good_id/id={id}")
    ResponseEntity<List<Sales>> getSalesByGoodID(@PathVariable Integer id) {
        return new ResponseEntity<>(salesService.findSalesByGoodID(id), HttpStatus.OK);
    }

    @GetMapping("/sales/find/equal/count={count}")
    ResponseEntity<List<Sales>> getSalesByCountEqual(@PathVariable Integer count) {
        return new ResponseEntity<>(salesService.findSalesByCountEqual(count), HttpStatus.OK);
    }

    @GetMapping("/sales/find/less/count={count}")
    ResponseEntity<List<Sales>> getSalesByCountLessThan(@PathVariable Integer count) {
        return new ResponseEntity<>(salesService.findSalesByCountLessThan(count), HttpStatus.OK);
    }

    @GetMapping("/sales/find/greater/count={count}")
    ResponseEntity<List<Sales>> getSalesByCountGreaterThan(@PathVariable Integer count) {
        return new ResponseEntity<>(salesService.findSalesByCountMoreThan(count), HttpStatus.OK);
    }

    @GetMapping("/sales/find/create_date/create_date={createDate}")
    ResponseEntity<List<Sales>> getSalesByCreateDate(@PathVariable String createDate) {
        return new ResponseEntity<>(salesService.findSalesByCreateDate(createDate), HttpStatus.OK);
    }

    @PostMapping(value = "/add_sale", consumes = "application/json", produces = "application/json")
    public Sales addSale(@RequestBody Sales newGood) {
        return salesService.addSale(newGood);
    }

    @PostMapping(value = "/add_list_sales", consumes = "application/json", produces = "application/json")
    public List<Sales> addListSales(@RequestBody List<Sales> newList) {
        return salesService.addListSales(newList);
    }

    @Autowired
    public void setSalesService(SalesService salesService) {
        this.salesService = salesService;
    }
}
