import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileManager {

    public void saveNotes(NoteManager manager) {

        try {

            FileWriter writer = new FileWriter("notes.txt");

            for (Note note : manager.getNotes()) {

                writer.write(
                        note.getNoteId() + "|" +
                        note.getTitle() + "|" +
                        note.getContent() + "|" +
                        note.getCategory() + "|" +
                        note.getOwner().getUserId() + "|" +
                        note.getOwner().getName() + "|" +
                        note.getOwner().getEmail() + "\n"
                );
            }

            writer.close();

            System.out.println("Notes saved successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Error while saving notes: " +
                    e.getMessage()
            );
        }
    }


    public void loadNotes(NoteManager manager) {

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader("notes.txt")
            );

            String line;

            while ((line = reader.readLine()) != null) {

                try {

                    String[] data = line.split("\\|");

                    if (data.length != 7) {

                        System.out.println(
                                "Invalid note data. Skipping line."
                        );

                        continue;
                    }


                    int noteId = Integer.parseInt(data[0]);

                    String title = data[1];
                    String content = data[2];
                    String category = data[3];

                    int userId = Integer.parseInt(data[4]);

                    String userName = data[5];
                    String userEmail = data[6];


                    User owner = new User(
                            userId,
                            userName,
                            userEmail
                    );


                    Note note = new Note(
                            noteId,
                            title,
                            content,
                            category,
                            owner
                    );


                    manager.addNote(note);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid number in note data. Skipping line."
                    );
                }
            }

            reader.close();

            System.out.println("Notes loaded successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Error while loading notes: " +
                    e.getMessage()
            );
        }
    }
}