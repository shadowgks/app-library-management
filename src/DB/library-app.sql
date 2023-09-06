drop database if exists library_app;
use library_app;

Create Table Author(
    id int PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(255),
    last_name varchar(255),
    awards text
);

Create Table Client(
    id int PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(255),
    last_name varchar(255),
    cin varchar(30)
);

Create Table Book(
    id int PRIMARY KEY AUTO_INCREMENT,
    title varchar(255),
    description varchar(255),
    date_publication date,
    quantity int,
    isbn varchar(255),
    authorID INT,
    FOREIGN KEY (authorID) REFERENCES author(id)
);

Create Table Reservation(
    id int PRIMARY KEY AUTO_INCREMENT,
    start_date DATETIME,
    end_date DATETIME,
    statut varchar(255),
    bookID INT,
    clientID INT,
    FOREIGN KEY (bookID) REFERENCES book(id),
    FOREIGN KEY (clientID) REFERENCES client(id)
);

insert into author (first_name, last_name, awards) values('Leo','Tolstoy','x'),('Lewis','Carroll','x1'),('Stephen','king','x2');
insert into client (first_name, last_name, cin) values('saad','momo','ha232456'),('ayoub','chrouai','hd23456'),('ikram','momo','hj435676');
insert into book (title, description, date_publication, quantity, isbn, authorID) values('Nineteen Eighty-Four','des1','2002-11-11',40,'978-0-9767736-6-5',1)
,('The Catcher in the Rye','des2','2005-02-11',15,'938-0-4536754-6-5',2)
,('Beloved','des3','1999-10-11',20,'928-0-3456789-6-5',3);
insert into reservation (start_date, end_date, statut, bookID, clientID) values('2023-09-01','2023-09-07','Borrowed',1,1)
,('2023-08-01','2023-08-30','Returned',3,1)
,('2023-08-01','2023-08-10','Lost',2,3);


DELIMITER //

CREATE TRIGGER check_count_book
BEFORE INSERT ON reservation
FOR EACH ROW
BEGIN
    DECLARE book_quantity INT;

    SELECT b.quantity INTO book_quantity
    FROM book b
    WHERE id = NEW.id;

    IF book_quantity = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'This book cannot be reserved because its quantity is 0';
    END IF;
END;
//

DELIMITER ;






