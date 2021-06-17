package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Util {
    private static Util instance;

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";

    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static  ArrayList<Book> alreadyReadBooks;

    private static int id = 0;

    public Util(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        if (getAllBooks() == null) {
//            allBooks = new ArrayList<>();
            initAllBooks();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getCurrentlyReadingBooks() == null) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (getWantToReadBooks() == null) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (getAlreadyReadBooks() == null) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    public static Util getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            instance = new Util(context);
            return instance;
        }
    }


    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return books;
    }

//    Adding Books into categories

    public boolean addCurrentlyReadingBooks (Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson =  new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;

//        boolean isAdded =  currentlyReadingBooks.add(book); // Not used anymore
//        return isAdded;
//        return currentlyReadingBooks.add(book); // This line does the same Job
    }

    public boolean addWantToReadBooks (Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson =  new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addAlreadyReadBooks (Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson =  new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

//    Removing Books from Categories

    public boolean removeCurrentlyReadingBooks(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeWantToReadBooks (Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeAlreadyReadBooks(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void initAllBooks() {
//        TODO: initialize all books Array List

        ArrayList<Book> books = new ArrayList<>();

        String name = "";
        String author = "";
        int pages = 0;
        String imgUrl = "";
        String description = "";

//        List from https://www.goodreads.com/list/show/137197.100_Books_to_Read_Before_You_Die_The_Ultimate_List

        id++;
        name = "1984";
        author = "George Orwell";
        pages = 298;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1532714506l/40961427._SX318_.jpg";
        description = "Among the seminal texts of the 20th century, Nineteen Eighty-Four is a rare work that grows more haunting as its futuristic purgatory becomes more real. Published in 1949, the book offers political satirist George Orwell's nightmarish vision of a totalitarian, bureaucratic world and one poor stiff's attempt to find individuality. The brilliance of the novel is Orwell's prescience of modern life—the ubiquity of television, the distortion of the language—and his ability to construct such a thorough version of hell. Required reading for students since it was published, it ranks among the most terrifying novels ever written. ";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "The Great Gatsby ";
        author = " F. Scott Fitzgerald,\n" +
                "Francis Scott Fitzgerald\n";
        pages = 200;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1490528560l/4671._SY475_.jpg";
        description = "The Great Gatsby, F. Scott Fitzgerald's third book, stands as the supreme achievement of his career. This exemplary novel of the Jazz Age has been acclaimed by generations of readers. The story is of the fabulously wealthy Jay Gatsby and his new love for the beautiful Daisy Buchanan, of lavish parties on Long Island at a time when The New York Times noted \"gin was the national drink and sex the national obsession,\" it is an exquisitely crafted tale of America in the 1920s.\n" +
                "\n" +
                "The Great Gatsby is one of the great classics of twentieth-century literature.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "To Kill a Mockingbird";
        author = "Harper Lee";
        pages = 324;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1553383690l/2657.jpg";
        description = "The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it. \"To Kill A Mockingbird\" became both an instant bestseller and a critical success when it was first published in 1960. It went on to win the Pulitzer Prize in 1961 and was later made into an Academy Award-winning film, also a classic.\n" +
                "\n" +
                "Compassionate, dramatic, and deeply moving, \"To Kill A Mockingbird\" takes readers to the roots of human behavior - to innocence and experience, kindness and cruelty, love and hatred, humor and pathos. Now with over 18 million copies in print and translated into forty languages, this regional story by a young Alabama woman claims universal appeal. Harper Lee always considered her book to be a simple love story. Today it is regarded as a masterpiece of American literature.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Animal Farm";
        author = " George Orwell,\n" +
                "Russell Baker";
        pages = 141;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1325861570l/170448.jpg";
        description = "A farm is taken over by its overworked, mistreated animals. With flaming idealism and stirring slogans, they set out to create a paradise of progress, justice, and equality. Thus the stage is set for one of the most telling satiric fables ever penned –a razor-edged fairy tale for grown-ups that records the evolution from revolution against tyranny to a totalitarianism just as terrible.\n" +
                "When Animal Farm was first published, Stalinist Russia was seen as its target. Today it is devastatingly clear that wherever and whenever freedom is attacked, under whatever banner, the cutting clarity and savage comedy of George Orwell’s masterpiece have a meaning and message still ferociously fresh.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Don Quixote";
        author = "Miguel de Cervantes Saavedra";
        pages = 1023;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1546112331l/3836._SY475_.jpg";
        description = "Don Quixote has become so entranced by reading chivalric romances that he determines to become a knight-errant himself. In the company of his faithful squire, Sancho Panza, his exploits blossom in all sorts of wonderful ways. While Quixote's fancy often leads him astray—he tilts at windmills, imagining them to be giants—Sancho acquires cunning and a certain sagacity. Sane madman and wise fool, they roam the world together, and together they have haunted readers' imaginations for nearly four hundred years.\n" +
                "\n" +
                "With its experimental form and literary playfulness, Don Quixote has been generally recognized as the first modern novel. The book has been enormously influential on a host of writers, from Fielding and Sterne to Flaubert, Dickens, Melville, and Faulkner, who reread it once a year, \"just as some people read the Bible.\"";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Fight Club";
        author = "Chuck Palahniuk";
        pages = 218;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1558216416l/36236124._SY475_.jpg";
        description = "Chuck Palahniuk showed himself to be his generation’s most visionary satirist in this, his first book. Fight Club’s estranged narrator leaves his lackluster job when he comes under the thrall of Tyler Durden, an enigmatic young man who holds secret after-hours boxing matches in the basement of bars. There, two men fight \"as long as they have to.\" This is a gloriously original work that exposes the darkness at the core of our modern world.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Lolita";
        author = " Vladimir Nabokov,\n" +
                "Craig Raine";
        pages = 331;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1377756377l/7604.jpg";
        description = "Humbert Humbert - scholar, aesthete and romantic - has fallen completely and utterly in love with Lolita Haze, his landlady's gum-snapping, silky skinned twelve-year-old daughter. Reluctantly agreeing to marry Mrs Haze just to be close to Lolita, Humbert suffers greatly in the pursuit of romance; but when Lo herself starts looking for attention elsewhere, he will carry her off on a desperate cross-country misadventure, all in the name of Love. Hilarious, flamboyant, heart-breaking and full of ingenious word play, Lolita is an immaculate, unforgettable masterpiece of obsession, delusion and lust.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Catch-22";
        author = "Joseph Heller";
        pages = 453;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1463157317l/168668.jpg";
        description = "Fifty years after its original publication, Catch-22 remains a cornerstone of American literature and one of the funniest—and most celebrated—books of all time. In recent years it has been named to “best novels” lists by Time, Newsweek, the Modern Library, and the London Observer.\n" +
                "\n" +
                "Set in Italy during World War II, this is the story of the incomparable, malingering bombardier, Yossarian, a hero who is furious because thousands of people he has never met are trying to kill him. But his real problem is not the enemy—it is his own army, which keeps increasing the number of missions the men must fly to complete their service. Yet if Yossarian makes any attempt to excuse himself from the perilous missions he’s assigned, he’ll be in violation of Catch-22, a hilariously sinister bureaucratic rule: a man is considered insane if he willingly continues to fly dangerous combat missions, but if he makes a formal request to be removed from duty, he is proven sane and therefore ineligible to be relieved.\n" +
                "\n" +
                "This fiftieth-anniversary edition commemorates Joseph Heller’s masterpiece with a new introduction by Christopher Buckley; a wealth of critical essays and reviews by Norman Mailer, Alfred Kazin, Anthony Burgess, and others; rare papers and photos from Joseph Heller’s personal archive; and much more. Here, at last, is the definitive edition of a classic of world literature.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "The Lord of the Rings";
        author = "J.R.R. Tolkien";
        pages = 1216;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1566425108l/33.jpg";
        description = "One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them\n" +
                "\n" +
                "In ancient times the Rings of Power were crafted by the Elven-smiths, and Sauron, the Dark Lord, forged the One Ring, filling it with his own power so that he could rule all others. But the One Ring was taken from him, and though he sought it throughout Middle-earth, it remained lost to him. After many ages it fell by chance into the hands of the hobbit Bilbo Baggins.\n" +
                "\n" +
                "From Sauron's fastness in the Dark Tower of Mordor, his power spread far and wide. Sauron gathered all the Great Rings to him, but always he searched for the One Ring that would complete his dominion.\n" +
                "\n" +
                "When Bilbo reached his eleventy-first birthday he disappeared, bequeathing to his young cousin Frodo the Ruling Ring and a perilous quest: to journey across Middle-earth, deep into the shadow of the Dark Lord, and destroy the Ring by casting it into the Cracks of Doom.\n" +
                "\n" +
                "The Lord of the Rings tells of the great quest undertaken by Frodo and the Fellowship of the Ring: Gandalf the Wizard; the hobbits Merry, Pippin, and Sam; Gimli the Dwarf; Legolas the Elf; Boromir of Gondor; and a tall, mysterious stranger called Strider.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Mrs. Dalloway";
        author = " Virginia Woolf,\n" +
                "Maureen Howard";
        pages = 194;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1479336522l/14942._SY475_.jpg";
        description = "Heralded as Virginia Woolf's greatest novel, this is a vivid portrait of a single day in a woman's life. When we meet her, Mrs. Clarissa Dalloway is preoccupied with the last-minute details of party preparation while in her mind she is something much more than a perfect society hostess. As she readies her house, she is flooded with remembrances of faraway times. And, met with the realities of the present, Clarissa reexamines the choices that brought her there, hesitantly looking ahead to the unfamiliar work of growing old. ";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Brave New World";
        author = "Aldous Huxley";
        pages = 268;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1575509280l/5129._SY475_.jpg";
        description = "Brave New World is a dystopian novel by English author Aldous Huxley, written in 1931 and published in 1932. Largely set in a futuristic World State, inhabited by genetically modified citizens and an intelligence-based social hierarchy, the novel anticipates huge scientific advancements in reproductive technology, sleep-learning, psychological manipulation and classical conditioning that are combined to make a dystopian society which is challenged by only a single individual: the story's protagonist.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Things Fall Apart";
        author = "Chinua Achebe";
        pages = 209;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1352082529l/37781.jpg";
        description = "More than two million copies of Things Fall Apart have been sold in the United States since it was first published here in 1959. Worldwide, there are eight million copies in print in fifty different languages. This is Chinua Achebe's masterpiece and it is often compared to the great Greek tragedies, and currently sells more than one hundred thousand copies a year in the United States.\n" +
                "\n" +
                "A simple story of a \"strong man\" whose life is dominated by fear and anger, Things Fall Apart is written with remarkable economy and subtle irony. Uniquely and richly African, at the same time it reveals Achebe's keen awareness of the human qualities common to men of all times and places. ";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Pride and Prejudice";
        author = " Jane Austen,\n" +
                "Anna Quindlen";
        pages = 279;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1320399351l/1885.jpg";
        description = "Since its immediate success in 1813, Pride and Prejudice has remained one of the most popular novels in the English language. Jane Austen called this brilliant work \"her own darling child\" and its vivacious heroine, Elizabeth Bennet, \"as delightful a creature as ever appeared in print.\" The romantic clash between the opinionated Elizabeth and her proud beau, Mr. Darcy, is a splendid performance of civilized sparring. And Jane Austen's radiant wit sparkles as her characters dance a delicate quadrille of flirtation and intrigue, making this book the most superb comedy of manners of Regency England.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "One Hundred Years of Solitude";
        author = "Gabriel García Márquez";
        pages = 417;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1327881361l/320.jpg";
        description = "The brilliant, bestselling, landmark novel that tells the story of the Buendia family, and chronicles the irreconcilable conflict between the desire for solitude and the need for love—in rich, imaginative prose that has come to define an entire genre known as \"magical realism.\"";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Slaughterhouse-Five";
        author = " Kurt Vonnegut Jr.";
        pages = 275;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1606208827l/4981._SY475_.jpg";
        description = "Selected by the Modern Library as one of the 100 best novels of all time, Slaughterhouse-Five, an American classic, is one of the world's great antiwar books. Centering on the infamous firebombing of Dresden, Billy Pilgrim's odyssey through time reflects the mythic journey of our own fractured lives as we search for meaning in what we fear most.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "The Stranger";
        author = " Albert Camus,\n" +
                "Vedat Günyol";
        pages = 123;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1590930002l/49552._SY475_.jpg";
        description = "Through the story of an ordinary man unwittingly drawn into a senseless murder on an Algerian beach, Camus explored what he termed \"the nakedness of man faced with the absurd.\" First published in English in 1946; now in a new translation by Matthew Ward.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Frankenstein: The 1818 Text";
        author = "Mary Wollstonecraft Shelley";
        pages = 260;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1498841231l/35031085.jpg";
        description = "Mary Shelley's seminal novel of the scientist whose creation becomes a monster\n" +
                "\n" +
                "This edition is the original 1818 text, which preserves the hard-hitting and politically charged aspects of Shelley's original writing, as well as her unflinching wit and strong female voice. This edition also includes a new introduction and suggestions for further reading by author and Shelley expert Charlotte Gordon, literary excerpts and reviews selected by Gordon and a chronology and essay by preeminent Shelley scholar Charles E. Robinson.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "Wuthering Heights";
        author = " Emily Brontë,\n" +
                "Richard J. Dunn";
        pages = 464;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1388212715l/6185._SY475_.jpg";
        description = "This best-selling Norton Critical Edition is based on the 1847 first edition of the novel. For the Fourth Edition, the editor has collated the 1847 text with several modern editions and has corrected a number of variants, including accidentals. The text is accompanied by entirely new explanatory annotations.\n" +
                "\n" +
                "New to the fourth Edition are twelve of Emily Bronte's letters regarding the publication of the 1847 edition of Wuthering Heights as well as the evolution of the 1850 edition, prose and poetry selections by the author, four reviews of the novel, and poetry selections by the author, four reviews of the novel, and Edward Chitham's insightful and informative chronology of the creative process behind the beloved work.\n" +
                "\n" +
                "Five major critical interpretations of Wuthering Heights are included, three of them new to the Fourth Edition. A Stuart Daley considers the importance of chronology in the novel. J. Hillis Miller examines Wuthering Heights's problems of genre and critical reputation. Sandra M. Gilbert assesses the role of Victorian Christianity plays in the novel, while Martha Nussbaum traces the novel's romanticism. Finally, Lin Haire-Sargeant scrutinizes the role of Heathcliff in film adaptations of Wuthering Heights.\n" +
                "\n" +
                "A Chronology and updated Selected Bibliography are also included.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "His Dark Materials";
        author = "Philip Pullman";
        pages = 1088;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1442329494l/18116._SY475_.jpg";
        description = "The Golden Compass, The Subtle Knife, and The Amber Spyglass are available together in one volume perfect for any fan or newcomer to this modern fantasy classic series.\n" +
                "\n" +
                "These thrilling adventures tell the story of Lyra and Will—two ordinary children on a perilous journey through shimmering haunted otherworlds. They will meet witches and armored bears, fallen angels and soul-eating specters. And in the end, the fate of both the living—and the dead—will rely on them.\n" +
                "\n" +
                "Phillip Pullman’s spellbinding His Dark Materials trilogy has captivated readers for over twenty years and won acclaim at every turn. It will have you questioning everything you know about your world and wondering what really lies just out of reach.";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        id++;
        name = "The Hitchhiker's Guide to the Galaxy";
        author = "Douglas Adams";
        pages = 193;
        imgUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1559986152l/386162.jpg";
        description = "A beautifully illustrated edition of the New York Times bestselling classic, timed to celebrate the pivotal 42nd anniversary of the original publication--with never-before-seen illustrations by award winner Chris Riddell\n" +
                "\n" +
                "Seconds before Earth is demolished to make way for a galactic freeway, Arthur Dent is plucked off the planet by his friend Ford Prefect, a researcher for the revised edition of The Hitchhiker's Guide to the Galaxy who, for the last fifteen years, has been posing as an out-of-work actor.\n" +
                "\n" +
                "Together, this dynamic pair begin a journey through space aided by a galaxyful of fellow travelers: Zaphod Beeblebrox--the two-headed, three-armed ex-hippie and totally out-to-lunch president of the galaxy; Trillian (formerly Tricia McMillan), Zaphod's girlfriend, whom Arthur tried to pick up at a cocktail party once upon a time zone; Marvin, a paranoid, brilliant, and chronically depressed robot; and Veet Voojagig, a former graduate student obsessed with the disappearance of all the ballpoint pens he's bought over the years.\n" +
                "\n" +
                "Where are these pens? Why are we born? Why do we die? For all the answers, stick your thumb to the stars! ";
        books.add(new Book(id, name, author, pages, imgUrl, description));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();

    }
}
