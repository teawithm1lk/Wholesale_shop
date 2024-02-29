package main.controller.gui;

import main.entity.*;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Controller
public class GUIController {
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private Warehouse1Repository warehouse1Repository;

    @Autowired
    private Warehouse2Repository warehouse2Repository;

    @GetMapping
    public String viewHomePage() {
        return "/home";
    }

    @GetMapping("/login")
    public String showSignInForm(final Model model) {
        model.addAttribute("user", new User());
        return "/login/sign_in";
    }

    @GetMapping("/goods")
    public String listGoods(final Model model) {
        final List<Goods> goods = ((List<Goods>) goodsRepository.findAll())
                .stream()
                .filter(good -> {
                    try {
                        return isNotNull(good);
                    } catch (IllegalAccessException exception) {
                        throw new RuntimeException(exception);
                    }
                })
                .filter(good -> good.getCurrentCount() > 0)
                .toList();
        model.addAttribute("goodsList", goods);
        return "/goods/goods";
    }

    @GetMapping("/sales")
    public String listSales(final Model model) {
        final List<Sales> sales = ((List<Sales>) salesRepository.findAll())
                .stream()
                .filter(sale -> {
                    try {
                        return isNotNull(sale);
                    } catch (IllegalAccessException exception) {
                        throw new RuntimeException(exception);
                    }
                }).toList();
        model.addAttribute("salesList", sales);
        return "/sales/sales";
    }

    @GetMapping("/warehouse/wh_id=1/notes")
    public String listWarehouse1Notes(final Model model) {
        final List<Warehouse1> notes = ((List<Warehouse1>) warehouse1Repository.findAll())
                .stream()
                .filter(note -> {
                    try {
                        return isNotNull(note);
                    } catch (IllegalAccessException exception) {
                        throw new RuntimeException(exception);
                    }
                }).toList();
        model.addAttribute("warehouse1Notes", notes);
        return "/warehouses/all_notes_1";
    }

    @GetMapping("/warehouse/wh_id=2/notes")
    public String listWarehouse2Notes(final Model model) {
        final List<Warehouse2> notes = ((List<Warehouse2>) warehouse2Repository.findAll())
                .stream()
                .filter(note -> {
                    try {
                        return isNotNull(note);
                    } catch (IllegalAccessException exception) {
                        throw new RuntimeException(exception);
                    }
                }).toList();
        model.addAttribute("warehouse2Notes", notes);
        return "/warehouses/all_notes_2";
    }

    @GetMapping("/goods/{id}")
    public String showGood(@PathVariable("id") Integer id, final Model model) {
        final Optional<Goods> good = goodsRepository.findById(id);
        if (good.isEmpty()) {
            return "/goods/not_found";
        }
        final Goods clearGood = good.get();
        model.addAttribute("good", clearGood);
        return "/goods/good";
    }

    @GetMapping("/sales/{id}")
    public String showSale(@PathVariable("id") Integer id, final Model model) {
        final List<Sales> sales = salesRepository.findByGoodID(id);
        if (sales.isEmpty()) {
            return "/sales/not_found";
        }
        model.addAttribute("sale", sales);
        model.addAttribute("goods", goodsRepository.findById(id).get());
        return "/sales/sale";
    }

    @GetMapping("/warehouse/wh_id=1/{id}")
    public String showNoteInWH1(@PathVariable("id") Integer id, final Model model) {
        final List<Warehouse1> notes = warehouse1Repository.findByGoodID(id);
        if (notes.isEmpty()) {
            return "/warehouses/not_found";
        }
        model.addAttribute("whNote", notes);
        model.addAttribute("goods", goodsRepository.findById(id).get());
        return "/warehouses/note";
    }

    @GetMapping("/warehouse/wh_id=2/{id}")
    public String showNoteInWH2(@PathVariable("id") Integer id, final Model model) {
        final List<Warehouse2> notes = warehouse2Repository.findByGoodID(id);
        if (notes.isEmpty()) {
            return "/warehouses/not_found";
        }
        model.addAttribute("whNote", notes);
        model.addAttribute("goods", goodsRepository.findById(id).get());
        return "/warehouses/note";
    }

    @GetMapping("/sales/add")
    public String showSaleRegistration(final Model model) {
        model.addAttribute("sale", new Sales());
        model.addAttribute("goodId", new GoodId());
        return "/sales/new_sale";
    }

    @PostMapping("/sales/add")
    public String processSaleRegistration(final Sales sale, final GoodId goodId) {
        final Optional<Goods> foundGood = goodsRepository.findById(goodId.getGoodId());
        if (foundGood.isEmpty()) {
            return "/goods/not_found";
        }

        final Goods clearGood = foundGood.get();
        if (sale.getGoodCount() > clearGood.getCurrentCount()) {
            return "/sales/not_enough";
        }

        sale.setGoods(clearGood);
        salesRepository.save(sale);
        return "/home";
    }

    @GetMapping("/goods/add")
    public String showGoodRegistration(final Model model) {
        model.addAttribute("good", new Goods());
        return "/goods/new_good";
    }

    @PostMapping("/goods/add")
    public String processGoodRegistration(final Goods good) {
        final Optional<Goods> foundGood = goodsRepository.findByName(good.getName());
        if (foundGood.isPresent()) {
            return "/goods/already_exists";
        }
        goodsRepository.save(good);
        return "/home";
    }

    @GetMapping("/warehouse/wh_id=1/add")
    public String showWarehouse1Registration(final Model model) {
        model.addAttribute("noteInWH1", new Warehouse1());
        model.addAttribute("goodId", new GoodId());
        return "/warehouses/new_note_1";
    }

    @PostMapping("/warehouse/wh_id=1/add")
    public String processWarehouse1Registration(final Warehouse1 note, final GoodId goodId) {
        final Optional<Goods> foundGood = goodsRepository.findById(goodId.getGoodId());
        if (foundGood.isEmpty()) {
            return "/goods/not_found";
        }
        note.setGoods(foundGood.get());
        warehouse1Repository.save(note);
        return "/home";
    }

    @GetMapping("/warehouse/wh_id=2/add")
    public String showWarehouse2Registration(final Model model) {
        model.addAttribute("noteInWH2", new Warehouse2());
        model.addAttribute("goodId", new GoodId());
        return "/warehouses/new_note_2";
    }

    @PostMapping("/warehouse/wh_id=2/add")
    public String processWarehouse2Registration(final Warehouse2 note, final GoodId goodId) {
        final Optional<Goods> foundGood = goodsRepository.findById(goodId.getGoodId());
        if (foundGood.isEmpty()) {
            return "/goods/not_found";
        }
        note.setGoods(foundGood.get());
        warehouse2Repository.save(note);
        return "/home";
    }

    public static boolean isNotNull(final Object obj) throws IllegalAccessException {
        for (final Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(obj) != null)
                return true;
        }
        return false;
    }
}