
/**
 * Write a description of Part4 here.
 * Write a program that reads the lines from the file at this URL location, 
 * http://www.dukelearntoprogram.com/course2/data/manylinks.html, and prints each URL on the 
 * page that is a link to youtube.com. Assume that a link to youtube.com has no spaces in it 
 * and would be in the format (where [stuff] represents characters that are not verbatim): 
 * “http:[stuff]youtube.com[stuff]”

Here are suggestions to get started.

1. Create a new Java Class named Part4 in the StringsFirstAssignments project and put your code 
in that class.

2. Use URLResource to read the file at 
http://www.dukelearntoprogram.com/course2/data/manylinks.html word by word.

3. For each word, check to see if “youtube.com” is in it. If it is, find the double quote to 
the left and right of the occurrence of “youtube.com” to identify the beginning and end of the 
URL. Note, the double quotation mark is a special character in Java. To look for a double quote,
look for (\”), since the backslash (\) character indicates we want the literal quotation marks
(“) and not the Java character. The string you search for would be written “\”” for one double
quotation mark.

4. In addition to the String method indexOf(x, num), you might want to consider using the 
String method lastIndexOf(s, num) that can be used with two parameters s and num. The 
parameter s is the string or character to look for, and num is the last position in the 
string to look for it. This method returns the last match from the start of the string up 
to the num position, so it is a good option for finding the opening quotation mark of a string
searching backward from “youtube.com.” More information on String methods can be found in the 
Java documentation for Strings: http://docs.oracle.com/javase/7/docs/api/java/lang/String.html.

Caution: The word Youtube could appear in different cases such as YouTube, youtube, or 
YOUTUBE. You can find the URLs more easily by converting the string to lowercase. However, 
you will need the original string (with uppercase and lowercase letters) to view the YouTube 
URL to answer a quiz question because YouTube links are case sensitive. The link 
https://www.youtube.com/watch?v=ji5_MqicxSo is different than the link 
https://www.youtube.com/watch?v=ji5_mqicxso, where all the letters are lowercase.

 * @author (Theary Im) 
 * @Date (7/23/19)
 */

import edu.duke.*;
import java.io.File;
import java.util.*;

public class Part4 {
    public ArrayList<String> getYouTubeURLs(){
        ArrayList<String> urls = new ArrayList<String>();
        URLResource resource = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String contents = resource.asString().toLowerCase();
        int subStringIndex = contents.indexOf("youtube.com");
        int leftIndex = -1;
        int rightIndex = -1;
        while (subStringIndex >= 0){
            for (int i = subStringIndex; i>0; i--){
                if(contents.charAt(i) == '"'){
                    leftIndex = i;
                    break;
                }
            }
            
            for (int j = subStringIndex; j<contents.length(); j++) {
                if (contents.charAt(j) == '"') {
                    rightIndex = j;
                    break;
                }
            }
            
            if (leftIndex >= 0 && rightIndex >= 0){
                urls.add(contents.substring(leftIndex + 1, rightIndex));
            }
            subStringIndex = contents.indexOf("youtube.com", subStringIndex + 1);
        }
        return urls;
    }
    
    public void testGetYouTubeURLs(){
        ArrayList<String> urls = getYouTubeURLs();
        for (String url : urls) {
            System.out.println(url);
        }
    }
}
