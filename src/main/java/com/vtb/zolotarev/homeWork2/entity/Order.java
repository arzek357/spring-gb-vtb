package com.vtb.zolotarev.homeWork2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "consumer_id")
    private Long consumerId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "actual_product_price")
    private Long actualProductPrice;

    @Column(name = "number")
    private int number;

    public Order(long consumer_id, long product_id,long actualProductPrice,int number) {
        this.consumerId=consumer_id;
        this.productId=product_id;
        this.actualProductPrice=actualProductPrice;
        this.number=number;
    }

    @Override
    public String toString() {
        return String.format("Order [id = %d, consumer_id = %d, product_id=%d, actual_price=%d, number=%d]", id,consumerId,productId,actualProductPrice,number);
    }
}
