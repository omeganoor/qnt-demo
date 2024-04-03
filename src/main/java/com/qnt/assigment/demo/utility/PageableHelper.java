package com.qnt.assigment.demo.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class PageableHelper {
  private PageableHelper() {
  }

  public static Pageable setPageable(Integer page, Integer size, Direction direction,
      String sort) {
    Direction setDirection = setDirection(direction);

    String setSort = setSortParam(sort);

    Integer setSize = maxSizePaginated(size);

    Integer setPage = getMinPage(page);

    return PageRequest.of(setPage, setSize, Sort.by(new Order(direction, setSort)));
  }

  private static Integer getMinPage(Integer page) {
    if (page == null){
      return 0;
    }
    return page;
  }


  public static Integer maxSizePaginated(Integer size) {
    Integer newSize = size;
    if (size == null || size > 1000) {
      newSize = 1000;
    }
    return newSize;
  }

  public static Direction setDirection(Direction sort) {
    if (sort == null ) {
      return Direction.ASC;
    }

    return sort;
  }

  public static String setSortParam(String sortParam) {
    if (sortParam == null) {
      return "id";
    }

    return sortParam;
  }
}