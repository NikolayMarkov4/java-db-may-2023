package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.PartsService;
import softuni.exam.service.TasksService;
import softuni.exam.service.MechanicsService;
import softuni.exam.service.CarsService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final PartsService PartsService;
    private final TasksService TasksService;
    private final MechanicsService mechanicService;
    private final CarsService carService;

    @Autowired
    public ImportController(PartsService PartsService, TasksService TasksService, MechanicsService mechanicService, CarsService carService) {
        this.PartsService = PartsService;
        this.TasksService = TasksService;
        this.mechanicService = mechanicService;
        this.carService = carService;
    }


    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.PartsService.areImported(),
                this.mechanicService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }


    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
                this.carService.areImported(),
                this.TasksService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }


    @GetMapping("/cars")
    public ModelAndView importCars() throws IOException {
        String carsXmlFileContent = this.carService.readCarsFromFile();
        return super.view("xml/import-cars", "cars", carsXmlFileContent);
    }

    @PostMapping("/cars")
    public ModelAndView importCarsConfirm() throws JAXBException, IOException {
        System.out.println(this.carService.importCars());

        return super.redirect("/import/xml");
    }

    @GetMapping("/tasks")
    public ModelAndView importTasks() throws IOException {
        String tasksXmlFileContent = this.TasksService.readTasksFileContent();

        return super.view("xml/import-tasks", "tasks", tasksXmlFileContent);
    }

    @PostMapping("/tasks")
    public ModelAndView importTasksConfirm() throws JAXBException, FileNotFoundException, IOException {
        System.out.println(this.TasksService.importTasks());

        return super.redirect("/import/xml");
    }

    @GetMapping("/parts")
    public ModelAndView importParts() throws IOException {
        String fileContent = this.PartsService.readPartsFileContent();

        return super.view("json/import-parts", "parts", fileContent);
    }

    @PostMapping("/parts")
    public ModelAndView importPartsConfirm() throws IOException {
        System.out.println(this.PartsService.importParts());
        return super.redirect("/import/json");
    }

    @GetMapping("/mechanics")
    public ModelAndView importMechanics() throws IOException {
        String fileContent = this.mechanicService.readMechanicsFromFile();

        return super.view("json/import-mechanics", "mechanics", fileContent);
    }

    @PostMapping("/mechanics")
    public ModelAndView importMechanicsConfirm() throws IOException, JAXBException {
        System.out.println(this.mechanicService.importMechanics());
        return super.redirect("/import/json");
    }
}
