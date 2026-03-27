package com.learningportal.learningportalproject.category;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseCategoryService {
	@Autowired
	private CourseCategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	public List<CategoryDto> findAllCategory() {
		return categoryMapper.toDto(categoryRepository.findAll());
	}

	public CategoryDto findById(Long id) {
		CourseCategoryEntity entity = categoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Course category " + id + "is not found"
				));
		return categoryMapper.toDto(entity);
	}

	public void deleteCategory(Long id)
	{
		if(!categoryRepository.existsById(id)) {
			throw new EntityNotFoundException("Category " + id + " not found");
		}
		categoryRepository.deleteById(id);
	}

	public CategoryDto saveCategory(CategoryDto dto) {
		CourseCategoryEntity entity = categoryMapper.toEntity(dto);
		entity.setCreatedOn(Timestamp.from(Instant.now()));
		CourseCategoryEntity saved = categoryRepository.save(entity);
		return categoryMapper.toDto(saved);
	}

	public CategoryDto updateCategory(CategoryDto dto, Long id) {

		CourseCategoryEntity existing = categoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Course Category " + id + " is not found"
				));

		existing.setName(dto.getName());
		existing.setUpdatedOn(Timestamp.from(Instant.now()));
		CourseCategoryEntity updated = categoryRepository.save(existing);
		return categoryMapper.toDto(updated);
	}
}
