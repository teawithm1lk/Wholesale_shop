package main.controller;

import jakarta.persistence.EntityNotFoundException;
import main.entity.Goods;
import main.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/wc")
public class ControllerByGoods {
    @Autowired
    GoodsService goodsService;

    @GetMapping("/goods/find/id/id={id}")
    ResponseEntity<Goods> getByID(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(goodsService.findGoodByID(id), HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Good with this id was not found!");
        }
    }

    @GetMapping("/goods/find/total_count_by_id/id={id}")
    ResponseEntity<Integer> getTotalCountGoodsByID(@PathVariable Integer id) {
        return new ResponseEntity<>(goodsService.totalCountOfGoodByID(id), HttpStatus.OK);
    }

    @GetMapping("/goods/find/total_count_by_name/name={name}")
    ResponseEntity<Integer> getTotalCountGoodsByName(@PathVariable String name) {
        return new ResponseEntity<>(goodsService.totalCountOfGoodByName(name), HttpStatus.OK);
    }

    @GetMapping("/goods/find/sold_count_by_name/name={name}")
    ResponseEntity<Integer> getCountOfSoldGoodsByName(@PathVariable String name) {
        return new ResponseEntity<>(goodsService.countOfSoldGoodsByName(name), HttpStatus.OK);
    }

    @GetMapping("/goods/find/time_by_name/name={name}")
    ResponseEntity<String> getTimeWhenGoodWasSoldByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(goodsService.getWhenGoodSoldLastTimeByName(name), HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Good with this name was not found!");
        }
    }

    @GetMapping("/goods")
    ResponseEntity<List<Goods>> getAllGoods() {
        return new ResponseEntity<>(goodsService.listGoods(), HttpStatus.OK);
    }

    @GetMapping("/goods/find/name/name={name}")
    ResponseEntity<Goods> getGoodsWithPriorityEqual(@PathVariable String name) {
        try {
            return new ResponseEntity<>(goodsService.findGoodsByName(name), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Good with this name was not found!");
        }
    }

    @GetMapping("/goods/find/equal/priority={priority}")
    ResponseEntity<List<Goods>> getGoodsWithPriorityEqual(@PathVariable double priority) {
        return new ResponseEntity<>(goodsService.findGoodsByPriorityEqual(priority), HttpStatus.OK);
    }

    @GetMapping("/goods/find/less/priority={priority}")
    ResponseEntity<List<Goods>> getGoodsWithPriorityLessThan(@PathVariable double priority) {
        return new ResponseEntity<>(goodsService.findGoodsByPriorityLessThan(priority), HttpStatus.OK);
    }

    @GetMapping("/goods/find/greater/priority={priority}")
    ResponseEntity<List<Goods>> getGoodsWithPriorityGreaterThan(@PathVariable double priority) {
        return new ResponseEntity<>(goodsService.findGoodsByPriorityMoreThan(priority), HttpStatus.OK);
    }

    @PostMapping(value = "/add_good", consumes = "good/json", produces = "good/json")
    public Goods addGood(@RequestBody Goods newGood) {
        return goodsService.addGood(newGood);
    }

    @PostMapping(value = "/add_list_goods", consumes = "good/json", produces = "good/json")
    public List<Goods> addListGoods(@RequestBody List<Goods> newList) {
        return goodsService.addListGoods(newList);
    }

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
}
