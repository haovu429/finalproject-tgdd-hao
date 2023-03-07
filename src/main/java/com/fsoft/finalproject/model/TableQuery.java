package com.fsoft.finalproject.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableQuery {
    String name;
    List<String> fieldQuery;
}
