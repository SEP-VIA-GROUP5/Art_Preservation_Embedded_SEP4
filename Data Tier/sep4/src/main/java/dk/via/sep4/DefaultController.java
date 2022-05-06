package dk.via.sep4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author $(Alina Chelmus)
 */
@RestController
public class DefaultController {

  @GetMapping("/")
  String message() {
    return "Hello sep4 students";
  }
}