package com.example.demo.conrtoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Variables;

@Controller
@RequestMapping("/Calculator")
public class Calculator {
	
	@ModelAttribute("variables")
	public Variables variables() {
		return new Variables();
	}
	
	@GetMapping
	public String getVariables() {
		return "Calculator";	
	}
	
	@PostMapping
	public String finalOutput(@ModelAttribute("variables") Variables variables,Model model) {
		double monthlyRate = variables.getInterest()/12/100;
		int months = variables.getNoofyears()*12;
		double ammount = variables.getPrincipal()*(Math.pow(1+monthlyRate, months)-1)/(monthlyRate)*(1+monthlyRate);
		double interestEarned = ammount-(variables.getPrincipal()*12*variables.getNoofyears());
		model.addAttribute("ammount", Math.round(ammount));
		model.addAttribute("variables", variables);
		model.addAttribute("interestEarned", Math.round(interestEarned));
		model.addAttribute("principal", Math.round(variables.getPrincipal()*12*variables.getNoofyears()));
		return "Calculator";
	}
}
