/**
 * All GuiHandler related classes are kept here.
 */
package UI.GUI;

import Domain.GameElements.Fields.ChanceField;
import Domain.GameElements.Fields.EmptyField;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Fields.PropertyField;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GuiHandler {
    GUI gui;
    GUI_Field[] gui_field = new GUI_Field[24];
    public GuiHandler(Field[] fields){//Field[] fields
        for(int i = 0; i < gui_field.length; i++){
            if(fields[i].getClass().equals(EmptyField.class) && i == 0){
                gui_field[i] = (new GUI_Start(fields[i].getName(), fields[i].getSubtext(), "", fields[i].getBgColour(), null));
            }else if(fields[i].getClass().equals(PropertyField.class)){
                PropertyField propertyField = (PropertyField) fields[i];
                gui_field[i] = (new GUI_Street(fields[i].getName(), fields[i].getSubtext(), "", Integer.toString(propertyField.getPrice()), fields[i].getBgColour(), null));
            }else if(fields[i].getClass().equals(EmptyField.class)){
                gui_field[i] = (new GUI_Street(fields[i].getName(), fields[i].getSubtext(), "", "0", fields[i].getBgColour(), null));//This one be causing trouble
            }else if(fields[i].getClass().equals(ChanceField.class)){
                gui_field[i] = (new GUI_Chance(fields[i].getName(), fields[i].getSubtext(), "", fields[i].getBgColour(), null));
            }
        }

        gui = new GUI(gui_field, Color.lightGray);
    }

    /**
     * Methods returns an integer given by a player
     * @param min The minimum number of players
     * @param max The maximum number of players
     */
    public int getNumberOfPlayers(int min, int max){
        return gui.getUserInteger("How many players are present?", min, max);
    }
}




