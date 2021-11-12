package com.example.demo.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String greeting(@RequestParam(name = "primero", required = false, defaultValue = "0") Double primero,
            @RequestParam(name = "segundo", required = false, defaultValue = "0") Double segundo,
            @RequestParam(name = "operador", required = true) String operador, Model model) {
        Double resultado = 0.0;
        if (operador.equals("sum")) {
            resultado = primero + segundo;
            operador = "+";
        } else if (operador.equals("res")) {
            resultado = primero - segundo;
            operador = "-";
        } else if (operador.equals("mul")) {
            resultado = primero * segundo;
            operador = "*";
        } else if (operador.equals("div")) {
            resultado = primero / segundo;
            operador = "/";
        }
        model.addAttribute("primero", primero);
        model.addAttribute("segundo", segundo);
        model.addAttribute("operador", operador);
        model.addAttribute("resultado", resultado);
        return "calculator";
    }

    @GetMapping("/calculatorplus")
    public String greeting(@RequestParam(name = "cadena", required = true) String cadena, Model model) {
        Double resultado = 0.0;
        String operador = "";
        Double num1 = 0.0, num2 = 0.0;
        System.out.println(cadena);

        if (cadena.chars().anyMatch(c -> c == '+')) {
            operador = "+";
            System.out.println("Suma");
            String path[] = cadena.split("\\" + operador);
            if (path.length == 2) {
                num1 = Double.parseDouble(path[0]);
                num2 = Double.parseDouble(path[1]);
                resultado = num1 + num2;
            }
        } else if (cadena.contains("-")) {
            operador = "-";
            String path[] = cadena.split("\\" + operador);
            if (path.length == 2) {
                num1 = Double.parseDouble(path[0]);
                num2 = Double.parseDouble(path[1]);
                resultado = num1 - num2;
            }
        } else if (cadena.contains("x")) {
            operador = "x";
            String path[] = cadena.split(operador);
            if (path.length == 2) {
                num1 = Double.parseDouble(path[0]);
                num2 = Double.parseDouble(path[1]);
                resultado = num1 * num2;
            }
        } else if (cadena.contains("/")) {
            String path[] = cadena.split("\\" + operador);
            if (path.length == 2) {
                num1 = Double.parseDouble(path[0]);
                num2 = Double.parseDouble(path[1]);
                resultado = num1 / num2;
            }
        }
        model.addAttribute("primero", num1);
        model.addAttribute("segundo", num2);
        model.addAttribute("operador", operador);
        model.addAttribute("resultado", resultado);
        return "calculator";
    }
}
