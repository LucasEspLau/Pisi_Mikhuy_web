package com.fia.sem7.controller;



import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fia.sem7.models.entity.Admin;
import com.fia.sem7.models.entity.Carrito;
import com.fia.sem7.models.entity.Cliente;
import com.fia.sem7.models.entity.ItemCarrito;
import com.fia.sem7.models.entity.Plato;
import com.fia.sem7.models.service.IAdminService;
import com.fia.sem7.models.service.ICarritoService;
import com.fia.sem7.models.service.IClienteService;
import com.fia.sem7.models.service.IItemCarritoService;
import com.fia.sem7.models.service.IPlatoService;

import jakarta.validation.Valid;



@Controller
@SessionAttributes("cliente")
public class PisiMikhuyController {
	
	
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IPlatoService platoService;
	@Autowired
	private ICarritoService carritoService;
	@Autowired
	private IItemCarritoService itemCarritoService;
	Cliente clienteIniciado;
	Admin adminIniciado;
	Integer contadorIniciado=1;
	Integer contaCarrito=1;
	Long cCar= Long.valueOf(contaCarrito);
	Double totalCarrito=0.0;
	Integer totCant=0;
	Double premioG=0.0;
	
	@RequestMapping(value={"/index","/"})
	public String index(Model model, Cliente cliente, Admin admin) {
		
		if(clienteIniciado!=null) {
			model.addAttribute("clienteI", clienteIniciado);
			
			model.addAttribute("nom",clienteIniciado.getNom());
			
			
		}else if(adminIniciado!=null){
			model.addAttribute("adminI", adminIniciado);
			model.addAttribute("nomA",adminIniciado.getNom());
		}
		model.addAttribute("clienteI", clienteIniciado);
		model.addAttribute("adminI", adminIniciado);
		model.addAttribute("contaI", contadorIniciado);

		return "inicio";
	}
	
	@RequestMapping(value="/preguntas")
	public String pregunta(Model model) {
		model.addAttribute("clienteI", clienteIniciado);
		model.addAttribute("adminI", adminIniciado);
		model.addAttribute("contaI", contadorIniciado);
			return "preguntas";

	}
	@RequestMapping(value="/premio")
	public String premio(Model model) {
		model.addAttribute("clienteI", clienteIniciado);
		model.addAttribute("adminI", adminIniciado);
		model.addAttribute("contaI", contadorIniciado);
		model.addAttribute("totalPlatos", totCant);
			return "premio";

	}
	@RequestMapping(value="/premio", method=RequestMethod.POST)
	public String recibirPremio(@RequestBody String premio, Model model) {
		String descuento=premio.substring(1,13);
		model.addAttribute("premio", descuento);
		if(descuento.equals("Descuento 2%")) {
			premioG=0.02;
		}else if(descuento.equals("Descuento 4%")) {
			premioG=0.04;
		}else if(descuento.equals("Descuento 6%")) {
			premioG=0.06;
		}else if(descuento.equals("Descuento 8%")) {
			premioG=0.08;
		}else{
			premioG=0.10;
		}
		contadorIniciado=0;
		return "redirect:/";

	}

	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion(Model model) {
		clienteIniciado = null;
		adminIniciado = null;
		return "redirect:/";
	}
	
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("adminI", adminIniciado);
		model.addAttribute("titulo", "Listado de Clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "lista";
	}
	@RequestMapping(value="/catalogo",method = RequestMethod.GET)
	public String listaPlatos(Model model) {
		model.addAttribute("clienteI", clienteIniciado);
		model.addAttribute("contaI", contadorIniciado);
		model.addAttribute("titulo", "Listado de Platos");
		model.addAttribute("platos", platoService.findAll());
		return "catalogo";
	}
	@RequestMapping(value="/catalogo/{idC}/{idP}")
	public String catalogoPlato(@PathVariable(value="idC") Long idC, @PathVariable(value="idP") Long idP, Map<String, Object> model) {
		model.put("clienteI", clienteIniciado);
		model.put("adminI", adminIniciado);
		model.put("contaI", contadorIniciado);
		Cliente cliente=null;
		Plato plato=null;

		if(idC>0) {
			cliente=clienteService.findOne(idC);
			plato=platoService.findOne(idP);
		}else {
			return "redirect:/";
		}
		
		Carrito carrito = new Carrito();
		carrito.setCliente(cliente);
		carrito.setId(cCar);
		carritoService.save(carrito);
		ItemCarrito itemC= new ItemCarrito();
		
		model.put("carrito", carrito);
		model.put("itemC",itemC);
		model.put("cliente", cliente);
		model.put("plato", plato);
		model.put("titulo", "Compra de Plato");
		return "compra";
	}
	@RequestMapping(value="/catalogo/{idC}/{idP}/{idCar}", method=RequestMethod.POST)
	public String compraPlato(@PathVariable(value="idC") Long idC, @PathVariable(value="idP") Long idP,  
			@PathVariable(value="idCar") Long idCar,@Valid ItemCarrito itemC, Map<String, Object> model,
			BindingResult result,SessionStatus status) {
		model.put("clienteI", clienteIniciado);
		model.put("adminI", adminIniciado);
		model.put("contaI", contadorIniciado);
		Cliente cliente=null;
		Plato plato=null;
		Carrito carrito=null;

		if(idC>0) {
			cliente=clienteService.findOne(idC);
			plato=platoService.findOne(idP);
			carrito=carritoService.findOne(idCar);
			
		}else {
			return "redirect:/";
		}
		itemC.setTotalPlato(itemC.getCantidad().doubleValue()*plato.getPrecio());
		totalCarrito=totalCarrito + itemC.getCantidad().doubleValue()*plato.getPrecio();
		totCant=totCant+itemC.getCantidad();
		
		itemC.setPlato(plato);
		itemC.setCarrito(carrito);
		carrito.addItemCarrito(itemC);
		itemCarritoService.save(itemC);
		
		carrito.setPago(totalCarrito);
		
		carrito.setCant(totCant);
		carrito.setDescuento(premioG);
		
		Double totalDescuento=(totalCarrito*premioG*100)/100;
		Double precioF=totalCarrito-totalDescuento;
		carrito.setTotDes(totalDescuento);
		carrito.setPrecioF(precioF);
		carritoService.save(carrito);
		status.setComplete();
		
		model.put("itemC", itemC);
		model.put("carrito",carrito);
		model.put("cliente", cliente);
		model.put("plato", plato);
		model.put("titulo", "Compra de Plato");
		return "compra";
	}
	@RequestMapping(value="/carrito/{idC}/{idCar}", method = RequestMethod.GET)
	public String verCarrito(@PathVariable(value="idC") Long idC,  
			@PathVariable(value="idCar") Long idCar,Map<String,Object> model,
						RedirectAttributes flash) {
		
		Cliente cliente= clienteService.findOne(idC);
		Carrito carrito=carritoService.findOne(idCar);
		if(cliente==null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/lista";
		}
		model.put("cliente", cliente);
		model.put("carrito", carrito);
		model.put("iC",carrito.getItems());
		model.put("items", itemCarritoService.findAll());
		model.put("titulo", "Detalle del Cliente: "+ cliente.getNom());
		return "carrito";
	}
	@RequestMapping(value="/carrito/{idC}/{idCar}", method = RequestMethod.POST)
	public String pagarCarrito(@PathVariable(value="idC") Long idC,  
			@PathVariable(value="idCar") Long idCar,Map<String,Object> model,
						RedirectAttributes flash) {
		
		Cliente cliente= clienteService.findOne(idC);
		Carrito carrito=carritoService.findOne(idCar);
		if(cliente==null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/lista";
		}
		contaCarrito=contaCarrito+1;
		totalCarrito=0.0;
		totCant=0;
		premioG=0.0;
		contadorIniciado=1;
		model.put("cliente", cliente);
		model.put("carrito", carrito);
		model.put("iC",carrito.getItems());
		model.put("items", itemCarritoService.findAll());
		model.put("titulo", "Detalle del Cliente: "+ cliente.getNom());
		return "redirect:/";
	}
	@RequestMapping(value="/registro")
	public String crear(Map<String,Object>model) {
		
		
		Cliente cliente=new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Bienvenido al Registro");
		return "registro";
	}
	
	@RequestMapping(value="/registro", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result,Model model,SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Fallo de Registro");
			return "registro";
		}
		clienteService.save(cliente);
		
		status.setComplete();
		return "redirect:/iniSesion";
	}
	@RequestMapping(value="/iniSesion")
	public String mostrarIniciarSesion(Map<String,Object>model) {

		model.put("cliente", new Cliente());
		model.put("titulo", "Bienvenido al Inicio Sesión");
		return "inicioSesion";
	}
	
	
	@RequestMapping(value="/iniSesion", method=RequestMethod.POST)
	public String comparaSesion(Map<String, Object> model, Cliente cliente) {

		model.put("cliente", cliente);
		if (clienteService.buscarCuenta(cliente)) {
			clienteIniciado = clienteService.buscarXIdentidad(cliente);
			
				return "redirect:/";
		}
			
		return "inicioSesion";
		
	}
	
	@RequestMapping(value="/iniSesionAdm")
	public String iniciarSesionAdm(Map<String,Object>model) {
		
		model.put("admin",new Admin());
		model.put("titulo", "Bienvenido al Inicio Sesión Admin");
		return "inicioSesionAdmin";
	}
	@RequestMapping(value="/iniSesionAdm", method=RequestMethod.POST)
	public String comparaSesionAdm(Map<String, Object> model, Admin admin) {
		
		
		model.put("admin", admin);
		if (adminService.buscarCuenta(admin)) {
			adminIniciado = adminService.buscarXIdentidad(admin);
				return "redirect:/";
		}
		
		return "inicioSesionAdmin";
		
	}
	
	@RequestMapping(value="/registro/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Cliente cliente=null;
		
		if(id>0) {
			cliente=clienteService.findOne(id);
		}else {
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "registro";
	}

	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			clienteService.delete(id);
		}
		
		return "redirect:/listar";
	}
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id,Map<String,Object> model,
						RedirectAttributes flash) {
		
		Cliente cliente= clienteService.findOne(id);
		
		if(cliente==null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/lista";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Detalle del Cliente: "+ cliente.getNom());
		return "ver";
	}

}
