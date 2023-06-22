package com.onerivet.deskbook.util;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationAndSorting implements Serializable {

    private static final long serialVersionUID = 2720377770109987183L;

    private int page = 0;

    private int size = 10;

    private String sortBy;

    private Direction direction;

    public Pageable createPageRequest() {
        return (sortBy != null ? PageRequest.of(this.page, this.size, Sort.by(this.direction, this.sortBy))
                : PageRequest.of(this.page, this.size));
    }

}
