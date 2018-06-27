package br.com.correios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.correios.dto.LogradouroDTO;
import br.com.correios.repository.LogradouroRepository;


@Controller
@RequestMapping("/buscaEndereco")
public class BuscaEnderecoController {
	
	@Autowired
	private LogradouroRepository logradouroRepository;
	
	@GetMapping
	public String buscaEndereco() {
		return "BuscaEndereco";
	}
	
	@GetMapping(value="/resultadoBuscaEndereco")
	public ModelAndView resultadoBuscaEndereco(@RequestParam("parametro1") String parametro1, RedirectAttributes attributes) {
		List<LogradouroDTO> logradouros = logradouroRepository.buscaPorCep(parametro1);
		
	
		int tamanho = parametro1.length();
		
		if(tamanho < 5){
			ModelAndView mv = new ModelAndView("redirect:/buscaEndereco");
			attributes.addFlashAttribute("mensagem", "Informe no mínimo os 5(cinco) primeiros dígitos do CEP. Ex. 70001");
			return mv;
		}
				
				
		ModelAndView mv = new ModelAndView("ResultadoBuscaEndereco");
		mv.addObject("logradouros", logradouros);
				
		//mensagem para quando o usuario digitar o CEP
		if(logradouros.isEmpty()) {
			mv.addObject("mensagem", "A BUSCA NÃO RETORNOU DADOS!");
		}else {
			mv.addObject("mensagem", "DADOS ENCONTRADOS COM SUCESSO!");
		}
				
		return mv;

	}

}