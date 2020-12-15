package com.et.expense.tracker.services;

import com.et.expense.tracker.domain.Category;
import com.et.expense.tracker.exception.EtBadRequestException;
import com.et.expense.tracker.exception.EtResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> fetchAllCategories(Integer userId);

    Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Category addCategory(Integer userId, String title, String description) throws EtBadRequestException;

    void updateCategory(Integer userId, Integer categoryId, Category category) throws EtResourceNotFoundException;

    void removeCategoryWithAllTransactions(Integer userId,Integer categoryId) throws EtResourceNotFoundException;

}
