package service;

import domain.entitys.Author;
import dao.AuthorDao;

import java.sql.SQLException;
import java.util.List;

import java.sql.Connection;

public class AuthorService {
    private AuthorDao authorDao;

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
}
