
IF NOT EXISTS(
    SELECT *
    FROM sysobjects
    WHERE name='Book'
  )
CREATE  TABLE BOOK(
                    id  int IDENTITY PRIMARY KEY ,
                    title text NOT NULL,
                    isbn  text NOT NULL,
                    genre text NOT NULL
);

IF NOT EXISTS(
    SELECT *
    FROM sysobjects
    WHERE name='Author'
  )
CREATE  TABLE Author(
                    id  int PRIMARY KEY,
                    firstname text NOT NULL,
                    lastname  text NOT NULL,
                    birthday  text NOT NULL
);

IF NOT EXISTS(
    SELECT *
    FROM sysobjects
    WHERE name='book_author'
  )
CREATE  TABLE book_author(
                           id  int PRIMARY KEY,
                           book_id   int NOT NULL REFERENCES Book (id),
                           author_id int NOT NULL REFERENCES Author (id)
);


-- CREATE TABLE IF NOT EXISTS Book
-- (
--   id  SERIAL PRIMARY KEY ,
--   title text NOT NULL,
--   isbn  text NOT NULL,
--   genre text NOT NULL
-- );
--
-- CREATE TABLE IF NOT EXISTS Author
-- (
--   id  SERIAL PRIMARY KEY,
--   firstname text NOT NULL,
--   lastname  text NOT NULL,
--   birthday  text NOT NULL
-- );
--
--
-- CREATE TABLE IF NOT EXISTS book_author
-- (
--   id  SERIAL PRIMARY KEY,
--   book_id   int NOT NULL REFERENCES Book (id),
--   author_id int NOT NULL REFERENCES Author (id)
-- );
