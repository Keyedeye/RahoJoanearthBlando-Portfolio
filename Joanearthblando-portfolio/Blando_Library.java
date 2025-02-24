import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isAvailable;

    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; 
    }

    
    public void displayDetails() {
        String availability = isAvailable ? "Available" : "Not Available";
        System.out.println("'" + title + "' by " + author + " - " + availability);
    }

    
    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You have borrowed '" + title + "'.");
        } else {
            System.out.println("Sorry, '" + title + "' is not available.");
        }
    }

    
    public void returnBook() {
        isAvailable = true;
        System.out.println("You have returned '" + title + "'.");
    }
}

class Library {
    ArrayList<Book> books;

    
    public Library() {
        books = new ArrayList<>();
    }

   
    public void addBook(Book book) {
        books.add(book);
    }

    
    public void displayAllBooks() {
        for (Book book : books) {
            book.displayDetails();
        }
    }

    
    public Book findBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        System.out.println("Book titled '" + title + "' not found.");
        return null;
    }
}

public class Blando_Library {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));

        int choice;
        do {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Display all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    library.displayAllBooks();
                    break;
                case 2:
                    System.out.print("Enter the title of the book to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    Book bookToBorrow = library.findBook(borrowTitle);
                    if (bookToBorrow != null) {
                        bookToBorrow.borrow();
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    Book bookToReturn = library.findBook(returnTitle);
                    if (bookToReturn != null) {
                        bookToReturn.returnBook();
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}