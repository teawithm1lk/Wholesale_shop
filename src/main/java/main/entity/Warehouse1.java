package main.entity;

import javax.persistence.*;

@Entity
public class Warehouse1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Goods goods;

    private Integer goodCount;

    public Warehouse1() {
    }

    public Warehouse1(Goods goods, Integer goodCount) {
        this.goods = goods;
        this.goodCount = goodCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }
}
