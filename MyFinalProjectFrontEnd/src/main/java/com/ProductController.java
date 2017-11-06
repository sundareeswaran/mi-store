package com;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dao.CategoryDAO;
import com.dao.ProductDAO;
import com.dao.SupplierDAO;
import com.model.Category;
import com.model.Product;
import com.model.Supplier;


@Controller
public class ProductController {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	SupplierDAO supplierDAO;
	
	@RequestMapping(value="product",method=RequestMethod.GET)
	public String showProduct(Model m)
	{
		Product product = new Product();
		m.addAttribute(product);
		List<Product> listProduct=productDAO.retrieveProduct();
		m.addAttribute("productList",listProduct);
		m.addAttribute("categoryList",this.getCatgories());
		m.addAttribute("supplierList", this.getSuppliers());
		return "Product";
	}
	
	@RequestMapping(value="AddProduct",method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product")Product product,Model m)
	{
		productDAO.addProduct(product);
		
		List<Product> listProduct=productDAO.retrieveProduct();
		m.addAttribute("productList",listProduct);
		
		return "Product";
	}
	
	
	@RequestMapping(value="updateProduct/{productId}",method=RequestMethod.GET)
	public String updateProduct(@PathVariable("productId") int productId,Model m)
	{
		Product product=productDAO.getProduct(productId);
	
		m.addAttribute(product);
		
		List<Product> listProduct=productDAO.retrieveProduct();
		m.addAttribute("productList",listProduct);
		m.addAttribute("categoryList",this.getCatgories());
		m.addAttribute("supplierList", this.getSuppliers());
		return "UpdateProduct";
	}
	
	@RequestMapping(value="UpdateProduct",method=RequestMethod.POST)
	public String UpdateMyPorduct(@ModelAttribute("product")Product product,Model m)
	{
		productDAO.updateProduct(product);
				
		/*String path="G:\\Karthick Workspace\\MyFinalProjectFrontEnd\\src\\main\\webapp\\resources\\images\\";
		
		String totalFileWithPath=path+String.valueOf(product.getProductId())+".jpg";
		
		File productImage = new File(totalFileWithPath);
		
		
		if(!fileDetail.isEmpty())
		{
			try
			{
				byte fileBuffer[]= fileDetail.getBytes();
				FileOutputStream fos = new FileOutputStream(productImage);
				BufferedOutputStream bs = new BufferedOutputStream(fos);
				bs.write(fileBuffer);
				bs.close();
			}
			catch(Exception e)
			{
				m.addAttribute("error",e.getMessage());
			}
		}
		else
		{
			m.addAttribute("error","Problem in file Uploading");
		}*/
		
		Product product1= new Product();
		m.addAttribute(product1);
		List<Product> listProduct=productDAO.retrieveProduct();
		m.addAttribute("productList",listProduct);
		return "Product";
	}
/*	@RequestMapping(value="UpdateProduct",method=RequestMethod.POST)
	public String updateMyProduct(@ModelAttribute("product")Product product,Model m)
	{
		productDAO.updateProduct(product);
		m.addAttribute(product);
		Product product1 = new Product();
		m.addAttribute(product1);
		
		List<Product> listProduct=productDAO.retrieveProduct();
		m.addAttribute("productList",listProduct);
		return "Product";
		
	}*/
	
	@RequestMapping(value="deleteProduct/{productId}",method=RequestMethod.GET)
	public String deletePorduct(@PathVariable("productId")int productId,Model m)
	{
		Product product=productDAO.getProduct(productId);
		m.addAttribute(product);
		productDAO.deleteProduct(product);
						
		/*String path="G:\\Karthick Workspace\\MyFinalProjectFrontEnd\\src\\main\\webapp\\resources\\images\\";
		
		String totalFileWithPath=path+String.valueOf(product.getProductId())+".jpg";
		
		File productImage = new File(totalFileWithPath);
	
		productImage.delete();*/
				
		List<Product> listProduct=productDAO.retrieveProduct();
		m.addAttribute("productList",listProduct);
		return "Product";
	}
	
/*	@RequestMapping(value="deleteProduct/{productId}",method=RequestMethod.GET)
	public String deleteProduct(@PathVariable("productId")int productId,Model m)
	{
	
		Product product=productDAO.getProduct(productId);
		m.addAttribute(product);
		productDAO.deleteProduct(product);
	
		List<Product> listProduct=productDAO.retrieveProduct();
		m.addAttribute("productList",listProduct);
		return "Product";
	}*/
	
	public LinkedHashMap<String, String> getCatgories()
	{
		List<Category> listCategories = categoryDAO.retrieveCategory();
		LinkedHashMap<String, String> categoriesList = new LinkedHashMap<String,String>();
		
		for(Category category:listCategories)
		{
			 categoriesList.put(category.getCatName(),category.getCatName());
			 
		}
		return categoriesList;
	}
	
	public LinkedHashMap<Integer, Integer> getSuppliers()
	{
		List<Supplier> listSuppliers = supplierDAO.retrieveSupplier();
		LinkedHashMap<Integer, Integer> suppliersList = new LinkedHashMap<Integer,Integer>();
		
		for(Supplier supplier:listSuppliers)
		{
			suppliersList.put(supplier.getSupplierId(),supplier.getSupplierId());
			 
		}
		return suppliersList;
	}
	/*@RequestMapping(value="InsertProduct",method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product")Product product,Model m)
	{
		productDAO.addProduct(product);
		
		List<Product> listProduct=productDAO.retrieveProduct();
		m.addAttribute("productList",listProduct);
		
		return "Product";
	}
	*/
	@RequestMapping(value="InsertProduct",method=RequestMethod.POST)
	public String insertPorduct(@ModelAttribute("product")Product product,@RequestParam("pimage")MultipartFile fileDetail,Model m)
	{
		productDAO.addProduct(product);
				
		String path="G:\\Karthick Workspace\\MyFinalProjectFrontEnd\\src\\main\\webapp\\resources\\images\\";
		
		String totalFileWithPath=path+String.valueOf(product.getProductId())+".jpg";
		
		File productImage = new File(totalFileWithPath);
	
		
		
		if(!fileDetail.isEmpty())
		{
			try
			{
				byte fileBuffer[]= fileDetail.getBytes();
				FileOutputStream fos = new FileOutputStream(productImage);
				BufferedOutputStream bs = new BufferedOutputStream(fos);
				bs.write(fileBuffer);
				bs.close();
			}
			catch(Exception e)
			{
				m.addAttribute("error",e.getMessage());
			}
		}
		else
		{
			m.addAttribute("error","Problem in file Uploading");
		}
		
		Product product1= new Product();
		m.addAttribute(product1);
		List<Product> listProduct=productDAO.retrieveProduct();
		m.addAttribute("productList",listProduct);
		return "Product";
	}
	
	@RequestMapping(value="userHome")	
	public String showProducts(Model m)
	{
		List<Product> listProducts=productDAO.retrieveProduct();
		m.addAttribute("productList",listProducts);
		return "UserHome";
	}
	/*@RequestMapping(value="spices")	
	public String showSpices(Model m)
	{
		List<Product> listProducts=productDAO.retrieveProduct();
		m.addAttribute("productList",listProducts);
		return "Spices";
	}*/
	@RequestMapping(value="productDesc/{productId}")
	public String showProductDesc(@PathVariable("productId")int productId,Model m)
	{
		Product product = productDAO.getProduct(productId);
		m.addAttribute("product",product);
		return "ProductDesc";
	}
	
/*	@ModelAttribute
	public void addAttributes(Model m)
	{
		m.addAttribute("categoryList",category);
	}*/
}
