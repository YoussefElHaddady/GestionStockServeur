package ma.tc.projects.controller.crud;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.Caisse;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/caisse")
public class CaisseController extends CrudController<Caisse, Long>{

}
