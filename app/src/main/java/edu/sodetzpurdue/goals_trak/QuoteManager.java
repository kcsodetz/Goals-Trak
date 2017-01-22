package edu.sodetzpurdue.goals_trak;

import java.util.Random;

/**
 * Created by Ken Sodetz on 1/21/2017.
 */

public class QuoteManager {
    public String[] quotes =
            {"\"Fall down 7 times, stand up 8.\" \n\t-Japanese Proverb",
            "\"When you're going through hell, keep going.\" \n\t-Winston Churchill",
            "\"It always seems impossible until it's done.\" \n\t-Nelson Mandela",
            "\"It's not about where your starting point is, but your end goal and the journey that will get you there.\" \n\t-Unknown",
            "\"It's not who you think you are that holds you back it's who you think you're not.\"\n\t-Unknown",
            "\"The first step to accepting yourself is to stop comparing yourself to others.\"\n\t-Joe Duncan"};
    public QuoteManager(){

    }

    //TODO finish addQuote method
    public void addQuote(String quote, String author){
        int numQuotes = quotes.length;
        quote = "\""+quote+".\""+"\n\t-"+author;
        quotes[numQuotes] = quote;
    }

    public String getQuote(){
        Random rand = new Random();
        int num = rand.nextInt(quotes.length);
        return quotes[num];
    }

}
