package course_service.service;

import course_service.model.Course;
import course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    // Obtener todos
    public List<Course> getCourses() {
        return repository.findAll();
    }

    // Obtener por ID
    public Course getCourseById(Long id) {

        Optional<Course> course = repository.findById(id);

        return course.orElse(null);
    }

    // Agregar curso
    public Course addCourse(Course course) {

        Course savedCourse = repository.save(course);

        try {

            // Crear carpeta resumenes si no existe
            File carpeta = new File("resumenes");

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            // Nombre del archivo
            String nombreArchivo =
                    "resumenes/Curso_" + savedCourse.getId() + ".txt";

            FileWriter writer = new FileWriter(nombreArchivo);

            writer.write("====================================\n");
            writer.write("      RESUMEN DEL CURSO\n");
            writer.write("====================================\n\n");

            writer.write("ID: " + savedCourse.getId() + "\n");
            writer.write("Nombre: " + savedCourse.getName() + "\n");
            writer.write("Instructor: " + savedCourse.getInstructor() + "\n");
            writer.write("Duración: " + savedCourse.getDuration() + "\n");
            writer.write("Costo: $" + savedCourse.getCost() + "\n");

            writer.close();

            System.out.println("Archivo generado correctamente: "
                    + nombreArchivo);

        } catch (IOException e) {

            System.out.println("Error al generar archivo.");
            e.printStackTrace();
        }

        return savedCourse;
    }

    // Actualizar curso
    public Course updateCourse(Long id, Course updatedCourse) {

        Optional<Course> existingCourse = repository.findById(id);

        if (existingCourse.isPresent()) {

            Course course = existingCourse.get();

            course.setName(updatedCourse.getName());
            course.setInstructor(updatedCourse.getInstructor());
            course.setDuration(updatedCourse.getDuration());
            course.setCost(updatedCourse.getCost());

            return repository.save(course);
        }

        return null;
    }

    // Eliminar curso
    public void deleteCourse(Long id) {

        repository.deleteById(id);
    }
}