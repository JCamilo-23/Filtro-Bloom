import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BloomFilterLibraryGUI extends JFrame {
    private final BloomFilter bloomFilter;
    private final JTextField inputField;
    private final JPanel bookshelfPanel;
    private final List<String> bookDatabase; // Base de datos de libros
    private final List<JButton> bookSlots; // Lista de botones para la estantería

    public BloomFilterLibraryGUI(int bitSetSize, int numHashFunctions) {
        bloomFilter = new BloomFilter(bitSetSize, numHashFunctions);
        bookDatabase = new ArrayList<>();
        bookSlots = new ArrayList<>();

        // Agregar algunos libros a la base de datos
        bookDatabase.add("1984");
        bookDatabase.add("Orgullo y prejuicio");
        bookDatabase.add("The Graveyard Book");
        bookDatabase.add("El Señor de los Anillos");
        bookDatabase.add("Harry Potter");
        bookDatabase.add("Los Juegos del Hambre");
        bookDatabase.add("Cien Años de Soledad");
        bookDatabase.add("Matar a un Ruiseñor");
        bookDatabase.add("La Odisea");
        bookDatabase.add("El Gran Gatsby");

        // Configuración de la ventana principal
        setTitle("Biblioteca Virtual (Filtro de Bloom)");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Campo de texto para ingresar elementos
        inputField = new JTextField();
        add(inputField, BorderLayout.NORTH);

        // Panel para mostrar la estantería
        bookshelfPanel = new JPanel();
        bookshelfPanel.setLayout(new GridLayout(5, 10, 5, 5)); // 5 filas y 10 columnas
        add(bookshelfPanel, BorderLayout.CENTER);

        // Agregar botones de interacción
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Agregar Libro");
        JButton checkButton = new JButton("Verificar Libro");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String element = inputField.getText().trim();
                if (!element.isEmpty()) {
                    // Agregar el libro al filtro de Bloom
                    bloomFilter.add(element);
                    addBookToBookshelf(element); // Mostrar el libro en la estantería
                    inputField.setText("");
                }
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String element = inputField.getText().trim();
                if (!element.isEmpty()) {
                    // Verificar si el libro está presente en el filtro de Bloom
                    boolean contains = bloomFilter.contains(element);
                    String message = contains ? "El libro '" + element + "' está en la estantería." :
                            "El libro '" + element + "' no está en la estantería.";
                    JOptionPane.showMessageDialog(null, message);
                    inputField.setText("");
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(checkButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Inicializa la estantería
        initializeBookshelf();

        // Agregar los libros predeterminados a la estantería
        SwingUtilities.invokeLater(() -> {
            for (String book : bookDatabase) {
                bloomFilter.add(book);
                addBookToBookshelf(book); // Refleja en la GUI
            }
        });
    }

    private void initializeBookshelf() {
        // Inicializa la estantería con casilleros vacíos (representados por botones)
        for (int i = 0; i < 50; i++) {
            JButton bookSlot = new JButton("Vacío");
            bookSlot.setBackground(Color.LIGHT_GRAY);
            bookSlot.setEnabled(false); // Los botones no son interactivos
            bookSlots.add(bookSlot);
            bookshelfPanel.add(bookSlot);
        }
    }

    private void addBookToBookshelf(String bookTitle) {
        // Añade un libro a la primera posición vacía en la estantería
        for (int i = 0; i < bookSlots.size(); i++) {
            JButton bookSlot = bookSlots.get(i);
            if (bookSlot.getText().equals("Vacío")) {
                bookSlot.setText(bookTitle); // Asignar el nombre del libro
                bookSlot.setBackground(Color.GREEN); // Cambiar el color para indicar que está presente
                break;
            }
        }
    }



}