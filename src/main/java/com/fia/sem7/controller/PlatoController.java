package com.fia.sem7.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fia.sem7.models.entity.Plato;
import com.fia.sem7.models.service.IPlatoService;
import com.fia.sem7.models.service.UploadFileService;

@Controller
@RequestMapping("/platos")
public class PlatoController {
	
	@Autowired
	private IPlatoService platoService;
	
	@Autowired
	private UploadFileService upload;
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("platos", platoService.findAll());
		return "/plato/show";
	}

	@GetMapping("/create")
	public String create(Model model) {

		Plato plato = new Plato();

		model.addAttribute("plato", plato);
		return "/plato/create";
	}

	@PostMapping("/save")
	public String save(Plato plato, @RequestParam("img") MultipartFile file, Model model)
			throws Exception {

		if (plato.getId() == null) {
			String nombreImagen = upload.saveImage(file);
			plato.setImagen(nombreImagen);
		}

		platoService.save(plato);
		return "redirect:/platos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		Plato plato = new Plato();

		Optional<Plato> optionalProducto = platoService.get(id);
		plato = optionalProducto.get();

		model.addAttribute("plato", plato);

		return "plato/edit";
	}

	@PostMapping("/update")
	public String update(Plato plato, @RequestParam("img") MultipartFile file) throws Exception {

		Plato p = new Plato();
		p = platoService.get(plato.getId()).get();

		if (file.isEmpty()) {

			plato.setImagen(p.getImagen());
		} else {

			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImagen(p.getImagen());
			}

			String nombreImagen = upload.saveImage(file);
			plato.setImagen(nombreImagen);
		}

		platoService.update(plato);
		return "redirect:/platos";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {

		Plato p = new Plato();
		p = platoService.get(id).get();

		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImagen(p.getImagen());
		}

		platoService.delete(id);
		return "redirect:/platos";
	}
}