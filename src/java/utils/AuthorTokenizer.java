package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorTokenizer {

    private String inputString;
    private String surname;
    private String name;
    int id;
    private boolean parsed;

    public AuthorTokenizer(String input) {
        this.parsed = false;
        this.id = -1;
        parseString(input);

    }
    
    public AuthorTokenizer(String name, String surname, int id){
        this.parsed = true;
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s, %s (%d)", this.getName(), this.getSurname(), this.getId());
    }

    /**
     * @return the state
     */
    public boolean stringParsed() {
        return this.parsed;
    }


    /**
     * @return the inputString
     */
    public String getInputString() {
        return inputString;
    }

    /**
     * @param inputString the inputString to set
     */
    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    private void parseString(String input) {
        Pattern regex = Pattern.compile("([\\wàèéìòù\\s]*),\\s([\\wàèéìòù\\s]*)\\s\\(([0-9]*)\\)");
        Matcher matcher = regex.matcher(input);
        if (matcher.find()) {
            this.parsed = true;
            this.name = matcher.group(1);
            this.surname = matcher.group(2);
            this.id = Integer.parseInt(matcher.group(3));
        }else{
            this.parsed = false;
        }

    }

}
