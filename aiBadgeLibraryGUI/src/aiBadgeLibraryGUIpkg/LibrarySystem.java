package aiBadgeLibraryGUIpkg;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class LibrarySystem extends JFrame
{
    private final Library library;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JTextField titleField, authorField, yearField, searchField;

    public LibrarySystem()
    {
        library = new Library();

        // --- Frame setup ---
        setTitle("Library Management System");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Top panel (Add/Update) ---
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Book Information"));

        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearField);

        JButton addBtn = new JButton("Add Book");
        JButton updateBtn = new JButton("Update Book");
        inputPanel.add(addBtn);
        inputPanel.add(updateBtn);

        // --- Table setup ---
        tableModel = new DefaultTableModel(new Object[]{"Title", "Author", "Year", "Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // --- Bottom panel (Actions/Search) ---
        JPanel bottomPanel = new JPanel();
        JButton removeBtn = new JButton("Remove Book");
        JButton checkBtn = new JButton("Check In/Out");
        searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search");
        JButton showAllBtn = new JButton("Show All");

        bottomPanel.add(removeBtn);
        bottomPanel.add(checkBtn);
        bottomPanel.add(new JLabel("Search:"));
        bottomPanel.add(searchField);
        bottomPanel.add(searchBtn);
        bottomPanel.add(showAllBtn);

        // --- Layout ---
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- Button Actions ---
        addBtn.addActionListener(e -> addBook());
        updateBtn.addActionListener(e -> updateBook());
        removeBtn.addActionListener(e -> removeBook());
        checkBtn.addActionListener(e -> toggleCheckOut());
        searchBtn.addActionListener(e -> searchBooks());
        showAllBtn.addActionListener(e -> refreshTable(library.getBooks()));

        setVisible(true);
    }
    
    // -------------------- Helper Methods --------------------
    private void addBook()
    {
        try
        {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            int year = Integer.parseInt(yearField.getText().trim());

            if (title.isEmpty() || author.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            library.addBook(new Book(title, author, year));
            refreshTable(library.getBooks());
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid year entered.");
        }
    }
    private void updateBook()
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a book to update.");
            return;
        }
        try {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            int year = Integer.parseInt(yearField.getText().trim());
            Book b = library.getBooks().get(selectedRow);
            b.setTitle(title);
            b.setAuthor(author);
            b.setYear(year);
            refreshTable(library.getBooks());
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid year entered.");
        }
    }
    private void removeBook()
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            library.removeBook(selectedRow);
            refreshTable(library.getBooks());
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to remove.");
        }
    }
	private void toggleCheckOut()
	{
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Book b = library.getBooks().get(selectedRow);
            b.setCheckedOut(!b.isCheckedOut());
            refreshTable(library.getBooks());
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to check in/out.");
        }
    }
    private void searchBooks()
    {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a search keyword.");
            return;
        }
        List<Book> results = library.search(keyword);
        refreshTable(results);
    }

    private void refreshTable(List<Book> books)
    {
        tableModel.setRowCount(0);
        for (Book b : books)
        {
            tableModel.addRow(new Object[]{
                    b.getTitle(), b.getAuthor(), b.getYear(),
                    b.isCheckedOut() ? "Checked Out" : "Available"
            });
        }
    }

    private void clearFields()
    {
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
    }

}
