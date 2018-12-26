package gnyma.truewords;

/**
 * Created by ASUS on 12/26/2018.
 */

public class Quotes {
    private String mQuote;
    private String mAuthor;

    Quotes(String quote, String author) {
        mQuote = quote;
        mAuthor = author;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getQuote() {
        return mQuote;
    }

}
