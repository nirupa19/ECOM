package com.cg.ecom.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ecom.dto.ProductItemsDTO;
import com.cg.ecom.entity.ProductItems;
import com.cg.ecom.entity.ProductSupplier;
import com.cg.ecom.repository.ProductItemsRepository;
import com.cg.ecom.service.ProductItemsService;

@Service
public class ProductItemsServiceImpl implements ProductItemsService {
	@Autowired
	private ProductItemsRepository productItemsRepository;

	
	
	@Override
	public ProductItemsDTO addProductItems(ProductItemsDTO productItemsDTO) {

		ProductItems productitems = new ProductItems();
		ProductSupplier rest=new ProductSupplier();
		rest.setId(productItemsDTO.getProductSupplierId());
		productitems.setProductSuppliers(rest);
		productitems.setName(productItemsDTO.getName());
		productitems.setPrice(productItemsDTO.getPrice());
		productitems.setQuantity(productItemsDTO.getQuantity());	
		
		ProductItems productitemssave=productItemsRepository.save(productitems);
		productItemsDTO.setProductId(productitemssave.getId());
		return productItemsDTO;
	}

	@Override
	public ProductItemsDTO updateProductItems(ProductItemsDTO productItemsDTO) {

		ProductItems productitems = new ProductItems();
		ProductSupplier rest=new ProductSupplier();
		rest.setId(productItemsDTO.getProductSupplierId());
		productitems.setProductSuppliers(rest);
		productitems.setName(productItemsDTO.getName());
		productitems.setPrice(productItemsDTO.getPrice());
		productitems.setQuantity(productItemsDTO.getQuantity());	
		productitems.setId(productItemsDTO.getProductId());
		
		productItemsRepository.save(productitems);		
		return productItemsDTO;
	}

	@Override
	public boolean deleteProductItems(ProductItemsDTO productItemsDTO) {

		ProductItems productitems = new ProductItems();
		productitems.setId(productItemsDTO.getProductId());
		productItemsRepository.delete(productitems);
		return true;
	}

	@Override
	public ProductItemsDTO getById(int id) {

		Optional<ProductItems> productitems = productItemsRepository.findById(id);
		if (productitems.isPresent()) {
			ProductItemsDTO dto = new ProductItemsDTO();
			BeanUtils.copyProperties(productitems.get(), dto);
			return dto;
		}
		return null;
	}

	@Override
	public List<ProductItemsDTO> findAll() {

		Iterable<ProductItems> productitems = productItemsRepository.findAll();
		List<ProductItemsDTO> dtos = new ArrayList<>();
		for (ProductItems fooditem : productitems) {
			ProductItemsDTO dto = new ProductItemsDTO();
			BeanUtils.copyProperties(fooditem, dto);
			dtos.add(dto);
		}
		return dtos;
	}

}