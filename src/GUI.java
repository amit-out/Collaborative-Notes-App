import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class GUI {

    private NoteManager manager;
    private User currentUser;
    private FileManager fileManager;

    public GUI(
            NoteManager manager,
            User currentUser,
            FileManager fileManager
    ) {

        this.manager = manager;
        this.currentUser = currentUser;
        this.fileManager = fileManager;

        JFrame frame = new JFrame("Collaborative Notes App");

        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(
                new GridLayout(6, 1, 10, 10)
        );

        JButton createButton = new JButton("Create Note");
        JButton viewButton = new JButton("View Notes");
        JButton searchButton = new JButton("Search Notes");
        JButton deleteButton = new JButton("Delete Note");
        JButton shareButton = new JButton("Share Note");
        JButton historyButton = new JButton("Edit History");

        panel.add(createButton);
        panel.add(viewButton);
        panel.add(searchButton);
        panel.add(deleteButton);
        panel.add(shareButton);
        panel.add(historyButton);

        // VIEW NOTES
        viewButton.addActionListener(e -> {

        JFrame notesFrame =
        new JFrame("All Notes");
        
        notesFrame.setSize(700, 600);

            notesFrame.setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE
            );

        notesFrame.setLocationRelativeTo(frame);

        // Main panel

            JPanel mainPanel =
            new JPanel(
            new BorderLayout(10, 10)
            );

            mainPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20,20, 20)
             );
             // Heading
            javax.swing.JLabel heading =
            new javax.swing.JLabel(
                    "All Notes"
            );

    heading.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    28
                    )
                );

    mainPanel.add(
            heading,
            BorderLayout.NORTH
    );

    // Notes list

    JPanel notesPanel =
            new JPanel();

    notesPanel.setLayout(
            new BoxLayout(
                    notesPanel,
                    BoxLayout.Y_AXIS
            )
    );

    // Display every note

    for (Note note : manager.getNotes()) {

        JButton noteButton =
                new JButton(
                        note.getTitle()
                );

        noteButton.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        18
                        )   
                    );
        noteButton.setHorizontalAlignment(
                JButton.LEFT
        );
        noteButton.setAlignmentX(
                JButton.LEFT_ALIGNMENT
        );

        // Open note when clicked

        noteButton.addActionListener(
                event -> {
                            openNoteEditor(
                            notesFrame,
                            note
                    );

                }
        );
        notesPanel.add(
                noteButton
        );
        // Space between notes

        notesPanel.add(
                javax.swing.Box.createVerticalStrut(
                        10
                )
        );
    }
    // Scrollable notes list

    JScrollPane scrollPane =
            new JScrollPane(
                    notesPanel
            );
    mainPanel.add(
            scrollPane,
            BorderLayout.CENTER
    );
    // Back button

    JButton backButton =
            new JButton("Back");

    backButton.addActionListener(event -> {

        notesFrame.dispose();

    });

    mainPanel.add(
            backButton,
            BorderLayout.SOUTH
    );


    // Add main panel to frame

    notesFrame.add(
            mainPanel
    );


    notesFrame.setVisible(true);

});
        // CREATE NOTE

        createButton.addActionListener(e -> {

            JFrame editorFrame =
                    new JFrame("Create New Note");

            editorFrame.setSize(800, 600);

            editorFrame.setDefaultCloseOperation(
                    JFrame.DISPOSE_ON_CLOSE
            );

            editorFrame.setLocationRelativeTo(frame);


            JPanel mainPanel =
                    new JPanel(new BorderLayout(10, 10));

            mainPanel.setBorder(
                    BorderFactory.createEmptyBorder(
                            15, 15, 15, 15
                    )
            );

            // TITLE

            JTextField titleField =
                    new JTextField();

            titleField.setFont(
                    new Font("SansSerif",Font.BOLD,24)
            );

            titleField.setBorder(
                    BorderFactory.createTitledBorder( "Title")
            );

            // CATEGORY

        JTextField categoryField =
        new JTextField("Type category...");

            JPanel topPanel =
                    new JPanel();

            topPanel.setLayout(
                    new BoxLayout( topPanel,BoxLayout.Y_AXIS)
            );

           topPanel.add(titleField);
           topPanel.add(categoryField);

            // CONTENT

            JTextArea contentArea =
                    new JTextArea();

            contentArea.setFont(
                    new Font("SansSerif",Font.PLAIN,16)
            );

            contentArea.setLineWrap(true);
            contentArea.setWrapStyleWord(true);

            contentArea.setBorder(
                    BorderFactory.createTitledBorder("Write your note")
            );

            JScrollPane scrollPane =
                    new JScrollPane(contentArea);

            // BUTTONS

            JButton cancelButton =
                    new JButton("Cancel");
            JButton saveButton =
                    new JButton("Save Note");
            JPanel buttonPanel =
                    new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(saveButton);


            // SAVE NOTE

            saveButton.addActionListener(event -> {

                String title = titleField.getText().trim();

                String content = contentArea.getText().trim();

                String category = categoryField.getText().trim();


                if (title.isEmpty()) {

                    JOptionPane.showMessageDialog(editorFrame, "Please enter a title.");
                    return;
                }

                if (content.isEmpty()) {
                    JOptionPane.showMessageDialog(editorFrame, "Please write something in your note.");
                    return;
                }

                // Generate new ID
                int newId = 1;
                for (Note note : manager.getNotes()) {
                    if (note.getNoteId() >= newId) {
                        newId = note.getNoteId() + 1;
                    }
                }


                Note newNote =
                        new Note(newId,title,content,category,currentUser);
                manager.addNote(newNote);
                fileManager.saveNotes(manager);
                JOptionPane.showMessageDialog(editorFrame,"Note saved successfully!");
                editorFrame.dispose();
            });

            // CANCEL

            cancelButton.addActionListener(event -> {
            editorFrame.dispose();
            });


            // BUILD WINDOW
            mainPanel.add(topPanel,BorderLayout.NORTH);
            mainPanel.add(
                    scrollPane,
                    BorderLayout.CENTER
            );
            mainPanel.add(
                    buttonPanel,
                    BorderLayout.SOUTH
            );
            editorFrame.add(mainPanel);
            editorFrame.setVisible(true);
            titleField.requestFocusInWindow();
        });

        // SEARCH NOTES

        searchButton.addActionListener(e -> {

            String keyword =JOptionPane.showInputDialog(frame,"Search notes:");
            if (keyword == null ||
                    keyword.trim().isEmpty()) {
                return;
            }
            keyword =keyword.toLowerCase().trim();
            JPanel resultsPanel =new JPanel();

            resultsPanel.setLayout(
                    new BoxLayout(resultsPanel,BoxLayout.Y_AXIS)
            );
            boolean found = false;
            for (Note note : manager.getNotes()) {
                String title =  note.getTitle().toLowerCase();
                String content = note.getContent().toLowerCase();
                if (title.contains(keyword) || content.contains(keyword)) 
                {

                    found = true;
                    JButton noteButton =new JButton(note.getTitle());
                    noteButton.setAlignmentX(
                            JButton.LEFT_ALIGNMENT
                    );
                    noteButton.addActionListener(
                            event -> {
                                openNoteEditor(frame,note);
                            }
                    );
                        resultsPanel.add(noteButton);
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(frame,"No notes found containing \""+ keyword+ "\".");
                return;
            }
            JScrollPane scrollPane =
                    new JScrollPane(resultsPanel);


            scrollPane.setPreferredSize(
                    new java.awt.Dimension(
                            450,
                            350
                    )
            );


            JOptionPane.showMessageDialog(
                    frame,
                    scrollPane,
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });
        deleteButton.addActionListener(e -> {

    String input = JOptionPane.showInputDialog(
            frame,
            "Enter Note ID or Note Title:"
    );

    if (input == null || input.trim().isEmpty()) {
        return;
    }

    input = input.trim();

    Note noteToDelete = null;

    // Try Note ID first
    try {
        int noteId = Integer.parseInt(input);
        noteToDelete = manager.getNoteById(noteId);

    } catch (NumberFormatException ex) {

        // If not an ID, search by title
        for (Note note : manager.getNotes()) {

            if (note.getTitle().equalsIgnoreCase(input)) {
                noteToDelete = note;
                break;
            }
        }
    }

    // If no note was found
    if (noteToDelete == null) {
        JOptionPane.showMessageDialog(
                frame,
                "Note not found.",
                "Delete Note",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // Confirmation before deleting
    int choice = JOptionPane.showConfirmDialog(
            frame,
            "Are you sure you want to delete \"" +
            noteToDelete.getTitle() + "\"?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
    );

    if (choice == JOptionPane.YES_OPTION) {

        manager.deleteNote(noteToDelete.getNoteId());

        fileManager.saveNotes(manager);

        JOptionPane.showMessageDialog(
                frame,
                "Note deleted successfully!"
        );
    }
});

// SHARE NOTE

shareButton.addActionListener(e -> {

    String input = JOptionPane.showInputDialog(
            frame,
            "Enter Note ID or Note Title:"
    );

    if (input == null || input.trim().isEmpty()) {
        return;
    }

    input = input.trim();

    Note note = null;

    // Try searching by Note ID
    try {

        int noteId = Integer.parseInt(input);

        note = manager.getNoteById(noteId);

    } catch (NumberFormatException ex) {

        // If input is not a number,
        // search by title

        for (Note n : manager.getNotes()) {

            if (n.getTitle().equalsIgnoreCase(input)) {

                note = n;
                break;
            }
        }
    }

    // Note not found

    if (note == null) {

        JOptionPane.showMessageDialog(
                frame,
                "Note not found."
        );

        return;
    }


    // User to share with

    User user2 = new User(
            2,
            "Rahul",
            "rahul234@gmail.com"
    );


    // Check if already shared

    if (note.getSharedUsers().contains(user2)) {

        JOptionPane.showMessageDialog(
                frame,
                "Note is already shared with "
                        + user2.getName()
        );

    } else {

        note.shareNote(user2);

        JOptionPane.showMessageDialog(
                frame,
                "Note \"" +
                        note.getTitle() +
                        "\" shared with "
                        + user2.getName()
        );
    }

});
// EDIT HISTORY
historyButton.addActionListener(e -> {

    String input = JOptionPane.showInputDialog(
            frame,
            "Enter Note ID or Note Title:"
    );
    if (input == null || input.trim().isEmpty()) {
        return;
    }

    input = input.trim();

    Note note = null;

    // Try searching by Note ID

    try {

        int noteId = Integer.parseInt(input);

        note = manager.getNoteById(noteId);

    } catch (NumberFormatException ex) {

        // If input is not a number,
        // search by title

        for (Note n : manager.getNotes()) {

            if (n.getTitle().equalsIgnoreCase(input)) {

                note = n;
                break;
            }
        }
    }
    // Note not found

    if (note == null) {

        JOptionPane.showMessageDialog(
                frame,
                "Note not found."
        );

        return;
    }

    // Build edit history

    StringBuilder output =
            new StringBuilder();

    for (
            Edithistory history :
            note.getEditHistory()
    ) {
        output.append("Old Title: ")
                .append(history.getOldTitle())
                .append("\n");

        output.append("Old Content: ")
                .append(history.getOldContent())
                .append("\n");

        output.append("Old Category: ")
                .append(history.getOldCategory())
                .append("\n");

        output.append("Edited By: ")
                .append(history.getEditor().getName())
                .append("\n");

        output.append("Edit Time: ")
                .append(history.getEditTime())
                .append("\n");

        output.append(
                "-----------------------------\n"
        );
    }

    if (output.length() == 0) {

        output.append(
                "No edit history available."
        );
    }

    JTextArea textArea =
            new JTextArea(
                    output.toString()
            );
    textArea.setEditable(false);

    JScrollPane scrollPane =
            new JScrollPane(textArea);

    JOptionPane.showMessageDialog(
            frame,
            scrollPane,
            "Edit History - " + note.getTitle(),
            JOptionPane.INFORMATION_MESSAGE
    );

});

        // MAIN WINDOW
        frame.add(panel);
        frame.setVisible(true);
    }

    // OPEN NOTE EDITOR
    private void openNoteEditor(
            JFrame parent,
            Note note
    ) {

        JFrame editorFrame =
                new JFrame("Edit Note");

        editorFrame.setSize(800, 600);

        editorFrame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        editorFrame.setLocationRelativeTo(parent);

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15,15,15,15)
        );

        // TITLE
        JTextField titleField =
                new JTextField(
                        note.getTitle()
                );
        titleField.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );
        titleField.setBorder(
                BorderFactory.createTitledBorder(
                        "Title"
                )
        );

        // CATEGORY
        JTextField categoryField =
        new JTextField(note.getCategory());

        JPanel topPanel =
                new JPanel();

        topPanel.setLayout(
                new BoxLayout(
                        topPanel,
                        BoxLayout.Y_AXIS
                )
        );

        topPanel.add(titleField);
        topPanel.add(categoryField);

        // CONTENT
        JTextArea contentArea =
                new JTextArea(
                        note.getContent()
                );

        contentArea.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                )
        );

        contentArea.setLineWrap(true);

        contentArea.setWrapStyleWord(true);
        contentArea.setBorder(
                BorderFactory.createTitledBorder(
                        "Write your note"
                )
        );
        JScrollPane scrollPane =
                new JScrollPane(
                        contentArea
                );

        // BUTTONS

        JButton cancelButton = new JButton("Cancel");
        JButton saveButton =new JButton("Save Changes");
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        // SAVE CHANGES

        saveButton.addActionListener(event -> {

            String newTitle = titleField.getText().trim();
            String newContent = contentArea.getText().trim();
            String newCategory = categoryField.getText().trim();

            if (newTitle.isEmpty()) {
                JOptionPane.showMessageDialog(
                        editorFrame,
                        "Please enter a title."
                );
                return;
            }

            if (newContent.isEmpty()) {
                JOptionPane.showMessageDialog(
                        editorFrame,
                        "Please write something in your note."
                );
                return;
            }
            manager.editNote(
                    note.getNoteId(),
                    newTitle,
                    newContent,
                    newCategory,
                    currentUser
            );
            fileManager.saveNotes(manager);
            JOptionPane.showMessageDialog(
                    editorFrame,
                    "Note updated successfully!"
            );
            editorFrame.dispose();
        });
        // CANCEL
        cancelButton.addActionListener(event -> {
            editorFrame.dispose();
        });

        // BUILD EDITOR
        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );
        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );
        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );
        editorFrame.add(mainPanel);
        editorFrame.setVisible(true);
        titleField.requestFocusInWindow();
    }
}