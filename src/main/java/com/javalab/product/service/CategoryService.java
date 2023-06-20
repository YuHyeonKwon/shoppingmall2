package com.javalab.product.service;

import java.util.List;

import com.javalab.product.dto.CategoryDTO;
import com.javalab.product.dto.PageRequestDTO;
import com.javalab.product.dto.PageResultDTO;
import com.javalab.product.entity.Category;

public interface CategoryService {

    PageResultDTO<CategoryDTO, Category> getList(PageRequestDTO requestDTO);    
    CategoryDTO read(Integer categoryId);    
    Category register(CategoryDTO category);			
    void modify(CategoryDTO categoryDTO);
	boolean remove(Integer categoryId);    
    
	

	/*
	 *  DTO --> Entity 전환을 위한 default 메소드
	 *  default 메소드는 기존의 인터페이스를 구현해서 사용하는 Impl클래스들이
	 *  의무적으로 구현하지 않아도 오류가 발생하지 않는다.
	 *  
	 *  Dto를 파라미터로 받아서 Entity에 담아준다. 왜냐하면 화면에서 받아서
	 *  저장할 때는 Entity 형태로 save(entity)해야 하기 때문이다. 물론
	 *  안해도 되지만 일반적으로 영속 영역에는 Entity만 넣는게 좋다.
	 */
	default Category dtoToEntity(CategoryDTO dto) {
		Category entity = Category.builder()
							.categoryId(dto.getCategoryId())
							.categoryName(dto.getCategoryName())
							.description(dto.getDescription())
							.build();
		return entity;
	}

	/*
	 *  DTO <-- Entity 전환을 위한 default 메소드
	 *  default 메소드는 기존의 인터페이스를 구현해서 사용하는 Impl클래스들이
	 *  의무적으로 구현하지 않아도 오류가 발생하지 않는다.
	 */

	default CategoryDTO entityToDto(Category entity) {

		CategoryDTO dto = CategoryDTO.builder()
							.categoryId(entity.getCategoryId())
							.categoryName(entity.getCategoryName())
							.description(entity.getDescription())
							.regDate(entity.getRegDate()) 
							.build();
							/*
							 *   getRegDate()는 BaseEntity에 선언된 자동 감사 목적의 메소드이다.
							 *   만약 BaseEntity의 클래스 접근제한자가 default라면 현재 CategoryService는
							 *   다른 패키지에 존재하므로 접근할 수 없게 된다. 
							 *   
							 *   만일 지금처럼 다른 패키지의 클래스에서 접근하려면 public으로 선언해야 한다.
							 */

		return dto;
	}
	
	// 상품등록폼에 카테고리 목록을 드롭다운 리스트 형태로 보여주기 위한 조회
	List<CategoryDTO> getList();

}
