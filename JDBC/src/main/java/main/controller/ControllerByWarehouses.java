package main.controller;

import jakarta.persistence.EntityNotFoundException;
import main.entity.Warehouse1;
import main.entity.Warehouse2;
import main.service.Warehouse1Service;
import main.service.Warehouse2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/wc")
public class ControllerByWarehouses {
    @Autowired
    Warehouse1Service warehouse1Service;

    @Autowired
    Warehouse2Service warehouse2Service;

    @GetMapping("/warehouse/wh_id=1/find/id/id={id}")
    ResponseEntity<Warehouse1> getNoteInWarehouse1ByID(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(warehouse1Service.findNoteByID(id), HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with this id in warehouse №1 was not found!");
        }
    }

    @GetMapping("/warehouse/wh_id=2/find/id/id={id}")
    ResponseEntity<Warehouse2> getNoteInWarehouse2ByID(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(warehouse2Service.findNoteByID(id), HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with this id in warehouse №2 was not found!");
        }
    }

    @GetMapping("/warehouse/wh_id=1/find/count_by_good_id/id={id}")
    ResponseEntity<Integer> getCountInWarehouse1ByGoodID(@PathVariable Integer id) {
        return new ResponseEntity<>(warehouse1Service.countInWarehouseByGoodID(id), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=2/find/count_by_good_id/id={id}")
    ResponseEntity<Integer> getCountInWarehouse2ByGoodID(@PathVariable Integer id) {
        return new ResponseEntity<>(warehouse2Service.countInWarehouseByGoodID(id), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=1")
    ResponseEntity<List<Warehouse1>> getAllNotesInWarehouse1() {
        return new ResponseEntity<>(warehouse1Service.listNotesInWarehouse1(), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=2")
    ResponseEntity<List<Warehouse2>> getAllNotesInWarehouse2() {
        return new ResponseEntity<>(warehouse2Service.listNotesInWarehouse2(), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=1/find/good_id/id={id}")
    ResponseEntity<List<Warehouse1>> getNotesInWarehouse1ByGoodID(@PathVariable Integer id) {
        return new ResponseEntity<>(warehouse1Service.findNotesByGoodID(id), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=2/find/good_id/id={id}")
    ResponseEntity<List<Warehouse2>> getNotesInWarehouse2ByGoodID(@PathVariable Integer id) {
        return new ResponseEntity<>(warehouse2Service.findNotesByGoodID(id), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=1/find/equal/count={count}")
    ResponseEntity<List<Warehouse1>> getNotesInWarehouse1ByCountEqual(@PathVariable Integer count) {
        return new ResponseEntity<>(warehouse1Service.findNotesByCountEqual(count), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=2/find/equal/count={count}")
    ResponseEntity<List<Warehouse2>> getNotesInWarehouse2ByCountEqual(@PathVariable Integer count) {
        return new ResponseEntity<>(warehouse2Service.findNotesByCountEqual(count), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=1/find/less/count={count}")
    ResponseEntity<List<Warehouse1>> getNotesInWarehouse1ByCountLessThan(@PathVariable Integer count) {
        return new ResponseEntity<>(warehouse1Service.findNotesByCountLessThan(count), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=2/find/less/count={count}")
    ResponseEntity<List<Warehouse2>> getNotesInWarehouse2ByCountLessThan(@PathVariable Integer count) {
        return new ResponseEntity<>(warehouse2Service.findNotesByCountLessThan(count), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=1/find/greater/count={count}")
    ResponseEntity<List<Warehouse1>> getNotesInWarehouse1ByCountGreaterThan(@PathVariable Integer count) {
        return new ResponseEntity<>(warehouse1Service.findNotesByCountMoreThan(count), HttpStatus.OK);
    }

    @GetMapping("/warehouse/wh_id=2/find/greater/count={count}")
    ResponseEntity<List<Warehouse2>> getNotesInWarehouse2ByCountGreaterThan(@PathVariable Integer count) {
        return new ResponseEntity<>(warehouse2Service.findNotesByCountMoreThan(count), HttpStatus.OK);
    }

    @PostMapping(value = "/add_note_to_wh/wh_id=1", consumes = "wh1/json", produces = "wh1/json")
    public Warehouse1 addNoteToWarehouse1(@RequestBody Warehouse1 newNote) {
        return warehouse1Service.addNote(newNote);
    }

    @PostMapping(value = "/add_note_to_wh/wh_id=2", consumes = "wh2/json", produces = "wh2/json")
    public Warehouse2 addNoteToWarehouse2(@RequestBody Warehouse2 newNote) {
        return warehouse2Service.addNote(newNote);
    }

    @PostMapping(value = "/add_list_notes_to_wh/wh_id=1", consumes = "lwh1/json", produces = "lwh1/json")
    public List<Warehouse1> addListNotesToWarehouse1(@RequestBody List<Warehouse1> newList) {
        return warehouse1Service.addListNotes(newList);
    }

    @PostMapping(value = "/add_list_notes_to_wh/wh_id=2", consumes = "lwh2/json", produces = "lwh2/json")
    public List<Warehouse2> addListNotesToWarehouse2(@RequestBody List<Warehouse2> newList) {
        return warehouse2Service.addListNotes(newList);
    }

    @Autowired
    public void setWarehouse1Service(Warehouse1Service warehouse1Service) {
        this.warehouse1Service = warehouse1Service;
    }

    @Autowired
    public void setWarehouse2Service(Warehouse2Service warehouse2Service) {
        this.warehouse2Service = warehouse2Service;
    }
}
