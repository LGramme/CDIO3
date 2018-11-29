package Domain.GameElements;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.ChanceField;
import Domain.GameElements.Fields.EmptyField;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Fields.PropertyField;

import java.awt.*;

public class Board {
    private Player[] players;
    private Field[] fields;
    private final Color BROWN = new Color(102,51,0);
    private final Color HOT_PINK = new Color(255, 105, 180);
    private final Color FOREST_GREEN = new Color(20, 126, 18);


    public Board() {
        setFields();
    }

    public void movePlayer(Player p, int dist){
        int currentPos = p.getPos();
        if((currentPos + dist) >= fields.length){
            p.getAccount().changeScore(2);
        }
        currentPos = (currentPos + dist)%fields.length;
        p.setPos(currentPos);
    }

    public Player[] getPlayers() {
        return players;
    }

    public Field[] getFields(){
        return fields;
    }

    public void initPlayers(int numberOfPlayers){
        players = new Player[numberOfPlayers];
        int startingCapital;
        if(numberOfPlayers == 2){
            startingCapital = 20;
        }else if(numberOfPlayers == 3){
            startingCapital = 18;
        }else if(numberOfPlayers == 4){
            startingCapital = 16;
        }else{
            startingCapital = 16;
        }
        for(int i = 0; i < numberOfPlayers; i++){
            players[i] = new Player("Player" + (i+1));
            players[i].getAccount().changeScore(startingCapital);
        }
    }

    /**
     * Method generates a bunch of fields in the shape of the provided monopoly game board
     */
    private void setFields(){
        fields = new Field[24];
        fields[0] = (new EmptyField("START", "Modtage $2, når du passerer", Color.lightGray));
        fields[1] = (new PropertyField("BURGERBAREN", "$1", BROWN, 1));
        fields[2] = (new PropertyField("PIZZARIAET", "$1", BROWN, 1));
        fields[3] = (new ChanceField("?", "",Color.lightGray));
        fields[4] = (new PropertyField("SLIKBUTIKKEN", "$1", Color.cyan, 1));
        fields[5] = (new PropertyField("ISKIOSKEN", "$1", Color.cyan, 1));
        fields[6] = (new EmptyField("PÅ BESØG", "", Color.lightGray));
        fields[7] = (new PropertyField("MUSEET", "$2", Color.magenta, 2));
        fields[8] = (new PropertyField("BIBLIOTEKET", "$2", Color.magenta, 2));
        fields[9] = (new ChanceField("?", "", Color.lightGray));
        fields[10] = (new PropertyField("SKATERPARKEN", "$2", Color.pink, 2));
        fields[11] = (new PropertyField("SWIMMINGPOOLEN", "$2", Color.pink, 2));
        fields[12] = (new EmptyField("GRATIS PARKERING", "", Color.lightGray));
        fields[13] = (new PropertyField("SPILLEHALLEN", "$3", Color.RED, 3));
        fields[14] = (new PropertyField("BIOGRAFEN", "$3", Color.RED, 3));
        fields[15] = (new ChanceField("?", "", Color.lightGray));
        fields[16] = (new PropertyField("LEGETØJSBUTIKKEN", "$3", Color.YELLOW, 3));
        fields[17] = (new PropertyField("DYREHANDLEN", "$3", Color.YELLOW, 3));
        fields[18] = (new EmptyField("GÅ I FÆNGSEL", "", Color.lightGray));
        fields[19] = (new PropertyField("BOWLINGHALLEN", "$4", FOREST_GREEN, 4));
        fields[20] = (new PropertyField("ZOO", "$4", FOREST_GREEN, 4));
        fields[21] = (new ChanceField("?", "", Color.lightGray));
        fields[22] = (new PropertyField("VANDLANDET", "$5", Color.BLUE, 5));
        fields[23] = (new PropertyField("STRANDPROMENADEN", "$5", Color.BLUE, 5));
    }

    public void UpdateRent(){
        boolean sameOwner;
        PropertyField[] fieldArr;
        Color[] colourArr = new Color[] {BROWN, HOT_PINK, FOREST_GREEN, Color.cyan,
                Color.magenta, Color.YELLOW, Color.BLUE, Color.RED}; //colours of all the propertyFields

        for (Color c : colourArr){
            fieldArr = getFieldsOfColor(c);
            sameOwner = true;

            for (int i = 1; i < fieldArr.length; i++){
                if (!fieldArr[i].getOwner().equals(fieldArr[i-1].getOwner())){
                    sameOwner = false;
                    break;
                }
            }

            if (sameOwner){
                for (PropertyField f : fieldArr)
                    f.setRent(f.getPrice() * 2);
            }
        }
    }

    public PropertyField[] getFieldsOfColor(Color colour) {
        int i = 0;
        PropertyField[] colouredFields = new PropertyField[fields.length];
        for (Field f : fields) {
            if (f.getClass().equals(PropertyField.class)){
                if (((PropertyField) f).getBgColour() == colour){
                    colouredFields[i++] = (PropertyField) f;
                }
            }
        }
        return colouredFields;
    }

    public PropertyField[] getFieldsOfOwner(Player owner){
        int i = 0;
        PropertyField[] colouredFields = new PropertyField[fields.length];
        for (Field f : fields) {
            if (f.getClass().equals(PropertyField.class)){
                if (((PropertyField) f).getOwner() == owner){
                    colouredFields[i++] = (PropertyField) f;
                }
            }
        }
        return colouredFields;
    }
}
