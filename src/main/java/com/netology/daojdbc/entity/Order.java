package com.netology.daojdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "ORDERS")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp date;

    @Column
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @Column(nullable = false)
    String productName;

    @Column
    @Min(1)
    int amount;


}
