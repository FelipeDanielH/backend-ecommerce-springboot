package com.ecomarket.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    private Categoria parent;
    private Categoria child;

    @BeforeEach
    void setUp() {
        parent = Categoria.builder()
                .nombre("Electrónica")
                .descripcion("Dispositivos electrónicos")
                .build();

        child = Categoria.builder()
                .nombre("Smartphones")
                .descripcion("Teléfonos inteligentes")
                .build();
    }

    @Test
    void testGettersSettersAndBuilder() {
        Categoria categoria = Categoria.builder()
                .id(1)
                .nombre("Informática")
                .descripcion("Equipos de computación")
                .categoriaPadre(parent)
                .build();

        assertAll("Validar campos básicos y relación padre",
                () -> assertEquals(1, categoria.getId()),
                () -> assertEquals("Informática", categoria.getNombre()),
                () -> assertEquals("Equipos de computación", categoria.getDescripcion()),
                () -> assertSame(parent, categoria.getCategoriaPadre())
        );
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        Categoria categoria = new Categoria();

        categoria.setId(2);
        categoria.setNombre("Hogar");
        categoria.setDescripcion("Artículos para el hogar");

        assertAll("Validar setters básicos",
                () -> assertEquals(2, categoria.getId()),
                () -> assertEquals("Hogar", categoria.getNombre()),
                () -> assertEquals("Artículos para el hogar", categoria.getDescripcion())
        );
    }

    @Test
    void testParentRelationship() {
        child.setCategoriaPadre(parent);

        assertAll("Validar relación padre-hijo",
                () -> assertSame(parent, child.getCategoriaPadre()),
                () -> assertNull(parent.getCategoriaPadre())
        );
    }
}