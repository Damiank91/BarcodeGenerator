package Model.Array;

import java.util.ArrayList;


/**
 * Created by Damian on 2015-08-22.
 */
public class ArrayData {

    public ArrayList<GetFromExcel> dataArrayList;

    public ArrayData(){

        dataArrayList = new ArrayList<>();
    }

    public void setDataArrayList(GetFromExcel getFromExcel){
        dataArrayList.add(getFromExcel);
    }

    public int getSize(){
        return dataArrayList.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i < dataArrayList.size(); i++){
            builder.append(dataArrayList.get(i));
            builder.append("\n");
        }
        return builder.toString();
    }

    public void remove(){
        dataArrayList.clear();
    }
}
