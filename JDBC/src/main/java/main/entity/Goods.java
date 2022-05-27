package main.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "priority")
    private Double priority;

    @OneToMany(mappedBy = "goods")
    private List<Sales> sales;

    @OneToMany(mappedBy = "goods")
    private List<Warehouse1> warehouse1s;

    @OneToMany(mappedBy = "goods")
    private List<Warehouse2> warehouse2s;

    public Goods() {
    }

    public Goods(String name, Double priority) {
        this.name = name;
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    public Integer getCountInWarehouse1() {
        int count = 0;
        for (Warehouse1 note: warehouse1s) {
            count += note.getGoodCount();
        }
        return count;
    }

    public Integer getCountInWarehouse2() {
        int count = 0;
        for (Warehouse2 note: warehouse2s) {
            count += note.getGoodCount();
        }
        return count;
    }

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }

    public List<Warehouse1> getWarehouse1s() {
        return warehouse1s;
    }

    public void setWarehouse1s(List<Warehouse1> warehouse1s) {
        this.warehouse1s = warehouse1s;
    }

    public List<Warehouse2> getWarehouse2s() {
        return warehouse2s;
    }

    public void setWarehouse2s(List<Warehouse2> warehouse2s) {
        this.warehouse2s = warehouse2s;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
