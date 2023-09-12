package service;

import domain.entitys.Author;
import dao.AuthorDao;
import domain.entitys.Book;

import java.sql.SQLException;
import java.util.List;

import java.sql.Connection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AuthorService {
    private AuthorDao authorDao;
    Scanner input = new Scanner(System.in);

    public AuthorService(Connection con){
        this.authorDao = new AuthorDao(con);
    }

    public List<Author> readAllAuthor(){
        try {
            return authorDao.readAllAuthor();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void saveAuthors() throws SQLException {
        String first_name, last_name, awards;
            do {
                System.out.println("Insert First Name: ");
                first_name = input.nextLine();
            }while(!Pattern.matches("\\S+", first_name));
            do {
                System.out.println("Insert Last Name: ");
                last_name = input.nextLine();
            }while(!Pattern.matches("\\S+", last_name));
            do {
                System.out.println("Insert Awards: ");
                awards = input.nextLine();
            }while(!Pattern.matches("\\S+", awards));

            Author author = new Author(first_name, last_name, awards);
            authorDao.saveAuthor(author);
    }
}
