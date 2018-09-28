/**
 * 
 */
package com.mak.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author amjad.k.mohammad
 *
 */
@Controller
public class LoginController {

@RequestMapping(value="/home")	
public String homePage() {
	return "home";
}
}
