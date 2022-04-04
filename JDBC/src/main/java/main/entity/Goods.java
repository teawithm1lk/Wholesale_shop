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

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
