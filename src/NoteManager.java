import java.util.ArrayList;
public class NoteManager{
    private ArrayList<Note> notes;

    public NoteManager(){
        notes = new ArrayList<>();
    }

    public void addNote(Note note){
        notes.add(note);
    }
    public void DisplayNotes(){

        for(Note note : notes){
        System.out.println("Title: " + note.getTitle());
        System.out.println("Category: " + note.getCategory());
        System.out.println("Owner: " + note.getOwner().getName());
        System.out.println();
        }
    }
    public int getTotalNotes(){
           return notes.size();
        }
        public void searchNotes(String keyword){
            boolean found = false;
            for(Note note : notes){
                if(note.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                    System.out.println("Title: " + note.getTitle());
                    System.out.println("Category: " + note.getCategory());
                    System.out.println("Owner: " + note.getOwner().getName());
                    System.out.println();
                    found = true;
                }
            }
            if(!found){
                System.out.println("Note do not exist.");
            }
        }
        public void editNote(int NoteId, 
        String newTitle, 
        String newContent, 
        String newCategory, 
        User editor
        ) {
            for(Note note : notes){
                if(note.getNoteId() == NoteId){
                    Edithistory history = new Edithistory(
                    note.getTitle(),
                    note.getContent(),
                    note.getCategory(),
                    editor
                    );
                note.addEditHistory(history);

                    note.setTitle(newTitle);
                    note.setContent(newContent);
                    note.setCategory(newCategory);

                    System.out.println("Note updated succesfully");
                    return;
                }
            }
                System.out.println("Note doesnt exist.");
        }
        public void deleteNote(int NoteId){
            for(int i=0; i< notes.size(); i++){
                if(notes.get(i).getNoteId() == NoteId){
                    notes.remove(i);
                    System.out.println("Note deleted sucessfully");
                    return;
                }
            }
            System.out.println("Note not found");
        }
        public void shareNote(int NoteId, User user){
                for(Note note : notes){
                    if(note.getNoteId() == NoteId){
                        note.shareNote(user);
                        return;
                    }
                }
                System.out.println("Note not found.");
        }
        public ArrayList<Note> getNotes() {
        return notes;
}
public Note getNoteById(int noteId) {

    for (Note note : notes) {

        if (note.getNoteId() == noteId) {
            return note;
        }
    }

    return null;
}
}