package com.milmaniq.customer.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Customer implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
