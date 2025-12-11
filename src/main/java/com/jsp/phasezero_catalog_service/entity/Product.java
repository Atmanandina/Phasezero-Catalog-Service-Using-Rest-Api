package com.jsp.phasezero_catalog_service.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="products")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@Column(unique = true, nullable = false)
private String partNumber;

@Column(nullable = false)
private String partName;

@Column(nullable = false)
private String category;

@Column(nullable = false)
private double price;

@Column(nullable = false)
private int stock;


private double value;

@Column(nullable = false, updatable = false)
private Instant createdAt = Instant.now();


}
