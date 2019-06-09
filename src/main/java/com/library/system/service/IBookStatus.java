package com.library.system.service;
import java.util.List;
import com.library.system.entity.BookStatus;

public interface IBookStatus {
    List<BookStatus> selectAll();
}
