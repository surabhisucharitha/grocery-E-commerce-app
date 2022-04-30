package app.grocery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.grocery.entities.Category;
import app.grocery.entities.Product;
import app.grocery.entities.Seller;
import app.grocery.implementation.ProductImpl;
import app.grocery.repository.CategoryRepository;
import app.grocery.repository.ProductRepository;
import app.grocery.repository.SellerRepository;

@RestController
@RequestMapping("/api/grocery/v1")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductImpl productImpl;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private SellerRepository sellerRepo;
	
	
	  @CrossOrigin
	  @GetMapping("/products") 
	  public List<Product> getProducts() 
	  {
	  
	  return productImpl.getProducts(); 
	  }
	  
	  @CrossOrigin
	  @GetMapping("/product/{productid}") 
	  public Product getProduct(@PathVariable("productid")long productid) 
	  {
	  
	  return productImpl.getProduct(productid);
	  }
	  
	  @Transactional
	  @CrossOrigin
	  @PostMapping("/addproduct") 
	  public String addProduct(@RequestBody Product product) 
	  {
	  Category category=categoryRepo.findById(product.getCategory().getCategoryId()).get();
	  Seller seller=sellerRepo.findById(product.getSeller().getSellerId()).get();
	  
	  product.setSeller(seller);
	  product.setCategory(category);
	  System.out.print(product);
	  return productImpl.addProduct(product); 
	  }
	  
	  @CrossOrigin
	  @RequestMapping("/getproductsbycategory/{categoryId}")
	  public List<Product> productByCategory(@PathVariable("categoryId") long categoryId)
	  {
		  return productImpl.findProductByCategory(categoryId);
	  }
	 

}
