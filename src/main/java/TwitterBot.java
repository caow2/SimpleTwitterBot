import twitter4j.*;
import twitter4j.api.PlacesGeoResources;

import java.io.*;
import java.nio.charset.Charset;

public class TwitterBot {
    private static Twitter twitter = TwitterFactory.getSingleton();

    public static void main(String[] args) {
        System.out.println("\n");
        tweetLines();
    }

    public static void tweetLines() {
        String line = null;
        try {
            InputStream in = new FileInputStream("./src/main/resources/sample_tweets");
            InputStreamReader reader = new InputStreamReader(in); //reading text
            BufferedReader br = new BufferedReader(reader);
            while((line = br.readLine()) != null) {
                sendTweet(line);
                System.out.println("Tweeting: " + line);
                //send every 10 seconds
                try {
                    System.out.println("Sleeping for 10 seconds");
                    Thread.sleep(10000);
                }
                catch (Exception e) {
                    e.printStackTrace();;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendTweet(String line) {
        Status status;
        try {
            status = twitter.updateStatus(line);
            System.out.println(status);
        }
        catch(TwitterException te) {
            System.out.println("Twitter Exception");
            te.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
