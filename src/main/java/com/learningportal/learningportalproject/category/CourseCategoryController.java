package com.learningportal.learningportalproject.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CourseCategoryController {
	@Autowired
	private CourseCategoryService categoryService;

	@GetMapping(value = "/getAllCategories", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryDto> findAllCategories() {
		List<CategoryDto> data = categoryService.findAllCategory();
		return data;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDto findCategoryById(@PathVariable("id") Long id) {
		return categoryService.findById(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
		return categoryService.saveCategory(categoryDto);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
		return categoryService.updateCategory(categoryDto, id);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteCategory(@PathVariable Long id)
	{
		categoryService.deleteCategory(id);
	}

}