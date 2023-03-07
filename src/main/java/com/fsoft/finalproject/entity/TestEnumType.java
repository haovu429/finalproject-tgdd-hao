//package com.fsoft.finalproject.entity;
//
//import com.fsoft.finalproject.statistic.Constant;
//
//import javax.persistence.*;
//
//public class TestEnumType {
//    @Id
//    // @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private long id;
//
//    @Column(name = "name", columnDefinition = "VARCHAR(50)")
//    private String name;
//
//    // district_id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = true)
//    @Enumerated(EnumType.STRING)
//    private Constant districtEntity;
//}
