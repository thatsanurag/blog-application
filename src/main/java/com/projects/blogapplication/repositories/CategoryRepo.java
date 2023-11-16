package com.projects.blogapplication.repositories;

import com.projects.blogapplication.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
