package app.grocery.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.grocery.entities.Product;
import app.grocery.repository.ProductRepository;
import app.grocery.service.ProductService;

@Service
public class ProductImpl implements ProductService{

	@Autowired
	ProductRepository productRepo;
	
	
	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		
		return (List<Product>) productRepo.findAll();
	}

	@Override
	public Product getProduct(long productId) {
		
		
		return productRepo.findById(productId).get();
	}

	

	
	@Override
	public String addProduct(Product product) {
		// TODO Auto-generated method stub
		 productRepo.save(product);
		 return "Product Added!";
	}

	@Override
	public Product updateProduct(long productId,Product product) {
		Product prod=productRepo.findById(productId).get();
		prod.setCategory(product.getCategory());
		prod.setProductImage(product.getProductImage());
		prod.setProductName(product.getProductName());
		prod.setProductInfo(product.getProductInfo());
		productRepo.save(prod);
		return prod;
	}

	@Override
	public String deleteProduct(long productId) {
		// TODO Auto-generated method stub
		productRepo.deleteById(productId);
		return "Product Deleted";
	}

	@Override
	public List<Product> findProductByCategory(long categoryId) {
		
		List<Product> productList=(List<Product>)productRepo.findAll();
		List<Product> productListByCategory=new ArrayList<Product>();
		for(Product product:productList)
		{
			if(product.getCategory().getCategoryId()==(categoryId))
			{
				productListByCategory.add(product);
			}
		}
		
		
		return productListByCategory;
	}

}
