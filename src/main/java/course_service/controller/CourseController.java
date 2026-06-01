package course_service.controller;

import course_service.model.Course;
import course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // GET - Mostrar todos los cursos
    @GetMapping
    public List<Course> getCourses() {

        return courseService.getCourses();
    }

    // GET por ID
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {

        return courseService.getCourseById(id);
    }

    // POST - Agregar curso
    @PostMapping
    public Course addCourse(@RequestBody Course course) {

        return courseService.addCourse(course);
    }

    // PUT - Actualizar curso
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id,
                               @RequestBody Course updatedCourse) {

        return courseService.updateCourse(id, updatedCourse);
    }

    // DELETE - Eliminar curso
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return "Curso eliminado correctamente";
    }
}