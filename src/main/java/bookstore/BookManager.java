package bookstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javassist.bytecode.BootstrapMethodsAttribute;

public class BookManager {
	public SessionFactory sessionFactory;
	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// TODO: handle exception
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
		
	}
	protected void exit() {
		sessionFactory.close();
		
	}
	protected void create() {
        // code to save a book
		Book book = new Book();
	    book.setTitle("Hibernate");
	    book.setAuthor("Nguyen Anh Tuan");
	    book.setPrice(96.69f);
	 
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	 
	    session.save(book);
	 
	    session.getTransaction().commit();
	    session.close();
    }
 
    protected void read() {
        // code to get a book
    	Session session = sessionFactory.openSession();
    	 
        long bookId = 20;
        Book book = session.get(Book.class, bookId);
     
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Price: " + book.getPrice());
     
        session.close();
    }
 
    protected void update() {
        // code to modify a book
    	Book book = new Book();
        book.setId(20);
        book.setTitle("Ultimate Java Programming");
        book.setAuthor("Nam Ha Minh");
        book.setPrice(19.99f);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.update(book);
     
        session.getTransaction().commit();
        session.close();
    }
 
    protected void delete() {
        // code to remove a book
    	Book book = new Book();
        book.setId(20);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.delete(book);
     
        session.getTransaction().commit();
        session.close();
    }
	public static void main(String[] args) {
		BookManager BM = new BookManager();
		BM.setup();
		BM.create();
		BM.read();
		BM.exit();
		
		
		
	}
}
