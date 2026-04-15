package com.adacho.ch04_structured_output.dto;

import java.util.List;

import lombok.Data;

@Data
public class Hotel {
  // 도시 이름
  private String city;
  // 호텔 이름 목록
  private List<String> names;
}
