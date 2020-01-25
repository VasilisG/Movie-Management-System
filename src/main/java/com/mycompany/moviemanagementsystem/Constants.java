/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviemanagementsystem;

import java.awt.Color;

/**
 *
 * @author Vasilis
 */
public final class Constants {
    
    public static final String TITLE = "Movie management system";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;
    
    public static final String INSERT_MOVIE_FRAME_TITLE = "Insert movie";
    public static final String MOVIE_DETAILS_FRAME_TITLE = "Movie details";
    public static final String EDIT_MOVIE_FRAME_TITLE = "Edit movie";
    public static final String SEARCH_MOVIE_FRAME_TITLE = "Search movie";
    public static final String MOVIE_CODE = "Code:";
    public static final String MOVIE_TITLE = "Title:";
    public static final String MOVIE_YEAR = "Year:";
    public static final String MOVIE_PLOT = "Plot:";
    public static final String MOVIE_ACTORS = "Actors:";
    public static final String MOVIE_DIRECTOR = "Director:";
    public static final String MOVIE_PLAYTIME = "Playtime:";
    public static final String MOVIE_PRICE = "Price:";
    public static final String MOVIE_QUANTITY = "Quantity:";
    
    public static final String INSERT_CUSTOMER_FRAME_TITLE = "Insert customer";
    public static final String CUSTOMER_DETAILS_FRAME_TITLE = "Customer details";
    public static final String EDIT_CUSTOMER_FRAME_TITLE = "Edit customer";
    public static final String SEARCH_CUSTOMER_FRAME_TITLE = "Search customer";
    
    public static final String INSERT_RESERVATION_FRAME_TITLE = "Insert reservation";
    public static final String EDIT_RESERVATION_FRAME_TITLE = "Edit reservation";
    
    public static final String MOVIES_TAB_TITLE = "Movies";
    public static final String CUSTOMERS_TAB_TITLE = "Customers";
    public static final String RESERVATIONS_TAB_TITLE = "Reservations";
    public static final String FINANCIAL_RECORDS_TAB_TITLE = "Transactions";
    public static final String HISTORY_TAB_TITLE = "History";
    
    public static final String INSERT = "Insert";
    public static final String DELETE = "Delete";
    public static final String DELETE_ALL = "Delete all";
    public static final String CANCEL_ALL = "Cancel all";
    public static final String COMPLETE = "Complete";
    public static final String COMPLETE_ALL = "Complete all";
    public static final String EDIT = "Edit";
    public static final String SEARCH = "Search";
    public static final String DETAILS = "Details";
    public static final String CANCEL = "Cancel";
    public static final String CLOSE = "Close";
    public static final String DROP_FILTERS = "Drop filters";
    
    public static final String[] MOVIE_TABLE_COLUMNS = {"SELECTED", "CODE", "TITLE", "PRICE", "QUANTITY"}; 
    public static final String[] CUSTOMER_TABLE_COLUMNS = {"SELECTED", "CODE", "FIRST NAME", "LAST NAME", "ADDRESS", "PHONE NUMBER", "MEMBER"};
    public static final String[] RESERVATION_TABLE_COLUMNS = {"SELECTED", "CUSTOMER CODE", "MOVIE CODE", "FROM", "TO", "STATUS"};
    public static final String[] TRANSACTION_TABLE_COLUMNS = {"SELECTED", "TRANSACTION DATE", "CUSTOMER CODE", "MOVIE CODE"};
    
    public static final String SELECT_TYPE = "- Select movie type -";
    public static final String ACTION = "Action";
    public static final String ADVENTURE = "Adventure";
    public static final String COMEDY = "Comedy";
    public static final String CRIME = "Crime";
    public static final String DRAMA = "Drama";
    public static final String FANTASY = "Fantasy";
    public static final String HISTORICAL = "Historical";
    public static final String HORROR = "Horror";
    public static final String MAGICAL = "Magical";
    public static final String MYSTERY = "Mystery";
    public static final String PARANOID = "Paranoid";
    public static final String PHILOSOPHICAL = "Philosophical";
    public static final String POLITICAL = "Political";
    public static final String ROMANCE = "Romance";
    public static final String SAGA = "Saga";
    public static final String SATIRE = "Satire";
    public static final String SCIENCE = "Science";
    public static final String SOCIAL = "Social";
    public static final String SPECULATIVE = "Speculative";
    public static final String THRILLER = "Thriller";
    public static final String URBAN = "Urban";
    public static final String WESTERN = "Western";
    
    public static final String INFO = "Info";
    public static final String ERROR = "Error";
    public static final String WARNING = "Warning";
    public static final String EMPTY_FIELD = "Empty field.";
    public static final String INVALID_DATA = "Invalid movie data.";
    public static final String INVALID_CODE = "Invalid code.";
    public static final String INVALID_TITLE = "Invalid title.";
    public static final String INVALID_YEAR = "Invalid year.";
    public static final String INVALID_PLOT = "Invalid plot";
    public static final String INVALID_PLAYTIME = "Invalid playtime.";
    public static final String INVALID_PRICE = "Invalid price";
    public static final String INVALID_QUANTITY = "Invalid quantity.";
    public static final String INVALID_NAME = "Invalid name.";
    public static final String DUPLICATE_MOVIE_CODE = "Duplicate movie code.";
    public static final String MOVIE_ADDED = "Movie added.";
    public static final String MOVIE_TABLE_UPDATED = "Movie table updated.";
    public static final String INVALID_MOVIE = "Could not update movie table. Invalid data.";
    public static final String DELETED = "Deleted ";
    public static final String RECORDS = " record(s).";
    public static final String NO_RECORDS_TO_DELETE = "There are no records to delete.";
    public static final String NO_RECORD_SELECTED = "No records selected.";
    public static final String RECORD_DELETED = "Record deleted.";
    public static final String CONFIRM_DELETION = "Are you sure you want to delete this/these records?";
    public static final String CONFIRM_DELETION_ALL = "Are you sure you want to delete all records?";
    public static final String FILTERED_RESULTS = "Results filtered.";
    public static final String NO_RECORDS_TO_FILTER = "No records to filter.";
    
    public static final String INVALID_FIRST_NAME = "Invalid first name.";
    public static final String INVALID_LAST_NAME = "Invalid last name.";
    public static final String INVALID_ADDRESS = "Invalid address.";
    public static final String INVALID_EMAIL_ADDRESS = "Invalid email address.";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number.";
    public static final String INVALID_CUSTOMER = "Could not add customer, please check if the fields are filled correctly.";
    public static final String CUSTOMER_ADDED = "Customer added.";
    public static final String CUSTOMER_TABLE_UPDATED = "Customer table updated.";
    
    public static final String CANNOT_INSERT_RESERVATION = "Cannot insert reservation. Both movie and customer tables must not be empty.";
    public static final String CANNOT_EDIT_RESERVATION = "Reservations that are either canceled or completed cannot be edited.";
    public static final String INVALID_MOVIE_CODE = "Invalid movie code.";
    public static final String INVALID_CUSTOMER_CODE = "Invalid customer code.";
    public static final String INVALID_DAYS_TO_RENT = "Invalid days to rent.";
    public static final String INVALID_DATE = "Invalid date.";
    
    public static final String RESERVATION_ADDED = "Reservation added.";
    public static final String NO_RESERVATIONS = "There are no reservations yet.";
    public static final String MOVIE_DOES_NOT_EXIST = "Movie does not exist.";
    
    public static final String CANCELED = "Canceled";
    public static final String ONGOING = "Active (Ongoing)";
    public static final String DUE = "Active (Due)";
    public static final String OVERDUE = "Active (Overdue)";
    public static final String COMPLETED = "Completed";
    
    public static final String CANCEL_ALL_RESERVATIONS = "Are you sure you want to cancel all reservations?";
    public static final String CANCEL_RESERVATIONS = "Are you sure you want to cancel these reservations?";
    public static final String ALL_RESERVATIONS_CANCELED = "All reservations have been canceled.";
    public static final String COMPLETE_ALL_RESERVATIONS = "Are you sure you want to complete all reservations?";
    public static final String COMPLETE_RESERVATIONS = "Are you sure you want to complete these reservations?";
    public static final String RESERVATIONS_COMPLETED = "Reservation(s) completed.";
    public static final String RESERVATIONS_NOT_COMPLETED = "Reservation(s) could not be completed.";
    public static final String ALL_RESERVATIONS_COMPLETED = "All reservations completed.";
    public static final String RESERVATION_IS_CANCELED = "Reservation is canceled and cannot be modified.";
    public static final String RESERVATION_IS_NOT_CANCELED = "Reservation could not be canceled.";
    public static final String RESERVATIONS_COULD_NOT_BE_CANCELLED = "Some reservations could not be canceled. Number: ";
    public static final String RESERVATIONS_CANCELED = "Reservations canceled.";
    
    public static final String NO_TRANSACTIONS = "There are no transactions yet.";
    
    public static final int STATUS_ONGOING = 0;
    public static final int STATUS_DUE = 1;
    public static final int STATUS_OVERDUE = 2;
    public static final int STATUS_CANCELED = 3;
    public static final int STATUS_COMPLETED = 4;
    
    public static final String EQUAL = "=";
    public static final String LESS = "<";
    public static final String MORE = ">";
    public static final String EMPTY = "";
    
    public static final int COLUMN_WIDTH = 150;
    public static final int BUTTON_WIDTH = 96;
    public static final int BUTTON_HEIGHT = 32;
    
    public static final int DUMMY_TABLE_ROWS = 10;
    public static final int ROW_HEIGHT = 24;
    
    public static final int RIGID_AREA_WIDTH = 24;
    public static final int RIGID_AREA_HEIGHT = 0;
    
    public static final int CODE_WIDTH = 50;
    public static final int TITLE_WIDTH = 50;
    public static final int NUMBER_WIDTH = 10;
    public static final int DIRECTOR_WIDTH = 50;
    public static final int TEXT_AREA_ROWS = 10;
    public static final int TEXT_AREA_COLUMNS = 70;
    
    public static final int PRECISE = 1;
    public static final int LIKE = 2;
    
    public static final int EQ = 1;
    public static final int LT = 2;
    public static final int MT = 3;
    public static final int LEQ = 4;
    public static final int MEQ = 5;
    public static final int RANGE = 6;
    
    public static final String CODE_PREFIX = "MOV-";
    public static final int SUFFIX_LENGTH = 10;
    
    public static final int MIN_LENGTH = 0;
    public static final int MAX_LENGTH = 64;
    
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 96;
    
    public static final int MAX_PLOT_LENGTH = 256;
    public static final int YEAR_THRESHOLD = 1900;
    
    public static final Color COLOR_GREEN = new Color(0,153,76);
    public static final Color COLOR_YELLOW = new Color(249,197,94);
    public static final Color COLOR_RED = new Color(205,0,0);
    
    public static final int PORT = 3306;
    public static final String DATABASE_NAME = "mmsDB";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    public static final String DATABASE_URL = CONNECTION_URL + DATABASE_NAME;
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    
    public static final String MOVIE_TABLE_NAME = "movies";
    public static final String CUSTOMER_TABLE_NAME = "customers";
    public static final String RESERVATION_TABLE_NAME = "reservations";
    public static final String TRANSACTION_TABLE_NAME = "transactions";
    
    public static final String SQL_DRIVER_ERROR = "Could not initialize SQL driver.";
    public static final String SQL_CONNECTION_ERROR = "Could not create SQL connection.";
    public static final String SQL_STATEMENT_ERROR = "Could not create SQL statement.";
    public static final String SQL_DATABASE_ERROR = "Could not create database.";
    public static final String SQL_MOVIE_TABLE_ERROR = "Could not create 'movies' table";
    public static final String SQL_CUSTOMER_TABLE_ERROR = "Could not create 'customers' table";
    public static final String SQL_RESERVATION_TABLE_ERROR = "Could not create 'reservation' table";
    public static final String SQL_TRANSACTION_TABLE_ERROR = "Could not create 'transaction' table";
    
    public static final String SQL_MOVIE_QUERY = "SELECT * FROM " + MOVIE_TABLE_NAME;
    public static final String SQL_CUSTOMER_QUERY = "SELECT * FROM " + CUSTOMER_TABLE_NAME;
    public static final String SQL_RESERVATION_QUERY = "SELECT * FROM " + RESERVATION_TABLE_NAME;
    public static final String SQL_TRANSACTION_QUERY = "SELECT * FROM " + TRANSACTION_TABLE_NAME;
}
