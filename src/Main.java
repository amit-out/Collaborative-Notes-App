import java.io.File;

public class Main {

    public static void main(String[] args) {

        User user1 = new User(
                1,
                "Rohan",
                "rohan123@gmail.com"
        );

        NoteManager manager = new NoteManager();

        FileManager fileManager = new FileManager();


        // Load existing notes

        File file = new File("notes.txt");

        if (file.exists()) {

            fileManager.loadNotes(manager);

        } else {

            Note note1 = new Note(
                    101,
                    "OOPs in Java",
                    "Classes, Objects, Inheritance, Polymorphism",
                    "College",
                    user1
            );

            Note note2 = new Note(
                    102,
                    "RDBMS",
                    "Normalization",
                    "College",
                    user1
            );

            Note note3 = new Note(
                    103,
                    "Project Ideas",
                    "Java based projects",
                    "Projects",
                    user1
            );


            manager.addNote(note1);
            manager.addNote(note2);
            manager.addNote(note3);

            fileManager.saveNotes(manager);
        }

        // Start GUI

        new GUI(
                manager,
                user1,
                fileManager
        );
    }
}