package ma.tc.projects.controller.crud;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.Facture;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/facture")
public class FactureController extends CrudController<Facture, Long>{

}
